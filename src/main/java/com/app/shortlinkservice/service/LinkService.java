package com.app.shortlinkservice.service;

import com.app.shortlinkservice.entity.ShortLink;
import com.app.shortlinkservice.exception.MyCustomException;
import com.app.shortlinkservice.repository.LinkRepo;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LinkService {

    private final LinkRepo linkRepo;

    @Transactional
    public ShortLink createShortLink(String longUrl) {
        if (linkRepo.existsByLongValue(longUrl)){
            return linkRepo.findByLongValue(longUrl);
        }
        String shortUrl = generateShortLink(5); // TODO: 11.11.2022 @Value
        return linkRepo.save(new ShortLink(shortUrl, longUrl, LocalDateTime.now(), LocalDateTime.now()));

    }

    public String generateShortLink(int length) {
        String generatedLink = RandomStringUtils.randomAlphanumeric(length);
        while (linkRepo.existsById(generatedLink)) {
            generatedLink = RandomStringUtils.randomAlphanumeric(length);
        }
        return generatedLink;
    }

    public String getOriginalUrlByShortUrl(String shortUrl) {
        ShortLink shortLink = linkRepo.findById(shortUrl).orElseThrow(() -> new MyCustomException()); // TODO: 11.11.2022 test exception scenario
        return shortLink.getLongValue();
    }
}
