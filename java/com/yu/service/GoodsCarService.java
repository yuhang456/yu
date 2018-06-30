package com.yu.service;

import java.util.List;

import com.yu.dto.DataEntity;
import com.yu.entity.Goods;
import com.yu.entity.GoodsCar;
import com.yu.entity.Page;

//定义一个操作购物车的接口
public interface GoodsCarService {
	// 初始化购物车页面的分页页面
//	public Page countCarInitPageService(int pageNow);
//
//	// 根据用户登陆的电话信息查询用户的购物车信息
//	public List<GoodsCar> goodsCarSelectService(Page page);

	// 根据用户的添加数量和商品信息查询商品是否符合添加要求（购买量大于商品剩余量就添加失败）
	// BuyNumber为购买的数量
	public Goods addGoodsCarService(long goodsNum, int BuyNumber);

	// 在购物车中添加商品
	public DataEntity updataGoodsNumService(long goodsNum);

	// 在购物车中减少商品
	public DataEntity updataReduceGoodsNumService(long goodsNum);

	// 计算商品的总价钱
	public double totalPrice();
	
	//根据商品编号删除商品信息
	public int deleteGoodsCarService(long goodsNum);
}
