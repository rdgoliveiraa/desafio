package br.com.roalmeida.desafio.application.services;

import br.com.roalmeida.desafio.application.domain.Conta;
import br.com.roalmeida.desafio.application.domain.ContaRepository;
import br.com.roalmeida.desafio.application.usecase.CriarContaUseCase;

public class CriarContaImpl implements CriarContaUseCase {

    private final ContaRepository contaRepository;

    public CriarContaImpl(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    @Override
    public Conta executar(Conta conta) {
        return this.contaRepository.save(conta);
    }

}
