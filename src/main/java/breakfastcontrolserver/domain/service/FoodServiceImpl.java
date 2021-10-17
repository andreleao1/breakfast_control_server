package breakfastcontrolserver.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import breakfastcontrolserver.domain.repository.FoodRepository;
import breakfastcontrolserver.domain.service.interfaces.FoodService;

@Service
public class FoodServiceImpl implements FoodService {

	@Autowired
	private FoodRepository foodRepository;

	@Override
	@Transactional
	public void save(String name, Long collaboratorId) {
		this.foodRepository.save(name, collaboratorId);
	}

	@Override
	@Transactional
	public void deleteById(Long foodId) {
		this.foodRepository.deleteById(foodId);
	}
	
	@Override
	@Transactional
	public void deleteByCollaboratorId(Long collaboratorId) {
		this.foodRepository.deleteByCollaboratorId(collaboratorId);
	}

	@Override
	public boolean existsByName(String name) {
		return this.foodRepository.existsByName(name) == 1 ? true : false;
	}

}
