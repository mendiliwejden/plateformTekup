package tekup.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tekup.project.model.Certification;

public interface CertificationRepository extends JpaRepository<Certification, Integer> {

}
