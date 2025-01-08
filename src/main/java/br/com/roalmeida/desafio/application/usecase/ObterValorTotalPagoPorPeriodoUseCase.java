package br.com.roalmeida.desafio.application.usecase;

import java.util.Date;

public interface ObterValorTotalPagoPorPeriodoUseCase {

    public Double executar(Date dataInicio, Date dataFim);

}
