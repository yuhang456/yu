package com.yu.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.yu.dao.UserDao;

public class CustomRealm extends AuthorizingRealm{
	@Autowired
	private UserDao userDao;
	{
		super.setName("CustomRealm");
	}
	//做授权的方法
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String userName=(String)principals.getPrimaryPrincipal();
		String role=userDao.getRolesByUserName(userName);
		SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
		simpleAuthorizationInfo.addRole(role);
		return simpleAuthorizationInfo;
	}
	//做认证的方法
	//AuthenticationToken主体传过来的认证信息
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//从主体传过来的认证信息
		String userTel=(String) token.getPrincipal();
		//通过用户名到数据库中获取凭证
		String password=userDao.getPasswordByUserName(userTel);
		if(password==null){
			return null;
		}
		String uuidString=userDao.getUUIDByUserName(userTel);
		SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(userTel,password,"CustomRealm");
		authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(uuidString));
		return authenticationInfo;
	}
}
