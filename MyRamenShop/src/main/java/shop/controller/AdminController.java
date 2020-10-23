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

import shop.service.AdminService;

@Controller

public class AdminController {

	@Autowired
	AdminService adminService;

////管理者登入介面
//	@GetMapping("/admin")
//	public String go() {
//
//		return "AdminLogin";
//	}
//
////管理者登入
//	@GetMapping("/doAdminlogin")
//	public String Re() {
//		return "ManageSys";
//	}
//
//	@PostMapping("/doAdminlogin")
//	public String doAdminLogin(@RequestParam("adminName") String username,
//			@RequestParam("adminPassword") String password, Map<String, Object> map, HttpSession session,
//			Model model) {
//		if (username.equals(admin.getUsername()) && admin.getPassowrd().equals(password)) {
//			System.out.println("管理者登入");
//			model.addAttribute("loginUser", username);
//			session.setAttribute("admin", username);
//
//			return "ManageSys";
//
//		} else {
//
//			map.put("err", "帳密錯誤");
//
//			return "AdminLogin";
//
//		}
//
//	}

//公告
	@GetMapping("/admin/bulletin")
	public String bulletin(Model model) {

		List<Bulletin> bulletinlist = adminService.findAllBulletin();
		model.addAttribute("list", bulletinlist);

		return "admin/adminBulletin";

	}

// 公告新增
	@GetMapping("/admin/bulletinNew")
	public String BulletinNew() {

		return "admin/adminBulletinNew";

	}

	@PostMapping("/admin/bulletinNew")
	public String GoBulletinNew(Bulletin bulletin) {

		adminService.addNewBulletin(bulletin);

		return "redirect:/admin/bulletin";
	}

//編輯公告
	@GetMapping("/admin/bulletinNew/{id}")
	public String EditBulletin(@PathVariable Integer id, Model model) {
		Bulletin bulletin = adminService.editBulletin(id);
		model.addAttribute("b", bulletin);

		return "admin/adminBulletinNew";

	}

	@PutMapping("/admin/bulletinNew")
	public String GoEditBulletin(Bulletin bulletin, @RequestParam Integer id) {

		adminService.doEditBulletin(bulletin, id);

		return "redirect:/admin/bulletin";

	}

//刪除公告	
	@PostMapping("/admin/bulletinNew/{id}")
	public String deleteBulletin(@PathVariable("id") Integer id) {
		adminService.deleteBulletinById(id);
		return "redirect:/admin/bulletin";
	}
//品項管理

	@GetMapping("/admin/productList")
	public String dolist(Model model) {

		List<RamenProduct> ramenproducts = adminService.findAllRamenProduct();
		model.addAttribute("list", ramenproducts);

		return "admin/adminViewProduct";

	}

//新增產品
	@GetMapping("/admin/productNew")
	public String New() {

		return "admin/add";
	}

	@PostMapping("/admin/productNew")
	public String doNEW(RamenProduct ramenProduct, @RequestParam("file") MultipartFile file, Model model) {

		String fileName = adminService.addNewProduct(ramenProduct, file);
		String filename = "/Ramen_pc/" + fileName;
		model.addAttribute("filename", filename);

		return "redirect:/admin/productList";
	}

//點擊編輯
	@GetMapping("/admin/productNew/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		RamenProduct ramenproduct = adminService.editProduct(id);
		model.addAttribute("DP", ramenproduct);
		return "admin/addput";
	}

//更新
	@PostMapping("/admin/put")
	public String update(@RequestParam Integer id, RamenProduct ramenProduct) {

		adminService.updateProduct(ramenProduct, id);

		return "redirect:/admin/productList";
	}

	@PostMapping("/admin/productNew/{id}")
	public String delete(@PathVariable("id") Integer id) {
		adminService.deleteProductById(id);
		return "redirect:/admin/productList";
	}

//訂單
	@GetMapping("/admin/orderList")
	public String orderGet(Model model) {
		List<OrderItem> list = adminService.findAllOrder();
		model.addAttribute("order", list);

		return "admin/adminOrderList";
	}

	@PostMapping("/admin/orderList")
	public String orderPostSucessfulUrl(Model model) {
		List<OrderItem> list = adminService.findAllOrder();
		model.addAttribute("order", list);

		return "admin/adminOrderList";
	}

	@PostMapping("/admin/orderComplete/{id}")
	public String orderComplete(@PathVariable Integer id) {

		adminService.finishOrder(id);
		return "redirect:/admin/orderList";
	}

//會員管理
	@GetMapping("/admin/memberList")
	public String Member(Model model) {

		List<Membership> list = adminService.findAllMember();
		model.addAttribute("m", list);

		return "admin/adminMember";
	}

	@PostMapping("/admin/memberList/{id}")
	public String deletemember(@PathVariable("id") Integer id) {
		adminService.deleteMemberById(id);
		return "redirect:/admin/memberList";
	}

}
