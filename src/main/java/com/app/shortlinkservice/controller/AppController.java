package com.app.shortlinkservice.controller;


import com.app.shortlinkservice.entity.ShortLink;
import com.app.shortlinkservice.service.LinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class AppController {

    private final LinkService service;

    @PostMapping("/create")
    public ResponseEntity<ShortLink> createShortLink(@RequestParam(name = "url") String url) {
        ShortLink shortLink = service.createShortLink(url);
        return ResponseEntity.created(URI.create("api/create")).body(shortLink);
    }

    @GetMapping("/get/{shortValue}")
    public ResponseEntity<String> getOriginalUrl(@PathVariable String shortValue) {
        String longValue = service.getLongValueByShortValue(shortValue);
        return ResponseEntity.ok().body(longValue);
    }
}
