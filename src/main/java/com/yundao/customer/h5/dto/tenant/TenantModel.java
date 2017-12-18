package com.yundao.customer.h5.dto.tenant;

import io.swagger.annotations.ApiModelProperty;

public class TenantModel {
    /**
	 * 名字
	 */
	@ApiModelProperty("租户id")
    private String name;

    /**
	 * 编码
	 */
    private String code;

    /**
	 * 类型
	 */
    private Integer type;

    /**
	 * 地址
	 */
    private String address;

    /**
	 * 排序，越小越靠前
	 */
    private Integer sequence;

    private static final long serialVersionUID = 1L;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
}