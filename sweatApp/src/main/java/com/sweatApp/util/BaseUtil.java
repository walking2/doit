package com.sweatApp.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class BaseUtil {

	private static SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
	private static SimpleDateFormat HHmmss = new SimpleDateFormat("HHmmss");


	/**
	 * 两个为数字的字符串相乘
	 * @return
	 */
	public static BigDecimal bigDecimalStringMultiply(String str1,String str2){
		BigDecimal d1 = new BigDecimal(str1);
		BigDecimal d2 = new BigDecimal(str2);
		return d1.multiply(d2);
	}


	/**
	 * 数值型字符串获取整数部分
	 * 
	 * @param str
	 * @return
	 * @author 谭军
	 */
	public static final Long getLong(String str) {
		if (CheckParam.isInteger(str)) {
			return Long.parseLong(str);
		} else if (CheckParam.isNum(str)) {
			int index = str.indexOf(".");
			String integer = str.substring(0, index);
			return Long.parseLong(integer);
		}
		return null;
	}

	/**
	 * 数值型字符串获取整数部分
	 * 
	 * @param str
	 * @return
	 * @author 谭军
	 */
	public static final Integer getInteger(String str) {
		if (CheckParam.isInteger(str)) {
			return Integer.parseInt(str);
		} else if (CheckParam.isNum(str)) {
			int index = str.indexOf(".");
			String integer = str.substring(0, index);
			return Integer.parseInt(integer);
		}
		return null;
	}

	/**
	 * 日期格式转换yyyy-MM-dd
	 * 
	 * @param date
	 *            要转换的日期
	 * @return yyyy-MM-dd
	 * @author 谭军 2015年11月3日下午4:20:21
	 */
	public static final Date DateToDate(Date date) {
		Date _date = null;
		try {
			_date = shortSdf.parse(shortSdf.format(date));
		} catch (Exception e) {
			e.printStackTrace();
			try {
				_date = shortSdf.parse(shortSdf.format(new Date()));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		return _date;
	}

	/**
	 * 日期格式转换yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 *            要转换的日期
	 * @return yyyy-MM-dd HH:mm:ss
	 * @author 谭军 2015年11月3日下午4:23:08
	 */
	public static final Date DateToDateTime(Date date) {
		Date _date = null;
		try {
			_date = shortSdf.parse(longSdf.format(date));
		} catch (Exception e) {
			e.printStackTrace();
			try {
				_date = shortSdf.parse(longSdf.format(new Date()));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		return _date;
	}

	/**
	 * double格式化
	 * 
	 * @author cj
	 */
	public static final double doubleFormart(double str) {
		if (str != 0) {
			DecimalFormat df = new DecimalFormat("###.00");
			return Double.valueOf(df.format(str));
		} else {
			return str;
		}
	}

	public static final Date strToDate(String str) throws ParseException {
		if (CheckParam.isNull(str)) {
			return null;
		}
		return new SimpleDateFormat("yyyy-MM-dd").parse(str);
	}

	public static final Date strToDate(Object str) throws ParseException {
		return strToDate(str + "");
	}

	public static final Date strToDateTime(String str) throws ParseException {
		if (CheckParam.isNull(str)) {
			return null;
		}
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
	}

	public static final Date strToDateTime(Object str) throws ParseException {
		return strToDateTime(str + "");
	}

	public static final Date strToDateTimeNoSecond(String str) throws ParseException {
		if (CheckParam.isNull(str)) {
			return null;
		}
		return new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(str);
	}

	public static final String DateToStr(Date date) {
		if (date == null) {
			return null;
		}
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

	public static final String DateTimeNoSecondToStr(Date date) {
		if (date == null) {
			return null;
		}
		return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
	}

	public static final String DateTimeToStr(Date date) {
		if (date == null) {
			return null;
		}
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}

	public static final String getYear(Date date) {
		if (date == null) {
			return null;
		}
		return new SimpleDateFormat("yyyy").format(date);
	}

	public static final String getDay(Date date) {
		if (date == null) {
			return null;
		}
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

	public static final String getDaySeparateWithPoint(Date date) {
		if (date == null) {
			return null;
		}
		return new SimpleDateFormat("yyyy.MM.dd").format(date);
	}

	public static final int getDayCount(Date startTime, Date endTim) {
		int days = (int) ((endTim.getTime() - startTime.getTime()) / 86400000);
		return days + 1;
	}

	public static final double getDoubleTwo(double str) {
		DecimalFormat df = new DecimalFormat("0.00");
		String num = df.format(str);
		return Double.parseDouble(num);
	}

	public static final List<Object> iteratorToList(Iterator<?> iterator) {
		List<Object> list = new ArrayList<Object>();
		for (; iterator.hasNext();) {
			Object element = (Object) iterator.next();
			list.add(element);
		}
		return list;
	}

	// 获取当前时间前 第N天的日期
	public static String getDayPre(Date date, int pre) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -pre);
		date = calendar.getTime();
		return getDay(date);
	}

	// 获取当前时间后 第N天的日期
	public static String getDayNext(Date date, int next) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, next);
		date = calendar.getTime();
		return getDay(date);
	}

	/**
	 * 取随机数字字符
	 * 
	 * @Title: createRandom
	 * @param numberFlag true表示只取数字组成的随机字符串，false则表示数字、字母混合
	 * @param length  字符串长度
	 * @return
	 * @return: String
	 */
	public static final String randomNumber(boolean numberFlag, int length) {
		String retStr = "";
		String strTable = numberFlag ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyz";
		int len = strTable.length();
		boolean bDone = true;
		do {
			retStr = "";
			int count = 0;
			for (int i = 0; i < length; i++) {
				double dblR = Math.random() * len;
				int intR = (int) Math.floor(dblR);
				char c = strTable.charAt(intR);
				if (('0' <= c) && (c <= '9')) {
					count++;
				}
				retStr += strTable.charAt(intR);
			}
			if (count >= 2) {
				bDone = false;
			}
		} while (bDone);
		return retStr;
	}

	// 获得本天的开始时间，即2012-01-01 00:00:00
	public static Date getCurrentDayStartTime() {
		Date now = new Date();
		try {
			now = shortSdf.parse(shortSdf.format(now));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return now;
	}

	// 获得本天的结束时间，即2012-01-01 23:59:59
	public static Date getCurrentDayEndTime() {
		Date now = new Date();
		try {
			now = longSdf.parse(shortSdf.format(now) + " 23:59:59");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return now;
	}

	// 获得本周的第一天，周一
	public static Date getCurrentWeekDayStartTime() {
		Calendar c = Calendar.getInstance();
		try {
			int weekday = c.get(Calendar.DAY_OF_WEEK) - 2;
			c.add(Calendar.DATE, -weekday);
			c.setTime(longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c.getTime();
	}

	// 获得本周的最后一天，周日
	public static Date getCurrentWeekDayEndTime() {
		Calendar c = Calendar.getInstance();
		try {
			int weekday = c.get(Calendar.DAY_OF_WEEK);
			c.add(Calendar.DATE, 8 - weekday);
			c.setTime(longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c.getTime();
	}

	// 获得本月的开始时间，即2012-01-01 00:00:00
	public static Date getCurrentMonthStartTime() {
		Calendar c = Calendar.getInstance();
		Date now = null;
		try {
			c.set(Calendar.DATE, 1);
			now = shortSdf.parse(shortSdf.format(c.getTime()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return now;
	}

	// 当前月的结束时间，即2012-01-31 23:59:59
	public static Date getCurrentMonthEndTime() {
		Calendar c = Calendar.getInstance();
		Date now = null;
		try {
			c.set(Calendar.DATE, 1);
			c.add(Calendar.MONTH, 1);
			c.add(Calendar.DATE, -1);
			now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return now;
	}

	// 获得指定月的开始时间，即2012-01-01 00:00:00
	public static Date getMonthStartTime(int month) {
		Calendar c = Calendar.getInstance();
		Date now = null;
		try {
			c.set(Calendar.DATE, 1);
			c.set(Calendar.MONTH, month - 1);
			now = shortSdf.parse(shortSdf.format(c.getTime()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return now;
	}

	// 获取指定月的结束时间，即2012-01-31 23:59:59
	public static Date getMonthEndTime(int month) {
		Calendar c = Calendar.getInstance();
		Date now = null;
		try {
			c.set(Calendar.DATE, 1);
			c.set(Calendar.MONTH, 1);
			c.add(Calendar.MONTH, month - 1);
			c.add(Calendar.DATE, -1);
			now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return now;
	}

	// 获得指定年和指定月的开始时间，即2012-01-01 00:00:00
	public static Date getYearMonthStartTime(int year, int month) {
		Calendar c = Calendar.getInstance();
		Date now = null;
		try {
			c.set(Calendar.YEAR, year);
			c.set(Calendar.DATE, 1);
			c.set(Calendar.MONTH, month - 1);
			now = shortSdf.parse(shortSdf.format(c.getTime()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return now;
	}

	// 获取指定年和指定月的结束时间，即2012-01-31 23:59:59
	public static Date getYearMonthEndTime(int year, int month) {
		Calendar c = Calendar.getInstance();
		Date now = null;
		try {
			c.set(Calendar.YEAR, year);
			c.set(Calendar.DATE, 1);
			c.set(Calendar.MONTH, 1);
			c.add(Calendar.MONTH, month - 1);
			c.add(Calendar.DATE, -1);
			now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return now;
	}

	// 当前季度的开始时间，即2012-01-1 00:00:00
	public static Date getCurrentQuarterStartTime() {
		Calendar c = Calendar.getInstance();
		int currentMonth = c.get(Calendar.MONTH) + 1;
		Date now = null;
		try {
			if (currentMonth >= 1 && currentMonth <= 3)
				c.set(Calendar.MONTH, 0);
			else if (currentMonth >= 4 && currentMonth <= 6)
				c.set(Calendar.MONTH, 3);
			else if (currentMonth >= 7 && currentMonth <= 9)
				c.set(Calendar.MONTH, 4);
			else if (currentMonth >= 10 && currentMonth <= 12)
				c.set(Calendar.MONTH, 9);
			c.set(Calendar.DATE, 1);
			now = longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return now;
	}

	// 当前季度的结束时间，即2012-03-31 23:59:59
	public static Date getCurrentQuarterEndTime() {
		Calendar c = Calendar.getInstance();
		int currentMonth = c.get(Calendar.MONTH) + 1;
		Date now = null;
		try {
			if (currentMonth >= 1 && currentMonth <= 3) {
				c.set(Calendar.MONTH, 2);
				c.set(Calendar.DATE, 31);
			} else if (currentMonth >= 4 && currentMonth <= 6) {
				c.set(Calendar.MONTH, 5);
				c.set(Calendar.DATE, 30);
			} else if (currentMonth >= 7 && currentMonth <= 9) {
				c.set(Calendar.MONTH, 8);
				c.set(Calendar.DATE, 30);
			} else if (currentMonth >= 10 && currentMonth <= 12) {
				c.set(Calendar.MONTH, 11);
				c.set(Calendar.DATE, 31);
			}
			now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return now;
	}

	// 当前年的开始时间，即2012-01-01 00:00:00
	public static Date getCurrentYearStartTime() {
		Calendar c = Calendar.getInstance();
		Date now = null;
		try {
			c.set(Calendar.MONTH, 0);
			c.set(Calendar.DATE, 1);
			now = shortSdf.parse(shortSdf.format(c.getTime()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return now;
	}

	// 当前年的结束时间，即2012-12-31 23:59:59
	public static Date getCurrentYearEndTime() {
		Calendar c = Calendar.getInstance();
		Date now = null;
		try {
			c.set(Calendar.MONTH, 11);
			c.set(Calendar.DATE, 31);
			now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return now;
	}

	// 指定年的开始时间，即2012-01-01 00:00:00
	public static Date getYearStartTime(int year) {
		Calendar c = Calendar.getInstance();
		Date now = null;
		try {
			c.set(Calendar.YEAR, year);
			c.set(Calendar.MONTH, 0);
			c.set(Calendar.DATE, 1);
			now = shortSdf.parse(shortSdf.format(c.getTime()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return now;
	}

	// 指定年的结束时间，即2012-12-31 23:59:59
	public static Date getYearEndTime(int year) {
		Calendar c = Calendar.getInstance();
		Date now = null;
		try {
			c.set(Calendar.YEAR, year);
			c.set(Calendar.MONTH, 11);
			c.set(Calendar.DATE, 31);
			now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return now;
	}

	public static Date getMonthFirstDay(int year, int month) {
		Calendar c = Calendar.getInstance();
		Date now = null;
		try {
			c.set(Calendar.YEAR, year);
			c.set(Calendar.MONTH, month - 1);
			c.set(Calendar.DAY_OF_MONTH, 1);
			now = longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00");
		} catch (Exception e) {

		}
		return now;
	}

	public static Date getMonthLastDay(int year, int month) {
		Calendar c = Calendar.getInstance();
		Date now = null;
		try {
			c.set(Calendar.YEAR, year);
			c.set(Calendar.MONTH, month - 1);
			c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
			now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");
		} catch (Exception e) {

		}
		return now;
	}

	public static String toDateTime(Object obj, String pattern) {
		if (obj == null || pattern == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		sdf.setLenient(false);// 此设置用于验证日期的合法性
		try {
			if (obj.getClass() == Date.class || obj instanceof Date) {
				Date date = (Date) obj;
				return sdf.format(date);
			} else {
				return sdf.format(sdf.parse(obj.toString()));
			}
		} catch (Exception e) {
			return null;
		}
	}

	public static Date toFormatDateTime(Object obj, String pattern) {
		if (obj == null || pattern == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		sdf.setLenient(false);// 此设置用于验证日期的合法性
		try {
			if (obj.getClass() == Date.class || obj instanceof Date) {
				Date date = (Date) obj;
				return sdf.parse(sdf.format(date));
			} else {
				return sdf.parse(obj.toString());
			}
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 当前时间+天数
	 * 
	 * @param day
	 * @return 运算后的时间
	 */
	public static Date addDay(int day) {
		return new Date(new Date().getTime() + 86400000 * day);
	}

	/**
	 * 指定时间+天数
	 * 
	 * @param date
	 * @param day
	 * @return 运算后的时间
	 */
	public static Date addDay(Date date, int day) {
		return new Date(date.getTime() + 86400000 * day);
	}

	public static String getSpecifiedDayBefore(String specifiedDay) {// 可以用new
																		// Date().toLocalString()传递参数
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - 1);

		String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
		return dayBefore;
	}


	// 生成团号
	public static String getTripNumber(String startStr, String endStr) {
		Date date = new Date();
		String year = new SimpleDateFormat("yyyyMMdd").format(date);
		String numString = randomNumber(true, 6);
		String tripNumber = startStr + year + numString + endStr;
		return tripNumber;
	}

	/**
	 * 生成报价单号
	 * 
	 * @return
	 */
	public static String getQuoteNumber() {
		Date date = new Date();
		String year = new SimpleDateFormat("yyyyMMdd").format(date);
		String numString = randomNumber(true, 6);
		return "BJDH" + year + numString;
	}

	// 生成团号 userName+系统时间时分秒+出团日期+随机码(6)
	public static String getTripNumber(String shortName, String startTime, int tripType) {
		Date date = new Date();
		String year = HHmmss.format(date);
		startTime = startTime + "";
		startTime = startTime.replace("-", "");
		if (startTime.length() != 8) {
			startTime = yyyyMMdd.format(BaseUtil.addDay(1));
		}
		String tripNumber = shortName + year + startTime + (tripType == 0 ? "S" : "T");
		return tripNumber;
	}

	// 比较两个时间大小
	public static boolean timeOneGreaterThanTimeTwo(Date timeOne, Date timeTwo) {
		return timeOne.getTime() - timeTwo.getTime() > 0;
	}

	// 比较两个时间大小
	public static boolean timeOnelessThanTimeTwo(Date timeOne, Date timeTwo) {
		return timeOne.getTime() - timeTwo.getTime() < 0;
	}

	/**
	 * 判断两个时间是否相等
	 * 
	 * @param timeOne
	 * @param timeTwo
	 * @return boolean
	 * @author 谭军
	 */
	public static boolean isDateEqual(Date timeOne, Date timeTwo) {
		if(timeOne == null || timeTwo == null){
			return false;
		}
		return timeOne.getTime() - timeTwo.getTime() == 0;
	}

	// 比较两个时间大小
	public static boolean currentTimeBetweenTimeOneAndTimeTwo(Date timeOne, Date timeTwo, Date currentTime) {
		return currentTime.getTime() - timeOne.getTime() > 0 && currentTime.getTime() - timeTwo.getTime() <= 0;
	}

	public static final int getTicketAtWhichDay(Date startTime, Date endTim) {
		int days = (int) ((endTim.getTime() - startTime.getTime()) / 86400000) + 1;
		return days;
	}

	/**
	 * 解决gson转换，整形带小数点和0的情况，结果会四舍五入 例如102.777转换后得103
	 * 
	 * @author
	 */
	public static final Long numToLong(String id) {
		id += "";
		return Math.round(Double.parseDouble(id));
	}

	public static final Integer strToInteger(String str) {
		str = "" + str;
		return Integer.valueOf(str.split("\\.")[0]);
	}

	public static final double strToDouble(String str) {
		return Double.parseDouble(str);
	}

	public static final Long strToLong(String str) {
		str = "" + str;
		return Long.valueOf(str.split("\\.")[0]);
	}

	// 比较开始时间小于结束时间
	public static final boolean compareDay(String startTime, String endTime) {
		java.text.DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		try {
			c1.setTime(df.parse(startTime));
			c2.setTime(df.parse(endTime));
		} catch (ParseException e) {
			System.err.println("格式不正确");
		}
		int result = c1.compareTo(c2);
		if (result < 0) {
			return true;
		} else {
			return false;
		}
	}

	// 判断一个List中是否有重复元素，有返回true，否则false
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static boolean listhasSameElement(List eleList) {
		Set eleSet = new HashSet<>(eleList);
		if (eleSet.size() != eleList.size()) {
			return true;
		}
		return false;
	}

	// 获取指定的年列表
	public static List<Integer> getYearList(int countYear) {
		List<Integer> yearList = new ArrayList<Integer>();
		Calendar calendar = new GregorianCalendar();
		for (int i = 1; i < countYear; i++) {
			yearList.add(calendar.get(Calendar.YEAR));
			calendar.add(Calendar.YEAR, -1);
		}
		return yearList;
	}


	public static String isField(Class<?> clz, Object name) {
		return isField(clz, name + "");
	}

	public static String toString(Object obj) {
		String str = (obj == null) ? "" : obj.toString();
		return CheckParam.isNull(str) ? "" : str;
	}

	/**
	 * 
	 * @TODO 转换函数，不为数字返回0.0， @参数： @返回：
	 * @时间：2015年10月30日 @author qiuyuan
	 */
	public static double toDouble(Object obj) {
		if (CheckParam.isNum(toString(obj))) {
			return Double.parseDouble(toString(obj));
		}
		return 0.0D;
	}

	/**
	 * 
	 * @TODO 转换函数，不为数字返回0 @参数： @返回：
	 * @时间：2015年10月30日 @author qiuyuan
	 */
	public static int toInt(Object obj) {
		if (CheckParam.isInteger(toString(obj))) {
			return Integer.parseInt(toString(obj));
		}
		return 0;
	}

	/**
	 * 
	 * @TODO 转换函数，不为数字返回0 @参数： @返回：
	 * @时间：2015年10月30日 @author qiuyuan
	 */
	public static Long toLong(Object obj) {
		if (CheckParam.isInteger(toString(obj))) {
			return Long.parseLong(toString(obj));
		}
		return 0L;
	}

	public static Date getDayEndTime(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sd.parse(sdf.format(date) + " 23:59:59");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date minusOrPlusMinutesToCurrentTime(long minutes) {
		long minus = minutes * 60 * 1000;
		return new Date(new Date().getTime() - minus);
	}

	/**
	 * 返回time2到time1的分钟差
	 * 
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static long getMinutesTime1ToTime2(Date time1, Date time2) {
		return time2.getTime() / 60000 - time1.getTime() / 60000;
	}

	/**
	 * 将一段英文的每个首字母变为大写(其他会变成小写)
	 * 
	 * @author huf
	 * @param str
	 * @return
	 */
	public static String toUpperString(String str) {
		StringBuffer stringbf = new StringBuffer();
		Matcher m = Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(str);
		while (m.find()) {
			m.appendReplacement(stringbf, m.group(1).toUpperCase() + m.group(2).toLowerCase());
		}
		return m.appendTail(stringbf).toString();
	}

	/**
	 * 将一段英文的首字母变为大写(其他不变)
	 * 
	 * @author huf
	 * @param str
	 * @return
	 */
	public static String toUpperFirstChar(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	/**
	 * 生成小组的订单号
	 * 
	 * @author huf
	 */
	public static String getOrderNumber(String shortName) {
		Date date = new Date();
		String dateString = HHmmss.format(date) + yyyyMMdd.format(date);
		return shortName + dateString + "TG";
	}

	/**
	 * 生成中转小组的订单号
	 * 
	 * @author huf
	 */
	public static String getTransitOrderNumber(String shortName) {
		Date date = new Date();
		String dateString = HHmmss.format(date) + yyyyMMdd.format(date);
		return shortName + dateString + "ZZ";
	}
	public static String getWeekOfDate(Date date) {
		String[] weekOfDays = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };
		Calendar calendar = Calendar.getInstance();
		if (date != null) {
			calendar.setTime(date);
		}
		int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0) {
			w = 0;
		}
		return weekOfDays[w];
	}

	/**
	 * list转字符串
	 * 
	 * @author cj
	 */
	public static String listToStr(List<String> a) {
		StringBuffer str = new StringBuffer();
		for (String s : a) {
			if (!CheckParam.isNull(s)) {
				str.append(s + ",");
			}
			// if (s != null&&!"null".equals(s)&&!"".equals(s)) {
			// str.append(s + ",");
			// }
		}
		if (str != null && str.length() > 0 && ',' == str.charAt(str.length() - 1)) {
			str = str.deleteCharAt(str.length() - 1);
		}
		return str.toString();
	}

	public static Integer toInteger(Object obj) {
		if (obj == null || !CheckParam.isInteger(obj.toString())) {
			return 0;
		}
		return Integer.parseInt(obj.toString());
	}
	
	/**
	 * 将费用项List中的 中转费用项 排第一个
	 * @param list
	 * @return list
	 * @author cj
	 */
	public static List<Map<String,Object>> sortFeeItem(List<Map<String,Object>> list){
		Collections.sort(list, new Comparator<Map<String, Object>>() {
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                String name1 = (String) o1.get("name");
                String name2 = (String) o2.get("name");
                return name1.compareTo(name2);
            }
        });
        return list;
	}
	
	/**
	 * 对Map集合按照指定的key排序
	 * @param list Map集合
	 * @param field 指定排序key
	 * @return List
	 * @author 谭军
	 */
	public static List<Map<String,Object>> sortMapList(List<Map<String,Object>> list,String field){
		try {
			Collections.sort(list, new Comparator<Map<String, Object>>() {
			    public int compare(Map<String, Object> o1, Map<String, Object> o2) {
			        String field1 = (String) o1.get(field);
			        String field2 = (String) o2.get(field);
			        return field1.compareTo(field2);
			    }
			});
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
		return new ArrayList<>();
	}
	

	

	/**
	 * 删除list中的“”或者null
	 * @param list
	 * @return list
	 * @author cj
	 */
	public static List<Object> removeNull(List<Object> list) {
		if (list != null) {
			List<String> nullArr = new ArrayList<String>();
			nullArr.add(null);
			nullArr.add("");
			list.remove(nullArr);
		}
		return list;
	}
	
	/**
	 * 
	 * @TODO 计算两个时间的相差天数，同天得0
	 * @时间：2016年2月26日
	 * @author qiuyuan
	 */
	public static int daysBetween(Date smdate, Date bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	public static void main(String[] args) {
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	        Date smdate = sdf.parse("2016-02-25 23:59:59");
			Date bdate = sdf.parse("2016-02-26 00:00:00");
			System.out.println(daysBetween(smdate, bdate)+1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
