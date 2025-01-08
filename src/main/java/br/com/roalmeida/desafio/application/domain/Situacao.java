package br.com.roalmeida.desafio.application.domain;

public enum Situacao {

    PENDENTE,
    PAGO,
    CANCELADO;

    public static Situacao fromString(String situacao) {
        return Situacao.valueOf(situacao.toUpperCase());
    }

}
