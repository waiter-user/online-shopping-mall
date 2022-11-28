package com.java.sportshop.servlet;


import com.java.sportshop.pojo.Goods;
import com.java.sportshop.service.GoodService;
import com.java.sportshop.service.impl.GoodServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "IndexServlet", value = "/index")
public class IndexServlet extends ViewBaseServlet {
    private GoodService goodService=new GoodServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Goods> goods = goodService.getTopFiveList();
        request.setAttribute("fiveList",goods);
        super.processTemplate("index.html",request,response);
    }
}
