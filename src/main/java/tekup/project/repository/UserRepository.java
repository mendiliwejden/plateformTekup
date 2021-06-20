package tekup.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tekup.project.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
public List<User> findByUsername(String username);


}
