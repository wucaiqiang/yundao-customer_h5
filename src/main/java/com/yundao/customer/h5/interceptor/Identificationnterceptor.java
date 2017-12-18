package com.yundao.customer.h5.interceptor;

import com.yundao.core.code.Result;
import com.yundao.core.constant.CommonConstant;
import com.yundao.core.exception.BaseException;
import com.yundao.core.interceptor.spring.AbstractSpringInterceptor;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.core.log.Log;
import com.yundao.core.log.LogFactory;
import com.yundao.core.utils.BooleanUtils;
import com.yundao.core.utils.ConfigUtils;
import com.yundao.core.utils.CookieUtils;
import com.yundao.customer.h5.constant.CodeConstant;
import com.yundao.customer.h5.constant.url.TenantUrl;
import com.yundao.customer.h5.dto.login.UserAccountResDto;
import com.yundao.customer.h5.util.HttpUtils;
import com.yundao.customer.h5.util.SignUtils;
import io.swagger.models.auth.In;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 从头部获取、设置用户信息拦截器
 *
 * @author jan
 * @create 2017-06-22 PM2:23
 **/
public class Identificationnterceptor extends AbstractSpringInterceptor {

    private static Log log = LogFactory.getLog(Identificationnterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String userId = CookieUtils.getCookie(request, CommonConstant.USERID);
        log.info("userId:%s", userId);
        String ticket = CookieUtils.getCookie(request, CommonConstant.TICKET);
//
        if (BooleanUtils.isBlank(userId) || BooleanUtils.isBlank(ticket)) {
            log.info("跳转到登录页userId=" + userId + ",ticket=" + ticket);
            throw new BaseException(CodeConstant.CODE_1300006);
        }

        // 从cookie中获取是否校验
        long cookieTime = NumberUtils.toLong(CookieUtils.getCookie(request, CommonConstant.COOKIE_TIME));
        String cookieSign = CookieUtils.getCookie(request, CommonConstant.COOKIE_SIGN);

        long validSeconds = NumberUtils.toLong(ConfigUtils.getValue(CommonConstant.LOGIN_VALID_SECONDS, "600"));
        long now = System.currentTimeMillis();
//
        // 若强制校验或过期或签名失败
        int forceValidateTgt = NumberUtils.toInt(ConfigUtils.getValue(CommonConstant.FORCE_VALIDATE_TGT));

        if (forceValidateTgt == 1 || validSeconds * 1000 + cookieTime < now
                || !SignUtils.getSign(userId, ticket, cookieTime).equals(cookieSign)) {

            Result<UserAccountResDto> result = validateTicket(ticket);
            if (!result.getSuccess()) {
                log.info("用户登录失败userId=" + userId + ",ticket=" + ticket);
                throw new BaseException(CodeConstant.CODE_1300007);
            } else {
                // 保存成功后的信息
                now = System.currentTimeMillis();
                String sign = SignUtils.getSign(userId, ticket, now);
                CookieUtils.addCookie(response, CommonConstant.COOKIE_SIGN, sign);
                CookieUtils.addCookie(response, CommonConstant.COOKIE_TIME, String.valueOf(now));
                //更新ticket在验证ticket之后
//                updateTicketDate(ticket);
            }
        }
        return true;
    }

    /**
     * 更新ticket时间
     * @param ticket
     * @throws BaseException
     */
    private void updateTicketDate(String ticket) throws BaseException {
        Map<String,Object> params = new HashMap<>();
        params.put("ticket",ticket);
        HttpUtils.post(TenantUrl.TICKET_UPDATE_EXPIRETIME,params,new BaseTypeReference<Result<Integer>>(){});
    }

    private Result<UserAccountResDto> validateTicket(String ticket) throws Exception {
        Map<String, Object> methodParams = new HashMap<String, Object>();
        methodParams.put("ticket", ticket);
        Result<UserAccountResDto> result = HttpUtils.post(TenantUrl.TICKET_VALIDATE, methodParams,
                new BaseTypeReference<Result<UserAccountResDto>>() {
                });
        return result;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        long now = System.currentTimeMillis();
        long consumeTime = now - (Long) request.getAttribute(CommonConstant.BEGIN_TIME);
        log.info("now=" + now + ",consumeTime=" + consumeTime);
    }

}
