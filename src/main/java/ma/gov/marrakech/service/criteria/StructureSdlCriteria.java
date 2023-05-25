package ma.gov.marrakech.service.criteria;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.*;
import ma.gov.marrakech.domain.StructureSdl;
import ma.gov.marrakech.repository.StructureSdlRepository;

import java.io.Serializable;

/**
 * Criteria class for the {@link StructureSdl} entity. This class is used
 * in {@link StructureSdlRepository} to filter the entity.
 */
public class StructureSdlCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter libelle;

    private StringFilter description;

    public StructureSdlCriteria() {}

    public StructureSdlCriteria(StructureSdlCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.libelle = other.libelle == null ? null : other.libelle.copy();
        this.description = other.description == null ? null : other.description.copy();
    }

    @Override
    public StructureSdlCriteria copy() {
        return new StructureSdlCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }
    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getLibelle() {
        return libelle;
    }
    public void setLibelle(StringFilter libelle) {
        this.libelle = libelle;
    }

    public StringFilter getDescription() {
        return description;
    }
    public void setDescription(StringFilter description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "StructureSdlCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (libelle != null ? "libelle=" + libelle + ", " : "") +
            (description != null ? "description=" + description + ", " : "") +
            "}";
    }

}
