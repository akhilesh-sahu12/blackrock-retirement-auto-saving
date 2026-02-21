package com.blackrock.challenge.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PerformanceResponse {

    private String time;
    private String memory;
    private int threads;
}