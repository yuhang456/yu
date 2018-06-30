package com.yu.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yu.dto.DataEntity;
import com.yu.entity.User;
import com.yu.service.GoodsCarServiceImpl;
import com.yu.service.UserServiceImpl;

@Controller
@RequestMapping(value="/user")
@RequiresRoles("user")
public class UserOperateCotroller {
	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired
	private GoodsCarServiceImpl goodsCarServiceImpl;
	//修改个人信息
	@RequestMapping(value="/userInforUpdata",method=RequestMethod.POST)
	public @ResponseBody DataEntity userInformationUpdataController(User user){
		DataEntity dataEntity=userServiceImpl.userInforUpdataService(user);
		return dataEntity;
	}
	//修改个人登录密码
	@RequestMapping(value="/updatePassword",method=RequestMethod.POST)
	public @ResponseBody DataEntity updatePasswordController(String userPassword,String userTel) {
		return userServiceImpl.updatePasswordService(userPassword, userTel);
	}
	//修改个人支付密码
	@RequestMapping(value="/updatePayPassword",method=RequestMethod.POST)
	public @ResponseBody DataEntity updatePayPasswordController(String userPayPassword,String userTel) {
		return userServiceImpl.updatePayPasswordService(userPayPassword, userTel);
	}
	//查看我的订单详情
	@RequestMapping(value="/myOrderView")
	public String myOrderViewController(long orderNum,Model model){
		model.addAttribute("order",goodsCarServiceImpl.selectMyOrderInforService(orderNum));
		return "orderinfor";
	}
	//订单操作(删除订单)
	@RequestMapping(value="/deleteMyOrder",method=RequestMethod.GET)
	public String deleteMyOrderInforController(long orderNum){
		goodsCarServiceImpl.deleteMyorderInforService(orderNum);
		return "redirect:userinfor/#myOrder";
	}
	
	//取消订单
	@RequestMapping(value="/cancelMyOrder",method=RequestMethod.POST)
	public @ResponseBody DataEntity cancelMyOrderController(long orderNum){
		return goodsCarServiceImpl.canaelMyOrderService(orderNum);
	}
	//用户支付订单
	@RequestMapping(value="/ablePayMoney",method=RequestMethod.POST)
	public @ResponseBody DataEntity ablePayMoneyController(double totalGoodsPrice,long orderNum,String userTel,String userPayPassword) {
		return goodsCarServiceImpl.ablePayMoneyService(totalGoodsPrice, orderNum, userTel,userPayPassword);
	}
}
