package com.yu.service;


import com.yu.dto.DataEntity;

//�û������ӿ�
public interface UserService {
	//���ݵ绰�����ѯ�û�����Ϣ���Ƿ���ڸ��û����绰������Ψһ�ģ�
	//�������ڣ����������ʾ�������ڣ���֮�����������ƥ��
	public DataEntity toUserTelSelectService(String userTel,String userPassword,boolean isrememberPassword,String piccode);
	//�û��˳���¼
	public void userExitLoginService();
	//ע��ʱ�ж��Ƿ���ڸ��û����жϵ绰���룬�绰ΪΨһ��
	public DataEntity isExistUserTelService(String userTel);
}
