package sn.edu.isepdiamniadio.edu.sn.demo;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import java.util.List;
import java.util.Optional;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.crossstore.ChangeSetPersister;
import sn.edu.isepdiamniadio.edu.sn.demo.entites.Vote;
import sn.edu.isepdiamniadio.edu.sn.demo.repository.VoteRepository;
import sn.edu.isepdiamniadio.edu.sn.demo.service.VoteService;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class VoteServiceTest {

    @Mock
    private VoteRepository voteRepository;

    @InjectMocks
    private VoteService voteService;

    @Test
    public void testCreerVote() {
        String libelle = "Projet de loi sur l'éducation";

        // Définir le comportement du mock
        Vote vote = new Vote();
        vote.setId(1L);
        vote.setLibelle(libelle);
        vote.setEtat("clos");
        when(voteRepository.save(any(Vote.class))).thenReturn(vote);

        Vote result = voteService.creerVote(libelle);

        assertNotNull(result);
        assertEquals("clos", result.getEtat());
        assertEquals(libelle, result.getLibelle());
    }

    @Test
    public void testChangerEtatVote() throws ChangeSetPersister.NotFoundException {
        Vote vote = new Vote();
        vote.setId(1L);
        vote.setEtat("clos");

        when(voteRepository.findById(1L)).thenReturn(Optional.of(vote));
        when(voteRepository.save(any(Vote.class))).thenReturn(vote);

        Vote updatedVote = voteService.changerEtat(1L);

        assertEquals("ouvert", updatedVote.getEtat());
    }

    @Test
    public void testObtenirVotesOuverts() {
        Vote vote1 = new Vote();
        vote1.setEtat("ouvert");
        Vote vote2 = new Vote();
        vote2.setEtat("ouvert");

        when(voteRepository.findByEtat("ouvert")).thenReturn(List.of(vote1, vote2));

        List<Vote> votesOuverts = voteService.obtenirVotesOuverts();

        assertEquals(2, votesOuverts.size());
    }
}

