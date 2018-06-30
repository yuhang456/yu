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
	//��ʼ�����ﳵҳ��ķ�ҳҳ��
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
	//����Ʒҳ����Ʒ��ӵ����ﳵ
	public Goods addGoodsCarService(long goodsNum, int BuyNumber) {
		User user=(User) session.getAttribute("user");
		List<Goods> list;
		String goodsPicture = null;
		String goodsName = null;
		double goodsPrice = 0;
		list = goodsDao.oneGoodsSelect(goodsNum);
		Goods goods = list.get(0);// ��������Ʒ��Ų�ѯ����Ʒ��Ϣ��������
		Integer goodsBuyNum = goodsCarDao.isExistGoods(user.getUserTel(), goodsNum);
		if (goodsBuyNum == null) {
			// java5���ϰ汾����ʵ������װ�����������integerװ��ת����int���ͺ�����ӣ�
			goodsBuyNum = BuyNumber;
		} else {
			goodsBuyNum = goodsBuyNum + BuyNumber;
		}
		if (goods.getGoodsSurplus() >= goodsBuyNum) {
			if (goodsBuyNum != BuyNumber) {
				// java5���ϰ汾����ʵ������װ�����������integerװ��ת����int���ͺ�����ӣ�
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
			goods.setError("���빺�ﳵʧ�ܣ�������Ʒ������������Ʒ�Ŀ��");
		}
		return goods;
	}
	
	
	//�ڹ��ﳵ�������Ʒ
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
	//�ڹ��ﳵ�м�����Ʒ
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
	
	//������Ʒ���ܼ�Ǯ
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
	
	//������Ʒ���ɾ����Ʒ��Ϣ
	public int deleteGoodsCarService(long goodsNum) {
		int backInfor=goodsCarDao.deleteGoodsCar(goodsNum);
		return backInfor;
	}
	
	/*
	 * ����Ϊ�������ֵĲ�����
	 * �漰�������٣�δ�����½��࣬
	 * �����빺�ﳵ�ಢ����һ��
	 * 
	 */
	long keepOrderNum=0;//���涩����ţ�������沿�ֺ�������
	@Transactional  //���������������ݿ����ʱӦ����������������ݿ���Ϊ���ɿ����س������ݳ���
	//���ύ�Ĺ��ﶩ����Ϣ��������ݿⲢ������ﳵ��Ϣ
	public DataEntity commitOrderService(String userTel,String userAddress){
		double totalGoodsPrice=totalPrice();
		Random random=new Random();
		long orderNum=random.nextInt(100000)+10000001;//������ɶ�����
		DataEntity dataEntity=null;
		if(myOrderDao.isExistOrderNum(orderNum)==null){
		keepOrderNum=orderNum;
		List<GoodsCar> list=goodsCarDao.allGoodsCarSelect(userTel);
		String goodsCarAllGoods="";//���û��Ĺ��ﳵ��Ϣ���ַ�������ʽ���浽�������У��м��Զ��Ÿ���
		for (GoodsCar goodsCar : list) {
			goodsCarAllGoods=goodsCarAllGoods+goodsCar.getGoodsName()+"X"+goodsCar.getGoodsBuyNum()+",";
			//�޸���Ʒ���
			goodsDao.reduceGoodsSurplus(goodsCar.getGoodsBuyNum(), goodsCar.getGoodsNum());
		}
		Date day=new Date();    
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String downOrderTime=df.format(day);
		String orderStore=(String) session.getAttribute("location");
		if(orderStore==""){
			orderStore="��Ȫ���� ��Ȫ�����ܵ�";
		}
		MyOrder myOrder=new MyOrder(orderNum, orderStore,userTel, userAddress, goodsCarAllGoods, totalGoodsPrice,0,downOrderTime);
		int back1=myOrderDao.insertUserOrder(myOrder);//��������Ϣд�����ݿ�
		int back2=goodsCarDao.deleteAllGoodsCar(userTel);//�����ﳵ��Ϣɾ��
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
	
	//��ȡ��ǰ������Ϣ
	public MyOrder selectMyOrderInforService(){
		return myOrderDao.selectMyOrderInfor(keepOrderNum);
	}
	
	//���붩���ŵ��û���Ϣ��ѯ
	public MyOrder selectMyOrderInforService(long orderNum){
		return myOrderDao.selectMyOrderInfor(orderNum);
	}
	
	//���ݵ绰�����ѯ���û������ö�����Ϣ
	public List<MyOrder> selectAllMyOrderInforService() {
		User user=(User) session.getAttribute("user");
		return myOrderDao.selectAllMyOrderInfor(user.getUserTel());
	}
	//���ݶ������ɾ������
	public  void deleteMyorderInforService(long orderNum){
		myOrderDao.deleteMyOrderInfor(orderNum);
	}
	//ȡ������
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
	
	//�û�֧������
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

