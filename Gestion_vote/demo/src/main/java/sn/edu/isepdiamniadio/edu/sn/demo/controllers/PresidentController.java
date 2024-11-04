package sn.edu.isepdiamniadio.edu.sn.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.edu.isepdiamniadio.edu.sn.demo.entites.Vote;
import sn.edu.isepdiamniadio.edu.sn.demo.service.VoteService;

@RestController
@RequestMapping("/api")
public class PresidentController {
    @Autowired
    private VoteService voteService;

    @PostMapping("/vote")
    public ResponseEntity<Vote> creerVote(@RequestBody String libelle) {
        return ResponseEntity.status(HttpStatus.CREATED).body(voteService.creerVote(libelle));
    }

    @PutMapping("/vote/{idVote}/status")
    public ResponseEntity<Vote> changerEtat(@PathVariable Long idVote) {
        try {
            return ResponseEntity.ok(voteService.changerEtat(idVote));
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


}
