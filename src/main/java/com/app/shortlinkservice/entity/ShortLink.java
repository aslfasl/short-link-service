package com.app.shortlinkservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ShortLink {
    @Id
    private String shortValue;
    private String longValue;
    private LocalDateTime creationTime;
    private LocalDateTime lastCallTime;
}
