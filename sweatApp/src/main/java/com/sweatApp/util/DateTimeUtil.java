package com.sweatApp.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期处理公共类
 * @author jxi
 *
 */
public class DateTimeUtil {
	public static String fullPattern2 = "yyyyMMddHHmmssSSS";
	public static String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
	public static String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static String DEFAULT_DATE = "yyyyMMdd";
	/**
	 * 获取指定格式日期字附后串
	 * @param format 日期格式
	 * @return 日期字附串
	 */
	public static String getformatdate(String format)
	{
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sp = new SimpleDateFormat(format);
		return sp.format(calendar.getTime());
	}
	
	/**
	 * 将字符串转换成格式为：yyyy-MM-dd HH:mm 日期.
	 * 
	 * @param str
	 * @return
	 */
	public static Date strToDate(final String str) {
		if (str == null || str.trim().length() == 0)
			return null;
		try {
			DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			return fmt.parse(str.trim());

		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 将字符串转换成指定格式的日期.
	 * 
	 * @param str
	 *            日期字符串.
	 * @param dateFormat
	 *            日期格式. 如果为空，默认为:yyyy-MM-dd HH:mm:ss.
	 * @return
	 */
	public static Date strToDate(final String str, String dateFormat) {
		if (str == null || str.trim().length() == 0)
			return null;
		try {
			if (dateFormat == null || dateFormat.length() == 0)
				dateFormat = "yyyy-MM-dd HH:mm:ss";
			DateFormat fmt = new SimpleDateFormat(dateFormat);
			Date  date=fmt.parse(str.trim());
			
			return  date;
		} catch (Exception ex) {
			/*
			 * log.error("将字符串(" + str + ")转换成指定格式(" + dateFormat +
			 * ")的日期时失败！错误原因：" + ex.getMessage());
			 */
			return null;
		}
	}

	/**
	 * 将当前日期转换成yyyyMMddHHmmss的字符串. 如：20071012141350
	 * 
	 * @return
	 */
	public static String currDateToStr() {
		return dateToStr(new Date(), "yyyyMMddHHmmss");
	}

	/**
	 * 将日期转换成 日期(yyyy-MM-dd)字符串.
	 *
	 * @param date
	 *            日期
	 * @return
	 */
	public static String dateTodateStr(final Date date) {
		return dateToStr(date, "yyyy-MM-dd");
	}

	/**
	 * 将日期转换成 yyyy-MM-dd HH:mm 字符串.
	 *
	 * @param date
	 *            日期
	 * @return
	 */
	public static String dateToStr(final Date date) {
		return dateToStr(date, "yyyy-MM-dd HH:mm");
	}

	/**
	 * 将数据库查询出来的日期进行转换
	 *
	 * @param list
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void getFormatDate(List<Map<String,Object>> list) {
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (Map<String, Object> item : list) {
			for (String key : item.keySet()) {
				Object value = item.get(key);
				if (value != null
						&& value.getClass() == Timestamp.class)
					item.put(key, fm.format(value));
			}
		}
	}

	/**
	 * 将日期转换成 yyyy-MM-dd HH:mm 日期类型.
	 *
	 * @param date
	 *            日期
	 * @return
	 */
	public static Date dateToDate(final Date date) {
		return dateToDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 数据库的日期类型转换成JAVA的DATE类型
	 *
	 * @param date
	 * @param dateFormat
	 * @return
	 */
	public static Date dateToDate(final Date date, String dateFormat) {
		if (date == null || "".equals(date)) {
			// log.debug("未知时间");
			return null;
			// return "未知时间";
		}
		SimpleDateFormat sf = new SimpleDateFormat(dateFormat);
		String time = sf.format(date);
		return Timestamp.valueOf(time);
	}

	/**
	 * 将日期转换成指定格式的字符串.
	 *
	 * @param date
	 *            日期
	 * @param dateFormat
	 *            日期格式. 如果为空，默认为:yyyy-MM-dd HH:mm:ss.
	 * @return
	 */
	public static String dateToStr(Date date, String dateFormat) {
		if (date == null || "".equals(date)) {
			// log.debug("未知时间");
			return "";
			// return "未知时间";
		}
		try {
			if (dateFormat == null || dateFormat.trim().length() == 0)
				dateFormat = "yyyy-MM-dd HH:mm:ss";
			if ("yyyy-MM-dd".equals(dateFormat))
				dateFormat = "yyyy-MM-dd";
			DateFormat fmt = new SimpleDateFormat(dateFormat.trim());

			return fmt.format(date);
		} catch (Exception ex) {
			 System.out.println("将日期转换成指定格式(" + dateFormat + ")的字符串时失败！错误原因：" +
			 ex.getMessage());

			return "";
			// return "日期格式不匹配";
		}
	}

	/**
	 * 返回减去指定天数的日期字符串. 获取计算后的日期：指定日期dateStr前day天
	 *
	 * @param date
	 *            将运算日期.
	 * @param day
	 *            减去的天数. return
	 */
	public static String subtractDateToString(final Date date, final int day) {
		try {
			Date tempDate = subtractDate(date, day);
			String dateStr = dateToStr(tempDate, null);
			return dateStr;
		} catch (Exception ex) {
			return "";
		}
	}

	/**
	 * 返回减去指定天数的日期.
	 *
	 * @param date
	 *            将运算日期.
	 * @param day
	 *            减去的天数. return
	 */
	public static Date subtractDate(final Date date, final int day) {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			int currDay = Calendar.DAY_OF_MONTH;
			calendar.set(currDay, calendar.get(currDay) - day); // 让日期减 day天

			return calendar.getTime();
		} catch (Exception ex) {

			return null;
		}
	}

	/**
	 * 返回减去指定小时数的日期.
	 *
	 * @param date
	 *            将运算日期.
	 * @param hour
	 *            减去的小时. return
	 */
	public static Date subtractTime(final Date date, final int hour) {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			int currHour = Calendar.HOUR_OF_DAY;
			calendar.set(currHour, calendar.get(currHour) - hour); // 让日期减 day天

			return calendar.getTime();
		} catch (Exception ex) {

			return null;
		}
	}

	// subtractDate

	/**
	 * 返回加上指定天数的日期.
	 *
	 * @param date
	 *            将运算日期.
	 * @param day
	 *            加上的天数. return
	 */
	public static Date plusDate(final Date date, final int day) {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			int currDay = Calendar.DAY_OF_MONTH;

			calendar.set(currDay, calendar.get(currDay) + day); // 让日期加 day天

			return calendar.getTime();
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 返回加上指定天数的日期字符串.
	 *
	 * @param date
	 *            将运算日期.
	 * @param day
	 *            加上的天数. return
	 */
	public static String plusDateToString(final Date date, final int day) {
		try {
			Date tempDate = subtractDate(date, day);
			String dateStr = dateToStr(tempDate, null);

			return dateStr;
		} catch (Exception ex) {
			return "";
		}
	}

	/**
	 * 取得指定日期date月份最大天数
	 * 
	 * @param date
	 * @return
	 */
	public static int getMaxDayOfMonth(Date date) {
		int max = 0;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 1);
		max = cal.get(Calendar.DAY_OF_MONTH);

		return max;
	}

	/**
	 * 计算数组的中年月日对应的天数,返回int
	 * 
	 * @param overplus
	 *            格式"X年X个月X天"
	 */
	public static int getDayForOverplus(String overplus,String sign) {
		Calendar c = getCalendarForOverplus(new Date(),overplus,sign);
		
		return getDateNumber(c);
	}

	/**
	 * 计算当前日期至此日期的天数差
	 * 
	 * @param c1
	 * @return
	 */
	public static int getDateNumber(Calendar c1) {
		long bts1, bts2;

		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(new Date());

		bts1 = c1.getTimeInMillis() - cal1.getTimeInMillis();
		bts2 = bts1 / (24 * 60 * 60 * 1000); // 结果也有可能超出int,故用long bts2

		return (int) bts2;
	}

	/**
	 * 计算两个时间（startDate,endDate）差值，并以数组形式返回差值，格式为resultArray[]={年,月,日}
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static int[] getDateArray(Date startDate, Date endDate) {
		int resultArray[] = new int[3]; // 存放结果，格式为resultArray[]={年,月,日}
		int day = 0;
		int month = 0;
		int year = 0;
		boolean dayFlag = false; // 日差为负标志，true：为负
		boolean monthFlag = false; // 月差为负标志，true：为负

		try {
			Calendar c1 = Calendar.getInstance();
			Calendar c2 = Calendar.getInstance();
			c1.setTime(startDate);
//			endDate = plusDate(endDate,1);	//加上结束时间当天
			c2.setTime(endDate);

			int startDay = c1.get(c1.DAY_OF_MONTH);
			int endDay = c2.get(c2.DAY_OF_MONTH);
			int startMonth = c1.get(c1.MONTH);
			int endMonth = c2.get(c2.MONTH);
			int startYear = c1.get(c1.YEAR);
			int endYear = c2.get(c2.YEAR);

			// 计算天数差
			int tempDay = endDay - startDay;
			if (tempDay < 0) {
				dayFlag = true;
				// day = getMaxDayOfMonth(startDate) - startDay + endDay;
				day = getMaxDayOfMonth(startDate) + tempDay;
			} else {
				day = tempDay;
			}

			// 计算月份差
			if (dayFlag)
				endMonth--;
			int tempMonth = endMonth - startMonth;
			if (tempMonth < 0) {
				monthFlag = true;
				month = 12 + tempMonth;
			} else {
				month = tempMonth;
			}

			// 计算年差
			if (monthFlag)
				endYear--;
			int tempYear = endYear - startYear;
			year = tempYear;

			// 封装结果
			resultArray[0] = year;
			resultArray[1] = month;
			resultArray[2] = day;

		} catch (Exception ex) {
			// log.error("计算时间段时失败！错误原因：" + ex.getMessage());
		}

		return resultArray;
	}
	
	/**
     * 计算两个日期之间相差的天数（有正负之分）
     * 
     * @param date1
     * @param date2
     * @return
     */
    public static int diffdates(Date date1, Date date2) {
        int result = 0;

        GregorianCalendar gc1 = new GregorianCalendar();
        GregorianCalendar gc2 = new GregorianCalendar();

        gc1.setTime(date1);
        gc2.setTime(date2);
        result = getDays(gc1, gc2);
        
        //date1大于date2时返回负数的天数
        if (gc1.after(gc2)) {
        	result = 0-result;
        }

        return result;
    } 


    public static int getDays(GregorianCalendar g1, GregorianCalendar g2) {
    	  int elapsed = 0;
    	  GregorianCalendar gc1, gc2;

    	  if (g2.after(g1)) {
    	   gc2 = (GregorianCalendar) g2.clone();
    	   gc1 = (GregorianCalendar) g1.clone();
    	  } else {
    	   gc2 = (GregorianCalendar) g1.clone();
    	   gc1 = (GregorianCalendar) g2.clone();
    	  }

    	  gc1.clear(Calendar.MILLISECOND);
    	  gc1.clear(Calendar.SECOND);
    	  gc1.clear(Calendar.MINUTE);
    	  gc1.clear(Calendar.HOUR_OF_DAY);

    	  gc2.clear(Calendar.MILLISECOND);
    	  gc2.clear(Calendar.SECOND);
    	  gc2.clear(Calendar.MINUTE);
    	  gc2.clear(Calendar.HOUR_OF_DAY);

    	  while (gc1.before(gc2)) {
    	   gc1.add(Calendar.DATE, 1);
    	   elapsed++;
    	  }
    	  return elapsed;
    	 }

    
    /**
     * 计算特定日期加上/减去数组的中年月日对应的日期        
     * @param baseDate
     * @param overplus 格式"X年X个月X天"
     * @param sign +(加上)，-(减去)
     * @return
     */
	public static Date getDateForOverplus(Date baseDate,String overplus,String sign) {
		return getCalendarForOverplus(baseDate,overplus,sign).getTime();
	}

	/**
	 * 计算特定日期加上/减去数组的中年月日对应Calendar类型  
	 * @param baseDate
	 * @param overplus
	 * @param sign
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static Calendar getCalendarForOverplus(Date baseDate,String overplus,String sign){
		// 解析传入X年X个月X天字符
		Calendar c1 = Calendar.getInstance();
		c1.setTime(baseDate);
		int year = 0;				//年
		int month = 0;				//月
		int day = 0;				//天
		int indexYear = overplus.indexOf("年");
		int indexMonth1 = overplus.indexOf("个");
		int indexMonth2 = overplus.indexOf("月");
		int indexDay = overplus.indexOf("天");
		boolean flag = false;
		
		try {
			
			if (indexYear != -1) {
				year = Integer.parseInt(overplus.substring(0,indexYear));
				flag = true;
			}
			if (indexMonth1 != -1) {
				month = Integer.parseInt(overplus.substring(indexYear+1,indexMonth1));
				flag = true;
			}
			if (indexDay != -1) {
				day = Integer.parseInt(overplus.substring(indexMonth2+1,indexDay));
				flag = true;
			}

			if (!flag)
				year = Integer.valueOf(overplus);
			
			System.out.println(year + "年" + month +"个月" + day + "天");
			
			//add
			if("+".equals(sign)){
				c1.set(c1.YEAR, c1.get(c1.YEAR) + year);
				c1.set(c1.MONTH, c1.get(c1.MONTH) + month);
				c1.set(c1.DAY_OF_MONTH, c1.get(c1.DAY_OF_MONTH) + day);
			}else if("-".equals(sign)){
				c1.set(c1.YEAR, c1.get(c1.YEAR) - year);
				c1.set(c1.MONTH, c1.get(c1.MONTH) - month);
				c1.set(c1.DAY_OF_MONTH, c1.get(c1.DAY_OF_MONTH) - day);
			}else
				System.out.println("ERROR:未计算！");
				
			
		} catch (Exception ex) {
			 System.out.println("计算时间段时失败！错误原因：" + ex.getMessage());
		}
		
		return c1;
	}
	
	
	 /**
     * 获得指定日期的前一天
     * 
     * @param specifiedDay
     * @return
     * @throws Exception
     */
    public static String getSpecifiedDayBefore(String specifiedDay) {
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

        String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c
                .getTime());
        return dayBefore;
    }
    
	/**
	 * 字符串转换为时间戳，只到秒
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static long strToLongDate(final String dateStr, String format) {
		long result = 0l;
		Date date = strToDate(dateStr, format);
		if (date != null) {
			result = date.getTime() / 1000;
		}
		return result;
	}
	
	/**
	 * 将时间搓转换成指定的时间格式字符串
	 * @param time  时间搓
	 * @param format 格式
	 * @return
	 */
	public static  String  Long2DateStr(Long  time,String format){
        SimpleDateFormat sdf=new SimpleDateFormat(format);
        String sd = sdf.format(new Date(time*1000));   // 时间戳转换成时间
        return sd;
		
	}
	
	
}
