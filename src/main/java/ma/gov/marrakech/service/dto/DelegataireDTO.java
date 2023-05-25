package ma.gov.marrakech.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class DelegataireDTO implements Serializable {
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

        private PieceJointeDTO pieceJointeDelegataire;
        private Set<SuiviDelegataireDTO> suiviDelegataires = new HashSet<>();
        private Set<TypeIndicDelegataireDTO> typeIndicataireDelegataires = new HashSet<>();

        // getters and setters omitted for brevity
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
        public String getStructure() {
            return structure;
        }
        public void setStructure(String structure) {
            this.structure = structure;
        }
        public String getCapital() {
            return capital;
        }
        public void setCapital(String capital) {
            this.capital = capital;
        }
        public PieceJointeDTO getPieceJointeDelegataire() {
            return pieceJointeDelegataire;
        }
        public void setPieceJointeDelegataire(PieceJointeDTO pieceJointeDelegataire) {
            this.pieceJointeDelegataire = pieceJointeDelegataire;
        }
        public Set<SuiviDelegataireDTO> getSuiviDelegataires() {
            return suiviDelegataires;
        }
        public void setSuiviDelegataires(Set<SuiviDelegataireDTO> suiviDelegataires) {
            this.suiviDelegataires = suiviDelegataires;
        }
        public Set<TypeIndicDelegataireDTO> getTypeIndicataireDelegataires() {
            return typeIndicataireDelegataires;
        }
        public void setTypeIndicataireDelegataires(Set<TypeIndicDelegataireDTO> typeIndicataireDelegataires) {
            this.typeIndicataireDelegataires = typeIndicataireDelegataires;
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DelegataireDTO)) {
            return false;
        }
        DelegataireDTO delegataireDTO = (DelegataireDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, delegataireDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
    public DelegataireDTO(){}
    public DelegataireDTO(Long id, String raisonSocial, String raisonCom, String idFiscale, String ice, String numCnss, String adresse, String tel, String fax, String email, String gerant, String structure, String capital) {
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

