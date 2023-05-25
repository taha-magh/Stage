package ma.gov.marrakech.utils;


import ma.gov.marrakech.domain.*;
import ma.gov.marrakech.service.dto.*;
import org.springframework.data.domain.Page;
import java.util.List;
import java.util.Set;


public interface Mapper {
    <T> T map(Object source, Class<T> targetClass);

    <S, T> List<T> mapList(List<S> source, Class<T> targetClass);

    <S, T> Set<T> mapSet(Set<S> source, Class<T> targetClass);

    <S, T> Page<T> mapPageToOther(Page<S> sourceClassPage, Class<T> targetClass);


    SdlDTO toDto(Sdl sdl);

    Sdl toEntity(SdlDTO sdlDTO);

    void partialUpdate(StructureSdl existingSdl, StructureSdlDTO sdlDTO);

    StructureDelegataire toEntity(StructureDelegataireDTO structureDelegataireDTO);

    StructureDelegataireDTO toDto(StructureDelegataire structureDelegataire);

    TypeIndicSdl toEntity(TypeIndicSdlDTO typeIndicSdlDTO);

    TypeIndicSdlDTO toDto(TypeIndicSdl typeIndicSdl);

    TypeIndicDelegataire toEntity(TypeIndicDelegataireDTO typeIndicDelegataireDTO);

    TypeIndicDelegataireDTO toDto(TypeIndicDelegataire typeIndicDelegataire);

    StructureSdl toEntity(StructureSdlDTO structureSdlDTO);

    StructureSdlDTO toDto(StructureSdl structureSdl);

    void partialUpdate(StructureDelegataire existingStructureDelegataire, StructureDelegataireDTO structureDelegataireDTO);

    SuiviSdlDTO toDto(SuiviSdl suiviSdl);

    SuiviDelegataireDTO toDto(SuiviDelegataire suiviDelegataire);

    void partialUpdate(Sdl existingSdl, SdlDTO sdlDTO);

    List<SuiviDelegataireDTO> toSuiviDelegataireDtoList(List<SuiviDelegataire> all);

    List<TypeIndicSdlDTO> toTypeIndicSdlDtoList(List<TypeIndicSdl> all);

    List<TypeIndicDelegataireDTO> toTypeIndicDelegataireDtoList(List<TypeIndicDelegataire> all);

    DelegataireDTO toDto(Delegataire delegataire);

    void partialUpdate(Delegataire existingDelegataire, DelegataireDTO delegataireDTO);

    Delegataire toEntity(DelegataireDTO delegataireDTO);

    SuiviDelegataire toEntity(SuiviDelegataireDTO suiviDelegataireDTO);

    void partialUpdate(SuiviDelegataire existingSuiviDelegataire, SuiviDelegataireDTO suiviDelegataireDTO);

    SuiviSdl toEntity(SuiviSdlDTO suiviSdlDTO);

    void partialUpdate(SuiviSdl existingSuiviSdl, SuiviSdlDTO suiviSdlDTO);
}
