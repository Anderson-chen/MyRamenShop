package shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.entity.RamenProduct;
import shop.repository.RamenProductRepository;

@Service
public class ProductService {
	
	@Autowired RamenProductRepository ramenProductRepository;
	
	//商品
		public List<RamenProduct> findAllProduct(String type) {

			return ramenProductRepository.findBytype(type);
		}

		public List<RamenProduct> Best3(String type) {

			return ramenProductRepository.bestseller(type);
		}

		public RamenProduct findRamenById(Integer id) {

			return ramenProductRepository.findById(id).get();
		}

}
