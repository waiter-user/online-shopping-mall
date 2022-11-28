package com.java.sportshop.servlet;


import com.java.sportshop.pojo.User;
import com.java.sportshop.service.UserService;
import com.java.sportshop.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "UserServlet", value = "/user")
public class UserServlet extends ViewBaseServlet {
    private UserService userService=new UserServiceImpl();
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String userPw = request.getParameter("userPw");
        User loginUser = userService.login(userName, userPw);
        if(loginUser!=null){
            HttpSession session = request.getSession();
            session.setAttribute("user",loginUser);
        }else {
            request.setAttribute("errorMsg","登录失败");
        }
        request.getRequestDispatcher("/index?action=doPost").forward(request,response);
    }

    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath()+"/index");
    }
    protected void getUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.processTemplate("userinfo/userXinxi",request,response);
    }
    protected void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String userPw = request.getParameter("userPw");
        String userRealname = request.getParameter("userRealname");
        String userAddress = request.getParameter("userAddress");
        String userSex = request.getParameter("userSex");
        String userTel = request.getParameter("userTel");
        String userEmail = request.getParameter("userEmail");
        String userQq = request.getParameter("userQq");
        int userId = Integer.parseInt(request.getParameter("userId"));
        User user=new User();
        user.setUser_id(userId);
        user.setUser_name(userName);
        user.setUser_sex(userSex);
        user.setUser_qq(userQq);
        user.setUser_realname(userRealname);
        user.setUser_address(userAddress);
        user.setUser_tel(userTel);
        user.setUser_pwd(userPw);
        user.setUser_email(userEmail);
        boolean b= userService.updateUser(user);
        if(b){
            HttpSession session=request.getSession();
            session.setAttribute("user", user);
            response.getWriter().write("<script>window.close()</script>");
        }

    }
}
