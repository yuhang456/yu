package com.yu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yu.entity.Goods;

//��Ʒ���������ݽӿ�
@Repository
public interface GoodsDao {
	//��ѯ������Ʒ����Ϣ
public List<Goods> allGoodsSelect();
//public List<Goods> allGoodsSelect(@Param("goodsName")String goodsName,@Param("startPos")int startPos,@Param("pageSize")int pageSize);
//������Ʒ��Ų�ѯ������Ʒ��Ϣ
public List<Goods> oneGoodsSelect(@Param("goodsNum")long goodsNum);
//��ѯ��Ʒ������
//public int AllCount(@Param("goodsName")String goodsName);
//������Ʒ�Ŀ��
public int reduceGoodsSurplus(@Param("reGoodsSurplus")int reGoodsSurplus,@Param("goodsNum")long goodsNum);
//ʱ�䵽��δ֧���������Զ�ȡ����������Ʒ�������
public int addGoodsSurplus(@Param("adGoodsSurplus")int adGoodsSurplus,@Param("goodsName")String goodsName);
//����Ա�޸���Ʒ��Ϣ
public int updateGoodsInfor(Goods goods);
//����Աɾ����Ʒ��Ϣ
public int delGoods(@Param("goodsNum")long goodsNum);
}
