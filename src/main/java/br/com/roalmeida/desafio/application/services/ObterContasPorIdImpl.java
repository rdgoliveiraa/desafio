package br.com.roalmeida.desafio.application.services;

import br.com.roalmeida.desafio.application.domain.Conta;
import br.com.roalmeida.desafio.application.domain.ContaRepository;
import br.com.roalmeida.desafio.application.usecase.ObterContasPorIdUseCase;

public class ObterContasPorIdImpl implements ObterContasPorIdUseCase {

    private final ContaRepository contaRepository;

    public ObterContasPorIdImpl(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    @Override
    public Conta executar(Long id) {
        return this.contaRepository.findById(id);
    }


}
