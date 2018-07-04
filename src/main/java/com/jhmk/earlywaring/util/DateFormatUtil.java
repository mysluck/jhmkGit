package com.jhmk.earlywaring.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;


/**
 * 日期转换工具类
 *
 * @author niqinqin
 */
public class DateFormatUtil {


    public static final String DATE_PATTERN_S = "yyyy-MM-dd";
    public static final String DATE_PATTERN_MM = "yyyy-MM";
    public static final String DATETIME_PATTERN_S = "yyyyMMddHHmmss";
    public static final String DATETIME_PATTERN_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_PATTERN = "yyyyMMdd";
    public static final String DATE_PATTERN_H = "HHmmss";
    public static final String DATETIME_PATTERN_S_N = "yyyyMMddHHmmssSSS";

    public static final String DATE_PATTERN_M_S = "MMddHHmmss";
    public static final String DATE_PATTERN_M = "MMdd";


    /**
     * date获取Calendar类型
     *
     * @param date
     * @return
     */
    public static Calendar getCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * 当前日期为一个月中的第几天
     *
     * @param todayCalendar
     * @return
     */
    public static int getDayOfMonth(Calendar todayCalendar) {
        return todayCalendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 当前日期为一星期中的第几天
     * 其中1表示星期一，7表示星期日
     *
     * @param todayCalendar
     * @return
     */
    public static int getDayOfWeek(Calendar todayCalendar) {
        int todayOfWeek = todayCalendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (todayOfWeek == 0) {
            todayOfWeek = 7;
        }
        return todayOfWeek;
    }

    /**
     * 获取一个月的天数
     *
     * @param date
     * @return
     */
    public static int getTotalDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        return days;
    }

    /**
     * 计算两个日期的间隔天数d1-d2
     *
     * @param d1
     * @param d2
     * @return
     */
    public static int dateDiff(Date d1, Date d2) {
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime dd1 = LocalDateTime.ofInstant(d1.toInstant(), zone);
        LocalDateTime dd2 = LocalDateTime.ofInstant(d2.toInstant(), zone);
        long i = dd1.toLocalDate().toEpochDay() - dd2.toLocalDate().toEpochDay();
        int j = new Long(i).intValue();
        return j;
    }

    public static Date getDateFromTimestamp(Timestamp tDate) {
        String dateStr = format(new Date(tDate.getTime()), DATE_PATTERN_S);
        Date newDate = parseDate(dateStr, DATE_PATTERN_S);
        return newDate;
    }

    public static String format(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        DateTimeFormatter format = DateTimeFormatter.ofPattern(pattern);
        String dateStr = localDateTime.format(format);
        return dateStr;
    }

    public static String format(Timestamp timestamp, String pattern) {
        if (timestamp == null) {
            return "";
        }
        Instant instant = timestamp.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        DateTimeFormatter format = DateTimeFormatter.ofPattern(pattern);
        String timeStr = localDateTime.format(format);
        return timeStr;
    }

    public static Date parseDate(String dateStr, String pattern) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern(pattern);
        LocalDate localDate = LocalDate.parse(dateStr, format);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        Date newDate = Date.from(instant);


        return newDate;
    }

    public static Date parseDateBySdf(String dateStr, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date parse = null;
        try {
            parse = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;

    }

    public static Timestamp parseDateTime(String dateStr, String pattern) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime localDatetime = LocalDateTime.parse(dateStr, format);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDatetime.atZone(zone).toInstant();
        return new Timestamp(instant.toEpochMilli());
    }

    public static Date add(Date date, int field, int days) {
        Calendar calendar = getCalendar(date);
        calendar.add(field, days);
        return calendar.getTime();
    }

    public static Timestamp resetDateTime(Date date, Timestamp time) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        cal.setTime(time);
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        return new Timestamp(cal.getTime().getTime());

    }

    /**
     * 获取指定日期的前n天日期
     *
     * @param date
     * @param n
     * @return
     */
    public static Date getPreNDate(Date date, int n) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalDate localDate = localDateTime.toLocalDate();
        localDate = localDate.plusDays(0 - n);
        instant = localDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     * 获取指定日期的前n月日期
     *
     * @param date
     * @param n
     * @return
     */
    public static Date getPreNMonth(Date date, int n) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalDate localDate = localDateTime.toLocalDate();
        localDate = localDate.plusMonths(0 - n);
        instant = localDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     * 获取指定日期的前n年的日期
     *
     * @param n
     * @return
     */
    public static Date getPreNYearDate(Date date, int n) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalDate localDate = localDateTime.toLocalDate();
        localDate = localDate.plusYears(0 - n);
        instant = localDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     * 获取指定日期的后n个月的日期
     *
     * @param date 格式：yyyyMMdd
     * @param n
     * @return
     */
    public static Date getNextNMonth(Date date, int n) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalDate localDate = localDateTime.toLocalDate();
        localDate = localDate.plusMonths(n);
        instant = localDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);

    }

    /**
     * 获取n天后的日期
     *
     * @param n
     * @return
     */
    public static Date getNextNDate(Date date, int n) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalDate localDate = localDateTime.toLocalDate();
        localDate = localDate.plusDays(n);
        instant = localDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     * 获取指定日期的后n年的日期
     *
     * @param n
     * @return
     */
    public static Date getNextNYear(Date date, int n) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalDate localDate = localDateTime.toLocalDate();
        localDate = localDate.plusYears(n);
        instant = localDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }


    /**
     * 获得本周一与当前日期相差的天数
     *
     * @return
     */
    public static int getMondayPlus() {
        Calendar cd = Calendar.getInstance();
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {
            return -6;
        } else {
            return 2 - dayOfWeek;
        }
    }

    /**
     * 获取本周周一时间
     */
    public static Date getMinWeekDate() {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus);
        Date monday = currentDate.getTime();
        return monday;
    }

    /**
     * 获得当前月--开始日期
     *
     * @param date
     * @return
     */
    public static Date getMinMonthDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    /**
     * 获取某月份第一天
     *
     * @param year
     * @param month
     * @return
     */
    public static String getFirstDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最小天数
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String firstDayOfMonth = sdf.format(cal.getTime());
        return firstDayOfMonth;
    }

    /**
     * 获得该月最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static String getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastDayOfMonth = sdf.format(cal.getTime());
        return lastDayOfMonth;
    }

    public static int getYearNow() {
        Calendar calendar = Calendar.getInstance();
        int nowYear = calendar.get(Calendar.YEAR);
        return nowYear;
    }

    public static Date getYearFirst(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    /**
     * 获取某年最后一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static Date getYearLast(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year+1);
        Date currYearLast = calendar.getTime();

        return currYearLast;
    }



    public static Date getLastYear() {
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(Calendar.YEAR - 1);
//        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();

        return currYearLast;
    }

    public static Date getYearLastMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        int i = calendar.get(Calendar.YEAR);
//        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();

        return currYearLast;
    }

    /**
     * 获取上年第一个月
     *
     * @return 2017-01
     */
    public static String getFirstMonthForLastYear() {
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(Calendar.YEAR);
        calendar.set(Calendar.YEAR, i - 1);
        calendar.set(Calendar.MONTH, 0);
        Date currYearLast = calendar.getTime();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        String format = simpleDateFormat.format(currYearLast);
        return format;
    }

    /**
     * 获取上年最后一个月
     *
     * @return 2017-12
     */
    public static String getLastMonthForLastYear() {
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(Calendar.YEAR);
        calendar.set(Calendar.YEAR, i - 1);
        calendar.set(Calendar.MONTH, 11);
        Date currYearLast = calendar.getTime();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        String format = simpleDateFormat.format(currYearLast);
        return format;
    }

    public static void main(String[] args) {
//        String dateStr = "2016-02-29";
//        Date date1 = DateFormatUtil.parseDate(dateStr, DATE_PATTERN_S);
//        Date date = new Date();
//        Date aa = DateFormatUtil.getNextNYear(date1, 1);
//        System.out.println("------: " + aa);

//        Calendar calendar = Calendar.getInstance();
//        int i = calendar.get(Calendar.YEAR);
//        calendar.set(Calendar.YEAR, i - 1);
//        calendar.set(Calendar.MONTH, 11);
//        Date currYearLast = calendar.getTime();
//
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
//        String format = simpleDateFormat.format(currYearLast);
//        System.out.println(format);

//        List<String> monthBetween = getMonthBetween("2017-01", "2017-12");
//        System.out.println(monthBetween.toString());
//        String startTime = "2017-01";
//        String[] split = startTime.split("-");
//        String firstDayOfMonth = DateFormatUtil.getFirstDayOfMonth(Integer.valueOf(split[0]), Integer.valueOf(split[1]));
//        System.out.println(firstDayOfMonth);

        Date yearFirst = getYearFirst(2018);
        Date yearLast = getYearLast(2018);
    }

    public static List<String> getMonthBetween(String minDate, String maxDate) {
        ArrayList<String> result = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月

        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        try {
            min.setTime(sdf.parse(minDate));
            min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);
            max.setTime(sdf.parse(maxDate));
            max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        Calendar curr = min;
        while (curr.before(max)) {
            result.add(sdf.format(curr.getTime()));
            curr.add(Calendar.MONTH, 1);
        }

        return result;
    }

}
