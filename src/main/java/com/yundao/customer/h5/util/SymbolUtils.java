package com.yundao.customer.h5.util;

import com.yundao.core.utils.BooleanUtils;
import com.yundao.customer.h5.constant.SymbolConstant;

import java.util.List;

/**
 * 符号工具类
 *
 * @author jan
 * @create 2017-07-02 PM1:18
 **/
public class SymbolUtils {

    /**
     * 删除最后一个特定符号
     *
     * @param content 内容
     * @param symbol  特定的符号
     * @return 处理结果
     */
    public static String replaceLastSymbol(String content, String symbol) {
        if (BooleanUtils.isNotBlank(content)) {
            if (content.endsWith(symbol)) {
                content = content.substring(0, content.length() - 1);
            }
        }
        return content;
    }

    /**
     * 将Long类型list转换为指定符号分隔的字符串，默认符号为：,(逗号)
     */
    public static String longToStr(List<Long> ids) {
        String content = "";
        for (Long id : ids) {
            if (id == null)
                continue;
            content += id.toString() + SymbolConstant.COMMA;
        }
        return replaceLastSymbol(content, SymbolConstant.COMMA);
    }

    /**
     * 将list转换为指定符号分隔的字符串，默认符号为：,(逗号)
     *
     * @param contents list
     * @return 处理后的结果
     */
    public static String toStr(List<String> contents) {
        return toStr(SymbolConstant.COMMA, contents);
    }

    /**
     * 将list转换为指定符号分隔的字符串
     *
     * @param SplitSymbol 指定符号
     * @param contents    list
     * @return 处理后的结果
     */
    private static String toStr(String SplitSymbol, List<String> contents) {
        return String.join(SplitSymbol, contents);
    }
}
