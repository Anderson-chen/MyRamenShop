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

import shop.entity.Bulletin;
import shop.service.BulletinAdminService;

@Controller
public class BulletinAdminController {
	
	@Autowired
	BulletinAdminService bulletinAdminService;
	//公告
		@GetMapping("/admin/bulletin")
		public String bulletin(Model model) {

			List<Bulletin> bulletinlist = bulletinAdminService.findAllBulletin();
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

			bulletinAdminService.addNewBulletin(bulletin);

			return "redirect:/admin/bulletin";
		}

	//編輯公告
		@GetMapping("/admin/bulletinNew/{id}")
		public String EditBulletin(@PathVariable Integer id, Model model) {
			Bulletin bulletin = bulletinAdminService.editBulletin(id);
			model.addAttribute("b", bulletin);

			return "admin/adminBulletinNew";

		}

		@PutMapping("/admin/bulletinNew")
		public String GoEditBulletin(Bulletin bulletin, @RequestParam Integer id) {

			bulletinAdminService.doEditBulletin(bulletin, id);

			return "redirect:/admin/bulletin";

		}

	//刪除公告	
		@PostMapping("/admin/bulletinNew/{id}")
		public String deleteBulletin(@PathVariable("id") Integer id) {
			bulletinAdminService.deleteBulletinById(id);
			return "redirect:/admin/bulletin";
		}

}
