package com.healthcare.hibernate.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Format {

	public static boolean isStringEmptyORNull(String input){
		
		if(input==null){
			return true;
		}else if(input.trim().equals("")){
			return true;
		}
		
		return false;
	}
	
	public static boolean isDateNull(Date input){
			
			if(input==null){
				return true;
			}
			
			return false;
	}
	
	public static Date getDateDDMMYYYYHHMM(String input){
		
		System.out.println("input :" +input);
		Date date=new Date();
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date=sdf.parse(input);
			System.out.println("date :"+date);
		} catch (ParseException e) {
			System.out.println("Not parsable String : "+input);
			e.printStackTrace();
		}
		
		return date;
 }
	
public static Date getDateYYYYMMDD(String input){
		
		System.out.println("input :" +input);
		Date date=new Date();
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		try {
			date=sdf.parse(input);
			System.out.println("date :"+date);
		} catch (ParseException e) {
			System.out.println("Not parsable String : "+input);
			e.printStackTrace();
		}
		
		return date;
 }
	
	
public static String getStringDDMMYYYYHHMM(Date input){
		
		System.out.println("input :" +input);
		String date="";
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		date=sdf.format(input);
		System.out.println("date :"+date);
		
		return date;
 }

public static String getStringYYYYMMDD(Date input){
	
	System.out.println("input :" +input);
	String date="";
	SimpleDateFormat sdf=null;
	if(input!=null){
	 sdf=new SimpleDateFormat("yyyy-MM-dd");
	 date=sdf.format(input);
	}
	
	System.out.println("date :"+date);
	
	return date;
}
	
	
	
	
}
