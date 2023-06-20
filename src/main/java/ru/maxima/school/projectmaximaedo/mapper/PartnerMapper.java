package ru.maxima.school.projectmaximaedo.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.maxima.school.projectmaximaedo.dto.CommentDto;
import ru.maxima.school.projectmaximaedo.dto.CredentialDto;
import ru.maxima.school.projectmaximaedo.dto.PartnerDto;
import ru.maxima.school.projectmaximaedo.model.Comment;
import ru.maxima.school.projectmaximaedo.model.Credential;
import ru.maxima.school.projectmaximaedo.model.Partner;
import ru.maxima.school.projectmaximaedo.utils.MapperUtil;

@Component
public class PartnerMapper implements IMapper<Partner, PartnerDto> {
    private final MapperUtil mapperUtil;
    @Autowired
    public PartnerMapper(MapperUtil mapperUtil) {
        this.mapperUtil = mapperUtil;
    }


    @Override
    public PartnerDto toDto(Partner partner) {
        PartnerDto partnerDto = mapperUtil.getMapper().map(partner, PartnerDto.class);
        partnerDto.setCredentialDto(
                mapperUtil.getMapper().map(partner.getCredential(), CredentialDto.class));
        partnerDto.setCommentsDto(
                mapperUtil.mapList(partner.getComments(), CommentDto.class));
        return partnerDto;
    }
    @Override
    public Partner toEntity(PartnerDto partnerDto) {
        Partner partner = mapperUtil.getMapper().map(partnerDto, Partner.class);
        partner.setCredential(
                mapperUtil.getMapper().map(partnerDto.getCredentialDto(), Credential.class));
        partner.setComments(
                mapperUtil.mapList(partnerDto.getCommentsDto(), Comment.class));
        return partner;
    }
}
