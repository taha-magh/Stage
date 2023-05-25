package ma.gov.marrakech.utils.impl;

import ma.gov.marrakech.domain.*;
import ma.gov.marrakech.service.dto.*;
import ma.gov.marrakech.utils.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class MapperImpl implements Mapper {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public <T> T map(Object source, Class<T> targetClass) {
        return modelMapper.map(source, targetClass);
    }

    @Override
    public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
            .stream()
            .map(element -> modelMapper.map(element, targetClass))
            .collect(Collectors.toList());
    }

    @Override
    public <S, T> Set<T> mapSet(Set<S> source, Class<T> targetClass) {
        return source
            .stream()
            .map(element -> modelMapper.map(element, targetClass))
            .collect(Collectors.toSet());
    }

    @Override
    public <S, T> Page<T> mapPageToOther(Page<S> sourceClassPage, Class<T> targetClass) {
        return sourceClassPage.map(i -> modelMapper.map(i, targetClass));
    }

    @Override
    public SdlDTO toDto(Sdl sdl) {
        return null;
    }

    @Override
    public Sdl toEntity(SdlDTO sdlDTO) {
        return null;
    }

    @Override
    public void partialUpdate(StructureSdl existingSdl, StructureSdlDTO sdlDTO) {

    }

    @Override
    public StructureDelegataire toEntity(StructureDelegataireDTO structureDelegataireDTO) {
        return null;
    }

    @Override
    public StructureDelegataireDTO toDto(StructureDelegataire structureDelegataire) {
        return null;
    }


    @Override
    public TypeIndicSdl toEntity(TypeIndicSdlDTO typeIndicSdlDTO) {
        return null;
    }

    @Override
    public TypeIndicSdlDTO toDto(TypeIndicSdl typeIndicSdl) {
        return null;
    }

    @Override
    public TypeIndicDelegataire toEntity(TypeIndicDelegataireDTO typeIndicDelegataireDTO) {
        return null;
    }

    @Override
    public TypeIndicDelegataireDTO toDto(TypeIndicDelegataire typeIndicDelegataire) {
        return null;
    }

    @Override
    public StructureSdl toEntity(StructureSdlDTO structureSdlDTO) {return null;}

    @Override
    public StructureSdlDTO toDto(StructureSdl structureSdl) {
        return null;
    }

    @Override
    public void partialUpdate(StructureDelegataire existingStructureDelegataire, StructureDelegataireDTO structureDelegataireDTO) {

    }

    @Override
    public SuiviSdlDTO toDto(SuiviSdl suiviSdl) {
        return null;
    }

    @Override
    public SuiviDelegataireDTO toDto(SuiviDelegataire suiviDelegataire) {
        return null;
    }

    @Override
    public void partialUpdate(Sdl existingSdl, SdlDTO sdlDTO) {

    }

    @Override
    public List<SuiviDelegataireDTO> toSuiviDelegataireDtoList(List<SuiviDelegataire> all) {
        return null;
    }

    @Override
    public List<TypeIndicSdlDTO> toTypeIndicSdlDtoList(List<TypeIndicSdl> all) {
        return null;
    }

    @Override
    public List<TypeIndicDelegataireDTO> toTypeIndicDelegataireDtoList(List<TypeIndicDelegataire> all) {
        return null;
    }

    @Override
    public DelegataireDTO toDto(Delegataire delegataire) {
        return null;
    }

    @Override
    public void partialUpdate(Delegataire existingDelegataire, DelegataireDTO delegataireDTO) {

    }

    @Override
    public Delegataire toEntity(DelegataireDTO delegataireDTO) {
        return null;
    }

    @Override
    public SuiviDelegataire toEntity(SuiviDelegataireDTO suiviDelegataireDTO) {
        return null;
    }

    @Override
    public void partialUpdate(SuiviDelegataire existingSuiviDelegataire, SuiviDelegataireDTO suiviDelegataireDTO) {

    }

    @Override
    public SuiviSdl toEntity(SuiviSdlDTO suiviSdlDTO) {
        return null;
    }

    @Override
    public void partialUpdate(SuiviSdl existingSuiviSdl, SuiviSdlDTO suiviSdlDTO) {

    }

}
