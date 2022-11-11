package com.app.shortlinkservice.controller;

import com.app.shortlinkservice.service.LinkService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AppControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LinkService linkService;

    @Test
    void createShortLink() throws Exception {
        String url = "testingMethodWithThisString";
        mockMvc.perform(post("/api/create?url={url}", url))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.longValue", equalTo(url)));
    }

    @Test
    void getOriginalUrl() {
    }
}