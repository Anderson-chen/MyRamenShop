package shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import shop.entity.OrderItem;
import shop.service.OrderAdminService;

@Controller
public class OrderAdminController {
	
	@Autowired
	OrderAdminService orderAdminService;
	
	//訂單
		@GetMapping("/admin/orderList")
		public String orderGet(Model model) {
			List<OrderItem> list = orderAdminService.findAllOrder();
			model.addAttribute("order", list);

			return "admin/adminOrderList";
		}

		@PostMapping("/admin/orderList")
		public String orderPostSucessfulUrl(Model model) {
			List<OrderItem> list = orderAdminService.findAllOrder();
			model.addAttribute("order", list);

			return "admin/adminOrderList";
		}

		@PostMapping("/admin/orderComplete/{id}")
		public String orderComplete(@PathVariable Integer id) {

			orderAdminService.finishOrder(id);
			return "redirect:/admin/orderList";
		}
}
