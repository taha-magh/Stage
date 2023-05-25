package ma.gov.marrakech.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link StructureSdlDTO} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class StructureSdlDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String libelle;
    private String description;

    // getters and setters
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
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StructureSdlDTO that = (StructureSdlDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(libelle, that.libelle) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, libelle, description);
    }

    public StructureSdlDTO(){}
    public StructureSdlDTO(Long id, String libelle, String description) {
        this.id = id;
        this.libelle = libelle;
        this.description = description;
    }

    @Override
    public String toString() {
        return "StructureSdlDTO{" +
            "id=" + id +
            ",libelle='" + libelle + '\'' +
            ",description='" + description + '\'' +
            '}';
    }
}

