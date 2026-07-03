package com.spond.club.api.mapper;

import com.spond.club.api.model.CreateRegistrationRequest;
import com.spond.club.api.model.RegistrationSubmissionResponse;
import com.spond.club.entity.MemberTypeEntity;
import com.spond.club.entity.MembershipFormEntity;
import com.spond.club.entity.RegistrationEntity;
import java.time.Instant;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-03T23:30:34+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.10 (Amazon.com Inc.)"
)
@Component
public class SignupRegistrationApiMapperImpl implements SignupRegistrationApiMapper {

    @Override
    public RegistrationEntity toRegistrationEntity(MembershipFormEntity formEntity, MemberTypeEntity memberTypeEntity, CreateRegistrationRequest request, Instant submittedAt) {
        if ( formEntity == null && memberTypeEntity == null && request == null && submittedAt == null ) {
            return null;
        }

        RegistrationEntity registrationEntity = new RegistrationEntity();

        if ( request != null ) {
            registrationEntity.setEmail( request.getEmail() );
            registrationEntity.setPhoneNumber( request.getPhoneNumber() );
            registrationEntity.setBirthDate( request.getBirthDate() );
        }
        registrationEntity.setForm( formEntity );
        registrationEntity.setMemberType( memberTypeEntity );
        registrationEntity.setSubmittedAt( submittedAt );
        registrationEntity.setFullName( request.getFirstName() + " " + request.getLastName() );

        return registrationEntity;
    }

    @Override
    public RegistrationSubmissionResponse toRegistrationSubmissionResponse(RegistrationEntity entity) {
        if ( entity == null ) {
            return null;
        }

        RegistrationSubmissionResponse registrationSubmissionResponse = new RegistrationSubmissionResponse();

        registrationSubmissionResponse.setFormId( entityFormFormId( entity ) );

        registrationSubmissionResponse.setSubmitted( true );
        registrationSubmissionResponse.setMessage( "Registration submitted successfully." );

        return registrationSubmissionResponse;
    }

    private String entityFormFormId(RegistrationEntity registrationEntity) {
        MembershipFormEntity form = registrationEntity.getForm();
        if ( form == null ) {
            return null;
        }
        return form.getFormId();
    }
}
