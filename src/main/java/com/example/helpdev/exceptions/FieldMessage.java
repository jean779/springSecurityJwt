package com.example.helpdev.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FieldMessage implements Serializable {
    private static final long serialVerionUID = 1L;

    private String fieldName;
    private String message;
}
