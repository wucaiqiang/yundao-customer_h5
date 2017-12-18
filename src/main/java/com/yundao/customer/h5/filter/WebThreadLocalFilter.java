package com.yundao.customer.h5.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import com.yundao.core.constant.CommonConstant;
import com.yundao.core.threadlocal.filter.ThreadLocalFilter;
import com.yundao.core.utils.CookieUtils;

/**
 * 本地线程过滤器
 *
 * @author jan
 * @create 2017-06-30 PM6:17
 **/
@Component
@WebFilter(filterName = "webThreadLocalFilter", urlPatterns = "/*")
public class WebThreadLocalFilter extends ThreadLocalFilter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	HttpServletRequest httpRequest = (HttpServletRequest) request;
    	String tenantId = CookieUtils.getCookie(httpRequest, CommonConstant.TENANT_ID);
    	MDC.put(CommonConstant.TENANT_ID, tenantId);
    	super.doFilter(request, response, chain);
    }
}
