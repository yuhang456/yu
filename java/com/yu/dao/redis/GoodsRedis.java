package com.yu.dao.redis;
/*
 * �������л�ʵ�ֵ�redis�����������������Զ������л�����Ϊjava�Դ����л�
 * �޷����ظ߲����ԣ����ҷǳ�ռ�ÿռ���ʱ�䣬ת��ʱռ��ʱ�䣬���л���ռ�ÿռ䣬
 * �����Զ�������л����ùȸ迪Դ������protostuff������ת��ʱ��ΪJDK�Դ����л�
 * �������ķ�֮һ֮�࣬����ת��ռ�ÿռ�������JDK����������������
 * ʹ��protostuff���л�����ʱ�������л��Ķ��������pojo���󣨾߱�setter/getter��
 * ����Jedis��û��ʵ���ڲ����л���������Java���õ����л����������ֲ��ߣ�
 * ������Ҫ���Ǹ߲����Ż������������ǲ��ÿ�Դ�����ṩ�ĸ������ܵ��Զ������л�����protostuff
 */

import java.util.List;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.yu.dto.GoodsRedisEntity;
import com.yu.entity.Goods;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class GoodsRedis {
	private final JedisPool jedisPool;
	public GoodsRedis(String ip,int port) {
		jedisPool=new JedisPool(ip,port);
	}
	private RuntimeSchema<GoodsRedisEntity> schema = RuntimeSchema.createFrom(GoodsRedisEntity.class);
	public List<Goods> getGoods(int goodsListId){
		//redis�����߼�
		try {
			Jedis jedis=jedisPool.getResource();
			try {
				String key="goodslist:"+goodsListId;
				//��ȡkey��Ӧ�Ķ����byteֵ
				byte[] bytes=jedis.get(key.getBytes());
				//�����ػ�ȡ��
				if(bytes!=null){
					//�����ն���
					GoodsRedisEntity goodsRedisEntity=schema.newMessage();
					ProtostuffIOUtil.mergeFrom(bytes, goodsRedisEntity, schema);
					//���󱻷����л�
					return goodsRedisEntity.getList();
				}
			} finally{
				jedis.close();
			}
		} catch (Exception e) {
			System.out.println("��������ʧ��");
		}
		return null;
	}
	
	public String putGoods(GoodsRedisEntity goodsRedisEntity) {
		//set Object -> ���л� ->byte[]
	try {
		Jedis jedis=jedisPool.getResource();
		try {
			String key="goodslist:"+goodsRedisEntity.getGoodsListId();
			byte[] bytes=ProtostuffIOUtil.toByteArray(goodsRedisEntity, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
			//���ó�ʱ����
			int timeout=60*60;//����һСʱ����һ�λ�������
			String result=jedis.setex(key.getBytes(), timeout, bytes);
			return result;
		} finally {
			jedis.close();
		}
	} catch (Exception e) {
		System.out.println("��ʼ������ʧ��");
	}
	return null;
	}
}
