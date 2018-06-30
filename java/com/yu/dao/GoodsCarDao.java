package com.yu.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.yu.entity.GoodsCar;

//���ﳵ���������ݽӿ�
@Repository //spring-dao�ѽ�com.yu.daoע�뵽spring�����У�����û��Ҫ�ٴ�ע�룬ֻ�Ǳ�ʶ�������ݿ����
public interface GoodsCarDao {
	//�����û��绰�����ѯ�û��Ĺ��ﳵ����Ϣ
	public List<GoodsCar> allGoodsCarSelect(@Param("userTel")String userTel);
	//�����ҳ�������
	//public List<GoodsCar> goodsCarSelect(@Param("userTel")String userTel,@Param("startPos")int startPos,@Param("pageSize")int pageSize);
	//���ﳵ�������Ʒ
	public int goodsCarInsert(GoodsCar goodsCar);
	//�����û��ĵ绰����Ʒ��Ÿ����û��Ĺ�������
	public int goodsBuyNumUpdate(@Param("goodsBuyNum")int goodsBuyNum,@Param("userTel")String userTel,@Param("goodsNum")long goodsNum);
	//�����û��ĵ绰����Ʒ��Ų�ѯ�Ƿ���ڸ���Ʒ����������Ʒ��������
	public Integer isExistGoods(@Param("userTel")String userTel,@Param("goodsNum")long goodsNum);
	//������ﳵ��ȫ������������ҳ����
	public int allGoodCarCount(@Param("userTel")String userTel);
	//������Ʒ���ɾ����Ʒ��Ϣ
	public int deleteGoodsCar(@Param("goodsNum")long goodsNum);
	//�����û��绰ɾ����չ��ﳵ
	public int deleteAllGoodsCar(@Param("userTel")String userTel);
	
}

