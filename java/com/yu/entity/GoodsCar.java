package com.yu.entity;

//���ﳵʵ����Ϣ
public class GoodsCar {
	// �û��ĵ绰���루�����û��ĵ绰�����ѯ���ﳵ��Ϣ��
	private String userTel;
	// ��ƷͼƬ��·��
	private String goodsPicture;
	// ��Ʒ�ı��
	private long goodsNum;
	// ��Ʒ������
	private String goodsName;
	// ��Ʒ�ļ�Ǯ
	private double goodsPrice;
	// ��Ʒ�Ĺ�������
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
