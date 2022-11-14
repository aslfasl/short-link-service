package com.app.shortlinkservice.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
public class ShortLink {

    @Id
    private String shortValue;
    private String longValue;
    private LocalDateTime creationTime;
    private LocalDateTime lastCallTime;
}
