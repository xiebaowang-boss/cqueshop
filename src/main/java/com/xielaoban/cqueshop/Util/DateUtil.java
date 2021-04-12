package com.xielaoban.cqueshop.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author 蟹老板
 * @Date 2021-3-30 21:23
 * @Version 1.0
 * @Package com.xielaoban.cqueshop.Util
 * @Description
 */
public class DateUtil {
    public static final SimpleDateFormat sdf = new SimpleDateFormat(" yyyy-MM-dd HH:mm:ss ");

    public static Date getCurrentDate() throws ParseException {
        Date date = new Date();//获得系统时间.
        String nowTime = sdf.format(date);
        Date time = sdf.parse(nowTime);
        return time;
    }

    public static String formatDateForOrder(Date date) {
        return sdf.format(date);
    }

    public static String formatDateForComment(Date date) {
        return sdf.format(date);
    }
}
