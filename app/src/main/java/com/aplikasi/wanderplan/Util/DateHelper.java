package com.aplikasi.wanderplan.Util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateHelper {
    public static String getHumanReadableDate(Date inputDate) {
        long now = new Date().getTime();
        long inputTime = inputDate.getTime();
        long difference = now - inputTime;

        if (difference < 60000) {  // Below 1 minute
            long seconds = TimeUnit.MILLISECONDS.toSeconds(difference);
            if(seconds <= 0)
                return "baru saja";
            else
                return seconds + " detik yang lalu";
        } else if (difference < 3600000) {  // Below 1 hour
            long minutes = TimeUnit.MILLISECONDS.toMinutes(difference);
            return minutes + " menit yang lalu";
        } else if (difference < 86400000) {  // Below 1 day
            long hours = TimeUnit.MILLISECONDS.toHours(difference);
            return hours + " jam yang lalu";
        } else if (difference < 604800000) {  // Below 1 week
            long days = TimeUnit.MILLISECONDS.toDays(difference);
            if (days <= 1) {
                return "kemarin";
            } else {
                return days + " hari yang lalu";
            }
        } else if (difference < 2592000000L) {  // Below 1 month
            long weeks = TimeUnit.MILLISECONDS.toDays(difference) / 7;
            return weeks + " minggu yang lalu";
        } else if (difference < 31536000000L) {  // Below 1 year
            long months = TimeUnit.MILLISECONDS.toDays(difference) / 30;
            return months + " bulan yang lalu";
        } else {  // More or equal to 1 year
            long years = TimeUnit.MILLISECONDS.toDays(difference) / 365;
            return years + " tahun yang lalu";
        }
    }

}
