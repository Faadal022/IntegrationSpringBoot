package sn.edu.isepdiamniadio.edu.sn.demo.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String libelle;
    private String etat;
    private LocalDate dateCreation;

    public void setLibelle(String libelle) {
    }

    public void setDateCreation(LocalDate now) {
    }

    public void setEtat(String clos) {
    }

    public Object getEtat() {
        return null;
    }
}
