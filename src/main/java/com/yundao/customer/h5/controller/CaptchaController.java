package com.yundao.customer.h5.controller;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.core.validator.spring.BindingResultHandler;
import com.yundao.customer.h5.constant.captcha.CaptchaImgType;
import com.yundao.customer.h5.dto.captcha.CaptchaCheckReqDto;
import com.yundao.customer.h5.service.captcha.CaptchaImgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * 验证码
 *
 * @author jan
 * @create 2017-09-11 PM4:51
 **/
@Controller
@RequestMapping("/captcha")
@ResponseBody
@Api("验证码")
public class CaptchaController {

    @Autowired
    private CaptchaImgService captchaImgService;

    @RequestMapping(value = "/img/get_for_register", method = RequestMethod.GET)
    @ApiOperation("获取注册页面图片验证码")
    public Result<String> getRegisterImgCode(@RequestParam String mobile, HttpServletResponse response) throws BaseException {
        return captchaImgService.getCode(mobile, CaptchaImgType.REGISTER, response);
    }

    @RequestMapping(value = "/img/get_for_retrieve_pwd", method = RequestMethod.GET)
    @ApiOperation("获取找回密码页面图片验证码")
    public Result<String> getRetrieveImgCode(@RequestParam String mobile, HttpServletResponse response) throws BaseException {
        return captchaImgService.getCode(mobile, CaptchaImgType.RETRIEVE_PWD, response);
    }

    @RequestMapping(value = "/img/check_for_register", method = RequestMethod.POST)
    @ApiOperation("校验注册页面图片验证码")
    public Result<Boolean> checkRegisterImgCode(@Validated @ModelAttribute CaptchaCheckReqDto dto, BindingResult bindingResult) throws BaseException {
        BindingResultHandler.handleByException(bindingResult);
        return Result.newSuccessResult(captchaImgService.checkCode(dto, CaptchaImgType.REGISTER));
    }

    @RequestMapping(value = "/img/check_for_retrieve_pwd", method = RequestMethod.POST)
    @ApiOperation("校验找回密码页面图片验证码")
    public Result<Boolean> checkRetrieveImgCode(@Validated @ModelAttribute CaptchaCheckReqDto dto, BindingResult bindingResult) throws BaseException {
        BindingResultHandler.handleByException(bindingResult);
        return Result.newSuccessResult(captchaImgService.checkCode(dto, CaptchaImgType.RETRIEVE_PWD));
    }

}
