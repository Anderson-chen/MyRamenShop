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
	


}
