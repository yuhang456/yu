package com.yu.entity;

//������Ʒʵ��
public class Goods {
	//��ƷͼƬ��·��
	private String goodsPicture;
	//��Ʒ�ı��
	private long goodsNum;		
	//��Ʒ������
	private String goodsName;
	//��Ʒ�ļ�Ǯ
	private double goodsPrice;
	//��Ʒ��ʣ��������棩
	private int goodsSurplus;
	//�ж��û��������Ƿ������Ʒ��ʣ���������������Ʒ��ʣ�����������������Ϣ
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
