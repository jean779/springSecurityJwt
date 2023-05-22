package com.example.helpdev.resources.exceptions;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandardError {
    private static final long serialVerionUID = 1L;
    private Long timeStamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
}
