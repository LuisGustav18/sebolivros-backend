package com.luis.sebolivros.domain.carrinho.enums;

public enum Status {

    ATIVO(0, "ATIVO"),
    FINALIZADO(1, "FINALIZADO");

    private Integer codigo;
    private String descricao;

    Status (Integer codigo, String descricao){
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Status toEnum(Integer cod){
        if (cod == null){
            return null;
        }

        for (Status s : Status.values()){
            if (cod.equals(s.getCodigo())){
                return s;
            }
        }
        throw new IllegalArgumentException("Codição invalida");
    }
}
