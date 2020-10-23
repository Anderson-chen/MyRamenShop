package shop.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import shop.entity.Bulletin;

@Transactional
public interface BulletinRepository extends JpaRepository<Bulletin, Integer> {
	@Query(value = "update display_product set title=?1 ,message=?2 where id=?3 ", nativeQuery = true)
	@Modifying
	public void updateBulletin(String title, String message, int id);

	@Query(value = "select * from bulletin order by id desc limit 1 ", nativeQuery = true)
	public Bulletin top1();

	@Query(value = "select * from bulletin order by id desc limit 1 offset 1", nativeQuery = true)
	public Bulletin top2();

	@Query(value = "select * from bulletin order by id desc limit 1 offset 2 ", nativeQuery = true)
	public Bulletin top3();
	
	@Query(value = "select * from bulletin order by id desc  ", nativeQuery = true)
	public List<Bulletin> news();

}
