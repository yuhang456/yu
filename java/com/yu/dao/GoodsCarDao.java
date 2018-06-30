package com.yu.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.yu.entity.GoodsCar;

//购物车操作的数据接口
@Repository //spring-dao已将com.yu.dao注入到spring容器中，这里没必要再次注入，只是标识这是数据库操作
public interface GoodsCarDao {
	//根据用户电话号码查询用户的购物车的信息
	public List<GoodsCar> allGoodsCarSelect(@Param("userTel")String userTel);
	//传入分页所需参数
	//public List<GoodsCar> goodsCarSelect(@Param("userTel")String userTel,@Param("startPos")int startPos,@Param("pageSize")int pageSize);
	//向购物车中添加商品
	public int goodsCarInsert(GoodsCar goodsCar);
	//根据用户的电话和商品编号更新用户的购买数量
	public int goodsBuyNumUpdate(@Param("goodsBuyNum")int goodsBuyNum,@Param("userTel")String userTel,@Param("goodsNum")long goodsNum);
	//根据用户的电话和商品编号查询是否存在该商品，并返回商品购买数量
	public Integer isExistGoods(@Param("userTel")String userTel,@Param("goodsNum")long goodsNum);
	//查出购物车的全部数量，做分页处理
	public int allGoodCarCount(@Param("userTel")String userTel);
	//根据商品编号删除商品信息
	public int deleteGoodsCar(@Param("goodsNum")long goodsNum);
	//根据用户电话删除清空购物车
	public int deleteAllGoodsCar(@Param("userTel")String userTel);
	
}

