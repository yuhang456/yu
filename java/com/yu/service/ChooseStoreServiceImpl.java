package com.yu.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.HTTP;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yu.dao.StoreDao;
import com.yu.entity.Country;
import com.yu.entity.Store;

//将api定位json数据转化
@Service
public class ChooseStoreServiceImpl {
	@Autowired
	private StoreDao storeDao; 
	
	private static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	  }
	  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
	    InputStream is = new URL(url).openStream();
	    try {
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	      String jsonText = readAll(rd);
	      JSONObject json = new JSONObject(jsonText);
	      return json;
	    } finally {
	      is.close();
	     // System.out.println("同时 从这里也能看出 即便return了，仍然会执行finally的！");
	    }
	  }
	public String GPSCity(HttpSession session){
		//这里调用百度的ip定位api服务 详见 http://api.map.baidu.com/lbsapi/cloud/ip-location-api.htm
	    JSONObject json = null;
		try {
			json = readJsonFromUrl("http://api.map.baidu.com/location/ip?ip=202.115.82.27&ak=GbVey4F5GGsmpGzeHG4FcFgz0t4ZxQSz");
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    String cityOne=(String) ((JSONObject) ((JSONObject) json.get("content")).get("address_detail")).get("city"),city;
	    city=cityOne.substring(0,2);
	    session.setAttribute("location", "");
		return city;
	}
	//在数据库查询该城市的所有地区
	public List<Country> allCountryService(String cityName){
		return storeDao.allCountry(cityName);
	}
	//在数据库查询所有地区的商铺
	public List<Store> allStoreService(int countryId,HttpSession session){
		List<Store> list=storeDao.allStore(countryId);
		String countryName=storeDao.countryNameById(countryId);
		session.setAttribute("location", countryName);
		return list;
	}
	//将用户选择的商铺保存起来
	public void savaStoreService(String store,HttpSession session){
		session.setAttribute("location", session.getAttribute("location")+" "+store);
	}
}
