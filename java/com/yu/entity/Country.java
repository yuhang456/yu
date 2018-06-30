package com.yu.entity;

public class Country {
	private String cityName;
	private int countryId;//定义为区的编号，数据库查询数字较快，较快查询数据
	private String countryName;
	
	public Country() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Country(String cityName, int countryId, String countryName) {
		super();
		this.cityName = cityName;
		this.countryId = countryId;
		this.countryName = countryName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public int getCountryId() {
		return countryId;
	}
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
	
}
