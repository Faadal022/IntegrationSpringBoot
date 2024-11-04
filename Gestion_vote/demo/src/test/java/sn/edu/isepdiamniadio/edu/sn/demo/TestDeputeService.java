package sn.edu.isepdiamniadio.edu.sn.demo;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import sn.edu.isepdiamniadio.edu.sn.demo.entites.Depute;
import sn.edu.isepdiamniadio.edu.sn.demo.entites.Vote;
import sn.edu.isepdiamniadio.edu.sn.demo.repository.DeputeRepository;
import sn.edu.isepdiamniadio.edu.sn.demo.repository.VoteRepository;
import sn.edu.isepdiamniadio.edu.sn.demo.service.DeputeService;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestDeputeService {


        @Mock
        private DeputeRepository deputeRepository;

        @Mock
        private VoteRepository voteRepository;

        @InjectMocks
        private DeputeService deputeService;

        @Test
        public void testVoterSuccess() {
            Long idDepute = 1L;
            Long idVote = 1L;
            String bulletin = "OUI";

            Depute depute = new Depute();
            depute.setId(idDepute);
            depute.setPrenom("Ibou");
            depute.setNom("Fall");

            Vote vote = new Vote();
            vote.setId(idVote);
            vote.setEtat("ouvert");

            ResponseEntity<String> response = deputeService.voter(idDepute, idVote, bulletin);


        }

        @Test
        public void testVoterDejaVote() {
            Long idDepute = 1L;
            Long idVote = 1L;
            String bulletin = "OUI";

            Depute depute = new Depute();
            depute.setId(idDepute);
            depute.getVotes().put(idVote, "OUI"); // Le député a déjà voté

            Vote vote = new Vote();
            vote.setId(idVote);
            vote.setEtat("ouvert");

        }

        @Test
        public void testVoterVoteClos() {
            Long idDepute = 1L;
            Long idVote = 1L;
            String bulletin = "OUI";

            Depute depute = new Depute();
            depute.setId(idDepute);

            Vote vote = new Vote();
            vote.setId(idVote);
            vote.setEtat("clos"); // Le vote est clos


        }
    }


