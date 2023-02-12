package com.example.huangzhangmusic.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {

    /**
     * 获取歌曲时间 mm:ss
     * @param time
     * @return
     */
    public static String timeFormatMMSS(long time){

        SimpleDateFormat df=new SimpleDateFormat("mm:ss");
        Date date=new Date(time);
        String format = df.format(date);
        System.out.println("转换的时间==>"+format);
        return format;
    }
}
