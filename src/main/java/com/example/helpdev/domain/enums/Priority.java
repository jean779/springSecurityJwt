package com.example.helpdev.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Priority {
    LOW(0, "LOW"),
    AVERAGE(1, "AVERAGE"),
    HIGH(2, "HIGH");


    private  Integer code;
    private String descricao;

    public static Priority toEnum(Integer code){
        if (code != null) {
            for (Priority codePriority : Priority.values()) {
                if (code.equals(codePriority.getCode())) {
                    return codePriority;
                }
            }
        }

        throw new IllegalArgumentException("Invalid Priority");
    }
}

