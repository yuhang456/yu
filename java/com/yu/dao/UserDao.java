package com.yu.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yu.entity.User;

//�����û���½ע������ݽӿ�
@Repository
public interface UserDao {
	//�����û��ֻ������ѯ�û�����Ϣ
	public User toUserTelSelect(@Param("userTel")String userTel);
	//�����û��绰�����ѯ�û��Ƿ����
	public int isExistUserTel(@Param("userTel")String userTel);
	//д���û�ע����Ϣ
	public int userRegister(User user);
	//�����û���Ϣ
	public int userInforUpdata(User user);
	//�����û��ĵ�����
	public int updatePassword(@Param("userPassword")String userPassword,@Param("uuidString")String uuidString,@Param("userTel")String userTel);
	//�޸��û���֧������
	public int updatePayPassword(@Param("userPayPassword")String userPayPassword,@Param("uuidPasswordString")String uuidPasswordString,@Param("userTel")String userTel);
	//�û�֧���ɹ�������û����
	public int reduceUserMoney(@Param("reduceMoney")double reduceMoney,@Param("userTel")String userTel);
	//�����û��绰�����ѯ�û��ĵ�½����
	public String getPasswordByUserName(@Param("userTel") String userTel);
	//�����û��绰�����ѯ�û������UUID������Կ
	public String getUUIDByUserName(@Param("userTel") String userTel);
	//�����û��绰�����ѯ�û��Ĺ���Ȩ��
	public String getRolesByUserName(@Param("userTel") String userTel);
	//���ݵ绰��������û�֧�������UUID������Կ
	public String getUUIDByUserPayPassword(@Param("userTel")String userTel);
	//�����û��绰��ѯ�û�֧������
	public String getByUserPayPassword(@Param("userTel")String userTel);
	//�鿴ȫ����Ա����Ϣ
	public List<User> allVipUserInfor();
}
