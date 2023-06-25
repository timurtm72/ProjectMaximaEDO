package ru.maxima.school.projectmaximaedo.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.maxima.school.projectmaximaedo.dto.*;
import ru.maxima.school.projectmaximaedo.model.*;
import ru.maxima.school.projectmaximaedo.utils.MapperUtil;

@Component
public class DocumentMapper implements IMapper<Document,DocumentDto> {
    private final MapperUtil mapperUtil;
    @Autowired
    public DocumentMapper(MapperUtil mapperUtil) {
        this.mapperUtil = mapperUtil;
    }

    @Override
    public DocumentDto toDto(Document document) {
        DocumentDto documentDto = mapperUtil.getMapper().map(document, DocumentDto.class);
        documentDto.setTemplate(
                mapperUtil.getMapper().map(document.getTemplate(), DocumentTemplateDto.class)
        );
        documentDto.setPartner(
                mapperUtil.getMapper().map(document.getPartner(), PartnerDto.class)
        );
        documentDto.setFiles(
                mapperUtil.mapList(document.getFiles(), AttachedFileDto.class)
        );
        documentDto.setCompletedFields(mapperUtil.mapList(document.getCompletedFields(), DocumentFieldDto.class)
        );
        documentDto.setUser(
                mapperUtil.getMapper().map(document.getUser(),UserReadDto.class)
        );
        return documentDto;
    }

    @Override
    public Document toEntity(DocumentDto documentDto) {
        Document document = mapperUtil.getMapper().map(documentDto, Document.class);
//        document.setTemplate(
//                mapperUtil.getMapper().map(documentDto.getTemplate(), DocumentTemplate.class)
//        );
//        document.setPartner(
//                mapperUtil.getMapper().map(documentDto.getPartner(), Partner.class)
//        );
//        document.setFiles(
//                mapperUtil.mapList(documentDto.getFiles(), AttachedFile.class)
//        );
        document.setCompletedFields(mapperUtil.mapList(documentDto.getCompletedFields(), DocumentField.class)
        );
//        document.setUser(
//                mapperUtil.getMapper().map(documentDto.getUser(),User.class)
//        );
        return document;
    }
}
