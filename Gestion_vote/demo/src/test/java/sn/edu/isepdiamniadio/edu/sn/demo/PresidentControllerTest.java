package sn.edu.isepdiamniadio.edu.sn.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import sn.edu.isepdiamniadio.edu.sn.demo.controllers.PresidentController;
import sn.edu.isepdiamniadio.edu.sn.demo.entites.Vote;
import sn.edu.isepdiamniadio.edu.sn.demo.service.VoteService;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PresidentController.class)
public class PresidentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VoteService voteService;

    @Test
    public void testCreerVote() throws Exception {
        String libelle = "Projet de loi sur la santé";
        Vote vote = new Vote();
        vote.setLibelle(libelle);
        vote.setId(1L);

        when(voteService.creerVote(anyString())).thenReturn(vote);

        mockMvc.perform(post("/api/vote")
                        .content(libelle)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect((ResultMatcher) jsonPath("$.libelle").value(libelle));
    }

    @Test
    public void testChangerEtatVote() throws Exception {
        Vote vote = new Vote();
        vote.setId(1L);
        vote.setEtat("ouvert");

        when(voteService.changerEtat(1L)).thenReturn(vote);

        mockMvc.perform(put("/api/vote/1/status"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.etat").value("ouvert"));
    }
}

