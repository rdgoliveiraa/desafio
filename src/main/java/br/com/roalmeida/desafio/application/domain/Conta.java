package br.com.roalmeida.desafio.application.domain;

import java.util.Date;

public class Conta {

    private Long id;
    private Date data_vencimento;
    private Date data_pagamento;
    private Double valor;
    private String descricao;
    private Situacao situacao;

    public Conta() {}

    public Conta(Long id, Date data_vencimento, Date data_pagamento, Double valor, String descricao,
            Situacao situacao) {
        if(valor < 0) {
            throw new IllegalArgumentException("O valor da conta não pode ser negativo");
        }
        this.id = id;
        this.data_vencimento = data_vencimento;
        this.data_pagamento = data_pagamento;
        this.valor = valor;
        this.descricao = descricao;
        this.situacao = situacao;
    }

    public Conta(Date data_vencimento, Date data_pagamento, Double valor, String descricao,
            Situacao situacao) {
        if(valor < 0) {
            throw new IllegalArgumentException("O valor da conta não pode ser negativo");
        }
        this.data_vencimento = data_vencimento;
        this.data_pagamento = data_pagamento;
        this.valor = valor;
        this.descricao = descricao;
        this.situacao = situacao;
    }

    public Conta(Long id, Date data_vencimento, Date data_pagamento, Double valor,
            String descricao) {
        if(valor < 0) {
            throw new IllegalArgumentException("O valor da conta não pode ser negativo");
        }
        this.id = id;
        this.data_vencimento = data_vencimento;
        this.data_pagamento = data_pagamento;
        this.valor = valor;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataVencimento() {
        return data_vencimento;
    }

    public void setDataVencimento(Date data_vencimento) {
        this.data_vencimento = data_vencimento;
    }

    public Date getDataPagamento() {
        return data_pagamento;
    }

    public void setDataPagamento(Date data_pagamento) {
        this.data_pagamento = data_pagamento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void AlterarSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

}
