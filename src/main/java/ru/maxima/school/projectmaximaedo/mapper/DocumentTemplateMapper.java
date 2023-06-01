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
    private IMapper mapper;


    private final MapperUtil mapperUtils;
    @Autowired
    public DocumentTemplateMapper(IMapper mapper, MapperUtil mapperUtils) {
        this.mapper = mapper;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public DocumentTemplateDto toDto(DocumentTemplate entity) {
        DocumentTemplateDto documentTemplateDto = mapperUtils.getMapper().map(entity, DocumentTemplateDto.class);
        documentTemplateDto.setTemplateFields(
                mapperUtils.mapList(entity.getTemplateFields(), DocumentFieldDto.class)
        );
        return documentTemplateDto;
    }
    @Override
    public DocumentTemplate toEntity(DocumentTemplateDto dto) {
        DocumentTemplate documentTemplate = mapperUtils.getMapper().map(dto, DocumentTemplate.class);
        documentTemplate.setTemplateFields(
                mapperUtils.mapList(dto.getTemplateFields(), DocumentField.class));
        return documentTemplate;
    }
}