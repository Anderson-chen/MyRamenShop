package shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.entity.Bulletin;
import shop.repository.BulletinRepository;

@Service
public class BulletinAdminService {
	@Autowired 
	BulletinRepository bulletinRepository;
	
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

}
