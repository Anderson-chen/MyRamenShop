package shop.repositry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import shop.entity.OrderItem;

public interface OrderRepositry extends JpaRepository<OrderItem, Integer> {

	@Query(value = "SELECT * FROM order_item s where s.username=?1", nativeQuery = true)
	List<OrderItem> findByUsername(String username);
}
