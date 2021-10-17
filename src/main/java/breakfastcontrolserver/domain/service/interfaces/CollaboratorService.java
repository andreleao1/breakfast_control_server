package breakfastcontrolserver.domain.service.interfaces;

import java.util.List;

import breakfastcontrolserver.domain.model.Collaborator;

public interface CollaboratorService {	
	
	void delete(long collaboratorId);
	
	Collaborator save(Collaborator collaborator);
	
	List<Collaborator> listCollaborators();
	
}
