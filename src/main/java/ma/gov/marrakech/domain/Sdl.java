package ma.gov.marrakech.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Sdl.
 */
@Entity
@Table(name = "sdl")
public class Sdl implements Serializable {

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
    private PieceJointe pieceJointeSdl;

    @OneToMany(mappedBy = "sdl", cascade = {CascadeType.ALL})
    private Set<SuiviSdl> suiviSdls = new HashSet<>();

    @OneToMany(mappedBy = "sdl", cascade = {CascadeType.ALL})
    private Set<TypeIndicSdl> typeIndicataireSdls = new HashSet<>();

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
    public Sdl raisonSocial(String raisonSocial) {
        this.raisonSocial = raisonSocial;
        return this;
    }
    public void setRaisonSocial(String raisonSocial) {
        this.raisonSocial = raisonSocial;
    }

    public String getRaisonCom() {
        return raisonCom;
    }
    public Sdl raisonCom(String raisonCom) {
        this.raisonCom = raisonCom;
        return this;
    }
    public void setRaisonCom(String raisonCom) {
        this.raisonCom = raisonCom;
    }

    public String getIdFiscale() {
        return idFiscale;
    }
    public Sdl idFiscale(String idFiscale) {
        this.idFiscale = idFiscale;
        return this;
    }
    public void setIdFiscale(String idFiscale) {
        this.idFiscale = idFiscale;
    }

    public String getIce() {
        return ice;
    }
    public Sdl ice(String ice) {
        this.ice = ice;
        return this;
    }
    public void setIce(String ice) {
        this.ice = ice;
    }

    public String getNumCnss() {
        return numCnss;
    }
    public Sdl numCnss(String numCnss) {
        this.numCnss = numCnss;
        return this;
    }
    public void setNumCnss(String numCnss) {
        this.numCnss = numCnss;
    }

    public String getAdresse() {
        return adresse;
    }
    public Sdl adresse(String adresse) {
        this.adresse = adresse;
        return this;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }
    public Sdl tel(String tel) {
        this.tel = tel;
        return this;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }
    public Sdl fax(String fax) {
        this.fax = fax;
        return this;
    }
    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }
    public Sdl email(String email) {
        this.email = email;
        return this;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getGerant() {
        return gerant;
    }
    public Sdl gerant(String gerant) {
        this.gerant = gerant;
        return this;
    }
    public void setGerant(String gerant) {
        this.gerant = gerant;
    }

    public String getStructure() {
        return structure;
    }
    public Sdl structure(String structure) {
        this.structure = structure;
        return this;
    }
    public void setStructure(String structure) {
        this.structure = structure;
    }

    public String getCapital() {
        return capital;
    }
    public Sdl capital(String capital) {
        this.capital = capital;
        return this;
    }
    public void setCapital(String capital) {
        this.capital = capital;
    }

    public PieceJointe getPieceJointeSdl() {
        return pieceJointeSdl;
    }
    public Sdl pieceJointeSdl(PieceJointe pieceJointe) {
        this.pieceJointeSdl = pieceJointe;
        return this;
    }
    public void setPieceJointeSdl(PieceJointe pieceJointe) {
        this.pieceJointeSdl = pieceJointe;
    }

    public Set<SuiviSdl> getSuiviSdls() {
        return suiviSdls;
    }

    public Sdl suiviSdls(Set<SuiviSdl> suiviSdls) {
        this.suiviSdls = suiviSdls;
        return this;
    }

    public Sdl addSuiviSdl(SuiviSdl suiviSdl) {
        this.suiviSdls.add(suiviSdl);
        suiviSdl.setSdl(this);
        return this;
    }

    public Sdl removeSuiviSdl(SuiviSdl suiviSdl) {
        this.suiviSdls.remove(suiviSdl);
        suiviSdl.setSdl(null);
        return this;
    }

    public void setSuiviSdls(Set<SuiviSdl> suiviSdls) {
        this.suiviSdls = suiviSdls;
    }

    public Set<TypeIndicSdl> getTypeIndicataireSdls() {
        return typeIndicataireSdls;
    }

    public Sdl typeIndicataireSdls(Set<TypeIndicSdl> typeIndicSdls) {
        this.typeIndicataireSdls = typeIndicSdls;
        return this;
    }

    public Sdl addTypeIndicataireSdl(TypeIndicSdl typeIndicSdl) {
        this.typeIndicataireSdls.add(typeIndicSdl);
        typeIndicSdl.setSdl(this);
        return this;
    }

    public Sdl removeTypeIndicataireSdl(TypeIndicSdl typeIndicSdl) {
        this.typeIndicataireSdls.remove(typeIndicSdl);
        typeIndicSdl.setSdl(null);
        return this;
    }

    public void setTypeIndicataireSdls(Set<TypeIndicSdl> typeIndicSdls) {
        this.typeIndicataireSdls = typeIndicSdls;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Sdl)) {
            return false;
        }
        return id != null && id.equals(((Sdl) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Sdl{" +
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
