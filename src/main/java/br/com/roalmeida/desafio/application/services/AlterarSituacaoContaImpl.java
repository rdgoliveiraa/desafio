package br.com.roalmeida.desafio.application.services;

import br.com.roalmeida.desafio.application.domain.Conta;
import br.com.roalmeida.desafio.application.domain.ContaRepository;
import br.com.roalmeida.desafio.application.domain.Situacao;
import br.com.roalmeida.desafio.application.usecase.AlterarSituacaoContaUseCase;

public class AlterarSituacaoContaImpl implements AlterarSituacaoContaUseCase {

    public final ContaRepository contaRepository;

    public AlterarSituacaoContaImpl(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    @Override
    public Conta executar(Long id, Situacao situacao) {
        Conta conta = this.contaRepository.findById(id);
        conta.AlterarSituacao(situacao);
        return this.contaRepository.update(conta);
    }

}
