package com.yundao.customer.h5.util;

import com.yundao.core.utils.BooleanUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 客户联系方式格式转换工具类
 *
 * @author jan
 * @create 2017-08-10 PM5:21
 **/
public class FormatContactUtils {

    private static final String ENCRYPT_STR = "***";

    /**
     * 根据权限，格式化客户手机号
     *
     * @param isCustomerReadPermission 是否具有读取客户信息权限
     * @param isContactReadPermission  是否具有读取客户联系方式权限
     * @param mobile                   手机号码
     * @return
     */
    public static String formatMobile(Boolean isCustomerReadPermission, Boolean isContactReadPermission, String
            mobile) {
        if (BooleanUtils.isEmpty(mobile))
            return ENCRYPT_STR;
        //两种权限都没有
        if (!isCustomerReadPermission && !isContactReadPermission)
            return ENCRYPT_STR;
        //有客户权限，没有联系方式权限
        if (isCustomerReadPermission && !isContactReadPermission)
            return mobileEncrypt(mobile);
        return mobile;
    }

    private static String mobileEncrypt(String mobile) {
        if (StringUtils.isEmpty(mobile)) {
            return mobile;
        }
        int len = mobile.length();
        if (len == 11) {
            return mobile.substring(0, mobile.length() - (mobile.substring(3)).length()) + "****" + mobile.substring(7);
        }
        return mobile;
    }

}
