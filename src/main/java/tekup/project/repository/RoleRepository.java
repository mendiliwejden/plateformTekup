package tekup.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tekup.project.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
