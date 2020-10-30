package shop.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import shop.entity.RamenProduct;
import shop.repository.RamenProductRepository;

@Service
public class ProductAdminService {

	@Autowired
	RamenProductRepository ramenProductRepository;

	public List<RamenProduct> findAllRamenProduct() {

		return ramenProductRepository.findAll();
	}

	public String uploadimage(MultipartFile file) {

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

		return fileName;
	}

	public void addNewProduct(RamenProduct ramenProduct) {

		ramenProduct.setCount(0);
		ramenProductRepository.save(ramenProduct);

	}

	public RamenProduct editProduct(Integer id) {

		return ramenProductRepository.findById(id).get();
	}

	public void updateProduct(RamenProduct ramenProduct, Integer id) {

		ramenProductRepository.updateRamen(ramenProduct.getTitle(), ramenProduct.getPrice(), ramenProduct.getType(),
				ramenProduct.getPcName(), id);

	}

	public void deleteProductById(Integer id) {
		ramenProductRepository.deleteById(id);
	}
}
