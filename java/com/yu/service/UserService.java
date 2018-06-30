package com.yu.service;


import com.yu.dto.DataEntity;

//用户操作接口
public interface UserService {
	//根据电话号码查询用户的信息，是否存在该用户（电话号码是唯一的）
	//若不存在，输出错误提示，若存在，与之输入的密码相匹配
	public DataEntity toUserTelSelectService(String userTel,String userPassword,boolean isrememberPassword,String piccode);
	//用户退出登录
	public void userExitLoginService();
	//注册时判断是否存在该用户（判断电话号码，电话为唯一）
	public DataEntity isExistUserTelService(String userTel);
}
