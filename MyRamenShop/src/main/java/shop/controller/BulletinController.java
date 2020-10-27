package shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import shop.entity.Bulletin;
import shop.service.BulletinService;

public class BulletinController {
	@Autowired
	BulletinService bulletinService;

	@GetMapping("goBulletin")
	public String goBulletin(Model model) {
		Bulletin top1 = bulletinService.findTop1();
		Bulletin top2 = bulletinService.findTop2();
		Bulletin top3 = bulletinService.findTop3();

		model.addAttribute("top1", top1);
		model.addAttribute("top2", top2);
		model.addAttribute("top3", top3);

		List<Bulletin> bulletins = bulletinService.findNews();
		model.addAttribute("list", bulletins);

		return "user/userBulletin";
	}

//點選公告
	@GetMapping("/goBulletin/{id}")
	public String DetailMessage(@PathVariable Integer id, Model model) {

		Bulletin bulletin = bulletinService.findNewById(id);

		model.addAttribute("news", bulletin);

		return "user/userDetailBulletin";
	}

}
