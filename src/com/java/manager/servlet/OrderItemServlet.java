package com.java.manager.servlet;

import com.java.manager.pojo.Orderitem;
import com.java.manager.service.OrderItemService;
import com.java.manager.service.impl.OrderItemServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/orderItem")
public class OrderItemServlet extends ViewBaseServlet {
    private OrderItemService orderItemService = new OrderItemServiceImpl();

    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String order_id = request.getParameter("id");
        List<Orderitem> orderitem = orderItemService.getOrderItems(order_id);
        request.setAttribute("list", orderitem);
        super.processTemplate("order/detail", request, response);
    }
}
