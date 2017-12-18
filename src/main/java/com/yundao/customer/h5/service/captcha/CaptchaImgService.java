package com.yundao.customer.h5.service.captcha;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.customer.h5.dto.captcha.CaptchaCheckReqDto;

import javax.servlet.http.HttpServletResponse;

/**
 * 验证码服务接口
 *
 * @author jan
 * @create 2017-09-11 PM5:08
 **/
public interface CaptchaImgService {

    /**
     * 移除图片验证码
     *
     * @param mobile 手机号码
     * @param type   类型，值请参考 CaptchaImgType 常量文件
     */
    Boolean remove(String mobile, String type) throws BaseException;

    /**
     * 校验图片验证码
     *
     * @param dto  入参
     * @param type 类型，值请参考 CaptchaImgType 常量文件
     */
    Boolean checkCode(CaptchaCheckReqDto dto, String type) throws BaseException;

    /**
     * 校验图片验证码
     *
     * @param mobile       手机号码
     * @param imageCaptcha 验证码
     * @param type         类型，值请参考 CaptchaImgType 常量文件
     */
    Boolean checkCode(String mobile, String imageCaptcha, String type) throws BaseException;

    /**
     * 获取图片验证码
     *
     * @param mobile 手机号码
     * @param type   类型，值请参考 CaptchaImgType 常量文件
     */
    Result<String> getCode(String mobile, String type, HttpServletResponse response) throws BaseException;

}
