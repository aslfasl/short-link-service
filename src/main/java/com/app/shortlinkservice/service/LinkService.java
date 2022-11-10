package com.app.shortlinkservice.service;

import com.app.shortlinkservice.entity.ShortLink;
import com.app.shortlinkservice.repository.LinkRepo;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LinkService {

    private final LinkRepo linkRepo;

    public Boolean checkUrlIfExists(String shortUrl) {
        // TODO: 09.11.2022
        return true;
    }


    // TODO: 10.11.2022  @Value
    public String createShortLink(String longUrl) {
        String shortUrl = generateShortLink(5);
        while (linkRepo.existsById(shortUrl)) {
            shortUrl = generateShortLink(5);
        }
        linkRepo.save(new ShortLink(shortUrl, longUrl, LocalDateTime.now(), LocalDateTime.now()));
        return shortUrl;
    }

    private String generateShortLink(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }
}
