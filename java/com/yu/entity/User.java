package com.yu.entity;

//�����û�ʵ��
/*
 * 
 * ���� ���� �绰���� ���(Payable) ��ַ Ȩ��
 */
public class User {

	private String userName;// �û���������varchar,64��
	private int userSex;//0��1,0����Ů��1������
	private String userPassword;// �û�������(varchar,128)
	private String userTel;// �û��ĵ绰���루varchar,128��
	private double userPayable;// �û��˻��п�֧�������(double,128)
	private String userAddress;// �û��ĵ�ַ(varchar,256)
	private int userPower;// �û���Ȩ�ޣ��Ƿ�Ϊ��Ա,����0Ϊ�ǻ�Ա��1Ϊ��Ա(int,2)
	private String userPayPassword;//�û���֧������
	private String uuidString;//UUID����ַ�����Ϊ��½����md5������ֵ
	private String power;//�жϸ��û���Ȩ��
	private String uuidPasswordString;//UUID����ַ�����Ϊ֧������md5������ֵ
	
	public User() {
		//super();
		// TODO Auto-generated constructor stub
	}

	public User(String userName, int userSex, String userPassword, String userTel, double userPayable,
			String userAddress, int userPower, String userPayPassword, String uuidString, String power,String uuidPasswordString) {
		//super();
		this.userName = userName;
		this.userSex = userSex;
		this.userPassword = userPassword;
		this.userTel = userTel;
		this.userPayable = userPayable;
		this.userAddress = userAddress;
		this.userPower = userPower;
		this.userPayPassword = userPayPassword;
		this.uuidString=uuidString;
		this.power=power;
		this.uuidPasswordString=uuidPasswordString;
	}

	
	@Override
	public String toString() {
		return "User [userName=" + userName + ", userSex=" + userSex + ", userPassword=" + userPassword + ", userTel="
				+ userTel + ", userPayable=" + userPayable + ", userAddress=" + userAddress + ", userPower=" + userPower
				+ ", userPayPassword=" + userPayPassword + "]";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	

	public int getUserSex() {
		return userSex;
	}

	public void setUserSex(int userSex) {
		this.userSex = userSex;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public double getUserPayable() {
		return userPayable;
	}

	public void setUserPayable(double userPayable) {
		this.userPayable = userPayable;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public int getUserPower() {
		return userPower;
	}

	public void setUserPower(int userPower) {
		this.userPower = userPower;
	}

	public String getUserPayPassword() {
		return userPayPassword;
	}

	public void setUserPayPassword(String userPayPassword) {
		this.userPayPassword = userPayPassword;
	}

	public String getUuidString() {
		return uuidString;
	}

	public void setUuidString(String uuidString) {
		this.uuidString = uuidString;
	}

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public String getUuidPasswordString() {
		return uuidPasswordString;
	}

	public void setUuidPasswordString(String uuidPasswordString) {
		this.uuidPasswordString = uuidPasswordString;
	}
	

}
