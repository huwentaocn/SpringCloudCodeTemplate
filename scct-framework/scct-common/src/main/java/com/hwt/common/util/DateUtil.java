package com.hwt.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {

    private static Logger logger = LoggerFactory.getLogger(DateUtil.class);

    private static SimpleDateFormat sdfShort = new SimpleDateFormat("yyyyMMdd");

    private static SimpleDateFormat simpleDateFormat = null;

    private static SimpleDateFormat getYearMonthDay =new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 将时间戳转换为时间格式
     *
     * @param time
     * @return
     */
    public static String getTimeMillisToString(long time) {
        Date d = new Date(time);
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(d);
    }

    public static String getYYMMDDHH(Date date) {
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return simpleDateFormat.format(date);
    }

    public  static  String getYearMonthDay(Date date){
        return getYearMonthDay.format(date);
    }

    public static String getDateToString(long time) {
        Date d = new Date(time * 1000L);
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(d);
    }

    public static String getHHmm(Date date) {
        simpleDateFormat = new SimpleDateFormat("HH:mm");
        return simpleDateFormat.format(date);
    }

    public static String getYYMMDD(String dateStr) {
        Date date = null;
        try {
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            date = simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return simpleDateFormat.format(date);
    }

    public static String getStringDate(Date date, String format) {
        simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    /**
     * 将String转化成date
     *
     * @throws ParseException
     */
    public static Date pStringToDate(String str, String sfgs)
            throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat(sfgs);
        return sf.parse(str);
    }

    public static Long dateToLong(Date date) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String format = formatter.format(date);
            return Long.parseLong(format);
        } catch (Exception e) {
            return null;
        }
    }

    public static int dateToInt(Date date) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHH");
            String format = formatter.format(date);
            return Integer.valueOf(format);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 日期格式字符串转unix时间戳
     *
     * @param string
     * @return
     * @throws Exception
     */
    public static long stringShortToMillisecond(String string) throws Exception {
        return stringShortToDate(string).getTime() / 1000;
    }

    public static Date stringShortToDate(String string) throws Exception {
        return sdfShort.parse(string);
    }


    public static List<String> getDays(String startTime, String endTime) {
        // 返回的日期集合
        List<String> days = new ArrayList<String>();
        java.text.DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date start = dateFormat.parse(startTime);
            Date end = dateFormat.parse(endTime);
            Calendar tempStart = Calendar.getInstance();
            tempStart.setTime(start);
            Calendar tempEnd = Calendar.getInstance();
            tempEnd.setTime(end);
            tempEnd.add(5, +1);// 日期加1(包含结束)
            while (tempStart.before(tempEnd)) {
                days.add(dateFormat.format(tempStart.getTime()));
                tempStart.add(5, 1);
            }
        } catch (Exception e) {
            logger.error("getDays" + e);
        }
        return days;
    }

    public static Long timeStamptoDate(String seconds) {
        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return 0l;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return Long.valueOf(sdf.format(new Date(Long.valueOf(seconds))));
    }

    public static String changeF2Y(int price) {
        return BigDecimal.valueOf(Long.valueOf(price)).divide(new BigDecimal(100)).toString();
    }

    public static int getDaysByYearMonth(String yyyyMM) {
        int year = Integer.valueOf(yyyyMM.substring(0, 4));
        int month = Integer.valueOf(yyyyMM.substring(4));
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    public static String getzheDateTime(String date, int day) {
        String pattern = "yyyyMMdd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Calendar instance = Calendar.getInstance();
        try {
            instance.setTime(simpleDateFormat.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        instance.add(5, day);
        return formatCalendar(instance, pattern);
    }

    public static String formatCalendar(Calendar calendar, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        // sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
        return sdf.format(calendar.getTime());
    }

    /**
     * 把时间字符串转换为指定格式
     *
     * @param dateString    时间串
     * @param datePattern   时间串格式
     * @param switchPattern 需要转换的格式
     * @return 返回转换之后的时间字符串 //当数据转换错误时会返回空
     * @throws
     */
    public static String dateSwitch(String dateString, String datePattern, String switchPattern) {
        String rtdate = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
            Date date = sdf.parse(dateString);
            SimpleDateFormat wcsdf = new SimpleDateFormat(switchPattern);
            rtdate = wcsdf.format(date);
        } catch (ParseException e) {
            logger.error("时间转换错误");
        }
        return rtdate;
    }


    public final static SimpleDateFormat SDF_YEAR = new SimpleDateFormat("yyyy");

    public final static SimpleDateFormat SDF_MOHTH = new SimpleDateFormat("MM");

    public final static SimpleDateFormat SDF_YEAR_MONTH = new SimpleDateFormat("yyyyMM");

    public final static SimpleDateFormat SDY_DAY = new SimpleDateFormat("dd");

    public final static SimpleDateFormat SDY_YEAR_MONTH_DAY_HOUR = new SimpleDateFormat("yyyyMMddHH");


    /**
     * 获取yyyy格式
     * @return
     */
    public static String getSimpleYear(Date date) {
        return SDF_YEAR.format(date);
    }

    /**
     * 获取MM格式
     * @return
     */
    public static String getSimpleMonth(Date date) {
        return SDF_MOHTH.format(date);
    }

    /**
     * 获取DD格式
     *
     * @return
     */
    public static String getSimpleDay() {
        return SDY_DAY.format(new Date());
    }

    /**
     * 获取YYYYMM格式
     *
     * @return
     */
    public static String getDayMonth() {
        return SDF_YEAR_MONTH.format(new Date());
    }


    public static String getSimpleHour() {
        return SDY_YEAR_MONTH_DAY_HOUR.format(new Date());
    }

    /**
     * string time 转为Date
     */
    public static Date stringToDate(String time) throws ParseException {
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.parse(time);
    }


    /**
     * 给定开始时间和结束时间，判断当前时间是第几周，七天为一周，按照自然周进行计算
     *
     * @param startTime
     * @return
     */
    public static String getWeek(long startTime, long currentTime, long endTime) {
        if (currentTime < startTime || endTime < startTime) {
            return "0";
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(getFirstDayOfWeek(startTime).getTime());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        calendar.clear();
        calendar.set(year, month, dayOfMonth);
        // 当前时间和开始时间0点的毫秒数差值
        long value = currentTime - calendar.getTimeInMillis();
        if (getLastDayOfWeek(endTime).getTime() < currentTime) {
            value = endTime - calendar.getTimeInMillis();
        }
        // 一周的毫秒数
        long oneWeekMillis = 1000 * 60 * 60 * 24 * 7;
        int weekin = (int) (value / oneWeekMillis + 1);
        String formatWeek = String.format("%02d", weekin);
        return formatWeek;
    }

    /**
     * 获取当前时间的周一时间
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfWeek(long date) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(date));
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            c.add(Calendar.DAY_OF_MONTH, -1);
        }
        c.add(Calendar.DATE, c.getFirstDayOfWeek() - c.get(Calendar.DAY_OF_WEEK) + 1);
        return c.getTime();
    }

    /**
     * 获取当前时间的周日时间
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfWeek(long date) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(date));
        // 如果是周日直接返回
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            return new Date(date);
        }
        c.add(Calendar.DATE, 7 - c.get(Calendar.DAY_OF_WEEK) + 1);
        return c.getTime();
    }

    /**
     * 获取当前日期是星期几<br>
     *
     * @param date
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date date) {
        String[] weekDays = {"7", "1", "2", "3", "4", "5", "6"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 计算给定周数内的所有日期。以下是一个示例方法，它接受开始日期、结束日期和周数作为参数，并返回在指定周数内的所有日期
     */

    public static List<String> getDatesByWeeks(String startDateStr, String endDateStr, int weekNumber) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(startDateStr, formatter);
        LocalDate endDate = LocalDate.parse(endDateStr, formatter);
        List<String> dates = new ArrayList<>();
        LocalDate firstDayOfWeek = startDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).plusWeeks(weekNumber - 1);
        LocalDate lastDayOfWeek = startDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)).plusWeeks(weekNumber - 1);
        while (!firstDayOfWeek.isAfter(endDate) && !firstDayOfWeek.isAfter(lastDayOfWeek)) {
            dates.add(firstDayOfWeek.format(formatter));
            firstDayOfWeek = firstDayOfWeek.plusDays(1);
        }
        return dates;
    }

    public static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    public static String strformatDate(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }
}
