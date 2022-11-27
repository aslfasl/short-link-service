package com.app.shortlinkservice.controller;

import com.app.shortlinkservice.entity.ShortLink;
import com.app.shortlinkservice.repository.LinkRepo;
import com.app.shortlinkservice.service.LinkService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AppControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LinkService mockLinkService;

//    @Autowired
//    private LinkRepo linkRepo;

    @Test
    void createShortLink() throws Exception {
        String longValue = "testingMethodWithThisString";
        String shortValue = "1234";
        ShortLink shortLink = new ShortLink();
        shortLink.setLongValue(longValue);
        shortLink.setShortValue(shortValue);
        when(mockLinkService.createShortLink(longValue)).thenReturn(shortLink);


        mockMvc.perform((post("/short-link-service/links/create"))
                        .content(longValue))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.longValue", equalTo(longValue)))
                .andExpect(jsonPath("$.shortValue", equalTo(shortValue)));
    }

    @Test
    void getOriginalUrl() throws Exception {
        String shortValue = "1234";
        String longValue = "zzzzzzzzz";
        when(mockLinkService.getLongValueByShortValue(shortValue)).thenReturn(longValue);

        mockMvc.perform(get("/short-link-service/links/get/" + shortValue))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", equalTo(longValue)));
    }
}