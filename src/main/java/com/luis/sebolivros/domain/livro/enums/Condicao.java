package com.luis.sebolivros.domain.livro.enums;

public enum Condicao {

    NOVO(0, "NOVO"),
    CONSERVADO(1, "CONSERVADO"),
    USADO(2, "USADO");

    private Integer codigo;
    private String descricao;

    Condicao(Integer codigo, String descricao){
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Condicao toEnum(Integer cod){
        if (cod == null){
            return null;
        }

        for (Condicao x : Condicao.values()){
            if (cod.equals(x.getCodigo())){
                return x;
            }
        }
        throw new IllegalArgumentException("Codição invalida");
    }
}
