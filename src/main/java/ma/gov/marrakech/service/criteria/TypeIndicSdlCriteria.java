package ma.gov.marrakech.service.criteria;

import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

import java.io.Serializable;
import java.util.Objects;



/**
 * Criteria class for the {@link ma.gov.marrakech.domain.TypeIndicSdl} entity. This class is used
 * in {@link ma.gov.marrakech.web.rest.TypeIndicSdlResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /typeIndicSdls?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class TypeIndicSdlCriteria implements Serializable, Criteria {
    private static final long serialVersionUID = 1L;
    private LongFilter id;
    private StringFilter libelle;
    private StringFilter description;
    private LongFilter sdl;
    public TypeIndicSdlCriteria() {}
    public TypeIndicSdlCriteria(TypeIndicSdlCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.libelle = other.libelle == null ? null : other.libelle.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.sdl = other.sdl == null ? null : other.sdl.copy();
    }

    @Override
    public TypeIndicSdlCriteria copy() {
        return new TypeIndicSdlCriteria(this);
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
    public LongFilter getSdl() {return sdl;}
    public void setSdl(LongFilter sdl) {
        this.sdl = sdl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeIndicSdlCriteria that = (TypeIndicSdlCriteria) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(libelle, that.libelle) &&
            Objects.equals(description, that.description) &&
            Objects.equals(sdl, that.sdl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,
            libelle,
            description,
            sdl
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TypeIndicSdlCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (libelle != null ? "libelle=" + libelle + ", " : "") +
            (description != null ? "description=" + description + ", " : "") +
            (sdl != null ? "sdl=" + sdl + ", " : "") +
            "}";
    }



/*    public static Predicate toPredicate(
        TypeIndicSdlCriteria criteria,
        Root<TypeIndicSdl> root,
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



