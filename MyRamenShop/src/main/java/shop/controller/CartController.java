package shop.controller;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import shop.entity.RamenProduct;
import shop.service.CartService;
import shop.service.ProductService;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	ProductService productService;
	CartService CartService;

	// 進入購物車頁面

	@GetMapping("/goCart")
	public String Cart() {

		return "user/userCart";

	}

	// 加入購物車
	@PostMapping("/goCart")
	public String goCart(Integer id, int num, HttpSession session) {
		
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
		return "redirect:/cart/goCart";
	}

	@GetMapping("/clearAll")
	public String clearAll(HttpSession session) {
		CartService.cleanCart(session);
		return "redirect:/cart/goCart";
	}

	// 更新鍵

	@PostMapping("/change")
	public String updateItem(HttpSession session, Integer total, Integer sum, Integer num, Integer id) {

		RamenProduct product = productService.findRamenById(id);
		CartService.updateItem(session, product, num, sum);

		return "redirect:/cart/goCart";
	}

}
