package com.blackrock.challenge.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InvalidTransaction {

    private String date;
	private Double amount;
	private Double ceiling;
	private Double remanent;
    private String message;
}