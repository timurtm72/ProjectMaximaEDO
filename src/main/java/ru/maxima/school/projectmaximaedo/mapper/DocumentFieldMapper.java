package ru.maxima.school.projectmaximaedo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.maxima.school.projectmaximaedo.dto.DocumentFieldDto;
import ru.maxima.school.projectmaximaedo.model.DocumentField;


@Component
public class DocumentFieldMapper  implements IMapper<DocumentField, DocumentFieldDto> {
    private final ModelMapper modelMapper;
    @Autowired
    public DocumentFieldMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public DocumentFieldDto toDto(DocumentField documentField) {
        return modelMapper.map(documentField, DocumentFieldDto.class);
    }

    @Override
    public DocumentField toEntity(DocumentFieldDto documentFieldDto) {
        return modelMapper.map(documentFieldDto, DocumentField.class);
    }
}
