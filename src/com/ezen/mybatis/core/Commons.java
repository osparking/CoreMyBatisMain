package com.ezen.mybatis.core;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Commons {
	final static DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public static String getY4MMDD(Date d) {
		String date = sdf.format(d);
		return date;
	}
	
	public static Date getDate(String dateString) {
		Date diedOn = null;
		try {
			diedOn = sdf.parse(dateString);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		return diedOn;
	}	
}
