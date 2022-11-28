package com.java.manager.servlet;

import com.java.manager.pojo.Order;
import com.java.manager.service.OrderService;
import com.java.manager.service.impl.OrderServiceImpl;
import com.java.manager.util.PageBean;
import com.java.manager.util.WebRequestUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/order")
public class OrderServlet extends ViewBaseServlet {
    private OrderService orderService = new OrderServiceImpl();

    /**
     * 处理分页获取会员列表的请求
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer pageNo = WebRequestUtil.getParamValue(request, "pageNo", PageBean.DEFAULT_PAGENO);
        Integer pageSize = WebRequestUtil.getParamValue(request, "pageSize", PageBean.DEAULT_PAGESIZE);
        PageBean<Order> pb = orderService.queryByPage(pageNo, pageSize);
        request.setAttribute("pb", pb);
        super.processTemplate("/order/list", request, response);
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Boolean b = orderService.updateZhuangTai(id);
        if (b) {
            this.list(request, response);
        }
    }

    protected void deleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Boolean b = orderService.deleteOrder(id);
        if (b) {
            this.list(request, response);
        }
    }
}
