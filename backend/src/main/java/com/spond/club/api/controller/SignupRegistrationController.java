package com.spond.club.api.controller;

import com.spond.club.api.SignupRegistrationsApi;
import com.spond.club.api.model.CreateRegistrationRequest;
import com.spond.club.api.model.RegistrationSubmissionResponse;
import com.spond.club.service.SignupRegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignupRegistrationController implements SignupRegistrationsApi {
    private final SignupRegistrationService signupRegistrationService;

    public SignupRegistrationController(SignupRegistrationService signupRegistrationService) {
        this.signupRegistrationService = signupRegistrationService;
    }

    @Override
    public ResponseEntity<RegistrationSubmissionResponse> submitSignupForm(
            String formId,
            CreateRegistrationRequest createRegistrationRequest
    ) {
        RegistrationSubmissionResponse response =
                signupRegistrationService.submitRegistration(formId, createRegistrationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
