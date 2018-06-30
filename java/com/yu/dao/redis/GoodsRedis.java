package com.yu.dao.redis;
/*
 * 对象序列化实现的redis缓存情况，这里采用自定义序列化，因为java自带序列化
 * 无法承载高并发性，而且非常占用空间与时间，转化时占用时间，序列化后占用空间，
 * 这里自定义的序列化采用谷歌开源社区的protostuff，它的转化时间为JDK自带序列化
 * 操作的四分之一之多，它的转化占用空间至少是JDK的两个数量级以上
 * 使用protostuff序列化工具时，被序列化的对象必须是pojo对象（具备setter/getter）
 * 由于Jedis并没有实现内部序列化操作，而Java内置的序列化机制性能又不高，
 * 我们需要考虑高并发优化，在这里我们采用开源社区提供的更高性能的自定义序列化工具protostuff
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
		//redis操作逻辑
		try {
			Jedis jedis=jedisPool.getResource();
			try {
				String key="goodslist:"+goodsListId;
				//获取key对应的对象的byte值
				byte[] bytes=jedis.get(key.getBytes());
				//缓存重获取到
				if(bytes!=null){
					//创建空对象
					GoodsRedisEntity goodsRedisEntity=schema.newMessage();
					ProtostuffIOUtil.mergeFrom(bytes, goodsRedisEntity, schema);
					//对象被反序列化
					return goodsRedisEntity.getList();
				}
			} finally{
				jedis.close();
			}
		} catch (Exception e) {
			System.out.println("缓存连接失败");
		}
		return null;
	}
	
	public String putGoods(GoodsRedisEntity goodsRedisEntity) {
		//set Object -> 序列化 ->byte[]
	try {
		Jedis jedis=jedisPool.getResource();
		try {
			String key="goodslist:"+goodsRedisEntity.getGoodsListId();
			byte[] bytes=ProtostuffIOUtil.toByteArray(goodsRedisEntity, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
			//设置超时缓存
			int timeout=60*60;//设置一小时更新一次缓存内容
			String result=jedis.setex(key.getBytes(), timeout, bytes);
			return result;
		} finally {
			jedis.close();
		}
	} catch (Exception e) {
		System.out.println("初始化缓存失败");
	}
	return null;
	}
}
