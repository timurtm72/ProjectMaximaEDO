package ru.maxima.school.projectmaximaedo.serviceImpl;

import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.maxima.school.projectmaximaedo.dto.CommentDto;
import ru.maxima.school.projectmaximaedo.dto.CredentialDto;
import ru.maxima.school.projectmaximaedo.dto.PartnerDto;
import ru.maxima.school.projectmaximaedo.mapper.PartnerMapper;
import ru.maxima.school.projectmaximaedo.model.Comment;
import ru.maxima.school.projectmaximaedo.model.Credential;
import ru.maxima.school.projectmaximaedo.model.Partner;
import ru.maxima.school.projectmaximaedo.repository.CommentRepository;
import ru.maxima.school.projectmaximaedo.repository.CredentialRepository;
import ru.maxima.school.projectmaximaedo.repository.PartnerRepository;
import ru.maxima.school.projectmaximaedo.service.CommentService;
import ru.maxima.school.projectmaximaedo.service.PartnerService;
import ru.maxima.school.projectmaximaedo.utils.MapperUtil;

import java.util.ArrayList;
import java.util.List;

@Service
public class PartnerServiceImpl implements PartnerService {
    private final PartnerRepository partnerRepository;
    private final PartnerMapper partnerMapper;
    private final CredentialRepository credentialRepository;
    private final CommentRepository commentRepository;
    private final CommentService commentService;
    private final MapperUtil mapperUtil;

    @Autowired
    public PartnerServiceImpl(PartnerRepository partnerRepository, PartnerMapper partnerMapper, CredentialRepository credentialRepository, CommentRepository commentRepository, CommentService commentService, MapperUtil mapperUtil) {
        this.partnerRepository = partnerRepository;
        this.partnerMapper = partnerMapper;
        this.credentialRepository = credentialRepository;
        this.commentRepository = commentRepository;
        this.commentService = commentService;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<PartnerDto> getAll() {
        List<Partner> partners = partnerRepository.findAllByIsRemovedIsFalseOrderByIdAsc();
        if (partners == null || partners.size() == 0) {
            return null;
        }
        return mapperUtil.mapList(partners, PartnerDto.class);
    }

    @Override
    public Boolean exists(Long id) {
        return partnerRepository.existsByIdAndIsRemovedIsFalse(id);
    }

    @Override
    public PartnerDto getById(Long id) {
        Partner partner = partnerRepository.findPartnerByIdAndIsRemovedIsFalse(id).orElse(null);
        return partner != null ? partnerMapper.toDto(partner) : null;
    }

    @Override
    public Boolean create(PartnerDto partnerDto) {
        if (partnerDto == null) {
            return true;
        }
        Partner partner = partnerMapper.toEntity(partnerDto);
        if (partnerDto.getCredId() != null) {
            Credential credential = credentialRepository.findCredentialByIdAndIsRemovedIsFalse(partnerDto.getCredId()).orElse(null);
            if (credential != null) {
                partner.setCredential(credential);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "В базе нет учетных данных с таким ID");
            }
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Выберите ID учетных данных");
        }
        if (partnerDto.getCommId() != null) {
            if (partner.getComments() == null) {
                partner.setComments(new ArrayList<>());
            }
            partner.getComments().clear();
            List<Comment> comments = commentRepository.findAllByIsRemovedIsFalseOrderByIdAsc();
            if (comments == null || comments.size() == 0) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Список комментариев пуст");
            }
            for (int i = 0; i < partnerDto.getCommId().size(); i++) {
                if (partnerDto.getCommId().get(i) <= 0) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Индекс списка комментариев не может быть меньше 0");
                }
                Comment comment = comments.get(partnerDto.getCommId().get(i) - 1);
                //comment.setPartner(partner);
                partner.getComments().add(comment);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Выберите коментарии");
        }
        partner.setRemoved(false);
        partnerRepository.save(partner);
        return false;
    }

    @Override
    public Boolean update(PartnerDto partnerDto, Long id) {
        if(partnerDto == null){
            return true;
        }
        partnerDto.setId(id);
        Partner partner = partnerMapper.toEntity(partnerDto);
        Partner readPartner = partnerRepository.findPartnerByIdAndIsRemovedIsFalse(id).orElse(null);
        if (readPartner != null) {
            partner.setRemoved(readPartner.isRemoved());
            partner.setComments(readPartner.getComments());
            partner.setCredential(readPartner.getCredential());
            partnerRepository.save(partner);
            return false;
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "В базе нет контрагента с таким ID");
        }
    }
    @Override
    public Boolean safeDelete(Long id) {
        Partner partner = partnerRepository.findPartnerByIdAndIsRemovedIsFalse(id).orElse(null);
        if (partner != null) {
            partner.setRemoved(true);
            partnerRepository.save(partner);
            return false;
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "В базе нет контрагента с таким ID");
        }
    }
}
