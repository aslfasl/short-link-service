package com.app.shortlinkservice.controller;


import com.app.shortlinkservice.service.LinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class AppController {

    private final LinkService service;

    @PostMapping("/create")
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
