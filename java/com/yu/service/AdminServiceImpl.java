package com.yu.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yu.dao.GoodsDao;
import com.yu.dao.MyOrderDao;
import com.yu.dao.UserDao;
import com.yu.dto.DataEntity;
import com.yu.entity.MyOrder;
import com.yu.entity.User;
@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
	private UserDao userDao;
	@Autowired
	private MyOrderDao myOrderDao;
	public DataEntity checkAdminLoginService(String userTel,String userPassword,String checkcode,HttpSession session) {
		DataEntity dataEntity;
		if(!session.getAttribute("piccode").equals(checkcode)){
		dataEntity=new DataEntity("��֤�����");
		return dataEntity;
		}
		Subject subject=SecurityUtils.getSubject();
		UsernamePasswordToken token=new UsernamePasswordToken(userTel, userPassword);
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			dataEntity = new DataEntity("�ֻ��Ż��������");
			return dataEntity;
		}
		dataEntity = new DataEntity("success");
		return dataEntity;
	}
	//����Ա�鿴���л�Ա��Ϣ
	public List<User> allVipUserInforService(){
		return userDao.allVipUserInfor();
	}
	
	/**
	 * �鿴���ж�����Ϣ
	 * @return  ���ض�������
	 */
	public List<MyOrder> allOrderInforService(){
		return myOrderDao.allOrderInfor();
	}
}
