package com.app.shortlinkservice.service;

import com.app.shortlinkservice.entity.ShortLink;
import com.app.shortlinkservice.repository.LinkRepo;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LinkService {

    private final LinkRepo linkRepo;

    public Boolean checkUrlIfExists(String shortUrl) {
        // TODO: 09.11.2022
        return true;
    }



    public String createShortLink(String longUrl) {
        String shortUrl = generateShortLink(5);
        if (checkUrlIfExists(shortUrl)) {
            // TODO: 09.11.2022
        }

        return shortUrl;
    }

    private String generateShortLink(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }
}
