package com.app.shortlinkservice.service;

import com.app.shortlinkservice.entity.ShortLink;
import com.app.shortlinkservice.repository.LinkRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LinkServiceTest {

    @Autowired
    private LinkRepo linkRepo;

    @Autowired
    private LinkService linkService;

    @Test
    void shouldGenerateNewUnoccupiedUrl() {
        String generatedUrl = linkService.generateShortLink(5);

        assertFalse(linkRepo.existsById(generatedUrl));
    }

    @Test
    void shouldReturnExistingObjectWhenGenerateCall() {
        String longValue = "testLongValue";
        ShortLink shortLink = new ShortLink("12345",
                longValue,
                LocalDateTime.now().minusDays(4).withNano(0),
                LocalDateTime.now().minusDays(2).withNano(0)); // TODO: 11.11.2022 differences in nano
        ShortLink savedLink = linkRepo.save(shortLink);

        ShortLink generatedShortLink = linkService.createShortLink(longValue);

        assertEquals(savedLink.getLongValue(), generatedShortLink.getLongValue());
        assertEquals(savedLink.getShortValue(), generatedShortLink.getShortValue());
        assertEquals(savedLink.getCreationTime(), generatedShortLink.getCreationTime());
    }

    @Test
    void shouldCreateAShortLink() {
        String veryLongTestUrl = "verylongtesturl";
        assertFalse(linkRepo.existsByLongValue(veryLongTestUrl));

        ShortLink shortLink = linkService.createShortLink(veryLongTestUrl);

        assertTrue(linkRepo.existsByLongValue(veryLongTestUrl));
        assertEquals(shortLink.getLongValue(), veryLongTestUrl);
    }

    @Test
    void shouldReturnLongUrlByShortUrl() {
        String shortUrl = "xPJRr";
        String longUrl = "asdf;lkjqwerup";
        linkRepo.save(new ShortLink(shortUrl, longUrl, null, null));

        String originalUrlByShortUrl = linkService.getOriginalUrlByShortUrl(shortUrl);

        assertEquals(longUrl, originalUrlByShortUrl);
    }
}