package com.yundao.customer.h5.interceptor;

import com.yundao.core.constant.CommonConstant;
import com.yundao.core.constant.StaticValue;
import com.yundao.core.log.Log;
import com.yundao.core.log.LogFactory;
import com.yundao.core.threadlocal.ThreadLocalUtils;
import com.yundao.core.threadlocal.filter.RequestCommonParams;
import com.yundao.core.utils.*;
import com.yundao.core.dto.HeaderUser;
import com.yundao.customer.h5.dto.tenant.TenantDomainResDto;
import com.yundao.customer.h5.util.TenantUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 从头部获取、设置用户信息拦截器
 *
 * @author jan
 * @create 2017-06-30 PM6:11
 **/
public class HeaderUserInterceptor extends Identificationnterceptor {

    private static Log log = LogFactory.getLog(HeaderUserInterceptor.class);

    private String excludeUrl = null;

    /**
     * 请求进入action前拦截处理
     *
     * @param request  请求
     * @param response 响应
     * @param handler  对象
     * @return 结果
     * @throws Exception 异常信息
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String uri = request.getRequestURI();
        if(!excludeUrl.contains(uri)) {
            super.preHandle(request, response, handler);
        }
        RequestCommonParams requestCommonParams = ThreadLocalUtils.getRequestCommonParams();
        log.info("requestCommonParams:%s", JsonUtils.objectToJson(requestCommonParams));
        String url = RequestUtils.getRequestUrl(request);
        String ip = "";
        if (requestCommonParams != null) {
            requestCommonParams.getIp();
        }
        String systemId = ConfigUtils.getValue(CommonConstant.ID);
        HeaderUser headerUser;
        // 获取用户的登录信息
        String userId = CookieUtils.getCookie(request, CommonConstant.USERID);
        log.info("userId:%s", userId);

        TenantDomainResDto tenant = TenantUtils.getTenant(request.getServerName(),
                ((Integer) request.getServerPort()).toString());

        String ticket = CookieUtils.getCookie(request, CommonConstant.TICKET);
        String realName = CookieUtils.getCookie(request, CommonConstant.REAL_NAME);

        headerUser = new HeaderUser();
        headerUser.setTenantId(tenant.getId());
        if (!BooleanUtils.isBlank(userId)) {
            headerUser.setUserId(NumberUtils.toLong(userId));
        }
        headerUser.setRealName(realName);

        request.setAttribute(CommonConstant.HEADER_USER, headerUser);
        request.setAttribute(CommonConstant.TICKET, ticket);
        request.setAttribute(CommonConstant.CONTEXT_PATH, StaticValue.contextPath);
        request.setAttribute(CommonConstant.IP, ip);
        request.setAttribute(CommonConstant.BEGIN_TIME, System.currentTimeMillis());
        log.info("ip=" + ip + ",url=" + url + ",systemId=" + systemId + ",userId=" + userId);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        long now = System.currentTimeMillis();
        long consumeTime = now - (Long) request.getAttribute(CommonConstant.BEGIN_TIME);
        log.info("now=" + now + ",consumeTime=" + consumeTime);
    }

    public String getExcludeUrl() {
        return excludeUrl;
    }

    public void setExcludeUrl(String excludeUrl) {
        this.excludeUrl = excludeUrl;
    }
}
