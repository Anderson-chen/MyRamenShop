package shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.entity.Bulletin;
import shop.entity.OrderItem;
import shop.entity.RamenProduct;
import shop.repositry.BulletinRepositry;
import shop.repositry.OrderRepositry;
import shop.repositry.RamenProductRepositry;

@Service
public class UserService {
	@Autowired
	RamenProductRepositry ramenProductRepositry;
	@Autowired
	BulletinRepositry bulletinRepositry;
	@Autowired
	OrderRepositry orderRepositry;
//商品
	public List<RamenProduct> findAllProduct(String type) {

		return ramenProductRepositry.findBytype(type);
	}

	public List<RamenProduct> Best3(String type) {

		return ramenProductRepositry.bestseller(type);
	}

	public RamenProduct findRamenById(Integer id) {

		return ramenProductRepositry.findById(id).get();
	}
//公告
	public Bulletin findTop1() {

		return bulletinRepositry.top1();
	}

	public Bulletin findTop2() {

		return bulletinRepositry.top2();
	}

	public Bulletin findTop3() {

		return bulletinRepositry.top3();
	}

	public List<Bulletin> findNews() {

		return bulletinRepositry.findAll();
	}

	public Bulletin findNewById(Integer id) {

		return bulletinRepositry.findById(id).get();
	}
//完成訂單
	public void giveOrder(OrderItem orderItem) {
		
		orderRepositry.save(orderItem);
	}

}
