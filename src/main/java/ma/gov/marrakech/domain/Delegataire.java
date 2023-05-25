package ma.gov.marrakech.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Delegataire.
 */
@Entity
@Table(name = "delegataire")
public class Delegataire implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "raison_social")
    private String raisonSocial;

    @Column(name = "raison_com")
    private String raisonCom;

    @Column(name = "id_fiscale")
    private String idFiscale;

    @Column(name = "ice")
    private String ice;

    @Column(name = "num_cnss")
    private String numCnss;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "tel")
    private String tel;

    @Column(name = "fax")
    private String fax;

    @Column(name = "email")
    private String email;

    @Column(name = "gerant")
    private String gerant;

    @Column(name = "structure")
    private String structure;

    @Column(name = "capital")
    private String capital;

    @OneToOne
    @JoinColumn(unique = true)
    private PieceJointe pieceJointeDelegataire;


    @OneToMany(mappedBy = "delegataire", cascade = {CascadeType.ALL})
    private Set<SuiviDelegataire> suiviDelegataires = new HashSet<>();

    @OneToMany(mappedBy = "delegataire", cascade = {CascadeType.ALL})
    private Set<TypeIndicDelegataire> typeIndicataireDelegataires = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRaisonSocial() {
        return raisonSocial;
    }

    public Delegataire raisonSocial(String raisonSocial) {
        this.raisonSocial = raisonSocial;
        return this;
    }

    public void setRaisonSocial(String raisonSocial) {
        this.raisonSocial = raisonSocial;
    }

    public String getRaisonCom() {
        return raisonCom;
    }

    public Delegataire raisonCom(String raisonCom) {
        this.raisonCom = raisonCom;
        return this;
    }

    public void setRaisonCom(String raisonCom) {
        this.raisonCom = raisonCom;
    }

    public String getIdFiscale() {
        return idFiscale;
    }

    public Delegataire idFiscale(String idFiscale) {
        this.idFiscale = idFiscale;
        return this;
    }

    public void setIdFiscale(String idFiscale) {
        this.idFiscale = idFiscale;
    }

    public String getIce() {
        return ice;
    }

    public Delegataire ice(String ice) {
        this.ice = ice;
        return this;
    }

    public void setIce(String ice) {
        this.ice = ice;
    }

    public String getNumCnss() {
        return numCnss;
    }

    public Delegataire numCnss(String numCnss) {
        this.numCnss = numCnss;
        return this;
    }

    public void setNumCnss(String numCnss) {
        this.numCnss = numCnss;
    }

    public String getAdresse() {
        return adresse;
    }

    public Delegataire adresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public Delegataire tel(String tel) {
        this.tel = tel;
        return this;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public Delegataire fax(String fax) {
        this.fax = fax;
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public Delegataire email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGerant() {
        return gerant;
    }

    public Delegataire gerant(String gerant) {
        this.gerant = gerant;
        return this;
    }

    public void setGerant(String gerant) {
        this.gerant = gerant;
    }

    public String getStructure() {
        return structure;
    }

    public Delegataire structure(String structure) {
        this.structure = structure;
        return this;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }
    public String getCapital() {
        return capital;
    }
    public Delegataire capital(String capital) {
        this.capital = capital;
        return this;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }


    public PieceJointe getPieceJointeDelegataire() {
        return pieceJointeDelegataire;
    }
    public Delegataire pieceJointeDelegataire(PieceJointe pieceJointe) {
        this.pieceJointeDelegataire = pieceJointe;
        return this;
    }
    public void setPieceJointeDelegataire(PieceJointe pieceJointe) {
        this.pieceJointeDelegataire = pieceJointe;
    }

    public Set<SuiviDelegataire> getSuiviDelegataires() {
        return suiviDelegataires;
    }

    public Delegataire suiviDelegataires(Set<SuiviDelegataire> suiviDelegataires) {
        this.suiviDelegataires = suiviDelegataires;
        return this;
    }

    public Delegataire addSuiviDelegataire(SuiviDelegataire suiviDelegataire) {
        this.suiviDelegataires.add(suiviDelegataire);
        suiviDelegataire.setDelegataire(this);
        return this;
    }

    public Delegataire removeSuiviDelegataire(SuiviDelegataire suiviDelegataire) {
        this.suiviDelegataires.remove(suiviDelegataire);
        suiviDelegataire.setDelegataire(null);
        return this;
    }

    public void setSuiviDelegataires(Set<SuiviDelegataire> suiviDelegataires) {
        this.suiviDelegataires = suiviDelegataires;
    }

    public Set<TypeIndicDelegataire> getTypeIndicataireDelegataires() {
        return typeIndicataireDelegataires;
    }

    public Delegataire typeIndicataireDelegataires(Set<TypeIndicDelegataire> typeIndicDelegataires) {
        this.typeIndicataireDelegataires = typeIndicDelegataires;
        return this;
    }

    public Delegataire addTypeIndicataireDelegataire(TypeIndicDelegataire typeIndicDelegataire) {
        this.typeIndicataireDelegataires.add(typeIndicDelegataire);
        typeIndicDelegataire.setDelegataire(this);
        return this;
    }

    public Delegataire removeTypeIndicataireDelegataire(TypeIndicDelegataire typeIndicDelegataire) {
        this.typeIndicataireDelegataires.remove(typeIndicDelegataire);
        typeIndicDelegataire.setDelegataire(null);
        return this;
    }

    public void setTypeIndicataireDelegataires(Set<TypeIndicDelegataire> typeIndicDelegataires) {
        this.typeIndicataireDelegataires = typeIndicDelegataires;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Delegataire)) {
            return false;
        }
        return id != null && id.equals(((Delegataire) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Delegataire{" +
            "id=" + getId() +
            ", raisonSocial='" + getRaisonSocial() + "'" +
            ", raisonCom='" + getRaisonCom() + "'" +
            ", idFiscale='" + getIdFiscale() + "'" +
            ", ice='" + getIce() + "'" +
            ", numCnss='" + getNumCnss() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", tel='" + getTel() + "'" +
            ", fax='" + getFax() + "'" +
            ", email='" + getEmail() + "'" +
            ", gerant='" + getGerant() + "'" +
            ", structure='" + getStructure() + "'" +
            ", capital='" + getCapital() + "'" +
            "}";
    }
}
