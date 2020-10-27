package shop.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import shop.entity.Bulletin;
import shop.entity.Membership;
import shop.entity.OrderItem;
import shop.entity.RamenProduct;
import shop.repository.BulletinRepository;
import shop.repository.UserRepository;
import shop.repository.OrderRepository;
import shop.repository.RamenProductRepository;

@Service
public class AdminService {

	@Autowired
	RamenProductRepository ramenProductRepository;
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	BulletinRepository bulletinRepository;

//公告
	public List<Bulletin> findAllBulletin() {

		return bulletinRepository.findAll();

	}

	public void addNewBulletin(Bulletin bulletin) {

		bulletinRepository.save(bulletin);
	}

	public Bulletin editBulletin(Integer id) {

		return bulletinRepository.findById(id).get();
	}

	public void doEditBulletin(Bulletin bulletin, Integer id) {

		bulletinRepository.updateBulletin(bulletin.getTitle(), bulletin.getMessage(), id);

	}

	public void deleteBulletinById(Integer id) {
		bulletinRepository.deleteById(id);
	}

//品項
	public List<RamenProduct> findAllRamenProduct() {

		return ramenProductRepository.findAll();
	}

	public String addNewProduct(RamenProduct ramenProduct, MultipartFile file) {

		if (file.isEmpty()) {
			System.out.println("檔案為空");
		}
		String fileName = file.getOriginalFilename(); // 檔名
		String suffixName = fileName.substring(fileName.lastIndexOf(".")); // 字尾名
		String filePath = "D://Ramen_pc/"; // 上傳後的路徑
		fileName = UUID.randomUUID() + suffixName; // 新檔名
		File dest = new File(filePath + fileName);
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}
		try {
			file.transferTo(dest);
		} catch (IOException e) {
			e.printStackTrace();
		}

		ramenProduct.setPcName(fileName);
		ramenProduct.setCount(0);
		ramenProductRepository.save(ramenProduct);

		return fileName;
	}

	public RamenProduct editProduct (Integer id) {
	
		return ramenProductRepository.findById(id).get();
	}
	public void updateProduct(RamenProduct ramenProduct,Integer id) {
		
		ramenProductRepository.updateRamen(ramenProduct.getTitle(), ramenProduct.getPrice(), ramenProduct.getType(), id);
	
	}
	public void deleteProductById(Integer id) {
		ramenProductRepository.deleteById(id);
	}
//訂單
	public List<OrderItem> findAllOrder () {
		
		return orderRepository.findAll();
		
	}
	public void finishOrder(Integer id) {
		
		String[] ss = orderRepository.getOne(id).getOrderlist().split(",");
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < ss.length; i++) {
			map.put(ss[i].split(":")[0], ss[i].split(":")[1]);
		}

		for (int i = 0; i < ramenProductRepository.findAll().size(); i++) {
			String title = ramenProductRepository.findAll().get(i).getTitle();

			if (map.containsKey(title)) {
				int a = Integer.parseInt(map.get(title));
				int cnt = ramenProductRepository.findByTitle(title).get().getCount();
				cnt = cnt + a;
				ramenProductRepository.updateOneByName(cnt, title);

			}

		}
		orderRepository.deleteById(id);
	}
//會員管理
	public List<Membership> findAllMember(){
		
		
		return userRepository.findAll();
	}
	public void deleteMemberById(Integer id) {
		
		userRepository.deleteById(id);
		
	}
}
