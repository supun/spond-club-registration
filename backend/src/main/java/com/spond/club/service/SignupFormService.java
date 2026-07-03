package com.spond.club.service;

import com.spond.club.api.model.PublicSignupForm;

public interface SignupFormService {
    PublicSignupForm getSignupForm(String formId);
}
