package com.demo.lyl.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public String AddDay(String date,int amount){
        Date sDate = null;
        String rdate = "";
        try {
            sDate = sdf.parse(date);
            Calendar c = Calendar.getInstance();
            c.setTime(sDate);
            c.add(Calendar.DAY_OF_MONTH,amount);
            sDate = c.getTime();
            rdate = sdf.format(sDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rdate;
    }

}
