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
	//��ת������Աҳ��
	@RequiresRoles("admin")
	@RequestMapping("/admin")
	public String adminLogin(){
		
		return "admin";
	}
	//��Ȩʧ����תҳ��
	@RequestMapping("/error")
	public String errorInfor(){
		
		return "error";
	}
	
	//����Ա��¼ҳ��
	@RequestMapping("/admin_login")
	public String admin_login(){
		
		return "admin_login";
	}
	
	//��֤����Ա�Ƿ��¼�ɹ�
	@ResponseBody
	@RequestMapping(value="/checkAdminLogin",method=RequestMethod.POST)
	public DataEntity checkAdminLogin(String userTel,String userPassword,String checkcode,HttpSession session){
		return adminServiceImpl.checkAdminLoginService(userTel, userPassword, checkcode, session);
	}
	//����Ա��ӭ����
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
	
	//��Ա����ҳ�桪��������Ա�б�
	@RequiresRoles("admin")
	@RequestMapping(value="/admin/admin_member",method=RequestMethod.GET)
	public String admin_member(Model model){
		model.addAttribute("user", adminServiceImpl.allVipUserInforService());
		
		return "admin_member";
	}
	//��Ա����ҳ�桪��������Ա������
	@RequiresRoles("admin")
	@RequestMapping(value="/admin/admin_member_del",method=RequestMethod.GET)
	public String admin_member_del(){
		
		
		return "admin_member";
	}
	
	//��������ҳ��
	@RequiresRoles("admin")
	@RequestMapping(value="/admin/admin_order",method=RequestMethod.GET)
	public String admin_order(Model model){
		model.addAttribute("order",adminServiceImpl.allOrderInforService() );
		return "admin_order";
	}
	//�����Ʒҳ��
	@RequiresRoles("admin")
	@RequestMapping(value="/admin/admin_addgoods",method=RequestMethod.GET)
	public String admin_addgoods(Goods goods,Model model){
		System.out.println(goods.getGoodsNum());
		System.out.println(goods.getGoodsName());
		model.addAttribute("goods",goods);
		return "admin_addgoods";
	}
	//�޸���Ʒ����
	@ResponseBody
	@RequiresRoles("admin")
	@RequestMapping(value="/admin/admin_updategoods")
	public int admin_updateGoods(Goods goods){
		//�޸ĳɹ��󷵻�1
		return goodsServiceImpl.updateGoodsInforService(goods);
	}
	//ɾ����Ʒ��Ϣ
	@ResponseBody
	@RequiresRoles("admin")
	@RequestMapping("/admin/admin_delgoods")
	public int admin_delGoods(long goodsNum){
		goodsServiceImpl.delGoodsService(goodsNum);
		return 1;
	}
}
