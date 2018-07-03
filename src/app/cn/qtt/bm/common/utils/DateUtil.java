package app.cn.qtt.bm.common.utils;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>Title: 全天通彩漫系统_日期工具类</p>
 *
 * <p>Description: 全天通彩漫系统2012年改造</p>
 *
 * <p>Copyright: Copyright (c) 2012</p>
 *
 * <p>Company: 北京全天通（www.qtt.cn）</p>
 *
 * @author zhengyi
 * @version 1.0
 */
public class DateUtil implements Serializable {
  private static Log log = LogFactory.getLog(DateUtil.class);

  /**
   * 把字符串型日期中的‘/’转换成‘-’
   * @param date String
   * @return String
   */
  public String formatDateString(String date) {
    if(date==null || date.trim().equals("")) return date;
    return date.replaceAll("/", "-");
  }

  /**
   * 字符型的日期转换成Date型的日期
   * @param myDateStr String
   * @return Date
   */
  public java.util.Date stringToDate(String myDateStr) {
    myDateStr = formatDateString(myDateStr);
    if (myDateStr.length() < 10) {
      return null;
    }
    String sFormat = "yyyy-MM-dd";
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(sFormat);
    java.util.Date d = null;
    try {
      d = sdf.parse(myDateStr);
    } catch (java.text.ParseException pe) {
      log.error("Parse data from string error!", pe);
    }
    return d;
  }

  /**
   * 字符型的日期转换成Date型的日期
   * @param myDateStr String
   * @return Date
   */
  public java.util.Date stringToDate(String myDateStr, String format) {
    myDateStr = formatDateString(myDateStr);
    if (myDateStr.length() < 10) {
      return null;
    }
    if (format == null || format.trim().equals("")) {
      format = "yyyy-MM-dd";
    }
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(format);
    java.util.Date d = null;
    try {
      d = sdf.parse(myDateStr);
    } catch (java.text.ParseException pe) {
      log.error("Parse data from string error!", pe);
    }
    return d;
  }

  /**
   * 字符型的日期转换成Date型的日期
   * @param myDateStr String
   * @return Date
   */
  public java.util.Date stringToDatetime(String myDateStr) {
    myDateStr = formatDateString(myDateStr);
    if (myDateStr.length() < 19) {
//      return null;
      myDateStr = myDateStr.substring(0, 10) + " 00:00:00";
    }
    myDateStr = myDateStr.substring(0, 19);
    String sFormat = "yyyy-MM-dd HH:mm:ss";
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(sFormat);
    java.util.Date d = null;
    try {
      d = sdf.parse(myDateStr);
    } catch (java.text.ParseException pe) {
      log.error("Parse date from string error!", pe);
    }
    return d;
  }

  /**
   * 长整型的值转换成日期
   * @param dateVal long 长整型的日期值
   * @return Date
   */
  public java.util.Date longToDatetime(long dateVal) {
    return new Date(dateVal);
  }

  /**
   * 长整型值转换日期值
   * @param dateVal Long 待转换值
   * @return Date
   */
  public Date longToDatetime(Long dateVal) {
    return longToDatetime(dateVal.longValue());
  }

  /**
   * Date型的日期转换成字符型的日期
   * @param myDate Date
   * @return String
   */
  public String dateToString(java.util.Date myDate) {
    if (myDate == null) {
      return null;
    }
    java.text.SimpleDateFormat sf = new java.text.SimpleDateFormat("yyyy-MM-dd");
    return sf.format(myDate);
  }

  /**
   * Date型的日期转换成字符型的日期
   * @param myDate Date
   * @return String
   */
  public String datetimeToString(java.util.Date myDate) {
    if (myDate == null) {
      return null;
    }
//    log.debug(myDate.toString());
    java.text.SimpleDateFormat sf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return sf.format(myDate);
  }
  
  /**
   * Date型的日期转换成字符型的日期_到毫秒
   * @param myDate Date
   * @return String
   */
  public String datetimeToStringSSS(java.util.Date myDate) {
    if (myDate == null) {
      return null;
    }
    java.text.SimpleDateFormat sf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
    return sf.format(myDate);
  }
  
  /**
   * Date型的日期转换成无分隔字符型的日期_到毫秒
   * @param myDate Date
   * @return String
   */
  public String datetimeToNoSplashStringSSS(java.util.Date myDate) {
	  if (myDate == null) {
	      return null;
	    }
	    java.text.SimpleDateFormat sf = new java.text.SimpleDateFormat("yyyyMMddHHmmssSSS");
	    return sf.format(myDate);
	}

  /**
   * Date型的日期转换成字符型的日期(24小时制)
   * @param myDate Date
   * @return String
   */
  public String datetimeToString24(java.util.Date myDate) {
    if (myDate == null) {
      return null;
    }
//    log.debug(myDate.toString());
    java.text.SimpleDateFormat sf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return sf.format(myDate);
  }

  /**
   * Date型的日期转换成字符型的日期(12小时制)
   * @param myDate Date
   * @return String
   */
  public String datetimeToString12(java.util.Date myDate) {
    if (myDate == null) {
      return null;
    }
//    log.debug(myDate.toString());
    java.text.SimpleDateFormat sf = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    return sf.format(myDate);
  }

  /**
   * Date型的日期转换成无分隔符的字符型的日期
   * @param myDate Date
   * @return String
   */
  public static String dateToNoSplashString(java.util.Date myDate) {
    if (myDate == null) {
      return null;
    }
    java.text.SimpleDateFormat sf = new java.text.SimpleDateFormat("yyyyMMdd");
    return sf.format(myDate);
  }

  /**
   * Date型的日期转换成无分隔符的字符型的日期
   * @param myDate Date
   * @return String
   */
  public String datetimeToNoSplashString(java.util.Date myDate) {
    if (myDate == null) {
      return null;
    }
    java.text.SimpleDateFormat sf = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
    return sf.format(myDate);
  }
  
  /**
   * 得到指定日期增加指定小时后的日期
   * @param myDate Date
   * @param difHour int
   * @return Date
   */
  public java.util.Date addHour(java.util.Date myDate, int difHour) {
    GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
    if (myDate == null) {
      return null;
    }
    cal.setTime(myDate);
    cal.add(Calendar.HOUR, difHour);
    return cal.getTime();
  }

  /**
   * 得到指定日期增加指定天数后的日期
   * @param myDate Date
   * @param difDay int
   * @return Date
   */
  public java.util.Date addDay(java.util.Date myDate, int difDay) {
    GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
    if (myDate == null) {
      return null;
    }
    cal.setTime(myDate);
    cal.add(Calendar.DATE, difDay);
    return cal.getTime();
  }

  /**
   * 得到指定日期增加指定月数后的日期
   * @param myDate Date
   * @param difMonth int
   * @return Date
   */
  public java.util.Date addMonth(java.util.Date myDate, int difMonth) {
    Calendar cal = GregorianCalendar.getInstance();
    if (myDate == null) {
      return null;
    }
    cal.setTime(myDate);
    cal.add(Calendar.MONTH, difMonth);
    return cal.getTime();
  }

  /**
   * 得到指定日期增加指定年数后的日期
   * @param myDate Date
   * @param difYear int
   * @return Date
   */
  public java.util.Date addYear(java.util.Date myDate, int difYear) {
    Calendar cal = GregorianCalendar.getInstance();
    if (myDate == null) {
      return null;
    }
    cal.setTime(myDate);
    cal.add(Calendar.YEAR, difYear);
    return cal.getTime();
  }

  /**
   * 取得某一日期所在月份的天数
   * @param myDate Date
   * @return int
   */
  public int getDaysOfMonth(java.util.Date myDate) {
    int days;
    GregorianCalendar gcal = new java.util.GregorianCalendar();
    if (myDate == null) {
      return 0;
    }
    gcal.setTime(myDate);
    days = gcal.getActualMaximum(Calendar.DAY_OF_MONTH);
    return days;
  }

  /**
   * 得到某一日期所在月份的第一天日期
   * @param myDate Date
   * @return Date
   */
  public Date getFirstDateOfMonth(java.util.Date myDate) {
    GregorianCalendar gcal = new java.util.GregorianCalendar();
    if (myDate == null) {
      return gcal.getTime();
    }
    gcal.setTime(myDate);
    gcal.set(gcal.get(Calendar.YEAR), gcal.get(Calendar.MONTH), 1);

    return gcal.getTime();
  }

  /**
   * 得到某一日期所在月份的第一天日期
   * @param myDate Date
   * @return String
   */
  public String getFirstDayOfMonth(java.util.Date myDate) {
    GregorianCalendar gcal = new java.util.GregorianCalendar();
    if (myDate == null) {
      return "";
    }
    gcal.setTime(myDate);
    java.text.SimpleDateFormat sf = new java.text.SimpleDateFormat("yyyy-MM-");
    return (sf.format(gcal.getTime()) + "01");
  }
  
  /**
   * 得到某一日期所在年份和月份：yyyyMM
   * @param myDate Date
   * @return String
   */
  public String getYearAndMonth(java.util.Date myDate) {
    GregorianCalendar gcal = new java.util.GregorianCalendar();
    if (myDate == null) {
      return "";
    }
    gcal.setTime(myDate);
    java.text.SimpleDateFormat sf = new java.text.SimpleDateFormat("yyyyMM");
    return (sf.format(gcal.getTime()));
  }
  /**
   * 得到某一日期所在月份的最后一天日期
   * @param myDate Date
   * @return Date
   */
  public Date getEndDateOfMonth(java.util.Date myDate) {
    GregorianCalendar gcal = new java.util.GregorianCalendar();
    if (myDate == null) {
      return gcal.getTime();
    }
    gcal.setTime(myDate);
    int days = this.getDaysOfMonth(myDate);
    gcal.set(gcal.get(Calendar.YEAR), gcal.get(Calendar.MONTH), days);

    return gcal.getTime();
  }

  /**
   * 得到某一日期所在月份的最后一天日期
   * @param myDate Date
   * @return String
   */
  public String getEndDayOfMonth(java.util.Date myDate) {
    GregorianCalendar gcal = new java.util.GregorianCalendar();
    if (myDate == null) {
      return "";
    }
    gcal.setTime(myDate);
    int days = this.getDaysOfMonth(myDate);
    java.text.SimpleDateFormat sf = new java.text.SimpleDateFormat("yyyy-MM-");
    return (sf.format(gcal.getTime()) + Integer.toString(days));
  }

  /**
   * 得到某一日期所在年份的第一天日期
   * @param myDate Date
   * @return Date
   */
  public Date getFirstDateOfYear(java.util.Date myDate) {
    GregorianCalendar gcal = new java.util.GregorianCalendar();
    if (myDate == null) {
      return gcal.getTime();
    }
    gcal.setTime(myDate);
    gcal.set(gcal.get(Calendar.YEAR), 0, 1);
    return gcal.getTime();
  }

  /**
   * 得到某一日期所在年份的第一天日期
   * @param myDate Date
   * @return String
   */
  public String getFirstDayOfYear(java.util.Date myDate) {
    GregorianCalendar gcal = new java.util.GregorianCalendar();
    if (myDate == null) {
      return "";
    }
    gcal.setTime(myDate);
    java.text.SimpleDateFormat sf = new java.text.SimpleDateFormat("yyyy-");
    return (sf.format(gcal.getTime()) + "01-01");
  }

  /**
   * 得到某一日期所在年份的最后一天日期
   * @param myDate Date
   * @return Date
   */
  public Date getEndDateOfYear(java.util.Date myDate) {
    GregorianCalendar gcal = new java.util.GregorianCalendar();
    if (myDate == null) {
      return gcal.getTime();
    }
    gcal.setTime(myDate);
    int days = this.getDaysOfMonth(myDate);
    gcal.set(gcal.get(Calendar.YEAR), 11, 31);
    return gcal.getTime();
  }

  /**
   * 得到某一日期所在年份的最后一天日期
   * @param myDate Date
   * @return String
   */
  public String getEndDayOfYear(java.util.Date myDate) {
    GregorianCalendar gcal = new java.util.GregorianCalendar();
    if (myDate == null) {
      return "";
    }
    gcal.setTime(myDate);
    int days = this.getDaysOfMonth(myDate);
    java.text.SimpleDateFormat sf = new java.text.SimpleDateFormat("yyyy-");
    return (sf.format(gcal.getTime()) + "12-31");
  }

  /**
   * 得到今天的日期
   * @return Date
   */
  public java.util.Date getToday() {
    GregorianCalendar gcal = new java.util.GregorianCalendar();
    return gcal.getTime();
  }

  public java.util.Date now() {
    return getToday();
  }

  /**
   * 取当前日期时间
   * @return String
   */
  public String getCurTime() {
    return datetimeToString(getToday());
  }
  
  /**
   * 取当前日期时间_到毫秒
   * @return String
   */
  public String getCurTimeSSS() {
    return datetimeToStringSSS(getToday());
  }

  /**
   * 取当前日期
   * @return String
   */
  public String getCurDate() {
    return dateToString(getToday());
  }

  /**
   * 求两个日期间相差的天数
   * @param date1 Date
   * @param date2 Date
   * @return int
   */
  public int daysAfter(Date date1, Date date2) {
    long lndate1 = date1.getTime();
    long lndate2 = date2.getTime();
    int dif = (int) ( (lndate1 - lndate2) / 1000 / 3600 / 24);
    return dif;
  }

  /**
   * 取得两个日期间相差的月数
   * @param beginDate Date
   * @param endDate Date
   * @return int
   */
  public int monthsBetween(Date beginDate, Date endDate) {
    GregorianCalendar gcBegin = new GregorianCalendar();
    GregorianCalendar gcEnd = new GregorianCalendar();
    if (beginDate.compareTo(endDate) > 0) {
      Date d = beginDate;
      beginDate = endDate;
      endDate = d;
    }
    gcBegin.setTime(beginDate);
    gcEnd.setTime(endDate);
    return (gcEnd.get(GregorianCalendar.YEAR) - gcBegin.get(GregorianCalendar.YEAR)) * 12 + gcEnd.get(GregorianCalendar.MONTH) -
      gcBegin.get(GregorianCalendar.MONTH);
  }

  /**
   * 计算从指定日期开始的后指定天数的日期
   * @param baseDate Date 基准日期
   * @param n int 需要加上的日期，如果是负值，表示往前算
   * @return Date
   */
  public Date relativeDate(Date baseDate, int n) {
    long ms = n * 24 * 60 * 60 * 1000L;
    return this.longToDatetime( (long) (baseDate.getTime() + ms));
  }
  

  /**
   * 计算从指定日期开始的后指定数值的秒
   * @param baseDate Date 基准日期
   * @param n int 需要加上的日期，如果是负值，表示往前算
   * @return Date
   */
  public Date relativeDate_M(Date baseDate, int n) {
    long ms = n * 1000L;
    return this.longToDatetime( (long) (baseDate.getTime() + ms));
  }

  /**
   * 取得指定日期的年份
   * @param date Date
   * @return int
   */
  public int getYear(Date date) {
    java.util.GregorianCalendar gcal = new java.util.GregorianCalendar();
    gcal.setTime(date);
    int year = gcal.get(Calendar.YEAR);
    return year;
  }

  /**
   * 取得指定日期所在的季度
   * @param date Date
   * @return int
   */
  public int getQuarter(Date date) {
    java.util.GregorianCalendar gcal = new java.util.GregorianCalendar();
    gcal.setTime(date);
    int quarter = 0;
    //int month = gcal.get(Calendar.MONTH);
    quarter = (gcal.get(Calendar.MONTH) / 3) + 1;
    return quarter;
  }

  /**
   * 取得指定日期的月份
   * @param date Date
   * @return int
   */
  public int getMonth(Date date) {
    java.util.GregorianCalendar gcal = new java.util.GregorianCalendar();
    gcal.setTime(date);
    int month = gcal.get(Calendar.MONTH) + 1;
    return month;
  }

  /**
   * 取出指定日期的日（天）
   * @param date Date
   * @return int
   */
  public int getDay(Date date) {
    java.util.GregorianCalendar gcal = new java.util.GregorianCalendar();
    gcal.setTime(date);
    int day = gcal.get(Calendar.DATE);
    return day;
  }

  /**
   * 取指定日期值中的小时数（12小时制）
   * @param date Date
   * @return int
   */
  public int getHour(Date date) {
    Calendar cl = Calendar.getInstance();
    cl.setTime(date);
    int hour = cl.get(Calendar.HOUR);
    return hour;
  }

  /**
   * 取得24小时制的小时
   * @param date Date
   * @return int
   */
  public int getHourOfDay(Date date) {
    Calendar cl = Calendar.getInstance();
    cl.setTime(date);
    int hour = cl.get(Calendar.HOUR_OF_DAY);
    return hour;
  }

  /**
   * 取得指定日期值中的分钟数
   * @param date Date
   * @return int
   */
  public int getMinute(Date date) {
    Calendar cl = Calendar.getInstance();
    cl.setTime(date);
    int minute = cl.get(Calendar.MINUTE);
    return minute;
  }

  /**
   * 取得指定日期值中的秒数
   * @param date Date
   * @return int
   */
  public int getSecond(Date date) {
    Calendar cl = Calendar.getInstance();
    cl.setTime(date);
    int second = cl.get(Calendar.SECOND);
    return second;
  }

  /**
   * 取得一个日期所对应的星期数
   * @param date Date
   * @return int
   */
  public int getDayOfWeek(Date date) {
    Calendar cl = Calendar.getInstance();
    cl.setTime(date);
    int weekDay = cl.get(Calendar.DAY_OF_WEEK);
    return weekDay;
  }

  /**
   * 取得一个日期值日所在月份的第几个星期
   * @param date Date
   * @return int
   */
  public int getWeekOfMonth(Date date) {
    Calendar cl = Calendar.getInstance();
    cl.setTime(date);
    int weekDay = cl.get(Calendar.WEEK_OF_MONTH);
    return weekDay;
  }

  /**
   * 取得指定日期所在季度的第一天
   * @param date Date 指定日期
   * @return Date
   */
  public Date getFirstDayOfQuarter(Date date) {
    java.util.GregorianCalendar gcal = new java.util.GregorianCalendar();
    gcal.setTime(date);
    int quarter = getQuarter(date);
    int month = gcal.get(Calendar.MONTH);
    switch (quarter) {
      case 1:
        month = 0;
        break;
      case 2:
        month = 3;
        break;
      case 3:
        month = 6;
        break;
      case 4:
        month = 9;
        break;
    }
    gcal.set(gcal.get(Calendar.YEAR), month, 1, gcal.get(Calendar.HOUR), gcal.get(Calendar.MINUTE), gcal.get(Calendar.SECOND));

    return gcal.getTime();
  }

  /**
   * 取得指定日期所在季度的最后一天
   * @param date Date 指定日期
   * @return Date
   */
  public Date getEndDayOfQuarter(Date date) {
    java.util.GregorianCalendar gcal = new java.util.GregorianCalendar();
    gcal.setTime(date);
    int quarter = getQuarter(date);
    int month = gcal.get(Calendar.MONTH);
    switch (quarter) {
      case 1:
        month = 2;
        break;
      case 2:
        month = 5;
        break;
      case 3:
        month = 8;
        break;
      case 4:
        month = 11;
        break;
    }
    gcal.set(gcal.get(Calendar.YEAR), month, 1);

    return stringToDate(getEndDayOfMonth(gcal.getTime()));
  }

  /**
   * 取得指定日期增加一个季度（3个月）的日期
   * @param date Date 指定日期
   * @return Date
   */
  public Date addQuarter(Date date, int difQuarter) {
    return addMonth(date, 3 * difQuarter);
  }

  /**
   * 检测指定的字符型日期值是否符合指定的格式
   * @param dateStr String 日期值
   * @param format String 格式串
   * @return boolean 结果值
   */
  public static boolean checkDateFormat(String dateStr, String format) {
    if (dateStr == null || "".equals(dateStr.trim())) {
      return true;
    }

    if (format == null || "".equals(format.trim())) {
      format = "yyyy-MM-dd";
    }
    if (format.length() != dateStr.length()) {
      log.error("The date string's length not equals format string's length.");
      return false;
    }
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(format);
    Date d = null;
    try {
      d = sdf.parse(dateStr);
//      log.debug("After parse date: " + d);
    } catch (java.text.ParseException pe) {
      log.error("", pe);
      return false;
    }
    return true;
  }

  /**
   * 检测指定的字符型日期值是否符合指定的格式
   * @param dateStr String 日期值
   * @param format String 格式串
   * @return boolean 结果值
   */
  public boolean checkDateFormatAndValue(String dateStr, String format) {
    boolean ret = true;
    if (dateStr == null || "".equals(dateStr.trim())) {
      return true;
    }

    if (format == null || "".equals(format.trim())) {
      format = "yyyy-MM-dd";
    }
    if (format.length() != dateStr.length()) {
      log.error("The date string's length not equals format string's length.");
      return false;
    }
    String d_month = dateStr.substring(5, 7);
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(format);
    Date d = null;
    try {
      d = sdf.parse(dateStr);
      int month = getMonth(d);
      if (Integer.parseInt(d_month) != month) {
        ret = false;
      }
    } catch (java.text.ParseException pe) {
      log.error("", pe);
      ret = false;
    }
    return ret;
  }

  /**
   * 从一个日期值中取出星期值，按指定格式显示，如果格式字符串为null，则返回“星期X”
   * @param value Date 日期值
   * @param formatStr String 格式字符串
   * @return String
   */
  public String formatWeek(Date value, String formatStr) {
    if (value == null) {
      return null;
    }

    if (formatStr == null || "".equals(formatStr.trim())) {
      formatStr = "星期F";
    }
    java.text.SimpleDateFormat sf = new java.text.SimpleDateFormat(formatStr);
    return sf.format(value);
  }

  /**
   * 得到两个日期之间间隔数
   * @param dateBefore Date 开始日期
   * @param dateAfter Date 结束日期
   * @param field int 要计算的时间类型，例如： 天，月，年(eg. Calendar.Month   Calendar.Year)
   * @param amount int 间隔数
   * @return int
   * 例：
   * getDateInterval(dateBefore,dateAfter,Calendar.Month,1),返回两个日期之间的月份。
   */
  public int getDateInterval(Date dateBefore, Date dateAfter, int field, int amount) {
    int interval = 0;
    Calendar cb = new GregorianCalendar();
    Calendar da = new GregorianCalendar();
    cb.setTime(dateBefore);
    da.setTime(dateAfter);
    da.add(field, 1);
    for (Calendar c = cb; c.before(da); c.add(field, amount)) {
      interval++;
    }
    return interval;
  }

  /**
   * 得到两个日期之前的天数
   * @param beginDate String 开始日期 格式:yyyy-MM-dd
   * @param endDate String 结束日期 格式:yyyy-MM-dd
   * @return int
   */
  public int getDateInterval(String beginDate, String endDate) {
    try {
      return getDateInterval(stringToDate(beginDate), stringToDate(endDate), Calendar.DATE, 1);
    } catch (Exception ex) {
      return 0;
    }
  }

  /**
   * 两个日期的间隔月份
   * @param dateBefore
   * @param dateAfter
   * @return
   */
  public int getMonthInterval(Date dateBefore, Date dateAfter) {
    return getDateInterval(dateBefore, dateAfter, Calendar.MONTH, 1);
  }

  /**
   * 根据传入的样式格式化日期
   * @param date Date
   * @param pattern String
   * @return String
   */
  public static String format(Date date, String pattern) {
    SimpleDateFormat formatter = new SimpleDateFormat(pattern);
    return formatter.format(date);
  }

  /**
   * 比较两个字符串大小
   * @param str1 String
   * @param str2 String
   * @return int 0 相等; 1 前大于后； 2 后大于前； 3 出错
   */
  public static int compare2StrDate(String str1, String str2) {
    int compareResult = 0;
    if (str1 == null || str2 == null) {
      return 3;
    }
    if (str1.length() != 10 || str2.length() != 10) {
      return 3;
    }
    char[] char_str1 = str1.toCharArray();
    char[] char_str2 = str2.toCharArray();
    for (int i = 0; i < 10; i++) {
      char tmpChar1 = char_str1[i];
      char tmpChar2 = char_str2[i];
      if (tmpChar1 > tmpChar2) {
        return 1;
      } else if (tmpChar1 < tmpChar2) {
        return 2;
      }
    }
    return compareResult;
  }

  public static void main(String[] args) {
    DateUtil du = new DateUtil();
    Date d = new Date();

    System.out.println("dateToString:" + du.dateToString(d));
    System.out.println("datetimeToString:" + du.datetimeToString(d));
    System.out.println("stringToDate:" + du.stringToDate(du.dateToString(d)));
    System.out.println("stringToDatetime:" + du.stringToDatetime(du.datetimeToString(d)));
    System.out.println("dateToNoSlashString:" + du.dateToNoSplashString(d));
    System.out.println("datetimeToNoSlashString:" + du.datetimeToNoSplashString(d));
    System.out.println("addDay:" + du.addDay(d, 1));
    System.out.println("addMonth:" + du.addMonth(d, 1));
    System.out.println("addYear:" + du.addYear(d, 1));
    System.out.println("getDaysOfMonth:" + du.getDaysOfMonth(d));
    System.out.println("getFirstDayOfMonth:" + du.getFirstDayOfMonth(d));
    System.out.println("getEndDayOfMonth:" + du.getEndDayOfMonth(d));
    System.out.println("getToday:" + du.getToday());
    System.out.println("daysAfter(Date date1, Date date2):" + du.daysAfter(d, du.addDay(d, 4)));
    System.out.println("getFirstDayOfYear:" + du.getFirstDayOfYear(d));
    System.out.println("getEndDayOfYear:" + du.getEndDayOfYear(d));
    System.out.println("checkDateFormat(\"20050101\",\"yyyy-MM-dd\"): " + checkDateFormat("20050101", "yyyy-MM-dd"));
    System.out.println("checkDateFormat(\"2005-1-01\",\"yyyy-MM-dd\"): " + checkDateFormat("2005-1-01", "yyyy-MM-dd"));
    System.out.println("checkDateFormat(\"2005-1-01\",\"yyyy-M-dd\"): " + checkDateFormat("2005-1-01", "yyyy-MM-dd"));
    System.out.println("checkDateFormat(\"2005-01-01\",\"yyyy-M-dd\"): " + checkDateFormat("2005-01-01", "yyyy-M-dd"));
    System.out.println("checkDateFormat(\"2005-01-01\",\"yyyyMMdd\"): " + checkDateFormat("2005-01-01", "yyyyMMdd"));
    System.out.println("checkDateFormat(\"2005-01-01\",\"yyyy-M-dd HH:mm:ss\"): " + checkDateFormat("2005-1-01", "yyyy-MM-dd HH:mm:ss"));
    System.out.println("checkDateFormat(\"2005-01-01\",\"yyyy-MM-dd\"): " + checkDateFormat("2005-01-01", "yyyy-MM-dd"));

    Date today = du.getToday();
    System.out.println(du.stringToDatetime("2006-12-01 12:00:00").getTime());
    System.out.println("today - 1000: " + du.datetimeToString(du.relativeDate(today, -1000)));
    System.out.println("today - 100: " + du.datetimeToString(du.relativeDate(today, -100)));
    System.out.println("today - 10: " + du.datetimeToString(du.relativeDate(today, -10)));
    System.out.println("today - 2: " + du.datetimeToString(du.relativeDate(today, -2)));
    System.out.println("today - 0: " + du.datetimeToString(du.relativeDate(today, 0)));
    System.out.println("today + 10: " + du.datetimeToString(du.relativeDate(today, 10)));
    System.out.println("today 100: " + du.datetimeToString(du.relativeDate(today, 100)));
    System.out.println("today 1000: " + du.datetimeToString(du.relativeDate(today, 1000)));
  }

}
