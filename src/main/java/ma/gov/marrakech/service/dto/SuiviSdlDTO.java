package ma.gov.marrakech.service.dto;

import ma.gov.marrakech.domain.SuiviSdl;

import java.io.Serializable;
import java.util.Objects;

public class SuiviSdlDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String typeIndicateur;
    private String nom;
    private String description;
    private String dateValeur;
    private String valeurContractuel;
    private String valeurConstate;
    private Long sdl;
    public SuiviSdlDTO() {}

    public SuiviSdlDTO(Long id, String typeIndicateur, String nom, String description, String dateValeur,
                               String valeurContractuel, String valeurConstate, Long sdl) {
        this.id = id;
        this.typeIndicateur = typeIndicateur;
        this.nom = nom;
        this.description = description;
        this.dateValeur = dateValeur;
        this.valeurContractuel = valeurContractuel;
        this.valeurConstate = valeurConstate;
        this.sdl = sdl;
    }

    public SuiviSdlDTO(SuiviSdl entity) {
        this(entity.getId(), entity.getTypeIndicateur(), entity.getNom(), entity.getDescription(),
            entity.getDateValeur(), entity.getValeurContractuel(), entity.getValeurConstate(),
            entity.getSdl().getId());
    }

    // Getters and setters omitted for brevity

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeIndicateur() {
        return typeIndicateur;
    }
    public void setTypeIndicateur(String typeIndicateur) {
        this.typeIndicateur = typeIndicateur;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateValeur() {
        return dateValeur;
    }
    public void setDateValeur(String dateValeur) {
        this.dateValeur = dateValeur;
    }

    public String getValeurContractuel() {
        return valeurContractuel;
    }
    public void setValeurContractuel(String valeurContractuel) {
        this.valeurContractuel = valeurContractuel;
    }

    public String getValeurConstate() {
        return valeurConstate;
    }
    public void setValeurConstate(String valeurConstate) {
        this.valeurConstate = valeurConstate;
    }

    public Long getSdl() {return sdl;}
    public void setSdl(Long sdl) {
        this.sdl = sdl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SuiviSdlDTO that = (SuiviSdlDTO) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(typeIndicateur, that.typeIndicateur) &&
            Objects.equals(nom, that.nom) &&
            Objects.equals(description, that.description) &&
            Objects.equals(dateValeur, that.dateValeur) &&
            Objects.equals(valeurContractuel, that.valeurContractuel) &&
            Objects.equals(valeurConstate, that.valeurConstate) &&
            Objects.equals(sdl, that.sdl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,
            typeIndicateur,
            nom,
            description,
            dateValeur,
            valeurContractuel,
            valeurConstate,
            sdl
        );
    }

    @Override
    public String toString() {
        return "SuiviSdlDTO{" +
            "id=" + id +
            ", typeIndicateur='" + typeIndicateur + '\'' +
            ", nom='" + nom + '\'' +
            ", description='" + description + '\'' +
            ", dateValeur='" + dateValeur + '\'' +
            ", valeurContractuel='" + valeurContractuel + '\'' +
            ", valeurConstate='" + valeurConstate + '\'' +
            ", sdl=" + sdl +
            '}';
    }
}

