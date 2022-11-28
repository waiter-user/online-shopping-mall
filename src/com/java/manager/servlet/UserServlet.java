package com.java.manager.servlet;

import com.java.manager.pojo.User;
import com.java.manager.service.UserService;
import com.java.manager.service.impl.UserServiceImpl;
import com.java.manager.util.PageBean;
import com.java.manager.util.WebRequestUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user")
public class UserServlet extends ViewBaseServlet {
   private UserService  userService=new UserServiceImpl();
    //处理分页获取会员列表的请求
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer pageNo = WebRequestUtil.getParamValue(request, "pageNo", PageBean.DEFAULT_PAGENO);
        Integer pageSize = WebRequestUtil.getParamValue(request, "pageSize", PageBean.DEAULT_PAGESIZE);
        PageBean<User> pb = userService.queryByPage(pageNo, pageSize);
        request.setAttribute("pb",pb);
        super.processTemplate("user/list",request,response);
    }
}
