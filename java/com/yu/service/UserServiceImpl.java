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

//ʵ���û������ӿ�
//�û���½
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
			dataEntity=new DataEntity("��֤�����");
			return dataEntity;
		}
		Subject subject=SecurityUtils.getSubject();
		UsernamePasswordToken token=new UsernamePasswordToken(userTel, userPassword);
		try {
			subject.login(token);
			token.setRememberMe(isrememberPassword);
		} catch (AuthenticationException e) {
			dataEntity=new DataEntity("�ֻ��Ż��������");
			return dataEntity;
		}
		dataEntity=new DataEntity("success");
		User user=userDao.toUserTelSelect(userTel);
		session.setAttribute("user", user);	
		return dataEntity;
		}
			

	//�û��˳���¼
	public void userExitLoginService() {
	//shiro������˳��û�
	Subject subject=SecurityUtils.getSubject();
	subject.logout();
	//���Session�����е�������Ϣ
	session.invalidate();
	}
	
	//ע��ʱ�ж��Ƿ���ڸ��û����жϵ绰���룬�绰ΪΨһ��
	public DataEntity isExistUserTelService(String userTel){
		DataEntity dataEntity;
		boolean result=userTel.matches("[0-9]+");
		if(!result){
		dataEntity = new DataEntity("�绰����ֻ��Ϊ����");
		return dataEntity;
		}
		int isExist=userDao.isExistUserTel(userTel);
		if(isExist==0){
		dataEntity=new DataEntity("");
		}
		else{
		dataEntity=new DataEntity("�ú����Ѿ�ע��");
		}
		return dataEntity;
	}
	//�û�ע����Ϣ
	public DataEntity userRegisterService(User user){
		//�������UUID�ַ�����Ϊmd5������ֵ
		String uuidString1=UUID.randomUUID().toString().replace("-", "");
		user.setUuidString(uuidString1);
		Md5Hash md5Hash=new Md5Hash(user.getUserPassword(),uuidString1);
		user.setUserPassword(md5Hash.toString());
		//֧������Ҳ���ܼ���
		String uuidString2=UUID.randomUUID().toString().replace("-", "");
		Md5Hash md5Hash2=new Md5Hash(user.getUserPayPassword(),uuidString2);
		user.setUuidPasswordString(uuidString2);
		user.setUserPayPassword(md5Hash2.toString());
		DataEntity dataEntity;
		int isSuccess=userDao.userRegister(user);
		if(isSuccess>0){
		dataEntity=new DataEntity("ע��ɹ����ɵ���ҳ���½����");
		}
		else{
		dataEntity=new DataEntity("ע��ʧ��");
		}
		return dataEntity;
	}
	//�û��޸ĸ�����Ϣ
	public DataEntity userInforUpdataService(User user) {
		DataEntity dataEntity;
		if(userDao.userInforUpdata(user)>0){
			dataEntity=new DataEntity("�޸ĳɹ�");
			session.setAttribute("user", userDao.toUserTelSelect(user.getUserTel()));
		}
		else{
			dataEntity=new DataEntity("�޸�ʧ��");
		}
		return dataEntity;
	}
	//�����û��ĵ�¼����
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
	
	//�޸��û���֧������
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
	
	//�û���½ʱ������֤��
	public void createCodeService(HttpServletResponse response) throws IOException {
		BufferedImage bi = new BufferedImage(75,30,BufferedImage.TYPE_INT_RGB);
		Graphics g = bi.getGraphics();
		//���������ɫ
		Color c = new Color(200,150,255);
		//���������С��ʽ
		Font font=new Font("����",Font.PLAIN,25);
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
