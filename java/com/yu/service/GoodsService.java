package com.yu.service;

import java.util.List;
import com.yu.entity.Goods;
import com.yu.entity.Page;

//定义一个操作商品的接口
public interface GoodsService {
	// 查询一件商品的全部信息，根据商品编号查询
	public List<Goods> oneGoodsSelectService(long goodsNum);
	
}
