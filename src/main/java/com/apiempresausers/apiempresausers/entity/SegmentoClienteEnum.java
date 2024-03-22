package com.apiempresausers.apiempresausers.entity;

public enum SegmentoClienteEnum {
    COMPRADOR(1, "Comprador"),
    VENDEDOR(2, "Vendedor");

    private final int valorReferencia;
    private final String nomeReferencia;

    SegmentoClienteEnum(int valorReferencia, String nomeReferencia) {
        this.valorReferencia = valorReferencia;
        this.nomeReferencia = nomeReferencia;
    }

    public String getNomeReferencia() {
        return nomeReferencia;
    }

    public int getValorReferencia() {
        return valorReferencia;
    }
}
