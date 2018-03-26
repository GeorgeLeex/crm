package com.neuedu.services.yx;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ddd {
public static void main(String[] args) throws ParseException {
	Date date=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	String s=sdf.format(date);
	System.out.println(sdf.format(date));
	//获取日期calendar函数
	Calendar c=Calendar.getInstance();
	c.setTime(date);
	//当前时间+10天
	c.add(Calendar.DAY_OF_MONTH, 10);
	Date t=c.getTime();
	System.out.println(sdf.format(t));
}
}
