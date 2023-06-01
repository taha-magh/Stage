package ma.gov.marrakech.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A SuiviDelegataire.
 */
@Entity
@Table(name = "suivi_delegataire")
public class SuiviDelegataire implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "type_indicateur")
    private String typeIndicateur;

    @Column(name = "nom")
    private String nom;

    @Column(name = "description")
    private String description;

    @Column(name = "date_valeur")
    private String dateValeur;

    @Column(name = "valeur_contractuel")
    private String valeurContractuel;

    @Column(name = "valeur_constate")
    private String valeurConstate;

    @ManyToOne( cascade = {CascadeType.DETACH})
    private Delegataire delegataire;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeIndicateur() {
        return typeIndicateur;
    }

    public SuiviDelegataire typeIndicateur(String typeIndicateur) {
        this.typeIndicateur = typeIndicateur;
        return this;
    }

    public void setTypeIndicateur(String typeIndicateur) {
        this.typeIndicateur = typeIndicateur;
    }

    public String getNom() {
        return nom;
    }

    public SuiviDelegataire nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public SuiviDelegataire description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateValeur() {
        return dateValeur;
    }

    public SuiviDelegataire dateValeur(String dateValeur) {
        this.dateValeur = dateValeur;
        return this;
    }

    public void setDateValeur(String dateValeur) {
        this.dateValeur = dateValeur;
    }

    public String getValeurContractuel() {
        return valeurContractuel;
    }

    public SuiviDelegataire valeurContractuel(String valeurContractuel) {
        this.valeurContractuel = valeurContractuel;
        return this;
    }

    public void setValeurContractuel(String valeurContractuel) {
        this.valeurContractuel = valeurContractuel;
    }

    public String getValeurConstate() {
        return valeurConstate;
    }

    public SuiviDelegataire valeurConstate(String valeurConstate) {
        this.valeurConstate = valeurConstate;
        return this;
    }

    public void setValeurConstate(String valeurConstate) {
        this.valeurConstate = valeurConstate;
    }

    public Delegataire getDelegataire() {
        return delegataire;
    }

    public SuiviDelegataire delegataire(Delegataire delegataire) {
        this.delegataire = delegataire;
        return this;
    }

    public void setDelegataire(Delegataire delegataire) {
        this.delegataire = delegataire;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SuiviDelegataire)) {
            return false;
        }
        return id != null && id.equals(((SuiviDelegataire) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SuiviDelegataire{" +
            "id=" + getId() +
            ", typeIndicateur='" + getTypeIndicateur() + "'" +
            ", nom='" + getNom() + "'" +
            ", description='" + getDescription() + "'" +
            ", dateValeur='" + getDateValeur() + "'" +
            ", valeurContractuel='" + getValeurContractuel() + "'" +
            ", valeurConstate='" + getValeurConstate() + "'" +
            "}";
    }
}