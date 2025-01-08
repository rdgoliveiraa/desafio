package br.com.roalmeida.desafio.application.usecase;

import br.com.roalmeida.desafio.application.domain.Conta;

public interface CriarContaUseCase {

    public Conta executar(Conta conta);
    
}
