package com.spond.club.api.mapper;

import com.spond.club.api.model.MemberType;
import com.spond.club.api.model.PublicSignupForm;
import com.spond.club.entity.MemberTypeEntity;
import com.spond.club.entity.MembershipFormEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface SignupFormApiMapper {
    @Mapping(target = "registrationOpens", source = "registrationOpens", qualifiedByName = "instantToOffsetDateTime")
    PublicSignupForm toPublicSignupForm(MembershipFormEntity entity);

    MemberType toMemberType(MemberTypeEntity entity);

    @Named("instantToOffsetDateTime")
    default OffsetDateTime instantToOffsetDateTime(Instant value) {
        return value == null ? null : value.atOffset(ZoneOffset.UTC);
    }
}