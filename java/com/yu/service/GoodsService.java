package com.yu.service;

import java.util.List;
import com.yu.entity.Goods;
import com.yu.entity.Page;

//����һ��������Ʒ�Ľӿ�
public interface GoodsService {
	// ��ѯһ����Ʒ��ȫ����Ϣ��������Ʒ��Ų�ѯ
	public List<Goods> oneGoodsSelectService(long goodsNum);
	
}
