package com.yu.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yu.dao.GoodsDao;
import com.yu.dao.MyOrderDao;
import com.yu.entity.MyOrder;

/*
 * ʱ����ȣ��ڳ�������ʱ��������ÿ��һ������һ�Σ�
 * ���Ҷ�����δ֧���Ķ���������δ֧���Ķ������ж���ʱ���Ƿ񳬳�һ��Сʱ��
 * �����ͰѸö�����״̬��Ϊ2��2��״̬Ϊȡ��������
 */
@Service
public class TimeControlCancelOrder extends TimerTask{
	@Autowired
	private GoodsDao goodsDao;
	@Autowired
	private MyOrderDao myOrderDao;
	public void run() {
	List<MyOrder> list=myOrderDao.getOrderStatus();
	if(list.size()!=0){
		Date day=new Date();    
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		String time2[]=df.format(day).split("-| |:");
		for (MyOrder myOrder2 : list) {
			String time1[]=myOrder2.getDownOrderTime().split("-| |:");
			if(Math.abs(Integer.parseInt(time2[3])-Integer.parseInt(time1[3]))>1&&Integer.parseInt(time2[4])==Integer.parseInt(time1[4])&&Integer.parseInt(time2[5])==Integer.parseInt(time1[5])){
				myOrderDao.updateOrderStatus(2,myOrder2.getOrderNum());
				String GoodsAndNum[]=myOrder2.getGoodsCarAllGoods().split(",|X");
				for (int i = 0; i < GoodsAndNum.length; i=i+2) {
					goodsDao.addGoodsSurplus(Integer.parseInt(GoodsAndNum[i+1]), GoodsAndNum[i]);
				}
			}
		}
	}
	}
}
