package com.yu.service;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yu.dao.UserDao;
import com.yu.dto.DataEntity;
import com.yu.entity.User;

//实现用户操作接口
//用户登陆
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;
	@Autowired
	private HttpSession session;
	
	public DataEntity toUserTelSelectService(String userTel,String userPassword,boolean isrememberPassword,String piccode) {
		DataEntity dataEntity;
		String checkcode=(String) session.getAttribute("piccode");
		if (!piccode.equals(checkcode)) {
			dataEntity=new DataEntity("验证码错误");
			return dataEntity;
		}
		Subject subject=SecurityUtils.getSubject();
		UsernamePasswordToken token=new UsernamePasswordToken(userTel, userPassword);
		try {
			subject.login(token);
			token.setRememberMe(isrememberPassword);
		} catch (AuthenticationException e) {
			dataEntity=new DataEntity("手机号或密码错误");
			return dataEntity;
		}
		dataEntity=new DataEntity("success");
		User user=userDao.toUserTelSelect(userTel);
		session.setAttribute("user", user);	
		return dataEntity;
		}
			

	//用户退出登录
	public void userExitLoginService() {
	//shiro框架中退出用户
	Subject subject=SecurityUtils.getSubject();
	subject.logout();
	//清除Session对象中的所有信息
	session.invalidate();
	}
	
	//注册时判断是否存在该用户（判断电话号码，电话为唯一）
	public DataEntity isExistUserTelService(String userTel){
		DataEntity dataEntity;
		boolean result=userTel.matches("[0-9]+");
		if(!result){
		dataEntity = new DataEntity("电话号码只能为数字");
		return dataEntity;
		}
		int isExist=userDao.isExistUserTel(userTel);
		if(isExist==0){
		dataEntity=new DataEntity("");
		}
		else{
		dataEntity=new DataEntity("该号码已经注册");
		}
		return dataEntity;
	}
	//用户注册信息
	public DataEntity userRegisterService(User user){
		//随机生成UUID字符串作为md5加密盐值
		String uuidString1=UUID.randomUUID().toString().replace("-", "");
		user.setUuidString(uuidString1);
		Md5Hash md5Hash=new Md5Hash(user.getUserPassword(),uuidString1);
		user.setUserPassword(md5Hash.toString());
		//支付密码也加密加盐
		String uuidString2=UUID.randomUUID().toString().replace("-", "");
		Md5Hash md5Hash2=new Md5Hash(user.getUserPayPassword(),uuidString2);
		user.setUuidPasswordString(uuidString2);
		user.setUserPayPassword(md5Hash2.toString());
		DataEntity dataEntity;
		int isSuccess=userDao.userRegister(user);
		if(isSuccess>0){
		dataEntity=new DataEntity("注册成功，可到主页面登陆购物");
		}
		else{
		dataEntity=new DataEntity("注册失败");
		}
		return dataEntity;
	}
	//用户修改个人信息
	public DataEntity userInforUpdataService(User user) {
		DataEntity dataEntity;
		if(userDao.userInforUpdata(user)>0){
			dataEntity=new DataEntity("修改成功");
			session.setAttribute("user", userDao.toUserTelSelect(user.getUserTel()));
		}
		else{
			dataEntity=new DataEntity("修改失败");
		}
		return dataEntity;
	}
	//更新用户的登录密码
	public DataEntity updatePasswordService(String userPassword,String userTel) {
		DataEntity dataEntity;
		String uuidString=UUID.randomUUID().toString().replace("-", "");
		Md5Hash md5Hash=new Md5Hash(userPassword,uuidString);
		if(userDao.updatePassword(md5Hash.toString(), uuidString,userTel)>0){
			dataEntity=new DataEntity("success");
		}
		else{
			dataEntity=new DataEntity("error");
		}
		return dataEntity;
	}
	
	//修改用户的支付密码
	public DataEntity updatePayPasswordService(String userPayPassword,String userTel) {
		DataEntity dataEntity;
		String uuidPasswordString=UUID.randomUUID().toString().replace("-", "");
		Md5Hash md5Hash=new Md5Hash(userPayPassword,uuidPasswordString);
		if(userDao.updatePayPassword(md5Hash.toString(),uuidPasswordString,userTel)>0){
			dataEntity=new DataEntity("success");
			session.setAttribute("user", userDao.toUserTelSelect(userTel));
		}
		else{
			dataEntity=new DataEntity("error");
		}
		return dataEntity;
	}
	
	//用户登陆时生成验证码
	public void createCodeService(HttpServletResponse response) throws IOException {
		BufferedImage bi = new BufferedImage(75,30,BufferedImage.TYPE_INT_RGB);
		Graphics g = bi.getGraphics();
		//设置填充颜色
		Color c = new Color(200,150,255);
		//设置字体大小样式
		Font font=new Font("宋体",Font.PLAIN,25);
		g.setColor(c);
		g.setFont(font);
		g.fillRect(0, 0, 75, 30);
		
		char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
		Random r = new Random();
		int len=ch.length,index;
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<4; i++){
			index = r.nextInt(len);
			g.setColor(new Color(r.nextInt(88),r.nextInt(188),r.nextInt(255)));
			g.drawString(ch[index]+"", (i*15)+3, 18);
			sb.append(ch[index]);
		}
		session.setAttribute("piccode", sb.toString());
		ImageIO.write(bi, "JPG", response.getOutputStream());
	}
}
