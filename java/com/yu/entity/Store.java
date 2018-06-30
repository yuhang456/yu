package com.yu.entity;

public class Store {
	private int countryId;//城市编号与商店编号相连
	private int storeId;//商店的编号，通过数字可以更快的定位到商店
	private String storeName;//商店名字
	
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
