package com.spond.club.api;

import com.spond.club.repository.RegistrationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SignupRegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    RegistrationRepository registrationRepository;

    // clean registration table before running the tests
    @BeforeEach
    void setUp() {
        registrationRepository.deleteAll();
    }

    @Test
    void submitValidForm_returnsCreated() throws Exception {
        String request = """
            {
              "memberTypeId": "8FE4113D4E4020E0DCF887803A886981",
              "firstName": "John",
              "lastName": "Doe",
              "email": "test@test.com",
              "phoneNumber": "12345678",
              "birthDate": "2010-01-01"
            }
            """;
        mockMvc.perform(post("/api/forms/B171388180BC457D9887AD92B6CCFC86/submissions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isCreated());
    }
    @Test
    void submitInvalidForm_withEmptyEmail_returnsBadRequest() throws Exception {
        String request = """
            {
              "memberTypeId": "8FE4113D4E4020E0DCF887803A886981",
              "firstName": "John",
              "lastName": "Doe",
              "email": "",
              "phoneNumber": "12345678",
              "birthDate": "2010-01-01"
            }
            """;
        mockMvc.perform(post("/api/forms/B171388180BC457D9887AD92B6CCFC86/submissions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Validation failed"));
    }

    @Test
    void submitInvalidForm_withInvalidMemberType_returnsBadRequest() throws Exception {
        String request = """
            {
              "memberTypeId": "invalid",
              "firstName": "John",
              "lastName": "Doe",
              "email": "",
              "phoneNumber": "12345678",
              "birthDate": "2010-01-01"
            }
            """;
        mockMvc.perform(post("/api/forms/B171388180BC457D9887AD92B6CCFC86/submissions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Validation failed"));
    }

    @Test
    void submitInvalidForm_withDuplicateEmail_returnsBadRequest() throws Exception {
        String request = """
            {
              "memberTypeId": "8FE4113D4E4020E0DCF887803A886981",
              "firstName": "John",
              "lastName": "Doe",
              "email": "test@mail.com",
              "phoneNumber": "12345678",
              "birthDate": "2010-01-01"
            }
            """;
        // first request - successful
        mockMvc.perform(post("/api/forms/B171388180BC457D9887AD92B6CCFC86/submissions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isCreated());

        // second request - fails
        mockMvc.perform(post("/api/forms/B171388180BC457D9887AD92B6CCFC86/submissions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Validation failed"))
                .andExpect(jsonPath("$.fieldErrors[0].message")
                        .value("test@mail.com is already registered for this form"));
    }

}
