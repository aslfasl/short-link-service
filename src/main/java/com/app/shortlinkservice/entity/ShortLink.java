package com.app.shortlinkservice.entity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@ToString
public class ShortLink {

    @Id
    private String shortValue;
    private String longValue;
    private LocalDateTime creationTime;
    private LocalDateTime lastCallTime;
}
