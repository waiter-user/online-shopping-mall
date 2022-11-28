package com.java.sportshop.servlet;


import com.java.sportshop.pojo.Goods;
import com.java.sportshop.pojo.Order;
import com.java.sportshop.pojo.Orderitem;
import com.java.sportshop.pojo.User;
import com.java.sportshop.pojo.cart.Cart;
import com.java.sportshop.pojo.cart.CartItem;
import com.java.sportshop.service.GoodService;
import com.java.sportshop.service.OrderService;
import com.java.sportshop.service.impl.GoodServiceImpl;
import com.java.sportshop.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "OrderServlet", value = "/order")
public class OrderServlet extends ViewBaseServlet {
    private OrderService orderService=new OrderServiceImpl();
    protected void sure(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.processTemplate("order/orderQueren",request,response);
    }

    protected void addOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.currentTimeMillis();
//        UUID.randomUUID().toString();
        String str= UUID.randomUUID().toString();
        String id = str.replaceAll("-", "");
        //删除无意义的-作为订单编号
        Timestamp now=new Timestamp(System.currentTimeMillis());
        Integer order_money=Integer.parseInt(request.getParameter("totalPrice"));
        String addres=request.getParameter("odderSonghuodizhi");
        String paymethod=request.getParameter("odderFukuangfangshi");
        int userid = Integer.parseInt(request.getParameter("userid"));
        Order order=new Order();
        order.setOrder_id(id);
        order.setOrder_address(addres);
        order.setOrder_jine(order_money);
        order.setOrder_time(now);
        order.setOrder_pay(paymethod);
        User user=new User();
        user.setUser_id(userid);
        order.setUser(user);
        HttpSession session=request.getSession();
        Cart cart=(Cart)session.getAttribute("cart");
        Collection<CartItem> items=cart.getItems();
        List<Orderitem> itemlist=new ArrayList<>();
        for(CartItem item:items){
            Orderitem orderitem=new Orderitem();
            orderitem.setOrder(order);
            Goods goods=item.getGoods();
            orderitem.setGoods(goods);
            orderitem.setGoods_num(item.getQuantity());
            itemlist.add(orderitem);
        }
        boolean b = orderService.addOrder(order, itemlist);
        if(b){
            session.removeAttribute("cart");
            request.setAttribute("order",order);
            super.processTemplate("order/orderSubmit",request,response);
        }


    }
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session= request.getSession();
        User user=(User) session.getAttribute("user");
        int User_id=user.getUser_id();
        List<Order> list=orderService.getOrderList(User_id);
        request.setAttribute("list",list);
        super.processTemplate("order/myOrder",request,response);
    }
    protected void orderDetail(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        List<Orderitem> orderitems = orderService.getOrderItem(request.getParameter("orderid"));
        GoodService goodService=new GoodServiceImpl();
        List<String> goodsname=new ArrayList<>();
        List<Integer> goodsnum=new ArrayList<>();
        for (int i=0;i<orderitems.size();i++){
            Goods goodsDetail = goodService.getGoodsDetail(orderitems.get(i).getGoods().getGoods_id());
            goodsname.add(goodsDetail.getGoods_name());
            goodsnum.add(orderitems.get(i).getGoods_num());
        }
        request.setAttribute("names",goodsname);
        request.setAttribute("quantity",goodsnum);
        super.processTemplate("order/orderDetail",request,response);
    }
}
