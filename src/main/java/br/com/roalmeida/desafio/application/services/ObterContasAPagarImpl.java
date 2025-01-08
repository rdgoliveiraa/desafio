package br.com.roalmeida.desafio.application.services;

import java.util.Date;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import br.com.roalmeida.desafio.application.domain.Conta;
import br.com.roalmeida.desafio.application.domain.ContaRepository;
import br.com.roalmeida.desafio.application.usecase.ObterContasAPagarUseCase;

public class ObterContasAPagarImpl implements ObterContasAPagarUseCase {

    private final ContaRepository contaRepository;

    public ObterContasAPagarImpl(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    @Override
    public Page<Conta> executar(Date dataVencimento, String descricao, Pageable pageable) {
        return contaRepository.findByDataVencimentoAndDescricao(dataVencimento, descricao, pageable);
    }

}
