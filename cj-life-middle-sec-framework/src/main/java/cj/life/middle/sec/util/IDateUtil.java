package cj.life.middle.sec.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public interface IDateUtil {
    static String toDateEndSecond(long time) {
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(date);
    }

    static String toDateEndMillisSecond(long time) {
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return sdf.format(date);
    }

    static Date fromDate(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        try {
            return sdf.parse(time);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
