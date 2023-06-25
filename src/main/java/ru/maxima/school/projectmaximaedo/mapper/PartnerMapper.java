package ru.maxima.school.projectmaximaedo.mapper;

import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.maxima.school.projectmaximaedo.dto.*;
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
        partnerDto.setComments(
                mapperUtil.mapList(partner.getComments(), CommentDto.class));
        partnerDto.setCredential(
                mapperUtil.getMapper().map(partner.getCredential(), CredentialDto.class));
        return partnerDto;
    }
    @Override
    public Partner toEntity(PartnerDto partnerDto) {
        Partner partner = mapperUtil.getMapper().map(partnerDto, Partner.class);
//        partner.setComments(
//                mapperUtil.mapList(partnerDto.getComments(), Comment.class));
//        partner.setCredential(
//                mapperUtil.getMapper().map(partnerDto.getCredential(), Credential.class));
        return partner;
    }
}
