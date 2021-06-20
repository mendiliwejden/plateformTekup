package tekup.project.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.query.criteria.internal.expression.function.CurrentDateFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tekup.project.model.Certification;
import tekup.project.model.Examen;
import tekup.project.model.Role;
import tekup.project.model.User;
import tekup.project.model.WhiteTest;
import tekup.project.repository.CertificationRepository;
import tekup.project.repository.ExamenRepository;
import tekup.project.repository.RoleRepository;
import tekup.project.repository.UserRepository;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private CertificationRepository certifRepo;
	@Autowired
	private RoleRepository roleRepo;
	@Autowired
	private ExamenRepository examRepo;

	@GetMapping("/allUsers")
	public List<User> userslist() {
		
		List<User> allUsers= userRepo.findAll();
		
		 return allUsers;

	}
	
	@PostMapping("/administrator/add")
	public String addAdministrator(@RequestBody User user) {
		
		
		Set<Role> roles=new HashSet<>();
		Role role=roleRepo.getOne(1);
		roles.add(role);
		user.setRoles(roles);
		userRepo.save(user);
		return user.getUsername()+" is a new administrator";
	}
	
	

	@GetMapping("/users/{username}")
	public List<User> userbyname(@PathVariable String username) {
		 List<User> user = userRepo.findByUsername(username);
		 
		 return user;
	}

	
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/certification/getAll")
	public List<Certification> getCertifications() {
		return certifRepo.findAll();
	}
	
	@PostMapping("/certification/add")
	public Certification addCertification(@RequestBody Certification certification) {
		certifRepo.save(certification);
		
		return certification;
	}

	@GetMapping("/certifNumber")
	public long getCertifNumber() {
		return certifRepo.count();
	}
	
	@GetMapping("/getUser/{username}")
	public User getUser(@PathVariable("username") String username) {
		
		return userRepo.findByUsername(username).get(0);	
	}
	
	
	@GetMapping("/getCertifByUser/{username}")
	public List<Certification> getCertifByUser(@PathVariable("username") String username){

		return userRepo.findByUsername(username).get(0).getCertifications();
	}
	
	@GetMapping("/getwhiteTestByUser/{username}")
	public List<WhiteTest> getwhiteTestByUser(@PathVariable("username") String username){

		return userRepo.findByUsername(username).get(0).getWhitetests();
	}
	
	
	
	@GetMapping("/assignExam/{certifId}/{username}")
	public boolean assignedStudentCertif(@PathVariable("certifId")Integer certifId,@PathVariable("username") String username) {

		User myUser=userRepo.findByUsername(username).get(0);
		List<WhiteTest> myWhiteTests=myUser.getWhitetests();

		Certification myCertif=certifRepo.findById(certifId).get();
		float myNote=myCertif.getNoteMinWhiteTest();
		Integer nbrWt=myCertif.getNombreWhitTest();
		List<WhiteTest> certifWhiteTest= myWhiteTests.stream()
				.filter(x->x.getCertification_name().equals(myCertif.getName()))
				.collect(Collectors.toList());
		List<WhiteTest> wT = certifWhiteTest.stream().filter(x->x.getScore()>myNote)
				.collect(Collectors.toList());
		
		int x = wT.size();
		return (x>=nbrWt);
	}
	
	@GetMapping("/createExam/{certifId}/{username}")
	public Examen createExam(@PathVariable("certifId")Integer certifId,@PathVariable("username") String username) {
	    LocalDate dateobj = LocalDate.now().plusDays(7);
	    User u=userRepo.findByUsername(username).get(0);
	    Examen exam=new Examen();
	    exam.setUserId(u.getUser_id());
	    exam.setCertificationId(certifId);
		exam.setExam_date(dateobj);
		examRepo.save(exam);
		return exam;
	}
	
	
}
