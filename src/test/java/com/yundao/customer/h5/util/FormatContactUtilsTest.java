package com.yundao.customer.h5.util;

import org.junit.Assert;
import org.junit.Test;

public class FormatContactUtilsTest {

    @Test
    public void formatMobile() throws Exception {
        String mobile = "13590340548";
        Boolean isCustomer = true;
        Boolean isContact = true;
        String result1 = FormatContactUtils.formatMobile(isCustomer, isContact, mobile);
        Assert.assertEquals("13590340548", result1);

        isContact = false;
        String result2 = FormatContactUtils.formatMobile(isCustomer, isContact, mobile);
        Assert.assertEquals("135****0548", result2);

        isCustomer=false;
        isContact = false;
        String result3 = FormatContactUtils.formatMobile(isCustomer, isContact, mobile);
        Assert.assertEquals("***", result3);

        isCustomer=false;
        isContact = true;
        String result4 = FormatContactUtils.formatMobile(isCustomer, isContact, mobile);
        Assert.assertEquals("13590340548", result4);
    }

}