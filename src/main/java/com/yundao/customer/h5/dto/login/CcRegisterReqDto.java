package com.yundao.customer.h5.dto.login;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by gjl on 2017/9/11.
 */
public class CcRegisterReqDto {
    /**
     * 手机号码
     */
    @ApiModelProperty("手机号码")
    private String mobile;

    /**
     * 图形验证码
     */
    @ApiModelProperty("图形验证码")
    private String imageCaptcha;

    /**
     * 验证码
     */
    @ApiModelProperty("验证码")
    private String captcha;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("昵称")
    private String realName;
    /**
     * 理财师uuid
     */
    @ApiModelProperty("理财师uuid")
    private String afpUUID;


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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getImageCaptcha() {
        return imageCaptcha;
    }

    public void setImageCaptcha(String imageCaptcha) {
        this.imageCaptcha = imageCaptcha;
    }

	public String getAfpUUID() {
	
		return afpUUID;
	}

	public void setAfpUUID(String afpUUID) {
	
		this.afpUUID = afpUUID;
	}
    
}
