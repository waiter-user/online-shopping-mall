package com.java.sportshop.servlet;


import com.java.sportshop.pojo.Goods;
import com.java.sportshop.service.GoodService;
import com.java.sportshop.service.impl.GoodServiceImpl;
import com.java.sportshop.util.PageBean;
import com.java.sportshop.util.WebRequestUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GoodsServlet", value = "/goods")
public class GoodsServlet extends ViewBaseServlet {
    private GoodService goodService=new GoodServiceImpl();
    protected void queryByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebRequestUtil webRequestUtil=new WebRequestUtil();
        Integer pageNo = webRequestUtil.getParamValue(request, "pageNo", PageBean.DEFAULT_PAGENO);
        Integer pageSize = webRequestUtil.getParamValue(request, "pageSize", PageBean.DEAULT_PAGESIZE);
        String goodsName = request.getParameter("goodsName");
        PageBean<Goods> goodsListByPage = goodService.getGoodsListByPage(goodsName,pageNo, pageSize);
        request.setAttribute("goodsName",goodsName);
        request.setAttribute("pb",goodsListByPage);
        System.out.println(goodsListByPage);
        super.processTemplate("goods/list",request,response);
    }

    protected void queryDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        Goods goods= goodService.getGoodsDetail(id);
        request.setAttribute("gd",goods);
        super.processTemplate("goods/goodsDetail",request,response);
    }
}
