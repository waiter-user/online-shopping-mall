package com.java.manager.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/info")
public class infoServlet extends ViewBaseServlet {
    //跳转到info页面回显信息
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.processTemplate("info",request,response);
    }
}
