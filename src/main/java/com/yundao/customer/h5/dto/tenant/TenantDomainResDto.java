package com.yundao.customer.h5.dto.tenant;


import java.io.Serializable;

/**
 * 租户域名dto
 *
 * @author jan
 * @create 2017-07-21 AM10:56
 **/
public class TenantDomainResDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 租户id
     */
    private Long id;

    /**
     * 域名链接
     */
    private String url;

    /**
     * 系统编码
     */
    private String systemCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }
}