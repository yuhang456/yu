package com.yu.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yu.entity.MyOrder;

@Repository
public interface MyOrderDao {
	//��������Ϣ��ӵ����ݿ�
	public int insertUserOrder(MyOrder myOrder);
	//���ݶ����Ų�ѯ�Ƿ���ڸö�����������ڣ�����������
	public String isExistOrderNum(@Param("orderNum")long orderNum);
	//���ݶ�����Ŷ�ȡ�ҵĶ�����Ϣ
	public MyOrder selectMyOrderInfor(@Param("orderNum")long orderNum);
	//��ѯ����״̬Ϊδ֧���Ķ���
	public List<MyOrder> getOrderStatus();
	//�޸Ķ�����״̬
	public int updateOrderStatus(@Param("orderStatus")int orderStatus,@Param("orderNum")long orderNum);
	//�����ֻ��Ų�ѯ���и��û������ж�����Ϣ
	public List<MyOrder> selectAllMyOrderInfor(@Param("userTel")String userTel);
	//���ݶ������ɾ������
	public int deleteMyOrderInfor(@Param("orderNum")long orderNum);
	//��ѯ���ж���
	public List<MyOrder> allOrderInfor();
}
