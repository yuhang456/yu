package com.yu.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yu.dto.DataEntity;
import com.yu.entity.MyOrder;
import com.yu.entity.User;
import com.yu.service.GoodsCarServiceImpl;
import com.yu.service.UserServiceImpl;

//实现用户登陆注册操作
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired
	private GoodsCarServiceImpl goodsCarServiceImpl;
	//用户登录
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public @ResponseBody DataEntity userLoginController(String userTel,String userPassword,boolean isrememberPassword,String piccode){
		DataEntity dataEntity=userServiceImpl.toUserTelSelectService(userTel, userPassword,isrememberPassword,piccode);
		return dataEntity;
	}
	//用户退出登录
	@RequestMapping(value="/exit")
	public String userExitLoginController() {
		userServiceImpl.userExitLoginService();
		return "redirect:/index";
	}
	//验证用户的手机号是否被注册
	@RequestMapping(value="/isExistUserTel",method=RequestMethod.POST)
	public @ResponseBody DataEntity isExistUserTelController(String userTel){
		DataEntity dataEntity=userServiceImpl.isExistUserTelService(userTel);
		return dataEntity;
	}
	//注册用户信息
	@RequestMapping(value="/userRegister",method=RequestMethod.POST)
	public @ResponseBody DataEntity userRegisterController(User user){
		DataEntity dataEntity=userServiceImpl.userRegisterService(user);
		return dataEntity;
	}
	//进入用户个人信息主页
	@RequiresRoles("user")
	@RequestMapping("/userinfor")
	public String UserOperateTest(Model model) {
		List<MyOrder> list=goodsCarServiceImpl.selectAllMyOrderInforService();
		model.addAttribute("order", list);
		return "userinfor";
	}
	//用户登陆验证码
	@RequestMapping("/code")
	public void createCodeCotroller(HttpServletResponse response) throws IOException {
		userServiceImpl.createCodeService(response);
	}
}
