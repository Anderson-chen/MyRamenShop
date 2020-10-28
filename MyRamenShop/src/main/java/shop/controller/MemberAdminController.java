package shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import shop.entity.Bulletin;

import shop.entity.Membership;
import shop.entity.OrderItem;
import shop.entity.RamenProduct;


import shop.service.MemberAdminService;

@Controller

public class MemberAdminController {

	@Autowired
	MemberAdminService memberAdminService;






//會員管理
	@GetMapping("/admin/memberList")
	public String Member(Model model) {

		List<Membership> list = memberAdminService.findAllMember();
		model.addAttribute("m", list);

		return "admin/adminMember";
	}

	@PostMapping("/admin/memberList/{id}")
	public String deletemember(@PathVariable("id") Integer id) {
		memberAdminService.deleteMemberById(id);
		return "redirect:/admin/memberList";
	}

}
