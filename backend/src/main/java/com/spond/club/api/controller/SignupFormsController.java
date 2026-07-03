package com.spond.club.api.controller;

import com.spond.club.api.SignupFormsApi;
import com.spond.club.api.model.PublicSignupForm;
import com.spond.club.service.SignupFormService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignupFormsController implements SignupFormsApi {
    private final SignupFormService signupFormService;

    public SignupFormsController(SignupFormService signupFormService) {
        this.signupFormService = signupFormService;
    }

    @Override
    public ResponseEntity<PublicSignupForm> getSignupForm(String formId) {
        return ResponseEntity.ok(signupFormService.getSignupForm(formId));
    }
}
