package com.slimdevs.loans.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Getter @Setter @ToString
public class BaseEntity {
    
    @Column(updatable = false)
    private String createdBy;

    @Column(updatable = false)
    private LocalDateTime createdAt;
    
    @Column(insertable = false)
    private String updatedBy;

    @Column(insertable = false)
    private LocalDateTime updatedAt;
}
