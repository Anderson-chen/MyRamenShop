package shop.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import shop.entity.RamenProduct;
import shop.service.ProductService;
@Controller
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	//首頁
		@GetMapping("/")
		public String index(HttpSession session) {
			if (session.getAttribute("loginUser") != null) {
				return "user/userProduct";
			} else {
				session.setAttribute("loginUser", "");

				return "user/userProduct";
			}
		}

	//點擊商品展示
		@GetMapping("/TastePick")
		public String show(Model model, @PathParam("type") String type

		) {

	//top3
			List<RamenProduct> best = productService.Best3(type);
	//列出全部
			List<RamenProduct> ALL = productService.findAllProduct(type);

			model.addAttribute("Best3", best);
			model.addAttribute("ALL", ALL);

			return "user/userPickTaste";
		}

		@GetMapping("/PickRamen/{id}")
		public String show1(Model model, @PathVariable("id") Integer id) {

			RamenProduct ramenProduct = productService.findRamenById(id);

			model.addAttribute("ALL", ramenProduct);

			return "user/userPickRamen";
		}

}
