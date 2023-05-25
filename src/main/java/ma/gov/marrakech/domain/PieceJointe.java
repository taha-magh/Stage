package ma.gov.marrakech.domain;

import ma.gov.marrakech.domain.enumeration.TypePieceJointe;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

/**
 * A PieceJointe.
 */
@Entity
@Table(name = "piece_jointe_")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PieceJointe implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_piece_jointe")
    private TypePieceJointe typePieceJointe;

    @Lob
    @Column(name = "jhi_file")
    private byte[] file;

    @NotNull
    @Column(name = "jhi_file_content_type")
    private String fileContentType;

    @NotNull
    @Size(max = 400)
    @Column(name = "file_name", length = 400)
    private String fileName;

    // jhipster-needle-entity-add-field - JHipster will add fields here


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypePieceJointe getTypePieceJointe() {
        return typePieceJointe;
    }
    public void setTypePieceJointe(TypePieceJointe typePieceJointe) {
        this.typePieceJointe = typePieceJointe;
    }

    public byte[] getFile() {
        return file;
    }
    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getFileContentType() {
        return fileContentType;
    }
    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getFileName() {
        return this.fileName;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PieceJointe that = (PieceJointe) o;
        return Objects.equals(id, that.id) &&
            typePieceJointe == that.typePieceJointe &&
            Arrays.equals(file, that.file) &&
            Objects.equals(fileContentType, that.fileContentType) &&
            Objects.equals(fileName, that.fileName);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id,
            typePieceJointe,
            fileContentType,
            fileName
        );
        result = 31 * result + Arrays.hashCode(file);
        return result;
    }

    // prettier-ignore

    @Override
    public String toString() {
        return "PieceJointe{" +
            "id=" + id +
            ", typePieceJointe=" + typePieceJointe +
            ", file=" + Arrays.toString(file) +
            ", fileContentType='" + fileContentType + '\'' +
            ", fileName='" + fileName + '\'' +
            '}';
    }
}
