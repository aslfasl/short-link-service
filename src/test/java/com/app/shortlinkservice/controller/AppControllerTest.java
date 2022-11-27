package com.app.shortlinkservice.controller;

import com.app.shortlinkservice.entity.ShortLink;
import com.app.shortlinkservice.repository.LinkRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AppControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LinkRepo linkRepo;

    @Test
    void createShortLink() throws Exception {
        String longValue = "testingMethodWithThisString";
        mockMvc.perform((post("/short-link-service/links/create"))
                        .content(longValue))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.longValue", equalTo(longValue)));
    }

    @Test
    void getOriginalUrl() throws Exception {
        String shortValue = "1234";
        String longValue = "zzzzzzzzz";
        ShortLink entity = new ShortLink();
        entity.setShortValue(shortValue);
        entity.setLongValue(longValue);
        entity.setCreationTime(LocalDateTime.now());
        entity.setLastCallTime(LocalDateTime.now());
        linkRepo.save(entity);

        mockMvc.perform(get("/short-link-service/links/get/" + shortValue))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", equalTo(longValue)));
    }
}