package com.yu.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yu.dao.GoodsCarDao;
import com.yu.dao.GoodsDao;
import com.yu.dao.MyOrderDao;
import com.yu.dao.UserDao;
import com.yu.dto.DataEntity;
import com.yu.entity.Goods;
import com.yu.entity.GoodsCar;
import com.yu.entity.MyOrder;
import com.yu.entity.Page;
import com.yu.entity.User;

@Service
public class GoodsCarServiceImpl implements GoodsCarService {
	@Autowired
	private GoodsCarDao goodsCarDao;
	@Autowired
	private GoodsDao goodsDao;
	@Autowired
	private MyOrderDao myOrderDao;
//	@Autowired
//	private GoodsPageCarService goodsPageCarService;
	@Autowired
	private UserDao userDao;
	@Autowired
	private HttpSession session;
	//初始化购物车页面的分页页面
//	public Page countCarInitPageService(int pageNow) {
//		Page page=goodsPageCarService.countInitPage(pageNow);
//		return page;
//	}
//	@Override
//	public List<GoodsCar> goodsCarSelectService(Page page) {
//		User user=(User) session.getAttribute("user");
//		List<GoodsCar> list;
//		list=goodsPageCarService.countPage(user.getUserTel(),page);
//		return list;
//	}
	public List<GoodsCar> goodsCarSelectService() {
	User user=(User) session.getAttribute("user");
	return goodsCarDao.allGoodsCarSelect(user.getUserTel());
}
	@Override
	//将商品页的商品添加到购物车
	public Goods addGoodsCarService(long goodsNum, int BuyNumber) {
		User user=(User) session.getAttribute("user");
		List<Goods> list;
		String goodsPicture = null;
		String goodsName = null;
		double goodsPrice = 0;
		list = goodsDao.oneGoodsSelect(goodsNum);
		Goods goods = list.get(0);// 将根据商品编号查询的商品信息保存起来
		Integer goodsBuyNum = goodsCarDao.isExistGoods(user.getUserTel(), goodsNum);
		if (goodsBuyNum == null) {
			// java5以上版本可以实现自行装包拆包（即将integer装包转换成int类型后再相加）
			goodsBuyNum = BuyNumber;
		} else {
			goodsBuyNum = goodsBuyNum + BuyNumber;
		}
		if (goods.getGoodsSurplus() >= goodsBuyNum) {
			if (goodsBuyNum != BuyNumber) {
				// java5以上版本可以实现自行装包拆包（即将integer装包转换成int类型后再相加）
				goodsCarDao.goodsBuyNumUpdate(goodsBuyNum, user.getUserTel(), goodsNum);
			} else {
				goodsPicture = goods.getGoodsPicture();
				goodsPicture = goods.getGoodsPicture();
				goodsName = goods.getGoodsName();
				goodsPrice = goods.getGoodsPrice();
				GoodsCar goodsCar = new GoodsCar(user.getUserTel(), goodsPicture, goodsNum, goodsName, goodsPrice, BuyNumber);
				goodsCarDao.goodsCarInsert(goodsCar);
			}
		} else {
			goods.setError("加入购物车失败，购买商品的数量大于商品的库存");
		}
		return goods;
	}
	
	
	//在购物车中添加商品
	public DataEntity updataGoodsNumService(long goodsNum) {
		User user=(User) session.getAttribute("user");
		List<Goods> list;
		Integer goodsBuyNum = goodsCarDao.isExistGoods(user.getUserTel(), goodsNum);
		list = goodsDao.oneGoodsSelect(goodsNum);
		Goods goods = list.get(0);
		if(goods.getGoodsSurplus() >= goodsBuyNum+1){
		goodsCarDao.goodsBuyNumUpdate(goodsBuyNum+1, user.getUserTel(), goodsNum);
		DataEntity dataEntity=new DataEntity(totalPrice(), null);
		return dataEntity;
		}
		else{
			DataEntity dataEntity=new DataEntity(totalPrice(),"error");
			return dataEntity;
		}
	}
	//在购物车中减少商品
	public DataEntity updataReduceGoodsNumService(long goodsNum) {
		User user=(User) session.getAttribute("user");
		Integer goodsBuyNum = goodsCarDao.isExistGoods(user.getUserTel(), goodsNum);
		if(goodsBuyNum>1){
		goodsCarDao.goodsBuyNumUpdate(goodsBuyNum-1, user.getUserTel(), goodsNum);
		DataEntity dataEntity=new DataEntity(totalPrice(), null);
		return dataEntity;
		}
		else{
			DataEntity dataEntity=new DataEntity(totalPrice(),"error");
			return dataEntity;
		}
	}
	
	//计算商品的总价钱
	public double totalPrice() {
		User user=(User) session.getAttribute("user");
		double totalGoodsPrice=0;
		List<GoodsCar> list;
		list=goodsCarDao.allGoodsCarSelect(user.getUserTel());
		for (GoodsCar goodsCar : list) {
		totalGoodsPrice=goodsCar.getGoodsPrice()*goodsCar.getGoodsBuyNum()+totalGoodsPrice;
		}
		return totalGoodsPrice;
	}
	
	//根据商品编号删除商品信息
	public int deleteGoodsCarService(long goodsNum) {
		int backInfor=goodsCarDao.deleteGoodsCar(goodsNum);
		return backInfor;
	}
	
	/*
	 * 下面为订单部分的操作，
	 * 涉及操作较少，未能重新建类，
	 * 这里与购物车类并在了一起
	 * 
	 */
	long keepOrderNum=0;//保存订单编号，方便后面部分函数操作
	@Transactional  //当有两条以上数据库操作时应用事务管理，避免数据库因为不可抗因素出现数据出错
	//将提交的购物订单信息添加至数据库并清除购物车信息
	public DataEntity commitOrderService(String userTel,String userAddress){
		double totalGoodsPrice=totalPrice();
		Random random=new Random();
		long orderNum=random.nextInt(100000)+10000001;//随机生成订单号
		DataEntity dataEntity=null;
		if(myOrderDao.isExistOrderNum(orderNum)==null){
		keepOrderNum=orderNum;
		List<GoodsCar> list=goodsCarDao.allGoodsCarSelect(userTel);
		String goodsCarAllGoods="";//将用户的购物车信息以字符串的形式保存到该属性中，中间以逗号隔开
		for (GoodsCar goodsCar : list) {
			goodsCarAllGoods=goodsCarAllGoods+goodsCar.getGoodsName()+"X"+goodsCar.getGoodsBuyNum()+",";
			//修改商品库存
			goodsDao.reduceGoodsSurplus(goodsCar.getGoodsBuyNum(), goodsCar.getGoodsNum());
		}
		Date day=new Date();    
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String downOrderTime=df.format(day);
		String orderStore=(String) session.getAttribute("location");
		if(orderStore==""){
			orderStore="龙泉驿区 龙泉驿区总店";
		}
		MyOrder myOrder=new MyOrder(orderNum, orderStore,userTel, userAddress, goodsCarAllGoods, totalGoodsPrice,0,downOrderTime);
		int back1=myOrderDao.insertUserOrder(myOrder);//将订单信息写入数据库
		int back2=goodsCarDao.deleteAllGoodsCar(userTel);//将购物车信息删除
		if(back1>0&&back2>0){
				dataEntity = new DataEntity("success");
			}
		else{
			dataEntity=new DataEntity("error");
		}
		}
		else {
			commitOrderService(userTel, userAddress);
		}
		return dataEntity;
	}
	
	//读取当前订单信息
	public MyOrder selectMyOrderInforService(){
		return myOrderDao.selectMyOrderInfor(keepOrderNum);
	}
	
	//传入订单号的用户信息查询
	public MyOrder selectMyOrderInforService(long orderNum){
		return myOrderDao.selectMyOrderInfor(orderNum);
	}
	
	//根据电话号码查询该用户的所用订单信息
	public List<MyOrder> selectAllMyOrderInforService() {
		User user=(User) session.getAttribute("user");
		return myOrderDao.selectAllMyOrderInfor(user.getUserTel());
	}
	//根据订单编号删除订单
	public  void deleteMyorderInforService(long orderNum){
		myOrderDao.deleteMyOrderInfor(orderNum);
	}
	//取消订单
	@Transactional
	public DataEntity canaelMyOrderService(long orderNum) {
		DataEntity dataEntity;
		String GoodsAndNum[]=myOrderDao.selectMyOrderInfor(orderNum).getGoodsCarAllGoods().split(",|X");
		for (int i = 0; i < GoodsAndNum.length; i=i+2) {
			goodsDao.addGoodsSurplus(Integer.parseInt(GoodsAndNum[i+1]), GoodsAndNum[i]);
		}
		if(myOrderDao.updateOrderStatus(2,orderNum)>0){
			dataEntity=new DataEntity("success");
		}
		else{
			dataEntity=new DataEntity("error");
		}
		return dataEntity;
	}
	
	//用户支付订单
	@Transactional
	public DataEntity ablePayMoneyService(double totalGoodsPrice,long orderNum,String userTel,String userPayPassword) {
		DataEntity dataEntity;
		Md5Hash md5Hash=new Md5Hash(userPayPassword,userDao.getUUIDByUserPayPassword(userTel));
		if(md5Hash.toString().equals(userDao.getByUserPayPassword(userTel))){
		if(myOrderDao.updateOrderStatus(1, orderNum)>0&&userDao.reduceUserMoney(totalGoodsPrice, userTel)>0){
			dataEntity=new DataEntity("success");
			session.setAttribute("user", userDao.toUserTelSelect(userTel));
		}
		else{
			dataEntity=new DataEntity("error");
		}
		return dataEntity;
		}
		else{
			dataEntity=new DataEntity("passwordError");
			return dataEntity;
		}
	}
}

