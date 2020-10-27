package shop.controller;

import java.util.HashMap;

import java.util.Map;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;


import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;




import shop.entity.OrderItem;

import shop.service.CartService;
import shop.service.ProductService;
import shop.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	@Autowired 
	ProductService productService;
	@Autowired 
	CartService cartService;
	 @Value("${procode1}")
	String procode1;
	 @Value("${procode2}")
		String procode2;



//公告
	

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
		cartService.cleanCart(session);
		return "redirect:/";
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
