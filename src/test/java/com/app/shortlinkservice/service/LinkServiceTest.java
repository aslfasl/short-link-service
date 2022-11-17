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
    void shouldGenerateNewUnoccupiedShortValue() {
        String generatedShortValue = linkService.generateShortValue(5);

        assertFalse(linkRepo.existsByShortValue(generatedShortValue));
    }

    @Test
    void shouldReturnExistingObjectWhenGenerateCall() {
        String longValue = "testLongValue";
        ShortLink shortLink = new ShortLink(); // TODO: 11.11.2022 differences in nano
        shortLink.setShortValue("12345");
        shortLink.setLongValue(longValue);
        shortLink.setCreationTime(LocalDateTime.now().minusDays(4).withNano(0));
        shortLink.setLastCallTime(LocalDateTime.now().minusDays(2).withNano(0));
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
    void shouldReturnLongValueByShortValue() {
        String shortValue = "xPJRr";
        String longValue = "asdf;lkjqwerup";
        ShortLink shortLink = new ShortLink();
        shortLink.setShortValue(shortValue);
        shortLink.setLongValue(longValue);
        linkRepo.save(shortLink);

        String originalLongValue = linkService.getLongValueByShortValue(shortValue);

        assertEquals(longValue, originalLongValue);
    }

    @Test
    void shouldDeleteOldLinks() {
        ShortLink shortLink1 = new ShortLink();
        shortLink1.setShortValue("s");
        shortLink1.setLongValue("asdf");
        shortLink1.setCreationTime(LocalDateTime.now().minusDays(60));
        shortLink1.setLastCallTime(LocalDateTime.now().minusDays(40));
        ShortLink savedLink = linkRepo.save(shortLink1);

        linkService.checkAndDeleteOldLinks();

        assertFalse(linkRepo.existsById(savedLink.getId()));
    }
}