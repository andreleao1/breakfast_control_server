package breakfastcontrolserver.domain.service.interfaces;

public interface FoodService {

	void save(String name, Long collaboratorId);
	
	void deleteById(Long foodId);
	
	void deleteByCollaboratorId(Long collaboratorId);
	
	boolean existsByName(String name);
}
