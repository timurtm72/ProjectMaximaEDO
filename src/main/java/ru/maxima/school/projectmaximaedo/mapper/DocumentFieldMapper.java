package ru.maxima.school.projectmaximaedo.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.maxima.school.projectmaximaedo.dto.DocumentFieldDto;
import ru.maxima.school.projectmaximaedo.model.DocumentField;
import ru.maxima.school.projectmaximaedo.utils.MapperUtil;


@Component
public class DocumentFieldMapper  implements IMapper<DocumentField, DocumentFieldDto> {
    private final MapperUtil mapperUtil;
    @Autowired
    public DocumentFieldMapper(MapperUtil mapperUtil) {
        this.mapperUtil = mapperUtil;
    }
    @Override
    public DocumentFieldDto toDto(DocumentField documentField) {
        return mapperUtil.getMapper().map(documentField, DocumentFieldDto.class);
    }

    @Override
    public DocumentField toEntity(DocumentFieldDto documentFieldDto) {
        return mapperUtil.getMapper().map(documentFieldDto, DocumentField.class);
    }
}
