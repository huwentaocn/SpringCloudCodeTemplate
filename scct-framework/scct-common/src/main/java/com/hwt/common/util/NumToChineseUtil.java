package com.hwt.common.util;

/**
 * @author wds
 * @version 1.0
 * @date 2023/6/25 18:02
 */
public class NumToChineseUtil {

    /**
     * 将数字转换成中文
     * @param digit
     * @return
     */
    public static String tochinese(String digit) {
        Integer number= Integer.valueOf(digit);
        if (number == 0) {
            return "零";
        }
        String[] nums = {"", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
        String[] units = {"", "十", "百", "千", "万", "亿"};
        StringBuilder sb = new StringBuilder();
        int unitIndex = 0;
        boolean isTen = false; // 标记前一个数字是否为10
        while (number > 0) {

            int num = number % 10;
            if (num > 0 || unitIndex == 4 || unitIndex == 8) { // 必须对万和亿进行处理
                sb.insert(0, units[unitIndex]);
            } else if (isTen) { // 如果前一个数字是10，则当前数字为0时也要插入一个“零”
                sb.insert(0, "零");
            }
            if (!(unitIndex == 0 && num == 1 && sb.length() > 0) && !(num == 10 && sb.length() > 0)) {
                sb.insert(0, nums[num]);
            } else if (num == 10) { // 如果当前数字是10，且前面已经有数字了，则不需要加上“一”
                sb.insert(0, "十");
                isTen = true;
            } else { // 其他数字需要将标记设为false
                isTen = false;
            }
            number /= 10;
            unitIndex++;
            if (sb.length() > 1 && sb.charAt(0) == '一' && sb.charAt(1) == '十') {
                sb.deleteCharAt(0);
            }
        }
        return sb.toString();
    }

}
