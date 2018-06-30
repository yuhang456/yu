package com.yu.controller;


import javax.servlet.http.HttpSession;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yu.dto.DataEntity;
import com.yu.entity.Goods;
import com.yu.service.AdminServiceImpl;
import com.yu.service.GoodsServiceImpl;

@Controller
@RequestMapping("/")
public class AdminController {
	@Autowired
	private GoodsServiceImpl goodsServiceImpl;
	@Autowired
	private AdminServiceImpl adminServiceImpl;
	//跳转到管理员页面
	@RequiresRoles("admin")
	@RequestMapping("/admin")
	public String adminLogin(){
		
		return "admin";
	}
	//授权失败跳转页面
	@RequestMapping("/error")
	public String errorInfor(){
		
		return "error";
	}
	
	//管理员登录页面
	@RequestMapping("/admin_login")
	public String admin_login(){
		
		return "admin_login";
	}
	
	//验证管理员是否登录成功
	@ResponseBody
	@RequestMapping(value="/checkAdminLogin",method=RequestMethod.POST)
	public DataEntity checkAdminLogin(String userTel,String userPassword,String checkcode,HttpSession session){
		return adminServiceImpl.checkAdminLoginService(userTel, userPassword, checkcode, session);
	}
	//管理员欢迎界面
	@RequiresRoles("admin")
	@RequestMapping(value="admin/admin_welcome",method=RequestMethod.GET)
	public String admin_welcome(){
		
		return "admin_welcome";
	}
	@RequiresRoles("admin")
	@RequestMapping(value = "/admin/admin_manage",method=RequestMethod.GET)
	public String admin_manage(Model model) {
		model.addAttribute("goods", goodsServiceImpl.allGoodsSelectService());
		return "admin_manage";
	}
	
	//会员管理页面————会员列表
	@RequiresRoles("admin")
	@RequestMapping(value="/admin/admin_member",method=RequestMethod.GET)
	public String admin_member(Model model){
		model.addAttribute("user", adminServiceImpl.allVipUserInforService());
		
		return "admin_member";
	}
	//会员管理页面————会员黑名单
	@RequiresRoles("admin")
	@RequestMapping(value="/admin/admin_member_del",method=RequestMethod.GET)
	public String admin_member_del(){
		
		
		return "admin_member";
	}
	
	//订单管理页面
	@RequiresRoles("admin")
	@RequestMapping(value="/admin/admin_order",method=RequestMethod.GET)
	public String admin_order(Model model){
		model.addAttribute("order",adminServiceImpl.allOrderInforService() );
		return "admin_order";
	}
	//添加商品页面
	@RequiresRoles("admin")
	@RequestMapping(value="/admin/admin_addgoods",method=RequestMethod.GET)
	public String admin_addgoods(Goods goods,Model model){
		System.out.println(goods.getGoodsNum());
		System.out.println(goods.getGoodsName());
		model.addAttribute("goods",goods);
		return "admin_addgoods";
	}
	//修改商品操作
	@ResponseBody
	@RequiresRoles("admin")
	@RequestMapping(value="/admin/admin_updategoods")
	public int admin_updateGoods(Goods goods){
		//修改成功后返回1
		return goodsServiceImpl.updateGoodsInforService(goods);
	}
	//删除商品信息
	@ResponseBody
	@RequiresRoles("admin")
	@RequestMapping("/admin/admin_delgoods")
	public int admin_delGoods(long goodsNum){
		goodsServiceImpl.delGoodsService(goodsNum);
		return 1;
	}
}
