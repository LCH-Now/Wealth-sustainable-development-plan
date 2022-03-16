package com.crystal.feature.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author CHUNHAO LIU
 * 日期工具类
 */
public class DateUtil {


    /**
     * 日期转化为具体的格式YYYY-MM-dd
     * @param date
     */
    public static String modifyFormat(Date date)
    {
        //获得当前时间
        SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd");
        String currentTime = df.format(new Date());

        return currentTime;
    }

}
