package com.yu.dto;

/*
 * com.yu.dto
 * 前台和控制层Controller传输的数据实体
 */

//传输的数据实体类
public class DataEntity {
	//购物车单个商品数量
//	private int oneGoodsNum;
	//购物车单个商品的价钱更新
//	private double oneGoodsPrice;
	//购物车总的商品的价钱更新 
	private double totalGoodsPrice;
	//购物车商品的总个数
//	private int totalGoodsNum;
	//dto层信息携带交互判断
	private String error;
	
	
	
	
	public DataEntity() {
		//super();
		// TODO Auto-generated constructor stub
	}
	
	//便于更新购物车页面设置的构造函数
	public DataEntity(double totalGoodsPrice, String error) {
		//super();
		this.totalGoodsPrice = totalGoodsPrice;
		this.error = error;
	}
	//便于用户登陆验证设置的构造函数
	public DataEntity(String error) {
		//super();
		this.error = error;
	}

//	public int getOneGoodsNum() {
//		return oneGoodsNum;
//	}
//	public void setOneGoodsNum(int oneGoodsNum) {
//		this.oneGoodsNum = oneGoodsNum;
//	}
//	public double getOneGoodsPrice() {
//		return oneGoodsPrice;
//	}
//	public void setOneGoodsPrice(double oneGoodsPrice) {
//		this.oneGoodsPrice = oneGoodsPrice;
//	}
	public double getTotalGoodsPrice() {
		return totalGoodsPrice;
	}
	public void setTotalGoodsPrice(double totalGoodsPrice) {
		this.totalGoodsPrice = totalGoodsPrice;
	}
//	public int getTotalGoodsNum() {
//		return totalGoodsNum;
//	}
//	public void setTotalGoodsNum(int totalGoodsNum) {
//		this.totalGoodsNum = totalGoodsNum;
//	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
}
