package com.yu.service;

import javax.servlet.http.HttpSession;

import com.yu.dto.DataEntity;

public interface AdminService {
	//��֤����Աʱ���½
	public DataEntity checkAdminLoginService(String userTel,String userPassword,String checkcode,HttpSession session);
}
