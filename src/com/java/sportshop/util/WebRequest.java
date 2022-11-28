package com.java.sportshop.util;

import javax.servlet.http.HttpServletRequest;

/**
 * web请求的工具类,封装请求处理过程的重复的代码
 */
public class WebRequest {
    /**
     * 通用的根据请求参数名获取其值的方法
     * @param request
     * @param paramName
     * @param defaultValue
     * @return
     */
    public static Integer getParamValues(HttpServletRequest request, String paramName, Integer defaultValue) {
        String str = request.getParameter(paramName);
        //待处理的值
        Integer value = defaultValue;
        if (null != str) {
            value = Integer.valueOf(str);
        }
        return value;
    }
}
