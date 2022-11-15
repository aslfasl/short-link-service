package com.app.shortlinkservice.entity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
public class ShortLink {

    @Id
    private String shortValue;
    private String longValue;
    private LocalDateTime creationTime = LocalDateTime.now();
    private LocalDateTime lastCallTime;
}
