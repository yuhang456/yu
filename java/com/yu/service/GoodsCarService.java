package com.yu.service;

import java.util.List;

import com.yu.dto.DataEntity;
import com.yu.entity.Goods;
import com.yu.entity.GoodsCar;
import com.yu.entity.Page;

//����һ���������ﳵ�Ľӿ�
public interface GoodsCarService {
	// ��ʼ�����ﳵҳ��ķ�ҳҳ��
//	public Page countCarInitPageService(int pageNow);
//
//	// �����û���½�ĵ绰��Ϣ��ѯ�û��Ĺ��ﳵ��Ϣ
//	public List<GoodsCar> goodsCarSelectService(Page page);

	// �����û��������������Ʒ��Ϣ��ѯ��Ʒ�Ƿ�������Ҫ�󣨹�����������Ʒʣ���������ʧ�ܣ�
	// BuyNumberΪ���������
	public Goods addGoodsCarService(long goodsNum, int BuyNumber);

	// �ڹ��ﳵ�������Ʒ
	public DataEntity updataGoodsNumService(long goodsNum);

	// �ڹ��ﳵ�м�����Ʒ
	public DataEntity updataReduceGoodsNumService(long goodsNum);

	// ������Ʒ���ܼ�Ǯ
	public double totalPrice();
	
	//������Ʒ���ɾ����Ʒ��Ϣ
	public int deleteGoodsCarService(long goodsNum);
}
