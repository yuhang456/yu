package com.yu.entity;

public class Store {
	private int countryId;//���б�����̵�������
	private int storeId;//�̵�ı�ţ�ͨ�����ֿ��Ը���Ķ�λ���̵�
	private String storeName;//�̵�����
	
	public Store() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Store(int countryId, int storeId, String storeName) {
		super();
		this.countryId = countryId;
		this.storeId = storeId;
		this.storeName = storeName;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	
}
