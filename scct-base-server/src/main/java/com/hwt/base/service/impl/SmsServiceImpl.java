package com.hwt.base.service.impl;

import com.aliyun.auth.credentials.Credential;
import com.aliyun.auth.credentials.provider.StaticCredentialProvider;
import com.aliyun.sdk.service.dysmsapi20170525.AsyncClient;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsResponse;
import com.google.gson.Gson;
import com.hwt.base.pojo.req.SendSmsReq;
import com.hwt.base.service.SmsService;
import com.hwt.common.constant.RedisConstant;
import com.hwt.common.util.RandomUtil;
import darabonba.core.client.ClientOverrideConfiguration;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @Description 短信模块接口实现
 * @Date 2023/8/26 13:58 星期六
 * @Author Hu Wentao
 */

@Slf4j
@Service
public class SmsServiceImpl implements SmsService {

    /**
     * 地域id
     */
    @Value("${aliyun.sms.region-id}")
    private String REGION_ID;

    /**
     * key
     */
    @Value("${aliyun.sms.key-id}")
    private String KEY_ID;

    /**
     * secret
     */
    @Value("${aliyun.sms.key-secret}")
    private String KEY_SECRET;

    /**
     * 签名
     */
    @Value("${aliyun.sms.sign-name}")
    private String SIGN_NAME;

    /**
     * 1分钟有效短信模板
     */
    @Value("${aliyun.sms.template-code-1}")
    private String TEMPLATE_CODE_1;

    /**
     * 2分钟有效短信模板
     */
    @Value("${aliyun.sms.template-code-2}")
    private String TEMPLATE_CODE_2;

    /**
     * 5分钟有效短信模板
     */
    @Value("${aliyun.sms.template-code-5}")
    private String TEMPLATE_CODE_5;

    @Autowired
    private RedisTemplate redisTemplate;

    @SneakyThrows
    @Override
    public void sendCommonMessage(SendSmsReq req) {
        String phoneNumber = req.getPhoneNumber();

        //判断redis中是否有已有key
        if (redisTemplate.hasKey(RedisConstant.getSmsCaptchaKey(phoneNumber))) {
            return;
        }

        String code = RandomUtil.getSixBitRandom();
        Map<String, Object> templateParamMap = new HashMap<>();
        templateParamMap.put("code", code);

        sendMessageAsync(phoneNumber, TEMPLATE_CODE_5, templateParamMap);

        redisTemplate.opsForValue().set(RedisConstant.getSmsCaptchaKey(phoneNumber), code, 5, TimeUnit.MINUTES);
    }

    /**
     * 发送短信验证码（异步）
     *
     * @param phoneNumber
     * @param templateCode
     * @param paramMap
     * @return
     * @throws Exception
     */
    public Object sendMessageAsync(String phoneNumber, String templateCode, Map<String, Object> paramMap) throws Exception {
        // Configure Credentials authentication information, including ak, secret, token
        StaticCredentialProvider provider = StaticCredentialProvider.create(Credential.builder()
                // Please ensure that the environment variables ALIBABA_CLOUD_ACCESS_KEY_ID and ALIBABA_CLOUD_ACCESS_KEY_SECRET are set.
                .accessKeyId(KEY_ID)
                .accessKeySecret(KEY_SECRET)
                //.securityToken(System.getenv("ALIBABA_CLOUD_SECURITY_TOKEN")) // use STS token
                .build());

        // Configure the Client
        AsyncClient client = AsyncClient.builder()
                .region(REGION_ID) // Region ID
                //.httpClient(httpClient) // Use the configured HttpClient, otherwise use the default HttpClient (Apache HttpClient)
                .credentialsProvider(provider)
                //.serviceConfiguration(Configuration.create()) // Service-level configuration
                // Client-level configuration rewrite, can set Endpoint, Http request parameters, etc.
                .overrideConfiguration(
                        ClientOverrideConfiguration.create()
                                // Endpoint 请参考 https://api.aliyun.com/product/Dysmsapi
                                .setEndpointOverride("dysmsapi.aliyuncs.com")
                        //.setConnectTimeout(Duration.ofSeconds(30))
                )
                .build();

        // Parameter settings for API request
        SendSmsRequest sendSmsRequest = SendSmsRequest.builder()
                .phoneNumbers(phoneNumber)
                .signName(SIGN_NAME)
                .templateCode(templateCode)
                .templateParam(JSONObject.toJSONString(paramMap))
                // Request-level configuration rewrite, can set Http request parameters, etc.
                // .requestConfiguration(RequestConfiguration.create().setHttpHeaders(new HttpHeaders()))
                .build();

        // Asynchronously get the return value of the API request
        CompletableFuture<SendSmsResponse> response = client.sendSms(sendSmsRequest);
        // Synchronously get the return value of the API request
        SendSmsResponse resp = response.get();
        System.out.println(new Gson().toJson(resp));
        // Asynchronous processing of return values
        /*response.thenAccept(resp -> {
            System.out.println(new Gson().toJson(resp));
        }).exceptionally(throwable -> { // Handling exceptions
            System.out.println(throwable.getMessage());
            return null;
        });*/

        // Finally, close the client
        client.close();
        return resp;
    }

}
