package br.com.roalmeida.desafio.application.usecase;

import br.com.roalmeida.desafio.application.domain.Conta;

public interface AtualizarContaUseCase {

    public Conta executar(Long id, Conta conta);
}
