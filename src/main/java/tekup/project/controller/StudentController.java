package tekup.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tekup.project.model.Certification;
import tekup.project.repository.CertificationRepository;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private CertificationRepository certifRepo;
	

	@GetMapping("/allCertif")
	public List<Certification> getAllCertif() {

		
		return certifRepo.findAll();

	}

}
