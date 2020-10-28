package shop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.entity.OrderItem;
import shop.repository.OrderRepository;
import shop.repository.RamenProductRepository;
@Service
public class OrderAdminService {
	
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	RamenProductRepository ramenProductRepository;
	//訂單
		public List<OrderItem> findAllOrder () {
			
			return orderRepository.findAll();
			
		}
		public void finishOrder(Integer id) {
			
		String[] ss = orderRepository.getOne(id).getOrderlist().split(",");
		Map<String, String> map = new HashMap<String, String>();
			for (int i = 0; i < ss.length; i++) {
				map.put(ss[i].split(":")[0], ss[i].split(":")[1]);
			}

			for (int i = 0; i < ramenProductRepository.findAll().size(); i++) {
				String title = ramenProductRepository.findAll().get(i).getTitle();

				if (map.containsKey(title)) {
					int a = Integer.parseInt(map.get(title));
					int cnt = ramenProductRepository.findByTitle(title).get().getCount();
					cnt = cnt + a;
					ramenProductRepository.updateOneByName(cnt, title);

				}

			}
			orderRepository.deleteById(id);
		}
}
