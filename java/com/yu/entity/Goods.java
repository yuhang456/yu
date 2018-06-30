package com.yu.entity;

//建立商品实体
public class Goods {
	//商品图片的路径
	private String goodsPicture;
	//商品的编号
	private long goodsNum;		
	//商品的名字
	private String goodsName;
	//商品的价钱
	private double goodsPrice;
	//商品的剩余量（库存）
	private int goodsSurplus;
	//判断用户购买量是否大于商品的剩余量，如果大于商品的剩余量，即输出错误信息
	private String error;
	
	public String getGoodsPicture() {
		return goodsPicture;
	}
	public void setGoodsPicture(String goodsPicture) {
		this.goodsPicture = goodsPicture;
	}
	public long getGoodsNum() {
		return goodsNum;
	}
	public void setGoodsNum(long goodsNum) {
		this.goodsNum = goodsNum;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public double getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public int getGoodsSurplus() {
		return goodsSurplus;
	}
	public void setGoodsSurplus(int goodsSurplus) {
		this.goodsSurplus = goodsSurplus;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}

	
}
