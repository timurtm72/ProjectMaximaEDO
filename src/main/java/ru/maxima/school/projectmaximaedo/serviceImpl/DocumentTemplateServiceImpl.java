package ru.maxima.school.projectmaximaedo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.maxima.school.projectmaximaedo.dto.DocumentFieldDto;
import ru.maxima.school.projectmaximaedo.dto.DocumentTemplateDto;
import ru.maxima.school.projectmaximaedo.mapper.DocumentTemplateMapper;
import ru.maxima.school.projectmaximaedo.model.DocumentTemplate;
import ru.maxima.school.projectmaximaedo.repository.DocumentTemplateRepository;
import ru.maxima.school.projectmaximaedo.service.DocumentTemplateService;
import ru.maxima.school.projectmaximaedo.utils.MapperUtil;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class DocumentTemplateServiceImpl implements DocumentTemplateService {

    private final DocumentTemplateRepository documentTemplateRepository;
    private final DocumentTemplateMapper documentTemplateMapper;
    private final DocumentFieldServiceImpl documentFieldService;
    private final MapperUtil mapperUtil;
    @Autowired
    public DocumentTemplateServiceImpl(DocumentTemplateRepository documentTemplateRepository, DocumentTemplateMapper documentTemplateMapper, DocumentFieldServiceImpl documentFieldService, MapperUtil mapperUtil) {
        this.documentTemplateRepository = documentTemplateRepository;
        this.documentTemplateMapper = documentTemplateMapper;
        this.documentFieldService = documentFieldService;
        this.mapperUtil = mapperUtil;
    }
    @Override
    @Transactional
    public List<DocumentTemplateDto> getAll() {
        List<DocumentTemplate> documentTemplates = documentTemplateRepository.findAllByIsRemovedIsFalseOrderByIdAsc();
        if(documentTemplates == null || documentTemplates.size() == 0 ){
            return null;
        }
        return mapperUtil.mapList(documentTemplates,DocumentTemplateDto.class);
    }

    @Override
    @Transactional
    public Boolean exists(Long id) {
        return documentTemplateRepository.existsById(id);
    }

    @Override
    @Transactional
    public DocumentTemplateDto getById(Long id) {
        DocumentTemplate documentTemplate = documentTemplateRepository.findDocumentTemplateById(id).orElse(null);
        return documentTemplate != null ? documentTemplateMapper.toDto(documentTemplate) : null;
    }

    @Override
    @Transactional
    public Boolean create(DocumentTemplateDto documentTemplateDto) {
        if(documentTemplateDto == null){
            return true;
        }
        if(documentTemplateDto.getTemplateFields() == null){
            documentTemplateDto.setTemplateFields(new ArrayList<>());
        }
        documentTemplateDto.getTemplateFields().clear();
        List<DocumentFieldDto> documentFieldsDto = documentFieldService.getAll();
        for (int i = 0; i < documentTemplateDto.getTemplateFieldNumbers().size(); i++) {
            if(documentTemplateDto.getTemplateFieldNumbers().get(i) <= 0){
                return true;
            }
             DocumentFieldDto documentFieldDto = documentFieldsDto.get(documentTemplateDto.getTemplateFieldNumbers().get(i) - 1);
             documentTemplateDto.getTemplateFields().add(documentFieldDto);
        }
        DocumentTemplate documentTemplate =  documentTemplateMapper.toEntity(documentTemplateDto);
        if(documentTemplate != null){
            documentTemplate.setCreatedAt(LocalDateTime.now());
            documentTemplate.setVersion(1);
            documentTemplateRepository.save(documentTemplate);
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public Boolean update(DocumentTemplateDto documentTemplateDto,Long id) {
        if(documentTemplateDto == null){
            return true;
        }
        documentTemplateDto.setId(id);
        if(documentTemplateDto.getTemplateFields() == null){
            documentTemplateDto.setTemplateFields(new ArrayList<>());
        }
        DocumentTemplate documentTemplate =  documentTemplateMapper.toEntity(documentTemplateDto);
        DocumentTemplate readDocumentTemplate = documentTemplateRepository.findDocumentTemplateById(id).orElse(null);
        if(readDocumentTemplate != null){
            documentTemplate.setVersion(readDocumentTemplate.getVersion() + 1);
            documentTemplate.setCreatedAt(readDocumentTemplate.getCreatedAt());
            documentTemplate.setRemoved(readDocumentTemplate.isRemoved());
            documentTemplateRepository.save(documentTemplate);
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public Boolean safeDelete(Long id) {
        DocumentTemplate documentTemplate = documentTemplateRepository.findDocumentTemplateById(id).orElse(null);
        if(documentTemplate != null){
            documentTemplate.setRemoved(true);
            documentTemplateRepository.save(documentTemplate);
            return false;
        }
        return true;
    }
}
