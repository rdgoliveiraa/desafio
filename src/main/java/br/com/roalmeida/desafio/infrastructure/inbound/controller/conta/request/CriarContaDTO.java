package br.com.roalmeida.desafio.infrastructure.inbound.controller.conta.request;

import java.util.Date;

public record CriarContaDTO(Date data_vencimento, Date data_pagamento, Double valor, String descricao, String situacao) {}