package com.spond.club.service;

import com.spond.club.api.mapper.SignupRegistrationApiMapper;
import com.spond.club.api.model.CreateRegistrationRequest;
import com.spond.club.api.model.RegistrationSubmissionResponse;
import com.spond.club.entity.MemberTypeEntity;
import com.spond.club.entity.MembershipFormEntity;
import com.spond.club.entity.RegistrationEntity;
import com.spond.club.exception.ValidationFailureException;
import com.spond.club.repository.MembershipFormRepository;
import com.spond.club.repository.RegistrationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SignupRegistrationServiceImplTest {

    @Mock
    private MembershipFormRepository membershipFormRepository;

    @Mock
    private RegistrationRepository registrationRepository;

    @Mock
    private SignupRegistrationApiMapper mapper;

    @InjectMocks
    private SignupRegistrationServiceImpl service;

    @Test
    void registersMember() {
        MembershipFormEntity form = form();
        MemberTypeEntity memberType = memberType();
        form.getMemberTypes().add(memberType);

        CreateRegistrationRequest request = request();
        RegistrationEntity registration = new RegistrationEntity();
        RegistrationSubmissionResponse response = new RegistrationSubmissionResponse(form.getFormId(), true);

        when(membershipFormRepository.findByFormId(form.getFormId())).thenReturn(Optional.of(form));
        when(registrationRepository.existsByForm_IdAndEmail(1L, "test@test.com")).thenReturn(false);
        when(mapper.toRegistrationEntity(eq(form), eq(memberType), eq(request), any(Instant.class)))
                .thenReturn(registration);
        when(registrationRepository.save(registration)).thenReturn(registration);
        when(mapper.toRegistrationSubmissionResponse(registration)).thenReturn(response);

        RegistrationSubmissionResponse result = service.submitRegistration(form.getFormId(), request);

        assertThat(result).isSameAs(response);
        verify(registrationRepository).save(registration);
    }

    @Test
    void checkDuplicateEmailAndThrowError() {
        MembershipFormEntity form = form();
        form.getMemberTypes().add(memberType());

        when(membershipFormRepository.findByFormId(form.getFormId())).thenReturn(Optional.of(form));
        when(registrationRepository.existsByForm_IdAndEmail(1L, "test@test.com")).thenReturn(true);

        assertThatThrownBy(() -> service.submitRegistration(form.getFormId(), request()))
                .isInstanceOf(ValidationFailureException.class);

        verify(registrationRepository, never()).save(any());
    }

    private MembershipFormEntity form() {
        MembershipFormEntity form = new MembershipFormEntity();
        form.setId(1L);
        form.setFormId("B171388180BC457D9887AD92B6CCFC86");
        form.setRegistrationOpens(Instant.now().minusSeconds(3600));
        return form;
    }

    private MemberTypeEntity memberType() {
        MemberTypeEntity memberType = new MemberTypeEntity();
        memberType.setId("8FE4113D4E4020E0DCF887803A886981");
        memberType.setName("Active Member");
        return memberType;
    }

    private CreateRegistrationRequest request() {
        return new CreateRegistrationRequest(
                "8FE4113D4E4020E0DCF887803A886981",
                "John",
                "Doe",
                "test@test.com",
                "12345678",
                LocalDate.of(2010, 1, 1)
        );
    }
}
