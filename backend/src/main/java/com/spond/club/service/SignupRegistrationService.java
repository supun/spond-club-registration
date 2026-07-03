package com.spond.club.service;

import com.spond.club.api.model.CreateRegistrationRequest;
import com.spond.club.api.model.RegistrationSubmissionResponse;

public interface SignupRegistrationService {
    RegistrationSubmissionResponse submitRegistration(String formId, CreateRegistrationRequest request);
}
