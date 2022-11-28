package com.java.manager.pojo;


import java.sql.Timestamp;

/**
 * 订单类
 * 
 * @author lujun
 * 
 */
public class Order {
	private String order_id; //订单编号
	private Timestamp order_time; //下单日期
	private int order_zhuangtai;//状态
	private int order_jine;//总金额
	private String order_address;//收货地址
	private String order_pay;//付款方式
	//订单所属的用户
	private User user;  //int order_uesrid;
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public Timestamp getOrder_time() {
		return order_time;
	}
	public void setOrder_time(Timestamp order_time) {
		this.order_time = order_time;
	}
	public int getOrder_zhuangtai() {
		return order_zhuangtai;
	}
	public void setOrder_zhuangtai(int order_zhuangtai) {
		this.order_zhuangtai = order_zhuangtai;
	}
	public int getOrder_jine() {
		return order_jine;
	}
	public void setOrder_jine(int order_jine) {
		this.order_jine = order_jine;
	}
	public String getOrder_address() {
		return order_address;
	}
	public void setOrder_address(String order_address) {
		this.order_address = order_address;
	}
	public String getOrder_pay() {
		return order_pay;
	}
	public void setOrder_pay(String order_pay) {
		this.order_pay = order_pay;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getStateStr(){
		switch (this.order_zhuangtai){
			case 0:
				return "待发货";
			case 1:
				return "已发货";
			case 2:
				return "已收货";
			case 3:
				return "已完成";
			default:
				return "";
		}
	}
}
