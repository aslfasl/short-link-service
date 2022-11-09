package com.app.shortlinkservice.controller;


import com.app.shortlinkservice.service.LinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AppController {

    private final LinkService service;

    @PostMapping()
    public String createShortLink(@RequestParam(name = "url") String url) {
        String link = service.createShortLink(url);
        // TODO: 09.11.2022  
        return link;
    }

//    public ResponseEntity<String> getOriginalLink(@RequestBody String shortLink) {
//        
    // TODO: 09.11.2022  
//    }
}
