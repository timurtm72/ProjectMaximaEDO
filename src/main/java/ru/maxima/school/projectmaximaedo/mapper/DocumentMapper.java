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
        documentDto.setDocumentTemplateDto(
                mapperUtil.getMapper().map(document.getTemplate(), DocumentTemplateDto.class)
        );
        documentDto.setPartnerDto(
                mapperUtil.getMapper().map(document.getPartner(), PartnerDto.class)
        );
        documentDto.setFilesDto(
                mapperUtil.mapList(document.getFiles(), AttachedFileDto.class)
        );
        documentDto.setCompletedFieldsDto(mapperUtil.mapList(document.getCompletedFields(), DocumentFieldDto.class)
        );
        documentDto.setUserDto(
                mapperUtil.getMapper().map(document.getUser(),UserDto.class)
        );
        return documentDto;
    }

    @Override
    public Document toEntity(DocumentDto documentDto) {
        Document document = mapperUtil.getMapper().map(documentDto, Document.class);
        document.setTemplate(
                mapperUtil.getMapper().map(documentDto.getDocumentTemplateDto(), DocumentTemplate.class)
        );
        document.setPartner(
                mapperUtil.getMapper().map(documentDto.getPartnerDto(), Partner.class)
        );
        document.setFiles(
                mapperUtil.mapList(documentDto.getFilesDto(), AttachedFile.class)
        );
        document.setCompletedFields(mapperUtil.mapList(documentDto.getCompletedFieldsDto(), DocumentField.class)
        );
        document.setUser(
                mapperUtil.getMapper().map(documentDto.getUserDto(),User.class)
        );
        return document;
    }
}
