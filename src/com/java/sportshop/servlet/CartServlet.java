package com.java.sportshop.servlet;


import com.java.sportshop.pojo.Goods;
import com.java.sportshop.pojo.cart.Cart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "CartServlet", value = "/cart")
public class CartServlet extends ViewBaseServlet {
    protected void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int goodsId = Integer.parseInt(request.getParameter("goodsId"));
        String goodsName = request.getParameter("goodsName");
        int goodsPrice = Integer.parseInt(request.getParameter("goodsPrice"));
        Goods goods=new Goods();
        goods.setGoods_id(goodsId);
        goods.setGoods_name(goodsName);
        goods.setMall_price(goodsPrice);
        int quantity=Integer.parseInt(request.getParameter("quantity"));
        HttpSession session = request.getSession();
        Cart cart= (Cart)session.getAttribute("cart");
        if(cart==null){
            cart=new Cart();
            session.setAttribute("cart",cart);
        }
        cart.addToCart(goods,quantity);
        super.processTemplate("myCart/myCart",request,response);
    }
    protected void showCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.processTemplate("myCart/myCart",request,response);
    }
    protected void deleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession session=request.getSession();
        Cart cart=(Cart) session.getAttribute("cart");
        cart.removeCartItem(id);
        super.processTemplate("myCart/myCart",request,response);
    }
    protected void updateQuantity(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        int id = Integer.parseInt(request.getParameter("id"));
        int num = Integer.parseInt(request.getParameter("num"));
        HttpSession session=request.getSession();
        Cart cart=(Cart) session.getAttribute("cart");
        cart.updateQuantity(id,num);
        super.processTemplate("myCart/myCart",request,response);
    }
    protected void clearCart(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        HttpSession session=request.getSession();
        Cart cart=(Cart) session.getAttribute("cart");
        cart.clearCart();
        super.processTemplate("myCart/myCart",request,response);
    }
}
