package shop.controller;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import shop.entity.RamenProduct;
import shop.service.CartService;
import shop.service.ProductService;

public class CartController {
	
	
	@Autowired
	ProductService productService;
	CartService CartService;
	
	//進入購物車頁面

		@GetMapping("/goCart")
		public String Cart() {

			return "user/userCart";

		}

	//加入購物車
		@GetMapping("/goCart/{id}")
		public String goCart(@PathVariable Integer id, @PathParam("num") int num, HttpSession session) {

			RamenProduct product = productService.findRamenById(id);
			int sum = CartService.opitem(product.getPrice(), num);
			CartService.saveProductToCart(session, product, num, sum);

			return "user/userCart";

		}

	//
		@PostMapping("/goCartDelete/{id}")
		public String Delete(HttpSession session, @PathVariable int id) {
			RamenProduct product = productService.findRamenById(id);
			CartService.deleteProductFromCart(session, product.getId());
			return "redirect:/goCart";
		}

		@GetMapping("/clearAll")
		public String clearAll(HttpSession session) {
			CartService.cleanCart(session);
			return "redirect:/goCart";
		}

		// 更新鍵

		@GetMapping("/change/{id}")
		public String updateItem(HttpSession session, @PathParam("total") Integer total, @PathParam("sum") Integer sum,
				@PathParam("num") Integer num, @PathVariable Integer id) {

			RamenProduct product = productService.findRamenById(id);
			CartService.updateItem(session, product, num, sum);

			return "redirect:/goCart";
		}

}
