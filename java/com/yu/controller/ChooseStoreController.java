package com.yu.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yu.entity.Country;
import com.yu.entity.Store;
import com.yu.service.ChooseStoreServiceImpl;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/")
public class ChooseStoreController {
	@Autowired
	private ChooseStoreServiceImpl chooseStoreServiceImpl;
	@RequestMapping(value="/choose_store",method=RequestMethod.GET)
	public String ChooseCountry(Model model,HttpSession session){
		String cityName=chooseStoreServiceImpl.GPSCity(session);
		List<Country> list=chooseStoreServiceImpl.allCountryService(cityName);
		model.addAttribute("city", cityName);
		model.addAttribute("country", list);
		return "choose_store";
	}
	//将商铺信息转换为json数据传回到前端
	@RequestMapping(value="/chooseStore",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String ChooseStore(int countryId,HttpSession session){
		List<Store> list=chooseStoreServiceImpl.allStoreService(countryId,session);
		String json = JSONArray.fromObject(list).toString();
		return json;
	}
	//完成位置保存后跳转页面
	@RequestMapping(value="/saveStore",method=RequestMethod.GET)
	public String saveStore(String store,HttpSession session){
		chooseStoreServiceImpl.savaStoreService(store, session);
		return "redirect:/index"; 
	}
}
