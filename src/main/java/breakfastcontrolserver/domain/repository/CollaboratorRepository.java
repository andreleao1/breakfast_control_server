package breakfastcontrolserver.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import breakfastcontrolserver.domain.model.Collaborator;

@Repository
public interface CollaboratorRepository extends JpaRepository<Collaborator, Long>{

	@Modifying
	@Query(value = "insert into collaborator (name,cpf) values (upper(:name), upper(:cpf))", nativeQuery = true)
	void save(@Param("name") String name, @Param("cpf") String cpf);
	
	@Modifying
	@Query(value = "update collaborator set name = upper(:name) "
			+ ", cpf = upper(:cpf) where collaborator_id = :collaboratorId", nativeQuery = true)
	void update(@Param("name") String name, @Param("cpf") String cpf, @Param("collaboratorId") Long collaboratorId);
	
	@Modifying
	@Query(value = "delete from collaborator where collaborator_id = :collaboratorId", nativeQuery = true)
	void deleteById(@Param("collaboratorId") Long collaboratorId);
	
	@Query(value = "select case when count(collaborator_id) > 0 "
			+ "then true else false end from collaborator where upper(cpf) = upper(:cpf)", nativeQuery = true)
	int existsByCpf(@Param("cpf") String cpf);
	
	@Query(value = "select max(collaborator_id) from collaborator", nativeQuery = true)
	Long getLastInsertId();
	
	@Query(value = "select * from collaborator where collaborator_id = :callaboratorId", nativeQuery = true)
	Optional<Collaborator> findById(@Param("callaboratorId") Long collaboratorId);
	
	@Query(value = "select * from collaborator", nativeQuery = true)
	List<Collaborator> findAll();
}
