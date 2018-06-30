package com.yu.service;

import javax.servlet.http.HttpSession;

import com.yu.dto.DataEntity;

public interface AdminService {
	//验证管理员时候登陆
	public DataEntity checkAdminLoginService(String userTel,String userPassword,String checkcode,HttpSession session);
}
