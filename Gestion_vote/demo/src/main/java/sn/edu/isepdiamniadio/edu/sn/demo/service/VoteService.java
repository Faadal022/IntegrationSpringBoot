package sn.edu.isepdiamniadio.edu.sn.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import sn.edu.isepdiamniadio.edu.sn.demo.entites.Vote;
import sn.edu.isepdiamniadio.edu.sn.demo.repository.VoteRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    public Vote creerVote(String libelle){
        Vote vote = new Vote();
        vote.setLibelle(libelle);
        vote.setDateCreation(LocalDate.now());
        vote.setEtat("clos");
        return voteRepository.save(vote);
    }
    public Vote changerEtat(Long idVote) throws ChangeSetPersister.NotFoundException {
        Vote vote = voteRepository.findById(idVote)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        vote.setEtat(vote.getEtat().equals("clos") ? "ouvert" : "clos");
        return voteRepository.save(vote);
    }
    public List<Vote> obtenirVotesOuverts() {
        return voteRepository.findByEtat("ouvert");
    }
}
