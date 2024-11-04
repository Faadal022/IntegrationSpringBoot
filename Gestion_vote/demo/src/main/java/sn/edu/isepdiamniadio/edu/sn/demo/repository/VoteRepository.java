package sn.edu.isepdiamniadio.edu.sn.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.edu.isepdiamniadio.edu.sn.demo.entites.Vote;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    List<Vote> findByEtat(String etat);
}