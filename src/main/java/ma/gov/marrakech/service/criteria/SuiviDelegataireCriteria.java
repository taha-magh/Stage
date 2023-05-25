package ma.gov.marrakech.service.criteria;

import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import ma.gov.marrakech.domain.SuiviDelegataire;
import ma.gov.marrakech.domain.SuiviDelegataire_;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Criteria class for the {@link ma.gov.marrakech.domain.SuiviDelegataire} entity. This class is used
 * in {@link ma.gov.marrakech.web.rest.SuiviDelegataireResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /suiviDelegataires?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */

public class SuiviDelegataireCriteria implements Serializable, Criteria {
    private static final long serialVersionUID = 1L;
    private LongFilter id;
    private StringFilter typeIndicateur;
    private StringFilter nom;
    private StringFilter description;
    private StringFilter dateValeur;
    private StringFilter valeurContractuel;
    private StringFilter valeurConstate;
    private LongFilter delegataire;

    public SuiviDelegataireCriteria() {}

    public SuiviDelegataireCriteria(SuiviDelegataireCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.typeIndicateur = other.typeIndicateur == null ? null : other.typeIndicateur.copy();
        this.nom = other.nom == null ? null : other.nom.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.dateValeur = other.dateValeur == null ? null : other.dateValeur.copy();
        this.valeurContractuel = other.valeurContractuel == null ? null : other.valeurContractuel.copy();
        this.valeurConstate = other.valeurConstate == null ? null : other.valeurConstate.copy();
        this.delegataire = other.delegataire == null ? null : other.delegataire.copy();
    }

    @Override
    public SuiviDelegataireCriteria copy() {
        return new SuiviDelegataireCriteria(this);
    }
//getters & setters
    public LongFilter getId() {
        return id;
    }
    public void setId(LongFilter id) {
        this.id = id;
    }
    public StringFilter getTypeIndicateur() {
        return typeIndicateur;
    }
    public void setTypeIndicateur(StringFilter typeIndicateur) {
        this.typeIndicateur = typeIndicateur;
    }
    public StringFilter getNom() {
        return nom;
    }
    public void setNom(StringFilter nom) {
        this.nom = nom;
    }
    public StringFilter getDescription() {
        return description;
    }
    public void setDescription(StringFilter description) {
        this.description = description;
    }
    public StringFilter getDateValeur() {
        return dateValeur;
    }
    public void setDateValeur(StringFilter dateValeur) {
        this.dateValeur = dateValeur;
    }
    public StringFilter getValeurContractuel() {
        return valeurContractuel;
    }
    public void setValeurContractuel(StringFilter valeurContractuel) {
        this.valeurContractuel = valeurContractuel;
    }
    public StringFilter getValeurConstate() {
        return valeurConstate;
    }
    public void setValeurConstate(StringFilter valeurConstate) {
        this.valeurConstate = valeurConstate;
    }
    public LongFilter getDelegataireId(){return delegataire;}
    public void setDelegataire(LongFilter delegataire) {
        this.delegataire = delegataire;
    }

/*    @Override
    public Predicate toPredicate(Root<SuiviDelegataire> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();

        if (getId() != null) {
            predicates.add(builder.equal(root.get(SuiviDelegataire_.id), getId()));
        }
        if (getTypeIndicateur() != null) {
            predicates.add(builder.like(builder.lower(root.get("typeIndicateur")), "%" + getTypeIndicateur().toLowerCase() + "%"));
        }
        if (getNom() != null) {
            predicates.add(builder.like(builder.lower(root.get("nom")), "%" + getNom().toLowerCase() + "%"));
        }
        if (getDescription() != null) {
            predicates.add(builder.like(builder.lower(root.get("description")), "%" + getDescription().toLowerCase() + "%"));
        }
        if (getDateValeur() != null) {
            predicates.add(builder.like(builder.lower(root.get("dateValeur")), "%" + getDateValeur().toLowerCase() + "%"));
        }
        if (getValeurContractuel() != null) {
            predicates.add(builder.like(builder.lower(root.get("valeurContractuel")), "%" + getValeurContractuel().toLowerCase() + "%"));
        }
        if (getValeurConstate() != null) {
            predicates.add(builder.like(builder.lower(root.get("valeurConstate")), "%" + getValeurConstate().toLowerCase() + "%"));
        }
        if (getDelegataire() != null) {
            predicates.add(builder.equal(root.get("delegataire"), getDelegataire()));
        }

        return builder.and(predicates.toArray(new Predicate[predicates.size()]));
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SuiviDelegataireCriteria that = (SuiviDelegataireCriteria) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(typeIndicateur, that.typeIndicateur) &&
            Objects.equals(nom, that.nom) &&
            Objects.equals(description, that.description) &&
            Objects.equals(dateValeur, that.dateValeur) &&
            Objects.equals(valeurContractuel, that.valeurContractuel) &&
            Objects.equals(valeurConstate, that.valeurConstate) &&
            Objects.equals(delegataire, that.delegataire);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            typeIndicateur,
            nom,
            description,
            dateValeur,
            valeurContractuel,
            valeurConstate,
            delegataire);
    }

    @Override
    public String toString() {
        return "SuiviDelegataireCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (typeIndicateur != null ? "typeIndicateur=" + typeIndicateur + ", " : "") +
            (nom != null ? "nom=" + nom + ", " : "") +
            (description != null ? "description=" + description + ", " : "") +
            (dateValeur != null ? "dateValeur=" + dateValeur + ", " : "") +
            (valeurContractuel != null ? "valeurContractuel=" + valeurContractuel + ", " : "") +
            (valeurConstate != null ? "valeurConstate=" + valeurConstate + ", " : "") +
            (delegataire != null ? "delegataireId=" + delegataire + ", " : "") +
            "}";
    }
}
