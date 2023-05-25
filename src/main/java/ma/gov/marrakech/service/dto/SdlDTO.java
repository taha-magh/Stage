package ma.gov.marrakech.service.dto;

import ma.gov.marrakech.domain.Sdl;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link Sdl} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SdlDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String raisonSocial;
    private String raisonCom;
    private String idFiscale;
    private String ice;
    private String numCnss;
    private String adresse;
    private String tel;
    private String fax;
    private String email;
    private String gerant;
    private String structure;
    private String capital;
    private PieceJointeDTO pieceJointeSdl;
    private Set<SuiviSdlDTO> suiviSdls = new HashSet<>();
    private Set<TypeIndicSdlDTO> typeIndicataireSdls = new HashSet<>();


    // getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getRaisonSocial() {
        return raisonSocial;
    }
    public void setRaisonSocial(String raisonSocial) {
        this.raisonSocial = raisonSocial;
    }
    public String getRaisonCom() {
        return raisonCom;
    }
    public void setRaisonCom(String raisonCom) {
        this.raisonCom = raisonCom;
    }
    public String getIdFiscale() {
        return idFiscale;
    }
    public void setIdFiscale(String idFiscale) {
        this.idFiscale = idFiscale;
    }
    public String getIce() {
        return ice;
    }
    public void setIce(String ice) {
        this.ice = ice;
    }
    public String getNumCnss() {
        return numCnss;
    }
    public void setNumCnss(String numCnss) {
        this.numCnss = numCnss;
    }
    public String getAdresse() {
        return adresse;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getFax() {
        return fax;
    }
    public void setFax(String fax) {
        this.fax = fax;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getGerant() {
        return gerant;
    }
    public void setGerant(String gerant) {
        this.gerant = gerant;
    }
    public String getStructure() {return structure;}
    public void setStructure(String structure) {this.structure = structure;}
    public String getCapital() {return capital;}
    public void setCapital(String capital) {this.capital = capital;}

    public PieceJointeDTO getPieceJointeSdlModelJ() {
        return pieceJointeSdl;
    }
    public void setPieceJointeSdl(PieceJointeDTO pieceJointeSdl) {
        this.pieceJointeSdl = pieceJointeSdl;
    }

    public Set<SuiviSdlDTO> getSuiviSdls() {
        return suiviSdls;
    }
    public void setSuiviSdls(Set<SuiviSdlDTO> suiviSdls) {
        this.suiviSdls = suiviSdls;
    }

    public Set<TypeIndicSdlDTO> getTypeIndicataireSdls() {
        return typeIndicataireSdls;
    }
    public void setTypeIndicataireSdls(Set<TypeIndicSdlDTO> typeIndicataireSdls) {
        this.typeIndicataireSdls = typeIndicataireSdls;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SdlDTO)) {
            return false;
        }
        SdlDTO sdlDTO = (SdlDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, sdlDTO.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    public SdlDTO(Long id, String raisonSocial, String raisonCom, String idFiscale, String ice, String numCnss, String adresse, String tel, String fax, String email, String gerant, String structure, String capital) {
        this.id = id;
        this.raisonSocial = raisonSocial;
        this.raisonCom = raisonCom;
        this.idFiscale = idFiscale;
        this.ice = ice;
        this.numCnss = numCnss;
        this.adresse = adresse;
        this.tel = tel;
        this.fax = fax;
        this.email = email;
        this.gerant = gerant;
        this.structure = structure;
        this.capital = capital;
    }
    @Override
    public String toString() {
        return "Delegataire:{" +
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
