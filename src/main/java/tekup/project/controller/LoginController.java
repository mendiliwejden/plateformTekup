package tekup.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tekup.project.model.AuthenticationModel;
import tekup.project.model.Certification;
import tekup.project.repository.CertificationRepository;
import tekup.project.service.JwtUtil;
import tekup.project.service.MyUserDetails;
import tekup.project.service.UserDetailsServicesImpl;

@RestController
@CrossOrigin(origins = "*")
public class LoginController {

	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	UserDetailsServicesImpl userDetailService;
	@Autowired
	private CertificationRepository certifRepo;
	

	@PostMapping("/authenticate")
	public String generateToken(@RequestBody AuthenticationModel authModel) throws Exception {

		try {
			authManager.authenticate(
					new UsernamePasswordAuthenticationToken(authModel.getUserName(), authModel.getPassword()));
		} catch (Exception ex) {
			throw new Exception("inavalid username/password");
		}

		MyUserDetails mud = (MyUserDetails) userDetailService.loadUserByUsername(authModel.getUserName());
		return jwtUtil.generateToken(mud);
	}

	@PostMapping("/authenticate1")
	public String generateToken1(@RequestBody AuthenticationModel authModel) throws Exception {

		try {
			authManager.authenticate(
					new UsernamePasswordAuthenticationToken(authModel.getUserName(), authModel.getPassword()));
		} catch (Exception ex) {
			throw new Exception("inavalid username/password");
		}

		MyUserDetails mud = (MyUserDetails) userDetailService.loadUserByUsername(authModel.getUserName());
		return jwtUtil.generateToken1(mud);
	}
	
	
	
	@PostMapping("/testCertif")
	public Certification createCertif(@RequestBody Certification certif) {
		
		certifRepo.save(certif);
		return certif;
	}
	
	
	
}
