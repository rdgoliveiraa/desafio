package br.com.roalmeida.desafio.infrastructure.outbound.conta.entities;

import java.util.Date;
import br.com.roalmeida.desafio.application.domain.Conta;
import br.com.roalmeida.desafio.application.domain.Situacao;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "conta")
public class JpaContaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "data_vencimento")
    private Date dataVencimento;

    @Column(name = "data_pagamento")
    private Date dataPagamento;

    @Column(name = "valor")
    private Double valor;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "situacao")
    private Situacao situacao;

    public JpaContaEntity() {}

    public JpaContaEntity(Conta conta) {
        this.id = conta.getId();
        this.dataVencimento = conta.getDataPagamento();
        this.dataPagamento = conta.getDataPagamento();
        this.valor = conta.getValor();
        this.descricao = conta.getDescricao();
        this.situacao = conta.getSituacao();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
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

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }
}
