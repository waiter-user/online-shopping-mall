package com.java.sportshop.pojo;

import java.util.Date;

public class Goods {
	// 商品编号
	private Integer goods_id;
	private String goods_name;
	private String goods_miaoshu;
	private String goods_pic;
	private Integer market_price; // 市场价
	private Integer mall_price;// 商城价
	private Catelog catelog; // 所属类别
	private Integer stock_num;
	private String goods_address;
	private Date enter_date;// 上市日期

	public Integer getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	public String getGoods_miaoshu() {
		return goods_miaoshu;
	}

	public void setGoods_miaoshu(String goods_miaoshu) {
		this.goods_miaoshu = goods_miaoshu;
	}

	public String getGoods_pic() {
		return goods_pic;
	}

	public void setGoods_pic(String goods_pic) {
		this.goods_pic = goods_pic;
	}

	public Integer getMarket_price() {
		return market_price;
	}

	public void setMarket_price(Integer market_price) {
		this.market_price = market_price;
	}

	public Integer getMall_price() {
		return mall_price;
	}

	public void setMall_price(Integer mall_price) {
		this.mall_price = mall_price;
	}

	public Catelog getCatelog() {
		return catelog;
	}

	public void setCatelog(Catelog catelog) {
		this.catelog = catelog;
	}

	public Integer getStock_num() {
		return stock_num;
	}

	public void setStock_num(Integer stock_num) {
		this.stock_num = stock_num;
	}

	public String getGoods_address() {
		return goods_address;
	}

	public void setGoods_address(String goods_address) {
		this.goods_address = goods_address;
	}

	public Date getEnter_date() {
		return enter_date;
	}

	public void setEnter_date(Date enter_date) {
		this.enter_date = enter_date;
	}

	@Override
	public String toString() {
		return "Goods [goods_id=" + goods_id + ", goods_name=" + goods_name
				+ ", goods_miaoshu=" + goods_miaoshu + ", goods_pic="
				+ goods_pic + ", market_price=" + market_price
				+ ", mall_price=" + mall_price + ", stock_num=" + stock_num
				+ ", goods_address=" + goods_address + ", enter_date="
				+ enter_date + "]";
	}

}
