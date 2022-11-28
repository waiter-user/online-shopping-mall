package com.java.sportshop.util;

import javax.servlet.http.HttpServletRequest;

public class WebRequestUtil {
    public Integer getParamValue(HttpServletRequest request, String paramName, Integer defaultValue){
        String str = request.getParameter(paramName);
        Integer value=defaultValue;
        if(str!=null){
            value=Integer.parseInt(str);
        }
        return value;
    }
}
