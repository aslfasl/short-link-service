package com.app.shortlinkservice.repository;

import com.app.shortlinkservice.entity.ShortLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LinkRepo extends JpaRepository<ShortLink, String> {
    Boolean existsByLongValue(String longUrl);
    Optional<ShortLink> findByLongValue(String longUrl);
}
