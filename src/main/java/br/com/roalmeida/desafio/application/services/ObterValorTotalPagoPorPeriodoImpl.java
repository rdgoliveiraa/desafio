package br.com.roalmeida.desafio.application.services;

import java.util.Date;
import br.com.roalmeida.desafio.application.domain.ContaRepository;
import br.com.roalmeida.desafio.application.usecase.ObterValorTotalPagoPorPeriodoUseCase;

public class ObterValorTotalPagoPorPeriodoImpl implements ObterValorTotalPagoPorPeriodoUseCase {

    private final ContaRepository contaRepository;

    public ObterValorTotalPagoPorPeriodoImpl(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    @Override
    public Double executar(Date dataInicio, Date dataFim) {
        Double totalPago = contaRepository.findTotalPagoPorPeriodo(dataInicio, dataFim);
        return totalPago == null ? 0.0 : totalPago;
    }

}
