package com.yundao.customer.h5.dto.consult;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author jan
 * @create 2017-09-12 AM10:35
 **/
public class BaseConsultProductReqDto {

    @ApiModelProperty(value = "产品id")
    private Long productId;

    @ApiModelProperty(value = "渠道")
    private Integer channel;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

}
