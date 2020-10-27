package shop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.CartUtil;
import shop.entity.Bulletin;

import shop.entity.OrderItem;
import shop.entity.RamenProduct;

import shop.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	 @Value("${procode1}")
	String procode1;
	 @Value("${procode2}")
		String procode2;

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
		List<RamenProduct> best = userService.Best3(type);
//列出全部
		List<RamenProduct> ALL = userService.findAllProduct(type);

		model.addAttribute("Best3", best);
		model.addAttribute("ALL", ALL);

		return "user/userPickTaste";
	}

	@GetMapping("/PickRamen/{id}")
	public String show1(Model model, @PathVariable("id") Integer id) {

		RamenProduct ramenProduct = userService.findRamenById(id);

		model.addAttribute("ALL", ramenProduct);

		return "user/userPickRamen";
	}

//公告
	@GetMapping("goBulletin")
	public String goBulletin(Model model) {
		Bulletin top1 = userService.findTop1();
		Bulletin top2 = userService.findTop2();
		Bulletin top3 = userService.findTop3();

		model.addAttribute("top1", top1);
		model.addAttribute("top2", top2);
		model.addAttribute("top3", top3);

		List<Bulletin> bulletins = userService.findNews();
		model.addAttribute("list", bulletins);

		return "user/userBulletin";
	}

//點選公告
	@GetMapping("/goBulletin/{id}")
	public String DetailMessage(@PathVariable Integer id, Model model) {

		Bulletin bulletin = userService.findNewById(id);

		model.addAttribute("news", bulletin);

		return "user/userDetailBulletin";
	}
//進入購物車頁面

	@GetMapping("/goCart")
	public String Cart() {

		return "user/userCart";

	}

//加入購物車
	@GetMapping("/goCart/{id}")
	public String goCart(@PathVariable Integer id, @PathParam("num") int num, HttpSession session) {

		RamenProduct product = userService.findRamenById(id);
		int sum = CartUtil.opitem(product.getPrice(), num);
		CartUtil.saveProductToCart(session, product, num, sum);

		return "user/userCart";

	}

//
	@PostMapping("/goCartDelete/{id}")
	public String Delete(HttpSession session, @PathVariable int id) {
		RamenProduct product = userService.findRamenById(id);
		CartUtil.deleteProductFromCart(session, product.getId());
		return "redirect:/goCart";
	}

	@GetMapping("/clearAll")
	public String clearAll(HttpSession session) {
		CartUtil.cleanCart(session);
		return "redirect:/goCart";
	}

	// 更新鍵

	@GetMapping("/change/{id}")
	public String updateItem(HttpSession session, @PathParam("total") Integer total, @PathParam("sum") Integer sum,
			@PathParam("num") Integer num, @PathVariable Integer id) {

		RamenProduct product = userService.findRamenById(id);
		CartUtil.updateItem(session, product, num, sum);

		return "redirect:/goCart";
	}
//確認訂單
	@GetMapping("/goComfirm")
	public String goComfirm(HttpSession session, Map<String, Boolean> map) {
		
		
		if (session.getAttribute("membership") != null) {
			map.put("test", true);

			return "user/userComfirm";

		}

		return "user/userLogin";

	}

	@PostMapping("/member/giveOrder")
	public String giveOrder(OrderItem orderItem, HttpSession session) {

//		String oidtoarray[]=oidto.getOrderlistfordisplay().split(",");
//		Map map = new HashMap();
//		for(int i=0;i<oidtoarray.length;i++) {
//			map.put(oidtoarray[i].split(":")[0], oidtoarray[i].split(":")[1]);
//		}

		userService.giveOrder(orderItem);
		CartUtil.cleanCart(session);
		return "redirect:/";
	}

	@PostMapping("/enterDiscount")
//	@ResponseBody
	public String discount(HttpSession session, @PathParam("procode") String procode, Map<String, Boolean> map) {

		CartUtil.discount(session, procode);
		map.put("test", false);
		return "user/userComfirm";

	}
	
	@PostMapping("/verify")
	@ResponseBody
	public Map<String,String> verify(@RequestBody Map<String,String> procodeMap) {
		String messsage = "";
		Map<String,String> jsonMap = new HashMap<String, String>();
		if(procode1.equals(procodeMap.get("procode")))
		{
			jsonMap.put("message", "10%off");
		}else if (procode2.equals(procodeMap.get("procode"))) {
			jsonMap.put("message", "20%off");
		}else{
			 jsonMap.put("message", "err");
		}
		return jsonMap;
	}

}
