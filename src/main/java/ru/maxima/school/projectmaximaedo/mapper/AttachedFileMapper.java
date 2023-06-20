package ru.maxima.school.projectmaximaedo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.maxima.school.projectmaximaedo.dto.AttachedFileDto;
import ru.maxima.school.projectmaximaedo.model.AttachedFile;
import ru.maxima.school.projectmaximaedo.utils.MapperUtil;

@Component
public class AttachedFileMapper implements IMapper<AttachedFile, AttachedFileDto>{
    private final MapperUtil mapperUtil;
    @Autowired
    public AttachedFileMapper(MapperUtil mapperUtil) {
        this.mapperUtil = mapperUtil;
    }
    @Override
    public AttachedFileDto toDto(AttachedFile file) {
        return mapperUtil.getMapper().map(file, AttachedFileDto.class);
    }

    @Override
    public AttachedFile toEntity(AttachedFileDto fileDto) {
        return mapperUtil.getMapper().map(fileDto, AttachedFile.class);
    }
}
