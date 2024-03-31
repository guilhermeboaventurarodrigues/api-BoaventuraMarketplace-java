package com.apiBoaventuraMarketplace.entity.enums;

public enum DepartamentoFuncionarioEnum {
    RH(1, "Recursos Humanos"),
    TI(2, "Tecnologia da Informacao"),
    FINANCEIRO(3, "Financeiro"),
    VENDAS(4, "Vendas"),
    MARKETING(5, "Marketing");

    private final int valorReferencia;
    private final String nomeReferencia;

    DepartamentoFuncionarioEnum(int valorReferencia, String nomeReferencia) {
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
