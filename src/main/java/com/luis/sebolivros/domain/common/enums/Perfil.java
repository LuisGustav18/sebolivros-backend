package com.luis.sebolivros.domain.common.enums;

public enum Perfil {

    CLIENTE(0, "ROLE_ADMIN"),
    GESTOR(1, "ROLE_GESTOR");

    private Integer codigo;
    private String descricao;

    Perfil(Integer codigo,String descricao){
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo(){
        return codigo;
    }

    public String getDescricao(){
        return descricao;
    }

    public static Perfil toEnum(Integer cod){
        if (cod == null){
            return null;
        }
        for (Perfil x : Perfil.values()){
            if (cod.equals(x.getCodigo())){
                return x;
            }
        }
        throw new IllegalArgumentException("Perfil invalido");
    }
}

