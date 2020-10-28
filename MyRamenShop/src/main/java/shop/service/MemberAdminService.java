package shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.entity.Membership;

import shop.repository.BulletinRepository;
import shop.repository.UserRepository;
import shop.repository.OrderRepository;
import shop.repository.RamenProductRepository;

@Service
public class MemberAdminService {

	@Autowired
	UserRepository userRepository;

//公告

//會員管理
	public List<Membership> findAllMember() {

		return userRepository.findAll();
	}

	public void deleteMemberById(Integer id) {

		userRepository.deleteById(id);

	}
}
