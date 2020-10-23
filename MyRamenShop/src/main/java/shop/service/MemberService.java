package shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Service;

import shop.entity.Membership;
import shop.entity.OrderItem;
import shop.repository.MembershipRepository;
import shop.repository.OrderRepository;

@Service
public class MemberService {
	@Autowired
	MembershipRepository membershipRepository;
	@Autowired
	OrderRepository orderRepository;

	public void saveMemberData(Membership membership) {

		membershipRepository.save(membership);
	}

	public List<OrderItem> findMemberOrder() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		List<OrderItem> memberOrderList = orderRepository.findByUsername(currentPrincipalName);

		return memberOrderList;
	}

	public Membership findMemberByUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();

		return membershipRepository.findByUsername(currentPrincipalName);
	}

	public void deleteOrder(Integer id) {
		orderRepository.deleteById(id);
	}

}
