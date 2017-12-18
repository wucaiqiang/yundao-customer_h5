package com.yundao.customer.h5.dto.captcha;

import com.yundao.customer.h5.constant.CodeConstant;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author jan
 * @create 2017-09-11 PM5:42
 **/
public class CaptchaCheckReqDto {

    @ApiModelProperty(value = "手机号码")
    @NotEmpty(message = "{" + CodeConstant.CODE_1300005 + "}")
    private String mobile;

    @ApiModelProperty(value = "验证码")
    @NotEmpty(message = "{" + CodeConstant.CODE_1300003 + "}")
    private String imageCaptcha;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getImageCaptcha() {
        return imageCaptcha;
    }

    public void setImageCaptcha(String imageCaptcha) {
        this.imageCaptcha = imageCaptcha;
    }
}
