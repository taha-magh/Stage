package ma.gov.marrakech.service.criteria;


import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import ma.gov.marrakech.domain.PieceJointe;
import ma.gov.marrakech.domain.enumeration.TypePieceJointe;
import org.springdoc.api.annotations.ParameterObject;

import java.io.Serializable;
import java.util.Objects;

/**
 * Criteria class for the {@link PieceJointe} entity. This class is used
 * in {@link ma.gov.marrakech.web.rest.PieceJointeResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /piece-jointe-model-j?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PieceJointeCriteria implements Serializable, Criteria {

    /**
     * Class for filtering TypePieceJointe
     */
    public static class TypePieceJointeFilter extends Filter<TypePieceJointe> {

        public TypePieceJointeFilter() {
        }

        public TypePieceJointeFilter(PieceJointeCriteria.TypePieceJointeFilter filter) {
            super(filter);
        }

        @Override
        public PieceJointeCriteria.TypePieceJointeFilter copy() {
            return new PieceJointeCriteria.TypePieceJointeFilter(this);
        }

    }


    private static final long serialVersionUID = 1L;

    private LongFilter id;
    private TypePieceJointeFilter typePieceJointe;
    private StringFilter file;
    private StringFilter fileContentType;
    private StringFilter fileName;



    public PieceJointeCriteria() {}

    public PieceJointeCriteria(PieceJointeCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.file = other.file == null ? null : other.file.copy();
        this.fileContentType = other.fileContentType == null ? null : other.fileContentType.copy();
        this.fileName = other.fileName == null ? null : other.fileName.copy();
    }

    @Override
    public PieceJointeCriteria copy() {
        return new PieceJointeCriteria (this);
    }

    public LongFilter getId() {
        return id;
    }
    public LongFilter id() {
        if (id == null) {
            id = new LongFilter();
        }
        return id;
    }
    public void setId(LongFilter id) {
        this.id = id;
    }

    public TypePieceJointeFilter getTypePieceJointe() {
        return typePieceJointe;
    }
    public void setTypePieceJointe(TypePieceJointeFilter typePieceJointe) {
        this.typePieceJointe = typePieceJointe;
    }

    public StringFilter getFile() {return file;}
    public StringFilter file() {
        if (file == null) {
            file = new StringFilter();
        }
        return file;
    }
    public void setFile(StringFilter file) {
        this.file = file;
    }

    public StringFilter getFileContentType() {
        return fileContentType;
    }
    public StringFilter fileContentType() {
        if (fileContentType == null) {
            fileContentType = new StringFilter();
        }
        return fileContentType;
    }
    public void setFileContentType(StringFilter fileContentType) {
        this.fileContentType = fileContentType;
    }

    public StringFilter getFileName() {
        return fileName;
    }
    public StringFilter fileName() {
        if (fileName == null) {
            fileName = new StringFilter();
        }
        return fileName;
    }
    public void setFileName(StringFilter fileName) {
        this.fileName = fileName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PieceJointeCriteria that = (PieceJointeCriteria) o;
        return Objects.equals(id, that.id) &&
            typePieceJointe == that.typePieceJointe &&
            Objects.equals(file, that.file) &&
            Objects.equals(fileContentType, that.fileContentType) &&
            Objects.equals(fileName, that.fileName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,
            typePieceJointe,
            file,
            fileContentType,
            fileName
        );
    }

    // prettier-ignore

    @Override
    public String toString() {
        return "PieceJointeCriteria{" +
            "id=" + id +
            ", typePieceJointe=" + typePieceJointe +
            ", file=" + file +
            ", fileContentType=" + fileContentType +
            ", fileName=" + fileName +
            '}';
    }
}
