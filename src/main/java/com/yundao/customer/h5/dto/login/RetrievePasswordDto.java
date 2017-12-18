package com.yundao.customer.h5.dto.login;

import io.swagger.annotations.ApiModelProperty;


public class RetrievePasswordDto {
    /**
	 * 手机号码
	 */
    @ApiModelProperty("手机号码")
    private String mobile;

    /**
	 * 验证码
	 */
    @ApiModelProperty("验证码")
    private String captcha;

    /**
     * 图形验证码
     */
    @ApiModelProperty("图形验证码")
    private String imageCaptcha;


    /**
	 * 密码
	 */
    @ApiModelProperty("密码")
    private String password;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImageCaptcha() {
        return imageCaptcha;
    }

    public void setImageCaptcha(String imageCaptcha) {
        this.imageCaptcha = imageCaptcha;
    }
}