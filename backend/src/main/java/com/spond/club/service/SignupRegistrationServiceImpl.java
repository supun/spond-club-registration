package com.spond.club.service;

import com.spond.club.api.mapper.SignupRegistrationApiMapper;
import com.spond.club.api.model.CreateRegistrationRequest;
import com.spond.club.api.model.RegistrationSubmissionResponse;
import com.spond.club.entity.MemberTypeEntity;
import com.spond.club.entity.MembershipFormEntity;
import com.spond.club.entity.RegistrationEntity;
import com.spond.club.repository.MembershipFormRepository;
import com.spond.club.repository.RegistrationRepository;
import com.spond.club.exception.ResourceNotFoundException;
import com.spond.club.exception.ValidationFailureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Locale;

@Service
public class SignupRegistrationServiceImpl implements SignupRegistrationService {

    private static final Logger log = LoggerFactory.getLogger(SignupRegistrationServiceImpl.class);

    private final MembershipFormRepository membershipFormRepository;
    private final RegistrationRepository registrationRepository;
    private final SignupRegistrationApiMapper mapper;

    public SignupRegistrationServiceImpl(
            MembershipFormRepository membershipFormRepository,
            RegistrationRepository registrationRepository,
            SignupRegistrationApiMapper mapper
    ) {
        this.membershipFormRepository = membershipFormRepository;
        this.registrationRepository = registrationRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public RegistrationSubmissionResponse submitRegistration(String formId, CreateRegistrationRequest request) {
        MembershipFormEntity form = membershipFormRepository.findByFormId(formId)
                .orElseThrow(() -> new ResourceNotFoundException("Form not found"));

        if (request.getPhoneNumber() == null || request.getPhoneNumber().isBlank()) {
            throw ValidationFailureException.singleField("phoneNumber", "must not be blank");
        }
        checkRegistrationWindow(form);

        MemberTypeEntity memberType = form.getMemberTypes().stream()
                .filter(mt -> mt.getId().equals(request.getMemberTypeId()))
                .findFirst()
                .orElseThrow(() -> ValidationFailureException.singleField(
                        "memberTypeId",
                        "Member type '%s' does not belong to the requested form"
                                .formatted(request.getMemberTypeId())
                ));

        String email = request.getEmail() == null ? null : request.getEmail().trim().toLowerCase(Locale.ROOT);

        if (registrationRepository.existsByForm_IdAndEmail(form.getId(), email)) {
            throw ValidationFailureException.singleField("email", String.format("%s is already registered for this form", email));
        }

        RegistrationEntity registration = mapper.toRegistrationEntity(form, memberType, request, Instant.now());
        registration.setEmail(email);

        RegistrationEntity saved;
        try {
            saved = registrationRepository.save(registration);
        } catch (DataIntegrityViolationException e) {
            log.debug("Duplicate registration for form {} / email {}, caught by DB constraint", form.getFormId(), email);
            throw ValidationFailureException.singleField("email", String.format("%s is already registered for this form", email));
        }

        return mapper.toRegistrationSubmissionResponse(saved);
    }

    private void checkRegistrationWindow(MembershipFormEntity form) {
        Instant now = Instant.now();
        if (form.getRegistrationOpens().isAfter(now)) {
            throw ValidationFailureException.singleField("formId", "registration is not open yet");
        }
    }

    // improvements
    // add check for registration close time
}
