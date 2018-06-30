package com.yu.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yu.entity.Goods;
import com.yu.entity.GoodsCar;
import com.yu.entity.Page;
import com.yu.service.GoodsCarServiceImpl;
import com.yu.service.GoodsServiceImpl;

@Controller
@RequestMapping("/")
public class GoodsController {
	@Autowired
	private GoodsServiceImpl goodsServiceImpl;
	@Autowired
	private GoodsCarServiceImpl goodsCarServiceImpl;
	
//	@RequestMapping(value = "/index")
//	public String allGoodsIndex(Model model, @RequestParam(value = "pageNow", defaultValue = "1") int pageNow,@RequestParam(value = "goodsName", defaultValue = "") String goodsName) {
//		Page page = goodsServiceImpl.countInitPageService(pageNow,goodsName);
//		List<Goods> list=goodsServiceImpl.allGoodsSelectService(page,goodsName);
//		model.addAttribute("goods", list);
//		model.addAttribute("page", page);
//		
//		return "index2";
//	}
	@RequestMapping(value = "/index")
	public String allGoodsIndex(Model model,HttpSession session) {
		model.addAttribute("goods",goodsServiceImpl.allGoodsSelectService());
		model.addAttribute("location",session.getAttribute("location"));
		return "index";
	}
	
	@ResponseBody
	@RequiresRoles("user")
	@RequestMapping(value = "/addcar")
	public Goods addCarCotroller(@RequestParam("goodsNum") Long goodsNum,
			@RequestParam(value = "BuyNumber", defaultValue = "1") Integer BuyNumber) {
		Goods goods = goodsCarServiceImpl.addGoodsCarService(goodsNum, BuyNumber);
		return goods;
	}
	
//	@RequiresRoles("user")
//	@RequestMapping(value = "/shopcar")
//	public String shopCarCotroller(Model model,@RequestParam(value = "pageNow", defaultValue = "1") int pageNow) {
//		Page page=goodsCarServiceImpl.countCarInitPageService(pageNow);
//		List<GoodsCar> list;
//		list = goodsCarServiceImpl.goodsCarSelectService(page);
//		double totalGoodsPrice = goodsCarServiceImpl.totalPrice();
//		model.addAttribute("totalGoodsPrice", totalGoodsPrice);
//		model.addAttribute("page",page);
//		model.addAttribute("goodscar", list);
//		return "shopcar";
//	}
	@RequiresRoles("user")
	@RequestMapping(value = "/shopcar")
	public String shopCarCotroller(Model model) {
		double totalGoodsPrice = goodsCarServiceImpl.totalPrice();
		model.addAttribute("totalGoodsPrice", totalGoodsPrice);
		model.addAttribute("goodscar", goodsCarServiceImpl.goodsCarSelectService());
		return "shopcar";
	}
}
