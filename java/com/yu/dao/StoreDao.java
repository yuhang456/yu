package com.yu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yu.entity.Country;
import com.yu.entity.Store;
@Repository
public interface StoreDao {
	//查询该城市的所有地区
	public List<Country> allCountry(@Param("cityName")String cityName);
	//查询的该地区的所有店铺
	public List<Store> allStore(@Param("countryId")int countryId);
	//根据市区编号查询市区名字
	public String countryNameById(@Param("countryId")int countryId);
}
