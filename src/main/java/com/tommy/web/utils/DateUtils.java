package com.tommy.web.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/**
 * 常用的时间操作(提供sceonds、Date、str互相转换的方法):
 * sceondsToDate\secondsToStr
 * strToDate\strToSeconds
 * dateToSeconds\dateToStr
 * 
 * @author Tommy Lau
 */
public class DateUtils {
	
	public static int currentTimestamp() {
		return (int)(new Date().getTime()/1000);
	}
	
	public static Date sceondsToDate(Integer seconds){
		return new Timestamp(seconds.longValue() * 1000);
	}
	
	public static Date strToDate(String dateString, String pattern) throws ParseException {
		if(StringUtils.isBlank(dateString)) return null;
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date date = format.parse(dateString);
		return date;
	}
	
	public static Integer strToSeconds(String dateString, String pattern) throws ParseException{
		Date date = DateUtils.strToDate(dateString, pattern);
		return DateUtils.dateToSeconds(date);
	}
	
	public static Integer dateToSeconds(Date date){
		return (int)(date.getTime()/1000);
	}
	
	public static String dateToStr(Date date, String pattern) {
		if(date == null){
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}
	
	public static String secondsToStr(Integer seconds, String pattern) {
		Date date = DateUtils.sceondsToDate(seconds);
		return DateUtils.dateToStr(date, pattern);
	}
	
}
