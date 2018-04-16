package com.example.DemoGraphQL.response;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(value=Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {
	@ApiModelProperty(notes = "HttpStatus Code ")
	protected HttpStatus status;
	protected String message;
}