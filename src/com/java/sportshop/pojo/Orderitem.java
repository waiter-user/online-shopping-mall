package com.java.sportshop.pojo;

/**
 * 订单明细类
 * 
 * @author lujun
 * 
 */
public class Orderitem {
	//订单明细编号
	private int orderitem_id;
	//所属订单
	private Order order;
	//商品
	private Goods goods;
	//购买数量
	private int goods_num;
  
	
	public int getOrderitem_id() {
		return orderitem_id;
	}
	public void setOrderitem_id(int orderitem_id) {
		this.orderitem_id = orderitem_id;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public int getGoods_num() {
		return goods_num;
	}
	public void setGoods_num(int goods_num) {
		this.goods_num = goods_num;
	}
	
}
