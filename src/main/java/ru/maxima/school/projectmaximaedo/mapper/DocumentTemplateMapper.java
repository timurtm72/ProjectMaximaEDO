package ru.maxima.school.projectmaximaedo.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.maxima.school.projectmaximaedo.dto.DocumentFieldDto;
import ru.maxima.school.projectmaximaedo.dto.DocumentTemplateDto;
import ru.maxima.school.projectmaximaedo.model.DocumentField;
import ru.maxima.school.projectmaximaedo.model.DocumentTemplate;
import ru.maxima.school.projectmaximaedo.utils.MapperUtil;

@Component
public class DocumentTemplateMapper implements IMapper<DocumentTemplate,DocumentTemplateDto> {
    private final MapperUtil mapperUtil;
    @Autowired
    public DocumentTemplateMapper(MapperUtil mapperUtil) {
        this.mapperUtil = mapperUtil;
    }
    @Override
    public DocumentTemplateDto toDto(DocumentTemplate documentTemplate) {
        DocumentTemplateDto documentTemplateDto = mapperUtil.getMapper().map(documentTemplate, DocumentTemplateDto.class);
        documentTemplateDto.setTemplateFields(
                mapperUtil.mapList(documentTemplate.getTemplateFields(), DocumentFieldDto.class)
        );
        return documentTemplateDto;
    }
    @Override
    public DocumentTemplate toEntity(DocumentTemplateDto documentTemplateDto) {
        DocumentTemplate documentTemplate = mapperUtil.getMapper().map(documentTemplateDto, DocumentTemplate.class);
        documentTemplate.setTemplateFields(
                mapperUtil.mapList(documentTemplateDto.getTemplateFields(), DocumentField.class));
        return documentTemplate;
    }
}