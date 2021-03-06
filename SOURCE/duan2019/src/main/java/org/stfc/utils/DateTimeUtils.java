package org.stfc.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Admin on 6/29/2017.
 */
public class DateTimeUtils {

    /*
    * @todo convest from date to string
    * @since 29/06/2017
    * @author viettx
     */
    private static final Logger logger = LoggerFactory.getLogger(DateTimeUtils.class);

    public static final String convestDateToString(Date pdtDateInput, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(pdtDateInput);
    }

    public static final Date convestStringToDate(String value, String pattern) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        try {
            Date date = dateFormat.parse(value);
            return date;
        } catch (ParseException px) {
            logger.error(px.getMessage(), px);
            throw px;
        }

    }

    /**
     *
     * @param value
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static final Long convestStringToTimestamp(String value, String pattern, int addDay) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        try {
            Date date = dateFormat.parse(value);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, addDay);
            return calendar.getTime().getTime();
        } catch (ParseException px) {
            logger.error(px.getMessage(), px);
            throw px;
        }

    }

    public static final Date convestTimestampToDate(Long timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);
        return calendar.getTime();

    }

    /**
     *
     * @param value
     * @return
     */
    public static String convertDate(String value) {

        String[] arr = value.split("/");

        String day = arr[0];
        String month = arr[1];
        String year = arr[2];
        if (Integer.parseInt(day) < 10) {
            day = "0" + Integer.parseInt(day);
        }
        if (Integer.parseInt(month) < 10) {
            month = "0" + Integer.parseInt(month);
        }

        return year + month + day;
    }

    public static Date addYear(Date currentDate, int year) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date truncYear(Date currentDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static void main(String[] args) {
        //1539881999000
        //1539795600000
        System.out.println(DateTimeUtils.truncYear(new Date()));
            try {
				System.out.println(DateTimeUtils.convestStringToDate("20190601000000", Contants.FORMAT_TIME));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
}
