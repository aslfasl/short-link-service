package com.app.shortlinkservice.repository;

import com.app.shortlinkservice.entity.ShortLink;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface LinkRepo extends JpaRepository<ShortLink, Long> {
    Boolean existsByLongValue(String longValue);

    Optional<ShortLink> findByLongValue(String longValue);

    Optional<ShortLink> findByShortValue(String shortValue);

    Boolean existsByShortValue(String shortValue);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM short_link WHERE creation_time < now() - 30", nativeQuery = true)
    void deleteAllExpiredLinks();
}
