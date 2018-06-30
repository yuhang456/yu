package com.yu.entity;

//购物车实体信息
public class GoodsCar {
	// 用户的电话号码（根据用户的电话号码查询购物车信息）
	private String userTel;
	// 商品图片的路径
	private String goodsPicture;
	// 商品的编号
	private long goodsNum;
	// 商品的名字
	private String goodsName;
	// 商品的价钱
	private double goodsPrice;
	// 商品的购买数量
	private int goodsBuyNum;

	public GoodsCar() {
		// super();
		// TODO Auto-generated constructor stub
	}

	public GoodsCar(String userTel, String goodsPicture, long goodsNum, String goodsName, double goodsPrice,
			int goodsBuyNum) {
		// super();
		this.userTel = userTel;
		this.goodsPicture = goodsPicture;
		this.goodsNum = goodsNum;
		this.goodsName = goodsName;
		this.goodsPrice = goodsPrice;
		this.goodsBuyNum = goodsBuyNum;
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

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

	public int getGoodsBuyNum() {
		return goodsBuyNum;
	}

	public void setGoodsBuyNum(int goodsBuyNum) {
		this.goodsBuyNum = goodsBuyNum;
	}

}
