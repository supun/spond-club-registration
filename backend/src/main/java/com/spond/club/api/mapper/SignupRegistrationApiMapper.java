package com.spond.club.api.mapper;

import com.spond.club.api.model.CreateRegistrationRequest;
import com.spond.club.api.model.RegistrationSubmissionResponse;
import com.spond.club.entity.MemberTypeEntity;
import com.spond.club.entity.MembershipFormEntity;
import com.spond.club.entity.RegistrationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.time.Instant;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface SignupRegistrationApiMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "form", source = "formEntity")
    @Mapping(target = "memberType", source = "memberTypeEntity")
    @Mapping(target = "fullName", expression = "java(request.getFirstName() + \" \" + request.getLastName())")
    @Mapping(target = "email", source = "request.email")
    @Mapping(target = "phoneNumber", source = "request.phoneNumber")
    @Mapping(target = "birthDate", source = "request.birthDate")
    @Mapping(target = "status", ignore = true)
    RegistrationEntity toRegistrationEntity(
            MembershipFormEntity formEntity,
            MemberTypeEntity memberTypeEntity,
            CreateRegistrationRequest request,
            Instant submittedAt
    );

    @Mapping(target = "formId", source = "form.formId")
    @Mapping(target = "submitted", constant = "true")
    @Mapping(target = "message", constant = "Registration submitted successfully.")
    RegistrationSubmissionResponse toRegistrationSubmissionResponse(RegistrationEntity entity);
}
