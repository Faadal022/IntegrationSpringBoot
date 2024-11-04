package sn.edu.isepdiamniadio.edu.sn.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.edu.isepdiamniadio.edu.sn.demo.entites.Vote;
import sn.edu.isepdiamniadio.edu.sn.demo.service.DeputeService;
import sn.edu.isepdiamniadio.edu.sn.demo.service.VoteService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DeputeController {
    @Autowired
    private VoteService voteService;

    @Autowired
    private DeputeService deputeService;

    @GetMapping("/votes")
    public List<Vote> obtenirVotesOuverts() {
        return voteService.obtenirVotesOuverts();
    }
    @PostMapping("/vote/{idVote}/depute/{idDepute}")
    public ResponseEntity<String> voter(@PathVariable Long idVote, @PathVariable Long idDepute, @RequestBody String bulletin) {
        return deputeService.voter(idDepute, idVote, bulletin);
    }
}
