package shop.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import shop.entity.Membership;
import shop.entity.OrderItem;

import shop.service.MemberService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	MemberService memberService;

//註冊
	@PostMapping("/signup")
	public String signup(Membership membership, Model model) {

		for (int i = 0; i < memberService.getSize(); i++) {
			if (membership.getUsername().equals(memberService.findAllUserName().get(i))) {

				model.addAttribute("used", "名稱已使用");
				return "user/userLogin";

			}

		}

		memberService.saveMemberData(membership);
		return "user/userLogin";
	}

//會員中心
	@GetMapping("/memberCenter")
	public String center(Model model) {

		List<OrderItem> orderItems = memberService.findMemberOrder();

		model.addAttribute("orderList", orderItems);
		return "user/member/memberCenter";
	}

	@PostMapping("/memberCenter")
	public String gosucessful(Model model, HttpSession session) {

		List<OrderItem> orderItems = memberService.findMemberOrder();

		model.addAttribute("orderList", orderItems);
//登入後名稱與資料加入session		

		Membership ms = memberService.findMemberByUsername();
		session.setAttribute("membership", ms);
		session.setAttribute("loginUser", ms.getLastname() + ms.getFirstname());
		if (session.getAttribute("cart") != null) {
			return "user/userComfirm";
		}
		return "user/member/memberCenter";
	}

//取消訂單
	@PostMapping("/deleteOrder/{id}")
	public String DeleteOrder(@PathVariable Integer id) {

		memberService.deleteOrder(id);

		return "redirect:/user/memberCenter";

	}
}
