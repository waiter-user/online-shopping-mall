package com.java.manager.servlet;

import com.alibaba.fastjson.JSON;
import com.java.manager.pojo.Catelog;
import com.java.manager.service.CatelogService;
import com.java.manager.service.impl.CatelogServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/catelog")
public class CatelogServlet extends ViewBaseServlet {
    private CatelogService  catelogService=new CatelogServiceImpl();
    /**
     * 获取商品类别列表
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用商品列表的service方法
        List<Catelog> list = catelogService.getlist();
        //存储到对象中
        request.setAttribute("list",list);
        super.processTemplate("/catelog/list",request,response);
    }

    protected void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //转发到add.html
        //request.getRequestDispatcher("WEB-INF/templates/catelog/add.html").forward(request,response);
        super.processTemplate("catelog/add",request,response);
    }

    //添加商品列表
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数列表
        String name = request.getParameter("catelog_name");
        String miaoshu = request.getParameter("catelog_miaoshu");
        //封装一个类别对象
        Catelog catelog=new Catelog();
        catelog.setCatelog_name(name);
        catelog.setCatelog_miaoshu(miaoshu);
        Boolean flag = catelogService.add(catelog);
        if (flag) {
            //重新请求list方法
            response.sendRedirect(request.getContextPath()+"/catelog?action=list");
        }
    }
    //异步获取商品类别列表
    protected void getCateLogList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用catelogservice中的获取类别列表的方法
        List<Catelog> list =catelogService.getlist();
        //设置响应的数据类型及格式
        response.setContentType("application/json;charset=utf-8");
        //Java集合不能直接响应给前端，要转化为json数据
        String s = JSON.toJSONString(list);
        //获取打印流
        PrintWriter out = response.getWriter();
        out.write(s);
    }
}
