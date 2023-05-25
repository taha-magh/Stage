package ma.gov.marrakech.service.criteria;

import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

import java.io.Serializable;
import java.util.Objects;

/**
 * Criteria class for the {@link ma.gov.marrakech.domain.TypeIndicDelegataire} entity. This class is used
 * in {@link ma.gov.marrakech.web.rest.TypeIndicDelegataireResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /typeIndicDelegataires?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class TypeIndicDelegataireCriteria implements Serializable, Criteria {
    private static final long serialVersionUID = 1L;
    private LongFilter id;
    private StringFilter libelle;
    private StringFilter description;
    private LongFilter delegataire;
    public TypeIndicDelegataireCriteria() {}
    public TypeIndicDelegataireCriteria(TypeIndicDelegataireCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.libelle = other.libelle == null ? null : other.libelle.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.delegataire = other.delegataire == null ? null : other.delegataire.copy();
    }

    @Override
    public TypeIndicDelegataireCriteria copy() {
        return new TypeIndicDelegataireCriteria(this);
    }

    //getters & setters
    public LongFilter getId() {return id;}
    public void setId(LongFilter id) {
        this.id = id;
    }
    public StringFilter getLibelle() {return libelle;}
    public void setLibelle(StringFilter libelle) {
        this.libelle = libelle;
    }
    public StringFilter getDescription() {return description;}
    public void setDescription(StringFilter description) {
        this.description = description;
    }
    public LongFilter getDelegataire() {return delegataire;}
    public void setDelegataire(LongFilter delegataire) {
        this.delegataire = delegataire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeIndicDelegataireCriteria that = (TypeIndicDelegataireCriteria) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(libelle, that.libelle) &&
            Objects.equals(description, that.description) &&
            Objects.equals(delegataire, that.delegataire);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,
            libelle,
            description,
            delegataire
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TypeIndicDelegataireCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (libelle != null ? "libelle=" + libelle + ", " : "") +
            (description != null ? "description=" + description + ", " : "") +
            (delegataire != null ? "delegataire=" + delegataire + ", " : "") +
            "}";
    }



/*    public static Predicate toPredicate(
        TypeIndicDelegataireCriteria criteria,
        Root<TypeIndicDelegataire> root,
        CriteriaQuery<?> criteriaQuery,
        CriteriaBuilder criteriaBuilder
    ) {
        Predicate predicate = criteriaBuilder.conjunction();

        LongFilter id = criteria.getId();
        if (id != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal
                ................
    }
*/


}

