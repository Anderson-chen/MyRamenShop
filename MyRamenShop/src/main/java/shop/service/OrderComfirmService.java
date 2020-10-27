package shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.entity.OrderItem;
import shop.repository.OrderRepository;

@Service
public class OrderComfirmService {
	
	@Autowired
	OrderRepository orderRepository;
	//完成訂單
		public void giveOrder(OrderItem orderItem) {
			
			orderRepository.save(orderItem);
		}

}
