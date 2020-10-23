package shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Service;

import shop.entity.Membership;
import shop.entity.OrderItem;
import shop.repositry.MembershipRepositry;
import shop.repositry.OrderRepositry;

@Service
public class MemberService {
	@Autowired
	MembershipRepositry membershipRepositry;
	@Autowired
	OrderRepositry orderRepositry;

	public void saveMemberData(Membership membership) {

		membershipRepositry.save(membership);
	}

	public List<OrderItem> findMemberOrder() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		List<OrderItem> memberOrderList = orderRepositry.findByUsername(currentPrincipalName);

		return memberOrderList;
	}

	public Membership findMemberByUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();

		return membershipRepositry.findByUsername(currentPrincipalName);
	}

	public void deleteOrder(Integer id) {
		orderRepositry.deleteById(id);
	}

}
