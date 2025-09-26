package com.exam.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class GeneralResponseDto {

    private String message;
    private boolean status;
    private int statusCode;
    private Map<String,Object> data;
}
