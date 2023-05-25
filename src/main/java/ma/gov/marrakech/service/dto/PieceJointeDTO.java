package ma.gov.marrakech.service.dto;

import ma.gov.marrakech.domain.enumeration.TypePieceJointe;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class PieceJointeDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private TypePieceJointe typePieceJointe;
    private Long id;
    private byte[] file;
    private String fileContentType;
    private String fileName;

    //getters & setters
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public TypePieceJointe getTypePieceJointe() {return typePieceJointe;}
    public void setTypePieceJointe(TypePieceJointe typePieceJointe) {this.typePieceJointe = typePieceJointe;}
    public byte[] getFile() {return file;}
    public void setFile(byte[] file) {this.file = file;}
    public String getFileContentType() {return fileContentType;}
    public void setFileContentType(String fileContentType) {this.fileContentType = fileContentType;}
    public String getFileName() {return fileName;}
    public void setFileName(String fileName) {this.fileName = fileName;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PieceJointeDTO that = (PieceJointeDTO) o;
        return typePieceJointe == that.typePieceJointe &&
            Objects.equals(id, that.id) &&
            Arrays.equals(file, that.file) &&
            Objects.equals(fileContentType, that.fileContentType) &&
            Objects.equals(fileName, that.fileName);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(typePieceJointe,
            id,
            fileContentType,
            fileName
        );
        result = 31 * result + Arrays.hashCode(file);
        return result;
    }

    public PieceJointeDTO(TypePieceJointe typePieceJointe, Long id, byte[] file, String fileContentType, String fileName) {
        this.typePieceJointe = typePieceJointe;
        this.id = id;
        this.file = file;
        this.fileContentType = fileContentType;
        this.fileName = fileName;
    }

    public PieceJointeDTO() {
    }

    @Override
    public String toString() {
        return "PieceJointeDTO{" +
            "typePieceJointe=" + typePieceJointe +
            ", id=" + id +
            ", file=" + Arrays.toString(file) +
            ", fileContentType='" + fileContentType + '\'' +
            ", fileName='" + fileName + '\'' +
            '}';
    }
}

