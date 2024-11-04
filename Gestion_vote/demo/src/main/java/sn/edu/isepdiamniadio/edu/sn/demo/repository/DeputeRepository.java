package sn.edu.isepdiamniadio.edu.sn.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.edu.isepdiamniadio.edu.sn.demo.entites.Depute;

import java.util.Optional;

public interface DeputeRepository extends JpaRepository<Depute, Long> {
    Optional<Depute> findById(Long id);
}