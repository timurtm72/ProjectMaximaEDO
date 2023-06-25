package ru.maxima.school.projectmaximaedo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.maxima.school.projectmaximaedo.dto.DocumentFieldDto;
import ru.maxima.school.projectmaximaedo.mapper.DocumentFieldMapper;
import ru.maxima.school.projectmaximaedo.model.DocumentField;
import ru.maxima.school.projectmaximaedo.repository.DocumentFieldRepository;
import ru.maxima.school.projectmaximaedo.service.DocumentFieldService;
import ru.maxima.school.projectmaximaedo.utils.MapperUtil;

import java.util.List;
@Service
public class DocumentFieldServiceImpl implements DocumentFieldService {
    private final DocumentFieldRepository documentFieldRepository;
    private final DocumentFieldMapper documentFieldMapper;
    private final MapperUtil mapperUtil;
    @Autowired
    public DocumentFieldServiceImpl(DocumentFieldRepository documentFieldRepository, DocumentFieldMapper documentFieldMapper, MapperUtil mapperUtil) {
        this.documentFieldRepository = documentFieldRepository;
        this.documentFieldMapper = documentFieldMapper;
        this.mapperUtil = mapperUtil;
    }
    @Override
    @Transactional
    public List<DocumentFieldDto> getAll() {
        List<DocumentField> documentFields = documentFieldRepository.findAllByIsRemovedIsFalseOrderByIdAsc();
        if(documentFields == null || documentFields.size() == 0 ){
            return null;
        }
        return mapperUtil.mapList(documentFields,DocumentFieldDto.class);
    }
    @Override
    @Transactional
    public Boolean exists(Long id) {
        return documentFieldRepository.existsByIdAndIsRemovedIsFalse(id);
    }
    @Override
    @Transactional
    public DocumentFieldDto getById(Long id) {
        DocumentField documentField = documentFieldRepository.findDocumentFieldByIdAndIsRemovedIsFalse(id).orElse(null);
        return documentField != null ?documentFieldMapper.toDto(documentField) : null;
    }

    @Override
    @Transactional
    public Boolean create(DocumentFieldDto documentFieldDto) {
        DocumentField documentField =  documentFieldMapper.toEntity(documentFieldDto);
        if(documentField != null){
            documentFieldRepository.save(documentField);
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public Boolean update(DocumentFieldDto documentFieldDto,Long id) {
        if(documentFieldDto == null){
            return true;
        }
        documentFieldDto.setId(id);
        DocumentField documentField =  documentFieldMapper.toEntity(documentFieldDto);
        DocumentField readDocumentField = documentFieldRepository.findDocumentFieldByIdAndIsRemovedIsFalse(id).orElse(null);
        if(readDocumentField != null){
            documentField.setRemoved(readDocumentField.isRemoved());
            documentFieldRepository.save(documentField);
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public Boolean safeDelete(Long id) {
        DocumentField documentField = documentFieldRepository.findDocumentFieldByIdAndIsRemovedIsFalse(id).orElse(null);
        if(documentField != null){
            documentField.setRemoved(true);
            documentFieldRepository.save(documentField);
            return false;
        }
        return true;
    }
}
