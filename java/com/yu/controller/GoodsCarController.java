package com.yu.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yu.dto.DataEntity;
import com.yu.service.GoodsCarServiceImpl;

@Controller
@RequestMapping(value="/shopcar")
public class GoodsCarController {
	@Autowired
	private GoodsCarServiceImpl goodsCarServiceImpl;
	
	@ResponseBody
	@RequestMapping(value="/addGoods",method=RequestMethod.GET)
	public DataEntity DataEntityController(long goodsNum) {
		DataEntity dataEntity=goodsCarServiceImpl.updataGoodsNumService(goodsNum);
		return dataEntity;
	}
	@ResponseBody
	@RequestMapping(value="/reduceGoods",method=RequestMethod.GET)
	public DataEntity dataReduceEntityController(long goodsNum) {
		DataEntity dataEntity=goodsCarServiceImpl.updataReduceGoodsNumService(goodsNum);
		return dataEntity;
	}
	
	@RequestMapping(value="/deleteGoods",method=RequestMethod.GET)
	public String deleteGoodsCarController(long goodsNum){
		goodsCarServiceImpl.deleteGoodsCarService(goodsNum);
		return "redirect:/shopcar";
	}
	//提交购物车订单
	@RequestMapping(value="/commitOrder",method=RequestMethod.POST)
	public @ResponseBody DataEntity commitOrderController(String userTel,String userAddress){
		return goodsCarServiceImpl.commitOrderService(userTel, userAddress);
	}
	//跳转至订单详情
	@RequestMapping(value="/orderInfor")
	public String myOrderInfor(Model model) {
		model.addAttribute("order",goodsCarServiceImpl.selectMyOrderInforService());
		return "orderinfor";
	}
}
