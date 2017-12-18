package com.yundao.customer.h5.util;

import org.junit.Assert;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类单元测试
 */
public class DateUtilsTest {
    @Test
    public void formatExactMinute() throws Exception {
        String strDate = "2017-08-08 18:30:10";
        String result = DateUtils.formatExactMinute(strDate);
        Assert.assertEquals("2017-08-08 18:30", result);
    }

    @Test
    public void formatExactMinute1() throws Exception {
        DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date myDate = dateFormat2.parse("2017-08-08 18:30:12");
        String myDateStr = DateUtils.formatExactMinute(myDate);
        Assert.assertEquals("2017-08-08 18:30", myDateStr);
    }

    @Test
    public void formatExactDay() throws Exception {
        String strDate = "2017-08-08 18:30:10";
        String result = DateUtils.formatExactDay(strDate);
        Assert.assertEquals("2017-08-08", result);
    }

    @Test
    public void formatExactDay1() throws Exception {
        DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date myDate = dateFormat2.parse("2017-08-08 18:30:12");
        String myDateStr = DateUtils.formatExactDay(myDate);
        Assert.assertEquals("2017-08-08", myDateStr);
    }

    @Test
    public void toEndDate() throws Exception {
        String endDateStr = "2017-08-08";
        String dateStr = DateUtils.toEndDate(endDateStr);
        Assert.assertEquals("2017-08-08 23:59:59", dateStr);
    }

}