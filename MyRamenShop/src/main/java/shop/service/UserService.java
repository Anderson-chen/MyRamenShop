package shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.entity.Bulletin;
import shop.entity.OrderItem;
import shop.entity.RamenProduct;
import shop.repository.BulletinRepository;
import shop.repository.OrderRepository;
import shop.repository.RamenProductRepository;

@Service
public class UserService {
	@Autowired
	RamenProductRepository ramenProductRepository;
	@Autowired
	BulletinRepository bulletinRepository;
	@Autowired
	OrderRepository orderRepository;
	
//公告
	public Bulletin findTop1() {

		return bulletinRepository.top1();
	}

	public Bulletin findTop2() {

		return bulletinRepository.top2();
	}

	public Bulletin findTop3() {

		return bulletinRepository.top3();
	}

	public List<Bulletin> findNews() {

		return bulletinRepository.findAll();
	}

	public Bulletin findNewById(Integer id) {

		return bulletinRepository.findById(id).get();
	}
//完成訂單
	public void giveOrder(OrderItem orderItem) {
		
		orderRepository.save(orderItem);
	}

}
