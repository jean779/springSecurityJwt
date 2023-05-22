package com.example.helpdev.exceptions;

import com.example.helpdev.resources.exceptions.StandardError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidationError extends StandardError {
    private static final long serialVerionUID = 1L;

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError() {
        super();
    }
    public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
        super(timestamp, status, error, message, path);
    }
    public void addError(String fieldName, String message){
        this.errors.add(new FieldMessage(fieldName,message));
    }
}
