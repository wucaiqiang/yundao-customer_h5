package com.yundao.customer.h5.cache;

/**
 * 缓存key
 *
 * @author jan
 * @create 2017-07-02 PM1:42
 **/
public class CacheKey {
    static final String CUSTOMER_H5_PREFIX = "customer_h5:";
    /**
     * 租户域名列表
     */
    public static final String TENANT_DOMAINS = CUSTOMER_H5_PREFIX + "tenant_domains";

    public static String getCaptcha(Long tenantId, String type, String mobile) {
        return CUSTOMER_H5_PREFIX + "com.yundao.customer.h5.captchServiceImpl[" + tenantId + "," + type + "," + mobile + "]";
    }
}
