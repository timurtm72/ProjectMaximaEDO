package ru.maxima.school.projectmaximaedo.serviceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.maxima.school.projectmaximaedo.dto.DocumentDto;
import ru.maxima.school.projectmaximaedo.service.DocumentService;
import java.util.List;
@Service
public class DocumentServiceImpl implements DocumentService {
//    private final DocumentRepository documentRepository;
//    private final DocumentMapper
    @Override
    @Transactional
    public List<DocumentDto> getAll() {
//        List<Document> documentTemplates = documentRepository.findAllByIsRemovedIsFalseOrderByIdAsc();
//        if(documentTemplates == null || documentTemplates.size() == 0 ){
//            return null;
//        }
        return null;//mapperUtil.mapList(documentTemplates, DocumentTemplateDto.class);
    }

    @Override
    @Transactional
    public Boolean exists(Long id) {
        return null;
    }

    @Override
    @Transactional
    public DocumentDto getById(Long id) {
        return null;
    }

    @Override
    @Transactional
    public Boolean create(DocumentDto documentDto) {
        return null;
    }

    @Override
    @Transactional
    public Boolean update(DocumentDto documentDto, Long id) {
        return null;
    }

    @Override
    @Transactional
    public Boolean safeDelete(Long id) {
        return null;
    }
}
