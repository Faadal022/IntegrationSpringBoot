package sn.edu.isepdiamniadio.edu.sn.demo.service;

import com.mysql.cj.protocol.ResultsetRows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sn.edu.isepdiamniadio.edu.sn.demo.entites.Depute;
import sn.edu.isepdiamniadio.edu.sn.demo.entites.Vote;
import sn.edu.isepdiamniadio.edu.sn.demo.repository.DeputeRepository;
import sn.edu.isepdiamniadio.edu.sn.demo.repository.VoteRepository;

import java.util.Arrays;
import java.util.Optional;

@Service
public class DeputeService {
    @Autowired
    private DeputeRepository deputeRepository;

    @Autowired
    private VoteRepository voteRepository;

    public ResponseEntity<String> voter(Long idDepute, long idVote, String bulletin){
        //verifier si le bulletin est valide
        if(!Arrays.asList("OUI","NON","ABSTENTION").contains(bulletin)){
            return (ResponseEntity<String>) ReponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body();

        }
        // Vérifiez si le vote existe
        Optional<Vote> voteOpt = voteRepository.findById(idVote);
        if (voteOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Identifiant de vote introuvable!");
        }
        Vote vote = voteOpt.get();

        // Vérifiez si le député existe
        ResultsetRows deputeOpt =null;
        if (deputeOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Désolé, vous n’êtes pas autorisé à voter !");
        }

        // Vérifier que le vote est ouvert
        Depute depute = new Depute();
        if (vote.getEtat().equals("clos")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Désolé, Honorable " + depute.getPrenom() + " " + depute.getNom() + ", le vote est déjà clos !");
        }
        // Vérifier que le député n'a pas déjà voté
        if (depute.getVotes().containsKey(idVote)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Honorable " + depute.getPrenom() + " " + depute.getNom() + ", vous avez déjà voté !");
        }
        // Enregistrer le vote
        depute.getVotes().put(idVote, bulletin);
        deputeRepository.save(depute);

        return ResponseEntity.status(HttpStatus.CREATED).body("Honorable " + depute.getPrenom() + " " + depute.getNom() + ", votre vote est bien pris en compte !");
    }
    }



