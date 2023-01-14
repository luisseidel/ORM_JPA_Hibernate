package org.seidelsoft.model.enums;

import lombok.Builder;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
public enum TipoTelefone {
    FIXO(1L, "Fixo", "+## (##) ####-####"),
    MOVEL(2L, "Móvel", "+## (##) ####-####")
    ;

    private Long id;
    private String descricao;
    private String mask;

    TipoTelefone(Long id, String descricao, String mask) {
        this.id = id;
        this.descricao = descricao;
        this.mask = mask;
    }

    public static TipoTelefone findById(Long id) {
        for (TipoTelefone tt : TipoTelefone.values()) {
            if (tt.getId().equals(id)) {
                return tt;
            }
        }
        return null;
    }
}
