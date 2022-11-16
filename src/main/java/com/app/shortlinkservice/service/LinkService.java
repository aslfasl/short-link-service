package com.app.shortlinkservice.service;

import com.app.shortlinkservice.entity.ShortLink;
import com.app.shortlinkservice.exception.CustomErrorType;
import com.app.shortlinkservice.exception.MyCustomException;
import com.app.shortlinkservice.repository.LinkRepo;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LinkService {

    private final LinkRepo linkRepo;
    @Value("${app.variables.randomStringLength}")
    private int randomStringLength;

    @Transactional
    public ShortLink createShortLink(String longUrl) {
        Optional<ShortLink> shortLinkOptional = linkRepo.findByLongValue(longUrl);
        if (shortLinkOptional.isPresent()) {
            return shortLinkOptional.get();
        }
        String shortUrl = generateShortValue(randomStringLength);
        ShortLink shortLink = new ShortLink();
        shortLink.setShortValue(shortUrl);
        shortLink.setLongValue(longUrl);
        shortLink.setCreationTime(LocalDateTime.now().withNano(0));
        shortLink.setLastCallTime(LocalDateTime.now().withNano(0));

        return linkRepo.save(shortLink);

    }

    public String generateShortValue(int length) {
        String generatedLink = RandomStringUtils.randomAlphanumeric(length);
        while (linkRepo.existsByShortValue(generatedLink)) {
            generatedLink = RandomStringUtils.randomAlphanumeric(length);
        }
        return generatedLink;
    }

    @Transactional
    public String getLongValueByShortValue(String shortUrl) {
        ShortLink shortLink = linkRepo.findByShortValue(shortUrl)
                .orElseThrow(() ->
                        new MyCustomException("There is no original link matching this url", CustomErrorType.NOT_FOUND));
        shortLink.setLastCallTime(LocalDateTime.now().withNano(0));
        return shortLink.getLongValue();
    }
}
