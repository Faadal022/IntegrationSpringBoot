package sn.edu.isepdiamniadio.edu.sn.demo.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Entity
@Getter
@Setter
public class Depute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String prenom;
    private String nom;

    @ElementCollection
    private Map<Long, String> votes = new HashMap<>();
}
