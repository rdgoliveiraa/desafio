package br.com.roalmeida.desafio.application.services;

import br.com.roalmeida.desafio.application.domain.Conta;
import br.com.roalmeida.desafio.application.domain.ContaRepository;
import br.com.roalmeida.desafio.application.usecase.AtualizarContaUseCase;

public class AtualizarContaImpl implements AtualizarContaUseCase {


    private final ContaRepository contaRepository;

    public AtualizarContaImpl(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    @Override
    public Conta executar(Long id, Conta conta) {
        Conta contaExistente = this.contaRepository.findById(id);
        contaExistente.setDescricao(conta.getDescricao());
        contaExistente.setDataPagamento(conta.getDataPagamento());
        contaExistente.setDataVencimento(conta.getDataVencimento());
        contaExistente.setValor(conta.getValor());
        return this.contaRepository.update(contaExistente);
    }


}
