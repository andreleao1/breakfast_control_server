package breakfastcontrolserver.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import breakfastcontrolserver.domain.model.Collaborator;
import breakfastcontrolserver.domain.service.interfaces.CollaboratorService;

@RestController
@RequestMapping("/contributors")
public class CollaboratorController {
	
	@Autowired
	private CollaboratorService collaboratorService;

	@PostMapping
	public ResponseEntity<Collaborator> save(@RequestBody @Valid Collaborator collaborator) {		
		return ResponseEntity.ok(this.collaboratorService.save(collaborator));
	}
	
	@GetMapping
	public ResponseEntity<List<Collaborator>> list() {
		return ResponseEntity.ok(this.collaboratorService.listCollaborators());
	}
	
	@PutMapping("/{collaboratorId}")
	public ResponseEntity<Collaborator> update(@RequestBody @Valid Collaborator collaborator) {
		return ResponseEntity.ok(this.collaboratorService.save(collaborator));
	}
	
	@DeleteMapping("/{collaboratorId}")
	public ResponseEntity<Void> delete(@PathVariable long collaboratorId) {
		this.collaboratorService.delete(collaboratorId);
		return ResponseEntity.noContent().build();
	}
}
