
package com.yundao.customer.h5.controller.login;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.yundao.core.code.Result;
import com.yundao.core.constant.CommonConstant;
import com.yundao.core.constant.HeaderConstant;
import com.yundao.core.dto.login.UserLoginReqDto;
import com.yundao.core.dto.login.UserLoginResDto;
import com.yundao.core.enums.AppTypeEnum;
import com.yundao.core.exception.BaseException;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.core.utils.CookieUtils;
import com.yundao.customer.h5.constant.CodeConstant;
import com.yundao.customer.h5.constant.captcha.CaptchaImgType;
import com.yundao.customer.h5.constant.url.TenantUrl;
import com.yundao.customer.h5.dto.login.CcRegisterReqDto;
import com.yundao.customer.h5.dto.login.RetrievePasswordDto;
import com.yundao.customer.h5.dto.login.RsaPublicResDto;
import com.yundao.customer.h5.dto.login.UserUpdatePasswordReqDto;
import com.yundao.customer.h5.service.captcha.CaptchaImgService;
import com.yundao.customer.h5.service.login.LoginService;
import com.yundao.customer.h5.util.ArgsUtils;
import com.yundao.customer.h5.util.HttpUtils;
import com.yundao.customer.h5.util.SignUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.http.util.Args;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Function: Reason: Date: 2017年8月1日 下午3:59:26
 *
 * @author wucq
 */
@Controller
@ResponseBody
@RequestMapping("/user")
@Api("登录")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private CaptchaImgService captchaImgService;

    /**
     * 获取公钥 getKeyPairPublic:
     *
     * @author: wucq
     * @return
     * @throws Exception
     * @description:
     */
    @RequestMapping(value = "/get_key_public", method = RequestMethod.GET)
    @ApiOperation(value = "获取密钥对", notes = "获取密钥对")
    public Result<RsaPublicResDto> getKeyPairPublic() throws Exception {
        return loginService.selectKeyPairPublic();
    }

    /**
     * 分页产品产品管理列表
     *
     * @param userLoginReqDto
     * @return
     * @throws BaseException
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "登录", notes = "登录")
    public Result<UserLoginResDto> login(@ModelAttribute UserLoginReqDto userLoginReqDto,HttpServletResponse response) throws BaseException {
        Result<UserLoginResDto> result =  HttpUtils.get(TenantUrl.USER_LOGIN, ArgsUtils.toMap(userLoginReqDto), new BaseTypeReference<Result<UserLoginResDto>>() {
        });
        if(result.getSuccess()) {
            UserLoginResDto dto = result.getResult();
            CookieUtils.addCookie(response, CommonConstant.USERID, dto.getId().toString());
            CookieUtils.addCookie(response, CommonConstant.TICKET, dto.getTicket());
//            CookieUtils.addCookie(response, CommonConstant.REAL_NAME, dto.getRealName());
//            CookieUtils.addCookie(response, CommonConstant.TENANT_ID, dto.getTenantId().toString());
            CookieUtils.addCookie(response, HeaderConstant.HEADER_APP_TYPE, AppTypeEnum.C_PC.getType());
            long now = System.currentTimeMillis();
            String sign = SignUtils.getSign(String.valueOf(dto.getId()), dto.getTicket(), now);
            CookieUtils.addCookie(response, CommonConstant.COOKIE_SIGN, sign);
            CookieUtils.addCookie(response, CommonConstant.COOKIE_TIME, String.valueOf(now));
        }
        return result;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ApiOperation(value = "登出", notes = "登出")
    public Result<Boolean> loginOut() throws BaseException {
        return HttpUtils.post(TenantUrl.USER_LOGIN_OUT, null, new BaseTypeReference<Result<Boolean>>() {
        });
    }

    @RequestMapping(value = "/update_password", method = RequestMethod.POST)
    @ApiOperation(value = "修改密码", notes = "修改密码")
    public Result<Integer> updateUserPassword(@ModelAttribute UserUpdatePasswordReqDto reqDto) throws BaseException {
        return HttpUtils.post(TenantUrl.USER_UPDATE_PASSWORD, ArgsUtils.toMap(reqDto), new BaseTypeReference<Result<Integer>>() {
        });
    }

    @RequestMapping(value = "/send_retrieve_password", method = RequestMethod.POST)
    @ApiOperation(value = "发送找回密码验证码", notes = "发送找回密码验证码")
    public Result<Integer> sendRetrievePassword(@RequestParam String mobile,@RequestParam String imageCaptcha) throws Exception {
        captchaImgService.checkCode(mobile,imageCaptcha,CaptchaImgType.RETRIEVE_PWD);
        Map<String,Object> params = new HashMap<>();
        params.put("mobile",mobile);
        return HttpUtils.post(TenantUrl.SEND_RETRIEVE_CAPTCHA, params, new BaseTypeReference<Result<Integer>>() {
        });
    }

    @RequestMapping(value = "/send_register_captcha", method = RequestMethod.POST)
    @ApiOperation(value = "发送注册验证码", notes = "发送注册验证码")
    public Result<Integer> sendRegisterCaptcha(@RequestParam String mobile,@RequestParam String imageCaptcha) throws Exception {
        captchaImgService.checkCode(mobile,imageCaptcha,CaptchaImgType.REGISTER);
        Map<String,Object> params = new HashMap<>();
        params.put("mobile",mobile);
        return HttpUtils.post(TenantUrl.SEND_REGISTER_CAPTCHA, params, new BaseTypeReference<Result<Integer>>() {
        });
    }

    @RequestMapping(value = "/retrieve_password", method = RequestMethod.POST)
    @ApiOperation(value = "找回密码", notes = "找回密码")
    public Result<Integer> retrievePassword(@ModelAttribute RetrievePasswordDto retrievePasswordDto) throws Exception {
        boolean checkCode = captchaImgService.checkCode(retrievePasswordDto.getMobile(),retrievePasswordDto.getImageCaptcha(), CaptchaImgType.RETRIEVE_PWD);
        if(!checkCode){
            throw new BaseException(CodeConstant.CODE_1300004);
        }
        Result<Integer> result = HttpUtils.post(TenantUrl.RETRIEVE_PASSWORD, ArgsUtils.toMap(retrievePasswordDto), new BaseTypeReference<Result<Integer>>() {
        });
        if(result.getSuccess()){
            captchaImgService.remove(retrievePasswordDto.getMobile(),CaptchaImgType.RETRIEVE_PWD);
        }
        return result;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ApiOperation(value = "注册", notes = "注册")
    public Result<UserLoginResDto> register(@ModelAttribute CcRegisterReqDto reqDto, HttpServletResponse response) throws Exception {
        //验证图片验证码是否正确
        boolean checkCode = captchaImgService.checkCode(reqDto.getMobile(),reqDto.getImageCaptcha(), CaptchaImgType.REGISTER);
        if(!checkCode){
            throw new BaseException(CodeConstant.CODE_1300004);
        }
        //固定渠道是服务号
        Map<String,Object> params = ArgsUtils.toMap(reqDto);
        params.put("channel",2);
        Result<Map<String, Object>> result =  HttpUtils.post(TenantUrl.REGISTER, params, new BaseTypeReference<Result<Map<String, Object>>>() {
        });
        if(result.getSuccess()){
            captchaImgService.remove(reqDto.getMobile(),CaptchaImgType.RETRIEVE_PWD);
            //登入
            UserLoginReqDto userLoginReqDto = new UserLoginReqDto();
            userLoginReqDto.setUserName(reqDto.getMobile());
            userLoginReqDto.setPassword(reqDto.getPassword());
            return login(userLoginReqDto,response);
        }
        return Result.newFailureResult(result.getCode(),result.getMessage(),null);
    }
    @RequestMapping(value = "/validate_mobile", method = RequestMethod.POST)
    @ApiOperation(value = "验证手机号是存在", notes = "验证手机号是存在")
    public Result<Boolean> validateMobile(@RequestParam String mobile) throws Exception{
        Map<String,Object> params = new HashMap<>();
        params.put("mobile",mobile);
        return HttpUtils.post(TenantUrl.VALIDATE_MOBILE, params,new BaseTypeReference<Result<Boolean>>() {
        });
    }
}
