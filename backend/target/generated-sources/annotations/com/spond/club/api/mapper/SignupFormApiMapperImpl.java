package com.spond.club.api.mapper;

import com.spond.club.api.model.MemberType;
import com.spond.club.api.model.PublicSignupForm;
import com.spond.club.entity.MemberTypeEntity;
import com.spond.club.entity.MembershipFormEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-03T23:30:34+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.10 (Amazon.com Inc.)"
)
@Component
public class SignupFormApiMapperImpl implements SignupFormApiMapper {

    @Override
    public PublicSignupForm toPublicSignupForm(MembershipFormEntity entity) {
        if ( entity == null ) {
            return null;
        }

        PublicSignupForm publicSignupForm = new PublicSignupForm();

        publicSignupForm.setRegistrationOpens( instantToOffsetDateTime( entity.getRegistrationOpens() ) );
        publicSignupForm.setClubId( entity.getClubId() );
        publicSignupForm.setMemberTypes( memberTypeEntitySetToMemberTypeList( entity.getMemberTypes() ) );
        publicSignupForm.setFormId( entity.getFormId() );
        publicSignupForm.setTitle( entity.getTitle() );
        publicSignupForm.setDescription( entity.getDescription() );

        return publicSignupForm;
    }

    @Override
    public MemberType toMemberType(MemberTypeEntity entity) {
        if ( entity == null ) {
            return null;
        }

        MemberType memberType = new MemberType();

        memberType.setId( entity.getId() );
        memberType.setName( entity.getName() );

        return memberType;
    }

    protected com.spond.club.membership.api.model.MemberType memberTypeEntityToMemberType(MemberTypeEntity memberTypeEntity) {
        if ( memberTypeEntity == null ) {
            return null;
        }

        com.spond.club.membership.api.model.MemberType memberType = new com.spond.club.membership.api.model.MemberType();

        memberType.setId( memberTypeEntity.getId() );
        memberType.setName( memberTypeEntity.getName() );

        return memberType;
    }

    protected List<com.spond.club.membership.api.model.MemberType> memberTypeEntitySetToMemberTypeList(Set<MemberTypeEntity> set) {
        if ( set == null ) {
            return null;
        }

        List<com.spond.club.membership.api.model.MemberType> list = new ArrayList<com.spond.club.membership.api.model.MemberType>( set.size() );
        for ( MemberTypeEntity memberTypeEntity : set ) {
            list.add( memberTypeEntityToMemberType( memberTypeEntity ) );
        }

        return list;
    }
}
