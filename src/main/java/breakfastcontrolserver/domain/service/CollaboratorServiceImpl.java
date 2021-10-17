package breakfastcontrolserver.domain.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import breakfastcontrolserver.domain.exceptions.CpfAlreadeyRegisteredException;
import breakfastcontrolserver.domain.exceptions.ErrorSavingEntityException;
import breakfastcontrolserver.domain.exceptions.FoodAlreadyRegisteredException;
import breakfastcontrolserver.domain.model.Collaborator;
import breakfastcontrolserver.domain.model.Food;
import breakfastcontrolserver.domain.repository.CollaboratorRepository;
import breakfastcontrolserver.domain.service.interfaces.CollaboratorService;
import breakfastcontrolserver.domain.service.interfaces.FoodService;

@Service
public class CollaboratorServiceImpl implements CollaboratorService {

	@Autowired
	private CollaboratorRepository collaboratorRepository;

	@Autowired
	private FoodService foodService;

	@Override
	@Transactional
	public Collaborator save(Collaborator collaborator) {
		if (collaborator.getId() > 0) {
			return update(collaborator);
		} else {
			return internalSave(collaborator);
		}
	}

	private Collaborator update(Collaborator collaborator) {
		find(collaborator.getId());
		updateCollaborator(collaborator);
		updateFoods(collaborator);
		return collaborator;
	}

	private void updateCollaborator(Collaborator collaborator) {
		this.collaboratorRepository.update(collaborator.getName(), collaborator.getCpf(), collaborator.getId());
	}

	private void updateFoods(Collaborator collaborator) {
		deleteFoods(collaborator);
		addFood(collaborator);
	}

	private void addFood(Collaborator collaborator) {
		collaborator.getFoods().stream().forEach(food -> {
			if (this.existsFoodByName(food.getName())) {
				throw new FoodAlreadyRegisteredException(food.getName());
			} else {
				this.foodService.save(food.getName(), collaborator.getId());
			}
		});
	}

	private void deleteFoods(Collaborator collaborator) {
		this.foodService.deleteByCollaboratorId(collaborator.getId());
	}

	private Collaborator find(Long collaboratorId) {
		return this.collaboratorRepository.findById(collaboratorId).orElseThrow(() -> new EntityNotFoundException());
	}

	private Collaborator internalSave(Collaborator collaborator) {
		if (existsByCpf(collaborator.getCpf())) {
			throw new CpfAlreadeyRegisteredException(collaborator.getCpf());
		} else {
			saveCollaborator(collaborator);
			saveFoods(collaborator.getFoods());
			return savedCollaborator(collaborator);
		}
	}

	private boolean existsByCpf(String cpf) {
		return this.collaboratorRepository.existsByCpf(cpf) == 1 ? true : false;
	}

	private void saveCollaborator(Collaborator collaborator) {
		try {
			this.collaboratorRepository.save(collaborator.getName(), collaborator.getCpf());
		} catch (Exception e) {
			new ErrorSavingEntityException(Collaborator.class.getName());
		}
	}

	private void saveFoods(List<Food> foods) {
		try {
			Long collaboratorId = this.collaboratorRepository.getLastInsertId();
			foods.stream().forEach(food -> {
				if (existsFoodByName(food.getName())) {
					throw new FoodAlreadyRegisteredException(food.getName());
				} else {
					this.foodService.save(food.getName(), collaboratorId);
				}
			});
		} catch (Exception e) {
			new ErrorSavingEntityException(Food.class.getName());
		}
	}

	private boolean existsFoodByName(String name) {
		return this.foodService.existsByName(name);
	}

	private Collaborator savedCollaborator(Collaborator collaborator) {
		collaborator.setId(this.collaboratorRepository.getLastInsertId());
		return collaborator;
	}

	@Override
	public List<Collaborator> listCollaborators() {
		return this.collaboratorRepository.findAll();
	}

	@Override
	@Transactional
	public void delete(long collaboratorId) {
		Collaborator researchedCollaborator = find(collaboratorId);
		deleteAssociatedFoods(researchedCollaborator);
		this.collaboratorRepository.deleteById(researchedCollaborator.getId());
	}

	private void deleteAssociatedFoods(Collaborator collaborator) {
		collaborator.getFoods().stream().forEach(food -> {
			this.foodService.deleteById(food.getId());
		});
	}
}
