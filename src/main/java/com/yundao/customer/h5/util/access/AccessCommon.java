package com.yundao.customer.h5.util.access;

import com.yundao.core.code.Result;
import com.yundao.core.code.config.CommonCode;
import com.yundao.core.exception.BaseException;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.core.log.Log;
import com.yundao.core.log.LogFactory;
import com.yundao.core.pagination.PaginationSupport;
import com.yundao.core.utils.BooleanUtils;
import com.yundao.customer.h5.annotation.IdField;
import com.yundao.customer.h5.annotation.NameField;
import com.yundao.customer.h5.annotation.Owner;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * 权限通用类
 *
 * @author jan
 * @create 2017-07-29 PM10:09
 **/
public class AccessCommon {

    private static Log log = LogFactory.getLog(AccessCommon.class);


    /**
     * 实例化指定类型对象
     */
    public static <T> T getResultInstance(BaseTypeReference<Result<T>> typeReference) throws BaseException {
        String typeName = typeReference.getType().getTypeName();
        String className;
        if (typeName.contains(PaginationSupport.class.getName()))
            className = PaginationSupport.class.getName();
        else if (typeName.contains(List.class.getName()))
            className = List.class.getName();
        else if (typeName.contains(Map.class.getName()))
            className = Map.class.getName();
        else
            className = null;

        if (BooleanUtils.isEmpty(className))
            return null;
        try {
            Class<?> clazz = Class.forName(className);
            return (T) clazz.newInstance();
        } catch (Exception ex) {
            log.error("getResultInstance 异常：%s", ex.toString());
            throw new BaseException(CommonCode.COMMON_1007);
        }
    }

    /**
     * 找出本条记录的创建者
     */
    public static <T> Object getOwner(T t) throws BaseException {
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field field : fields) {
            Owner ownerAnnotation = field.getAnnotation(Owner.class);
            if (ownerAnnotation != null) {
                return getFieldValue(t, field);
            }
        }
        return AccessCommon.getFieldValue(t, "createUserId");
    }


    /**
     * 找出本条记录的名称
     */
    public static <T> Object getName(T t) throws BaseException {
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field f : fields) {
            if (f.getAnnotation(NameField.class) != null)
                return AccessCommon.getFieldValue(t, f);
        }
        return AccessCommon.getFieldValue(t, "name");
    }

    /**
     * 找出本条记录的id
     */
    public static <T> Object getId(T t) throws BaseException {
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field f : fields) {
            if (f.getAnnotation(IdField.class) != null)
                return AccessCommon.getFieldValue(t, f);
        }
        return AccessCommon.getFieldValue(t, "id");
    }


    /**
     * 获取字段值
     */
    public static <T> Object getFieldValue(T t, Field field) throws BaseException {
        try {
            String methodName = methodGetName(field);
            Method method = t.getClass().getMethod(methodName);
            return method.invoke(t);
        } catch (Exception ex) {
            log.error("ResultAccess.getFieldValue Exception:%s", ex.toString());
            throw new BaseException(CommonCode.COMMON_1007);
        }
    }


    public static <T> Method setMethod(T t, Field field) throws BaseException {
        try {
            return t.getClass().getMethod(methodSetName(field), field.getType());
        } catch (Exception ex) {
            log.error("setMethod 异常:%s", ex.toString());
            throw new BaseException(CommonCode.COMMON_1007);
        }
    }

    /**
     * 获取指定字段的值
     *
     * @param t         对象
     * @param fieldCode 字段编码
     */
    public static <T> Object getFieldValue(T t, String fieldCode) throws BaseException {
        log.begin();
        try {
            String methodName = getMethodName(fieldCode);
            return t.getClass().getMethod(methodName).invoke(t);
        } catch (Exception ex) {
            log.error("geMethod 异常:%s", ex.toString());
            throw new BaseException(CommonCode.COMMON_1007);
        }
    }

    public static <T> Method getMethod(T t, Field field) throws BaseException {
        log.begin();
        try {
            String methodName = AccessCommon.methodGetName(field);
            return t.getClass().getMethod(methodName);
        } catch (Exception ex) {
            log.error("geMethod 异常:%s", ex.toString());
            throw new BaseException(CommonCode.COMMON_1007);
        }
    }

    /**
     * GET/SET 方法名称
     */
    private static String getMethodName(Field field, String method) {
        String fieldName = field.getName();
        return method + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

    /**
     * GET/SET 方法名称
     */
    private static String getMethodName(String fieldName) {
        return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

    /**
     * GET 方法
     */
    private static String methodGetName(Field field) {
        return getMethodName(field, "get");
    }

    /**
     * SET方法
     */
    private static String methodSetName(Field field) {
        return getMethodName(field, "set");
    }

}
