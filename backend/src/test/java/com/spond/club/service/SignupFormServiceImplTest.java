package com.spond.club.service;

import com.spond.club.api.mapper.SignupFormApiMapper;
import com.spond.club.api.model.PublicSignupForm;
import com.spond.club.entity.MembershipFormEntity;
import com.spond.club.exception.ResourceNotFoundException;
import com.spond.club.repository.MembershipFormRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SignupFormServiceImplTest {

    @Mock
    private MembershipFormRepository membershipFormRepository;

    @Mock
    private SignupFormApiMapper mapper;

    @InjectMocks
    private SignupFormServiceImpl service;

    @Test
    void getRegistrationForm() {
        MembershipFormEntity form = new MembershipFormEntity();
        form.setFormId("B171388180BC457D9887AD92B6CCFC86");

        PublicSignupForm response = new PublicSignupForm();
        response.setFormId(form.getFormId());

        when(membershipFormRepository.findByFormId(form.getFormId())).thenReturn(Optional.of(form));
        when(mapper.toPublicSignupForm(form)).thenReturn(response);

        PublicSignupForm result = service.getSignupForm(form.getFormId());

        assertThat(result).isSameAs(response);
    }

    @Test
    void registrationFormNotFound() {
        String formId = "invalid-form-id";
        when(membershipFormRepository.findByFormId(formId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.getSignupForm(formId))
                .isInstanceOf(ResourceNotFoundException.class);
    }
}
