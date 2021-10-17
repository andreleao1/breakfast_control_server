package breakfastcontrolserver.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import breakfastcontrolserver.domain.model.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long>{

	@Modifying
	@Query(value = "insert into food (name, collaborator_id) values (upper(:name), :collaboratorId)", nativeQuery = true)
	void save(@Param("name") String name, @Param("collaboratorId") Long collaboratorId);
	
	@Modifying
	@Query(value = "delete from food where food_id = :foodId", nativeQuery = true)
	void deleteById(@Param("foodId") Long foodId);
	
	@Modifying
	@Query(value = "delete from food where collaborator_id = :collaboratorId", nativeQuery = true)
	void deleteByCollaboratorId(@Param("collaboratorId") Long collaboratorId);
	
	@Query(value = "select case when count(food_id) > 0 "
			+ "then true else false end from food where upper(name) = upper(:name)", nativeQuery = true)
	int existsByName(@Param("name") String name);
	
	@Query(value="select case when count(food_id) > 0 then true else false end "
			+ "from food where collaborator_id = :collaboratorId", nativeQuery = true)
	int existsFoodAssociatedWithCurrentCollaborator(@Param("collaboratorId") Long collaboratorId);
}
