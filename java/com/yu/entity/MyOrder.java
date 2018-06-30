package com.yu.entity;


//我的订单实体
public class MyOrder {

	private long orderNum;// 订单编号(主键)
	private String orderStore;//订单商店名称
	private String userTel;// 用户姓名
	private String userAddress;// 用户地址
	private String goodsCarAllGoods;// 购物车中全部物品，将购物车中全部商品保存到该字符串中，然后采用分割函数进行分割提取
	private double totalGoodsPrice;
	private int orderStatus;// 订单状态（0未支付，1支付成功，2订单已取消）
	private String downOrderTime;// 下单时间

	public MyOrder() {
		// super();
		// TODO Auto-generated constructor stub
	}

	public MyOrder(long orderNum, String orderStore,String userTel, String userAddress, String goodsCarAllGoods, double totalGoodsPrice,
			int orderStatus, String downOrderTime) {
		//super();
		this.orderNum = orderNum;
		this.orderStore=orderStore;
		this.userTel = userTel;
		this.userAddress = userAddress;
		this.goodsCarAllGoods = goodsCarAllGoods;
		this.totalGoodsPrice = totalGoodsPrice;
		this.orderStatus = orderStatus;
		this.downOrderTime = downOrderTime;
	}

	@Override
	public String toString() {
		return "MyOrder [orderNum=" + orderNum + ", userTel=" + userTel + ", userAddress=" + userAddress
				+ ", goodsCarAllGoods=" + goodsCarAllGoods + ", totalGoodsPrice=" + totalGoodsPrice + ", orderStatus="
				+ orderStatus + ", downOrderTime=" + downOrderTime + "]";
	}

	public long getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(long orderNum) {
		this.orderNum = orderNum;
	}

	public String getOrderStore() {
		return orderStore;
	}

	public void setOrderStore(String orderStore) {
		this.orderStore = orderStore;
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getGoodsCarAllGoods() {
		return goodsCarAllGoods;
	}

	public void setGoodsCarAllGoods(String goodsCarAllGoods) {
		this.goodsCarAllGoods = goodsCarAllGoods;
	}

	public double getTotalGoodsPrice() {
		return totalGoodsPrice;
	}

	public void setTotalGoodsPrice(double totalGoodsPrice) {
		this.totalGoodsPrice = totalGoodsPrice;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getDownOrderTime() {
		return downOrderTime;
	}

	public void setDownOrderTime(String downOrderTime) {
		this.downOrderTime = downOrderTime;
	}


}
