package shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import shop.entity.RamenProduct;
import shop.service.ProductAdminService;
@Controller
@RequestMapping("/admin")
public class ProductAdminController {
	@Autowired 
	ProductAdminService productAdminService ;
	
	//品項管理

		@GetMapping("/productList")
		public String dolist(Model model) {

			List<RamenProduct> ramenproducts = productAdminService.findAllRamenProduct();
			model.addAttribute("list", ramenproducts);

			return "admin/adminViewProduct";

		}

	//新增產品
		@GetMapping("/productNew")
		public String New() {

			return "admin/add";
		}

		@PostMapping("/productNew")
		public String doNEW(RamenProduct ramenProduct, @RequestParam("file") MultipartFile file, Model model) {

			String fileName = productAdminService.addNewProduct(ramenProduct, file);
			String filename = "/Ramen_pc/" + fileName;
			model.addAttribute("filename", filename);

			return "redirect:/admin/productList";
		}

	//點擊編輯
		@GetMapping("/productNew/{id}")
		public String edit(@PathVariable("id") Integer id, Model model) {
			RamenProduct ramenproduct = productAdminService.editProduct(id);
			model.addAttribute("DP", ramenproduct);
			return "admin/addput";
		}

	//更新
		@PostMapping("/put")
		public String update(@RequestParam Integer id, RamenProduct ramenProduct) {

			productAdminService.updateProduct(ramenProduct, id);

			return "redirect:/admin/productList";
		}

		@PostMapping("/productDelete/{id}")
		public String delete(@PathVariable("id") Integer id) {
			productAdminService.deleteProductById(id);
			return "redirect:/admin/productList";
		}


}
