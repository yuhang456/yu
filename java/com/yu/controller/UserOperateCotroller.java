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
	//�޸ĸ�����Ϣ
	@RequestMapping(value="/userInforUpdata",method=RequestMethod.POST)
	public @ResponseBody DataEntity userInformationUpdataController(User user){
		DataEntity dataEntity=userServiceImpl.userInforUpdataService(user);
		return dataEntity;
	}
	//�޸ĸ��˵�¼����
	@RequestMapping(value="/updatePassword",method=RequestMethod.POST)
	public @ResponseBody DataEntity updatePasswordController(String userPassword,String userTel) {
		return userServiceImpl.updatePasswordService(userPassword, userTel);
	}
	//�޸ĸ���֧������
	@RequestMapping(value="/updatePayPassword",method=RequestMethod.POST)
	public @ResponseBody DataEntity updatePayPasswordController(String userPayPassword,String userTel) {
		return userServiceImpl.updatePayPasswordService(userPayPassword, userTel);
	}
	//�鿴�ҵĶ�������
	@RequestMapping(value="/myOrderView")
	public String myOrderViewController(long orderNum,Model model){
		model.addAttribute("order",goodsCarServiceImpl.selectMyOrderInforService(orderNum));
		return "orderinfor";
	}
	//��������(ɾ������)
	@RequestMapping(value="/deleteMyOrder",method=RequestMethod.GET)
	public String deleteMyOrderInforController(long orderNum){
		goodsCarServiceImpl.deleteMyorderInforService(orderNum);
		return "redirect:userinfor/#myOrder";
	}
	
	//ȡ������
	@RequestMapping(value="/cancelMyOrder",method=RequestMethod.POST)
	public @ResponseBody DataEntity cancelMyOrderController(long orderNum){
		return goodsCarServiceImpl.canaelMyOrderService(orderNum);
	}
	//�û�֧������
	@RequestMapping(value="/ablePayMoney",method=RequestMethod.POST)
	public @ResponseBody DataEntity ablePayMoneyController(double totalGoodsPrice,long orderNum,String userTel,String userPayPassword) {
		return goodsCarServiceImpl.ablePayMoneyService(totalGoodsPrice, orderNum, userTel,userPayPassword);
	}
}
