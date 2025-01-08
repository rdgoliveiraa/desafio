package br.com.roalmeida.desafio.application.usecase;

import br.com.roalmeida.desafio.application.domain.Conta;
import br.com.roalmeida.desafio.application.domain.Situacao;

public interface AlterarSituacaoContaUseCase {

    Conta executar(Long id, Situacao situacao);
}
