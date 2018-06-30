package com.yu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yu.entity.Goods;

//商品操作的数据接口
@Repository
public interface GoodsDao {
	//查询所有商品的信息
public List<Goods> allGoodsSelect();
//public List<Goods> allGoodsSelect(@Param("goodsName")String goodsName,@Param("startPos")int startPos,@Param("pageSize")int pageSize);
//根据商品编号查询单个商品信息
public List<Goods> oneGoodsSelect(@Param("goodsNum")long goodsNum);
//查询商品总数量
//public int AllCount(@Param("goodsName")String goodsName);
//减少商品的库存
public int reduceGoodsSurplus(@Param("reGoodsSurplus")int reGoodsSurplus,@Param("goodsNum")long goodsNum);
//时间到了未支付订单，自动取消订单，商品库存增加
public int addGoodsSurplus(@Param("adGoodsSurplus")int adGoodsSurplus,@Param("goodsName")String goodsName);
//管理员修改商品信息
public int updateGoodsInfor(Goods goods);
//管理员删除商品信息
public int delGoods(@Param("goodsNum")long goodsNum);
}
