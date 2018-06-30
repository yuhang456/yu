package com.yu.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yu.entity.MyOrder;

@Repository
public interface MyOrderDao {
	//将订单信息添加到数据库
	public int insertUserOrder(MyOrder myOrder);
	//根据订单号查询是否存在该订单，如果存在，将重新生成
	public String isExistOrderNum(@Param("orderNum")long orderNum);
	//根据订单编号读取我的订单信息
	public MyOrder selectMyOrderInfor(@Param("orderNum")long orderNum);
	//查询订单状态为未支付的订单
	public List<MyOrder> getOrderStatus();
	//修改订单的状态
	public int updateOrderStatus(@Param("orderStatus")int orderStatus,@Param("orderNum")long orderNum);
	//根据手机号查询所有该用户的所有订单信息
	public List<MyOrder> selectAllMyOrderInfor(@Param("userTel")String userTel);
	//根据订单编号删除订单
	public int deleteMyOrderInfor(@Param("orderNum")long orderNum);
	//查询所有订单
	public List<MyOrder> allOrderInfor();
}
