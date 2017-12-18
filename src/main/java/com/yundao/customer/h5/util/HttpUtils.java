package com.yundao.customer.h5.util;


import com.yundao.core.code.Result;
import com.yundao.core.code.config.CommonCode;
import com.yundao.core.constant.CommonConstant;
import com.yundao.core.constant.HeaderConstant;
import com.yundao.core.constant.MethodConstant;
import com.yundao.core.enums.AppTypeEnum;
import com.yundao.core.exception.BaseException;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.core.log.Log;
import com.yundao.core.log.LogFactory;
import com.yundao.core.threadlocal.ThreadLocalUtils;
import com.yundao.core.threadlocal.filter.RequestCommonParams;
import com.yundao.core.utils.BooleanUtils;
import com.yundao.core.utils.EDUtils;
import com.yundao.core.utils.JsonUtils;
import com.yundao.customer.h5.constant.RequestHeaderConstant;
import com.yundao.core.dto.HeaderUser;

import java.util.HashMap;
import java.util.Map;

/**
 * HTTP调用工具类
 *
 * @author jan
 * @create 2017-06-21 PM5:16
 **/
public class HttpUtils {

    private static Log log = LogFactory.getLog(HttpUtils.class);

    /**
     * 执行post方法
     *
     * @param url          url地址
     * @param methodParams 键值形式参数
     * @return 返回结果
     * @throws BaseException 异常信息
     */
    public static <T> Result<T> post(String url, Map<String, Object> methodParams, BaseTypeReference<Result<T>> typeReference) throws BaseException {
        return doExecute(url, null, methodParams, typeReference, MethodConstant.POST);
    }

    /**
     * 执行post方法
     *
     * @param url          url地址
     * @param methodParams 键值形式参数
     * @return 返回结果
     * @throws BaseException 异常信息
     */
    public static <T> Result<T> post(String url, Map<String, Object> headerParams, Map<String, Object> methodParams, BaseTypeReference<Result<T>> typeReference)
            throws BaseException {
        return doExecute(url, headerParams, methodParams, typeReference, MethodConstant.POST);
    }

    /**
     * 执行get方法 ;
     *
     * @param url           url地址
     * @param methodParams  键值形式参数
     * @param typeReference 返回类型信息
     * @return 返回结果
     * @throws BaseException 异常信息
     */
    public static <T> Result<T> get(String url, Map<String, Object> methodParams, BaseTypeReference<Result<T>> typeReference) throws BaseException {
        return doExecute(url, null, methodParams, typeReference, MethodConstant.GET);
    }


    /**
     * 执行get方法 ;
     *
     * @param url          url地址
     * @param methodParams 键值形式参数
     * @return 返回结果
     * @throws BaseException 异常信息
     */
    public static <T> Result<T> get(String url, Map<String, Object> headerParams, Map<String, Object> methodParams, BaseTypeReference<Result<T>> typeReference)
            throws BaseException {
        return doExecute(url, headerParams, methodParams, typeReference, MethodConstant.GET);
    }


    /**
     * 获取头部参数
     *
     * @return 头部参数
     */
    public static Map<String, String> getHeaderParams() {
        HeaderUser headerUser = (HeaderUser) ThreadLocalUtils.getFromRequest(CommonConstant.HEADER_USER);
        Map<String, String> result = new HashMap<>();
        if (headerUser != null) {
            result.put(RequestHeaderConstant.HEADER_USER_ID, String.valueOf(headerUser.getUserId()));
            result.put(RequestHeaderConstant.HEADER_TENANT_ID, headerUser.getTenantId().toString());
            result.put(RequestHeaderConstant.HEADER_REAL_NAME, EDUtils.encode(headerUser.getRealName()));
        }
        result.put(HeaderConstant.HEADER_APP_TYPE, AppTypeEnum.C_PC.getType());
        return result;
    }


    /**
     * http请求统一调用
     *
     * @param url           url地址
     * @param headerParams  头部参数
     * @param methodParams  方法参数
     * @param typeReference 返回类型
     * @param method        请求方式
     * @param <T>           返回类型
     * @return 返回结果
     * @throws BaseException 异常信息
     */
    private static <T> Result<T> doExecute(String url, Map<String, Object> headerParams, Map<String, Object> methodParams, BaseTypeReference<Result<T>> typeReference,
                                           String method) throws BaseException {
        try {
            url = url.trim();
            RequestCommonParams commonParams = RequestCommonParams.newDefault();
            commonParams.setMethodParams(methodParams);

            commonParams.setHeaderParams(getHeaderParams());

            if (null != headerParams && !headerParams.isEmpty()) {
                for (String name : headerParams.keySet()) {
                    String value = String.valueOf(headerParams.get(name));
                    if (!BooleanUtils.isBlank(name) && !BooleanUtils.isBlank(value)) {
                        commonParams.addHeaderParams(name, value);
                    }
                }
            }
            log.info("头部参数headerParams=" + commonParams.getHeaderParams() + ",方法参数methodParams=" + methodParams);

            String response;
            if (MethodConstant.POST.equals(method)) response = com.yundao.core.utils.HttpUtils.post(url, commonParams);
            else response = com.yundao.core.utils.HttpUtils.get(url, commonParams);
            return JsonUtils.jsonToObject(response, typeReference);
        } catch (Exception e) {
            log.error("调用链接时异常", e);
            throw new BaseException(CommonCode.COMMON_1007, e);
        }
    }

}
