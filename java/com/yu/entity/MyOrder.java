package com.yu.entity;


//�ҵĶ���ʵ��
public class MyOrder {

	private long orderNum;// �������(����)
	private String orderStore;//�����̵�����
	private String userTel;// �û�����
	private String userAddress;// �û���ַ
	private String goodsCarAllGoods;// ���ﳵ��ȫ����Ʒ�������ﳵ��ȫ����Ʒ���浽���ַ����У�Ȼ����÷ָ�����зָ���ȡ
	private double totalGoodsPrice;
	private int orderStatus;// ����״̬��0δ֧����1֧���ɹ���2������ȡ����
	private String downOrderTime;// �µ�ʱ��

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
