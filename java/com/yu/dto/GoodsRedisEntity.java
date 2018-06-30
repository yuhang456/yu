package com.yu.dto;
/*
 * 
 * 该类用来联系Goods实体类，用来序列化list，因为protostuff不能直接序列化map和list，现在即用一个对象
 * 来转载list，序列化对象即序列化了list
 */

import java.util.List;

import com.yu.entity.Goods;

public class GoodsRedisEntity {
	/*读取商品信息的唯一id作为key值，用来查找商品信息
	 * 
	 */
	private int goodsListId;
	//创建list，方便序列化goods的对象
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
