package ru.maxima.school.projectmaximaedo.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.maxima.school.projectmaximaedo.dto.CredentialDto;
import ru.maxima.school.projectmaximaedo.model.Credential;
import ru.maxima.school.projectmaximaedo.utils.MapperUtil;
@Component
public class CredentialMapper implements IMapper<Credential, CredentialDto>{
    private final MapperUtil mapperUtil;
    @Autowired
    public CredentialMapper(MapperUtil mapperUtil) {
        this.mapperUtil = mapperUtil;
    }
    @Override
    public CredentialDto toDto(Credential credential) {
        return mapperUtil.getMapper().map(credential, CredentialDto.class);
    }

    @Override
    public Credential toEntity(CredentialDto credentialDto) {
        return mapperUtil.getMapper().map(credentialDto, Credential.class);
    }
}
