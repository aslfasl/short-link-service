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
        // TODO: 09.11.2022  Return Object, ResponseEntity or String.
        return ResponseEntity.created(URI.create("api/create")).body(shortLink);
    }

    @GetMapping("/get/{shortLink}")
    public ResponseEntity<String> getOriginalUrl(@PathVariable String shortLink) {
        String url = service.getOriginalUrlByShortUrl(shortLink);
        return ResponseEntity.ok().body(url);
    }
}
