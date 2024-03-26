package com.hwt.common.util.ffmpeg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CmdExecuter 命令执行器 封装对操作系统命令行发送指令相关操作
 */
public class CmdExecuter {

	private static Logger logger = LoggerFactory.getLogger(CmdExecuter.class);

	private static final Map<String, Map<String,Process>> roomObj = new HashMap<String,Map<String,Process>>();
	private static final Map<String,String[]> streamMap = new HashMap<String, String[]>();
	private static final Map<String,Map<String,Process>> recordObj = new HashMap<String,Map<String,Process>>();
	private static final Map<String,String[]> streamRecordMap = new HashMap<String, String[]>();
	private static final Map<String,Map<String,Process>> tranObj = new HashMap<String,Map<String,Process>>();
	private static final Map<String,Map<String,Process>> liveObj = new HashMap<String,Map<String,Process>>();
	private static final Map<String,String[]> streamLiveMap = new HashMap<String, String[]>();

	/**
	 * 执行指令
	 *
	 * @param cmd
	 *            执行指令
	 * @param getter
	 *            指令返回处理接口，若为null则不处理输出
	 */
	static public void exec(List<String> cmd, IStringGetter getter) {
		try {
			ProcessBuilder builder = new ProcessBuilder();
			builder.command(cmd);
			builder.redirectErrorStream(true);
			Process proc = builder.start();
			BufferedReader stdout = new BufferedReader(new InputStreamReader(
					proc.getInputStream()));
			String line;
			while ((line = stdout.readLine()) != null) {
				if (getter != null)
					getter.dealString(line);
			}
			proc.waitFor();
			stdout.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
