package shop.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import shop.entity.RamenProduct;

@Transactional
public interface RamenProductRepository extends JpaRepository<RamenProduct, Integer> {
	@Query(value = "select*from ramen_product where type=?1 order by sales  desc limit 0, 3", nativeQuery = true)
	List<RamenProduct> bestseller(String type);

//	@Query(value = "select ramen_price from display_product\r\n" + "order by ramen_price  desc\r\n"
//			+ "limit 0, 2", nativeQuery = true)
//	List<DisplayProduct> bestprice();

	@Query(value = "update ramen_product set sales=?1 where id=?2 ", nativeQuery = true)
	@Modifying
	public void updateOne(int cnt, int id);

	@Query(value = "update ramen_product set ramen_title=?1 ,ramen_price=?2 ,type=?3 where id=?4 ", nativeQuery = true)
	@Modifying
	public void updateRamen(String name, int price,String type, int id);

	@Query(value = "update ramen_product set sales=?1 where ramen_title=?2 ", nativeQuery = true)
	@Modifying
	public void updateOneByName(int cnt, String title);

	Optional<RamenProduct> findByTitle(String title);

	List<RamenProduct> findBytype(String type);

}
