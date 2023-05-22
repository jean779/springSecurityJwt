package com.example.helpdev.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
    OPEN(0, "OPEN"),
    PROGRESS(1, "PROGRESS"),
    CLOSED(2, "CLOSED");


    private  Integer code;
    private String descricao;

    public static Status toEnum(Integer code){
        if (code != null) {
            for (Status codestatus : Status.values()) {
                if (code.equals(codestatus.getCode())) {
                    return codestatus;
                }
            }
        }

        throw new IllegalArgumentException("Invalid Status");
    }
}

