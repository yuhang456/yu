package com.yu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yu.entity.Country;
import com.yu.entity.Store;
@Repository
public interface StoreDao {
	//��ѯ�ó��е����е���
	public List<Country> allCountry(@Param("cityName")String cityName);
	//��ѯ�ĸõ��������е���
	public List<Store> allStore(@Param("countryId")int countryId);
	//����������Ų�ѯ��������
	public String countryNameById(@Param("countryId")int countryId);
}
