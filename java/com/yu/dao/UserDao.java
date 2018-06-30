package com.yu.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yu.entity.User;

//操作用户登陆注册的数据接口
@Repository
public interface UserDao {
	//根据用户手机号码查询用户的信息
	public User toUserTelSelect(@Param("userTel")String userTel);
	//根据用户电话号码查询用户是否存在
	public int isExistUserTel(@Param("userTel")String userTel);
	//写入用户注册信息
	public int userRegister(User user);
	//更新用户信息
	public int userInforUpdata(User user);
	//更新用户的登密码
	public int updatePassword(@Param("userPassword")String userPassword,@Param("uuidString")String uuidString,@Param("userTel")String userTel);
	//修改用户的支付密码
	public int updatePayPassword(@Param("userPayPassword")String userPayPassword,@Param("uuidPasswordString")String uuidPasswordString,@Param("userTel")String userTel);
	//用户支付成功后减少用户余额
	public int reduceUserMoney(@Param("reduceMoney")double reduceMoney,@Param("userTel")String userTel);
	//根据用户电话号码查询用户的登陆密码
	public String getPasswordByUserName(@Param("userTel") String userTel);
	//根据用户电话号码查询用户密码的UUID加盐密钥
	public String getUUIDByUserName(@Param("userTel") String userTel);
	//根据用户电话号码查询用户的管理权限
	public String getRolesByUserName(@Param("userTel") String userTel);
	//根据电话号码查找用户支付密码的UUID加盐密钥
	public String getUUIDByUserPayPassword(@Param("userTel")String userTel);
	//根据用户电话查询用户支付密码
	public String getByUserPayPassword(@Param("userTel")String userTel);
	//查看全部会员的信息
	public List<User> allVipUserInfor();
}
