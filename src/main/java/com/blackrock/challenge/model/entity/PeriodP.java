package com.blackrock.challenge.model.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PeriodP {

    private double extra;
    private LocalDateTime start;
    private LocalDateTime end;
}