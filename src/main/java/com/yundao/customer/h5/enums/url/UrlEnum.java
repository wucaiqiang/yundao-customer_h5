package com.yundao.customer.h5.enums.url;

/**
 * url枚举
 *
 * @author jan
 * @create 2017-06-30 PM4:10
 **/
public enum UrlEnum {

    /**
     * 租户后置系统
     */
    TENANT_URL("tenant.url"),

    /**
     * 公共服务系统
     */
    SCM_URL("scm.url"),

    /**
     * 基础服务系统
     */
    BASE_URL("base.url");

    private String key;

    UrlEnum(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
