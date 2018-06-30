package com.yu.entity;

//构建用户实体
/*
 * 
 * 姓名 密码 电话号码 余额(Payable) 地址 权限
 */
public class User {

	private String userName;// 用户的姓名（varchar,64）
	private int userSex;//0和1,0代表女，1代表男
	private String userPassword;// 用户的密码(varchar,128)
	private String userTel;// 用户的电话号码（varchar,128）
	private double userPayable;// 用户账户中可支付的余额(double,128)
	private String userAddress;// 用户的地址(varchar,256)
	private int userPower;// 用户的权限，是否为会员,其中0为非会员，1为会员(int,2)
	private String userPayPassword;//用户的支付密码
	private String uuidString;//UUID随机字符串作为登陆密码md5加密盐值
	private String power;//判断该用户的权限
	private String uuidPasswordString;//UUID随机字符串作为支付密码md5加密盐值
	
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
