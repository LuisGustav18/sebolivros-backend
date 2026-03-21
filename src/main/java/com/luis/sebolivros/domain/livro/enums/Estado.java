package com.luis.sebolivros.domain.livro.enums;

public enum Estado {

    DISPONIVEL(0, "DISPONIVEL"),
    INDISPONIVEL(1, "INDISPONIVEL");

    private Integer codigo;
    private String descricao;

    Estado(Integer codigo, String descricao){
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Estado toEnum(Integer cod){
        if (cod == null){
            return null;
        }
        for (Estado x : Estado.values()){
            if (cod.equals(x.getCodigo())){
                return x;
            }
        }
        throw new IllegalArgumentException("Estado invalido");
    }
}
