package com.java.manager.servlet;

import com.java.manager.pojo.Admin;
import com.java.manager.pojo.User;
import com.java.manager.service.AdminService;
import com.java.manager.service.impl.AdminServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/admin")
public class AdminServlet extends ViewBaseServlet {
    private AdminService adminService=new AdminServiceImpl();
    //登录
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String loginname = request.getParameter("uloginname");
        String password = request.getParameter("upsw");
        //获取输入的验证码
        String checkCode = request.getParameter("code");
        //检验验证码
        //1)获取session中的验证码
        HttpSession session = request.getSession();
        String code =(String) session.getAttribute("code");
        if(checkCode.equalsIgnoreCase(code)){
            //调用service的login方法
            Admin admin = adminService.login(loginname, password);
            if(null!=admin){
                //登录成功
                session.setAttribute("admin",admin);
                super.processTemplate("main/index",request,response);
            }else{
                //登陆失败，跳转到登录页面
                request.setAttribute("msg","用户名或密码错误");
                super.processTemplate("login",request,response);
            }
        }else{
            //验证码错误，返回登录页面
            request.setAttribute("msg","验证码错误");
            super.processTemplate("login",request,response);
        }
    }

    /**
     * 要进入登录页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void toLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.processTemplate("login",request,response);
    }
    //退出
    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        //跳转到登录页面
        response.sendRedirect(request.getContextPath()+"/admin?action=toLogin");
    }

    protected void toupdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.processTemplate("login",request,response);
    }

    /**
     * 进入修改密码页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void toUpdatePwd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.processTemplate("user/pass",request,response);
    }
    /**
     * 修改密码页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void updatePwd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取新的密码
        String newpass = request.getParameter("newpass");
        //获取用户id
        Integer userid =Integer.valueOf(request.getParameter("userid")) ;
        String username = request.getParameter("username");
        //封装为用户对象
        Admin admin=new Admin();
        admin.setUserid(userid);
        admin.setUserpwd(newpass);
        admin.setUsername(username);
        //调用service修改密码方法
        Boolean b = adminService.updatePwd(admin);
        if (b) {
            //跳转到登录页面and清空session
            HttpSession session = request.getSession();
            session.invalidate();
            //跳转到登录页面
            //response.sendRedirect(request.getContextPath()+"/login.html");
            //使用js做跳转，跳转到iframe所在小窗口的父窗口，要改变父窗口的路径
            response.setContentType("text/html;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.print("<script>alert('修改密码成功！');parent.location.href='"+request.getContextPath()+"/admin?action=toLogin';</script>");
            //关闭流
            writer.flush();
            writer.close();
        }


    }
}
