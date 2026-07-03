package com.spond.club.service;

import com.spond.club.api.mapper.SignupFormApiMapper;
import com.spond.club.api.model.PublicSignupForm;
import com.spond.club.repository.MembershipFormRepository;
import com.spond.club.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SignupFormServiceImpl implements SignupFormService {

    private final MembershipFormRepository membershipFormRepository;
    private final SignupFormApiMapper mapper;

    public SignupFormServiceImpl(MembershipFormRepository membershipFormRepository, SignupFormApiMapper mapper) {
        this.membershipFormRepository = membershipFormRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public PublicSignupForm getSignupForm(String formId) {
        return membershipFormRepository.findByFormId(formId)
                .map(mapper::toPublicSignupForm)
                .orElseThrow(() -> new ResourceNotFoundException("Form not found"));
    }
}