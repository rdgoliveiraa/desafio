package br.com.roalmeida.desafio.infrastructure.utils;

import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.springframework.util.StringUtils;
import br.com.roalmeida.desafio.application.domain.Conta;
import br.com.roalmeida.desafio.application.domain.Situacao;

public class ImportarContasCsv {

    private final static String CABECALHO = "data_vencimento;data_pagamento;valor;descricao;situacao";
    
    private ImportarContasCsv() {}
    
    public static List<Conta> lerArquivo(final InputStream arquivoInput) {
        var contas = new ArrayList<Conta>();

        try (var scanner = new Scanner(arquivoInput)){
            scanner.useDelimiter("\n");
            var cabecalho = scanner.nextLine();
            validarCabecalho(cabecalho);
            while(scanner.hasNext()) {
                adicionarContas(scanner.next(), contas);
            }
        }
            
        return contas;
    }
            
    private static void validarCabecalho(String cabecalho) {
        if (!StringUtils.hasText(cabecalho) || !CABECALHO.equals(cabecalho)) {
            throw new IllegalArgumentException("Arquivo inválido");
        }
    }

    private static void adicionarContas(String linha, List<Conta> contas) {
        try {
            var campos = linha.split(";");
            var dataVencimento = campos[0].isEmpty() ? null : Date.valueOf(campos[0]);
            var dataPagamento = campos[1].isEmpty() ? null : Date.valueOf(campos[1]);
            var valor = campos[2].isEmpty() ? null : Double.parseDouble(campos[2]);
            var descricao = campos[3].isEmpty() ? null : campos[3];
            var situacao = campos[4].isEmpty() ? null : Situacao.fromString(campos[4]);
        
            contas.add(new Conta(dataVencimento, dataPagamento, valor, descricao, situacao));
        } catch (Exception e) {
            throw new IllegalArgumentException("Campos com formato inválido");
        }
    }
}
