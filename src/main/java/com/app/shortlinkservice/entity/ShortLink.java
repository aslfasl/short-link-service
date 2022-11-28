package com.app.shortlinkservice.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@Table(indexes = @Index(columnList = "shortValue", unique = true))
public class ShortLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String shortValue;
    private String longValue;
    private LocalDateTime creationTime = LocalDateTime.now();
    private LocalDateTime lastCallTime;
}
