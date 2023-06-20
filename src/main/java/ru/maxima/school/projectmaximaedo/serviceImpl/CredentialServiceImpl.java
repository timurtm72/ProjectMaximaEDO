package ru.maxima.school.projectmaximaedo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.maxima.school.projectmaximaedo.dto.CredentialDto;
import ru.maxima.school.projectmaximaedo.mapper.CredentialMapper;
import ru.maxima.school.projectmaximaedo.model.Credential;
import ru.maxima.school.projectmaximaedo.repository.CredentialRepository;
import ru.maxima.school.projectmaximaedo.service.CredentialService;
import ru.maxima.school.projectmaximaedo.utils.MapperUtil;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class CredentialServiceImpl implements CredentialService {
    private final CredentialRepository credentialRepository;
    private final CredentialMapper credentialMapper;
    private final MapperUtil mapperUtil;
    @Autowired
    public CredentialServiceImpl(CredentialRepository credentialRepository, CredentialMapper credentialMapper, MapperUtil mapperUtil) {
        this.credentialRepository = credentialRepository;
        this.credentialMapper = credentialMapper;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<CredentialDto> getAll() {
        List<Credential> credentials =credentialRepository.findAllByIsRemovedIsFalseOrderByIdAsc();
        if(credentials == null || credentials.size() == 0 ){
            return null;
        }
        return mapperUtil.mapList(credentials,CredentialDto.class);
    }

    @Override
    public Boolean exists(Long id) {
        return credentialRepository.existsByIdIsRemovedIsFalse(id);
    }

    @Override
    public CredentialDto getById(Long id) {
        Credential credential = credentialRepository.findCredentialByIdIsRemovedIsFalse(id).orElse(null);
        return credential != null ?credentialMapper.toDto(credential) : null;
    }

    @Override
    public Boolean create(CredentialDto credentialDto) {
        Credential credential =  credentialMapper.toEntity(credentialDto);
        if(credential != null){
            credential.setCreatedAt(LocalDateTime.now());
            credential.setVersion(1);
            credential.setRemoved(false);
            credentialRepository.save(credential);
            return false;
        }
        return true;
    }

    @Override
    public Boolean update(CredentialDto credentialDto, Long id) {
        if(credentialDto == null){
            return true;
        }
        credentialDto.setId(id);
        Credential credential =  credentialMapper.toEntity(credentialDto);
        Credential readCredential = credentialRepository.findCredentialByIdIsRemovedIsFalse(id).orElse(null);
        if(readCredential != null){
            credential.setVersion(readCredential.getVersion() + 1);
            credential.setRemoved(readCredential.getRemoved());
            credentialRepository.save(credential);
            return false;
        }
        return true;
    }

    @Override
    public Boolean safeDelete(Long id) {
        Credential credential = credentialRepository.findCredentialByIdIsRemovedIsFalse(id).orElse(null);
        if(credential != null){
            credential.setRemoved(true);
            credentialRepository.save(credential);
            return false;
        }
        return true;
    }
}
