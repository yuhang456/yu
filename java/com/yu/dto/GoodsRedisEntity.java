package com.yu.dto;
/*
 * 
 * ����������ϵGoodsʵ���࣬�������л�list����Ϊprotostuff����ֱ�����л�map��list�����ڼ���һ������
 * ��ת��list�����л��������л���list
 */

import java.util.List;

import com.yu.entity.Goods;

public class GoodsRedisEntity {
	/*��ȡ��Ʒ��Ϣ��Ψһid��Ϊkeyֵ������������Ʒ��Ϣ
	 * 
	 */
	private int goodsListId;
	//����list���������л�goods�Ķ���
	private List<Goods> list;
	
	
	public GoodsRedisEntity() {
		//super();
		// TODO Auto-generated constructor stub
	}


	public GoodsRedisEntity(int goodsListId, List<Goods> list) {
		//super();
		this.goodsListId = goodsListId;
		this.list = list;
	}


	public int getGoodsListId() {
		return goodsListId;
	}


	public void setGoodsListId(int goodsListId) {
		this.goodsListId = goodsListId;
	}


	public List<Goods> getList() {
		return list;
	}


	public void setList(List<Goods> list) {
		this.list = list;
	}
	
	
	
	
	
}
