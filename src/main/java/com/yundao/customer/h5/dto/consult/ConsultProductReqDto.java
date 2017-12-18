package com.yundao.customer.h5.dto.consult;


/**
 * 预约产品数据请求dto
 *
 * @author jan
 * @create 2017-09-07 PM3:57
 **/
public class ConsultProductReqDto extends BaseConsultProductReqDto {

    /**
     * 理财用户id
     */
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
