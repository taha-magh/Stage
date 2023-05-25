package ma.gov.marrakech.service.dto;

import java.io.Serializable;
import java.util.Objects;

public class TypeIndicDelegataireDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String libelle;
    private String description;
    private Long delegataire;

    // jhipster-needle-dto-add-field - JHipster will add fields here

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getLibelle() {
        return libelle;
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {this.description = description;}
    public Long getDelegataire() {return delegataire;}
    public void setDelegataire(Long delegataire) {this.delegataire = delegataire;}

    // jhipster-needle-dto-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeIndicDelegataireDTO that = (TypeIndicDelegataireDTO) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(libelle, that.libelle) &&
            Objects.equals(description, that.description) &&
            Objects.equals(delegataire, that.delegataire
            );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            libelle,
            description,
            delegataire
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TypeIndicDelegataireDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", description='" + getDescription() + "'" +
            ", delegataire='" + getDelegataire() + "'" +
            "}";
    }
}

