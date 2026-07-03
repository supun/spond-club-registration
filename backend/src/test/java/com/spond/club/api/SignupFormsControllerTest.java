package com.spond.club.api;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SignupFormsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void fetchValid_Form_Returns_OK() throws Exception {
        mockMvc.perform(get("/api/forms/B171388180BC457D9887AD92B6CCFC86"))
                .andExpect(status().isOk());
    }
    @Test
    void fetchInvalid_Form_Returns_NotFound() throws Exception {
        mockMvc.perform(get("/api/forms/1"))
                .andExpect(status().isNotFound());
    }
}
