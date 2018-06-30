package com.yu.service;

import java.util.List;
import java.util.Timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yu.dao.GoodsDao;
import com.yu.dao.redis.GoodsRedis;
import com.yu.dto.GoodsRedisEntity;
import com.yu.entity.Goods;
import com.yu.entity.Page;

@Service
public class GoodsServiceImpl implements GoodsService{
	@Autowired
	private GoodsDao goodsDao;
	@Autowired
	private TimeControlCancelOrder timeControlCancelOrder;

	int i=0;//��֤ʱ�����ִֻ��һ��
	//��ʼ����Ʒ��ʱ�����
	public List<Goods> allGoodsSelectService(){
		if(i==0){
		Timer timer=new Timer();
		timer.schedule(timeControlCancelOrder, 0,60*1000*60);
		i=1;
		}
		//��redis��������Ʒ��Ϣ����id123456��ΪredisΨһkeyֵ��ѯ��Ʒ��Ϣ
		GoodsRedis goodsRedis=new GoodsRedis("192.168.127.128",6379);
		List<Goods> list=goodsRedis.getGoods(123456);
		if(list==null){
		GoodsRedisEntity goodsRedisEntity=new GoodsRedisEntity(123456, goodsDao.allGoodsSelect());
		goodsRedis.putGoods(goodsRedisEntity);
		list=goodsRedis.getGoods(123456);
		}
		return list;
	}

	public List<Goods> oneGoodsSelectService(long goodsNum) {
		return goodsDao.oneGoodsSelect(goodsNum);
	}
	//�޸���Ʒ����Ϣ
	public int updateGoodsInforService(Goods goods){
		if(goodsDao.updateGoodsInfor(goods)>=1){
			//����޸ĳɹ���Ӧ���������»���
			GoodsRedis goodsRedis=new GoodsRedis("192.168.127.128",6379);
			GoodsRedisEntity goodsRedisEntity=new GoodsRedisEntity(123456, goodsDao.allGoodsSelect());
			goodsRedis.putGoods(goodsRedisEntity);
			return 1;
		}
		else {
			return 0;
		}
	}
	//����Ա������Ʒ��Ϣ��ɾ����Ʒ��Ϣ��
		public void delGoodsService(long goodsNum){
		goodsDao.delGoods(goodsNum);	
		}
}
