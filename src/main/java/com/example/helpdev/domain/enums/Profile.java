package com.example.helpdev.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Profile {
    ADMIN(0, "ROLE_ADMIN"),
    CLIENT(1, "ROLE_CLIENT"),
    TECHNICAL(2, "ROLE_TECHNICAL");


    private  Integer codigo;
    private String descricao;

    public static Profile toEnum(Integer code){
        if (code != null) {
            for (Profile codeProfile : Profile.values()) {
                if (code.equals(codeProfile.getCodigo())) {
                    return codeProfile;
                }
            }
        }

        throw new IllegalArgumentException("Invalid Status");
    }
}

