package com.yu.dto;

/*
 * com.yu.dto
 * ǰ̨�Ϳ��Ʋ�Controller���������ʵ��
 */

//���������ʵ����
public class DataEntity {
	//���ﳵ������Ʒ����
//	private int oneGoodsNum;
	//���ﳵ������Ʒ�ļ�Ǯ����
//	private double oneGoodsPrice;
	//���ﳵ�ܵ���Ʒ�ļ�Ǯ���� 
	private double totalGoodsPrice;
	//���ﳵ��Ʒ���ܸ���
//	private int totalGoodsNum;
	//dto����ϢЯ�������ж�
	private String error;
	
	
	
	
	public DataEntity() {
		//super();
		// TODO Auto-generated constructor stub
	}
	
	//���ڸ��¹��ﳵҳ�����õĹ��캯��
	public DataEntity(double totalGoodsPrice, String error) {
		//super();
		this.totalGoodsPrice = totalGoodsPrice;
		this.error = error;
	}
	//�����û���½��֤���õĹ��캯��
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
