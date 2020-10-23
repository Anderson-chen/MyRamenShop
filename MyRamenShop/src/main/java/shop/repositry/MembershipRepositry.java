package shop.repositry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import shop.entity.Membership;

public interface MembershipRepositry extends JpaRepository<Membership, Integer> {
	public Membership findByUsernameAndPassword(String username, String password);

	public Membership findByUsername(String username);

	@Query(value = "SELECT username FROM ramen.membership ", nativeQuery = true)
	List<String> findAllusername();

	@Query(value = "SELECT password FROM ramen.membership ", nativeQuery = true)
	List<String> findAllpassword();

}
