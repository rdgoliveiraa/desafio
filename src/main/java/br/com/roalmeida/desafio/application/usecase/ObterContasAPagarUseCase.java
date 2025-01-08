package br.com.roalmeida.desafio.application.usecase;

import java.util.Date;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import br.com.roalmeida.desafio.application.domain.Conta;

public interface ObterContasAPagarUseCase {

    public Page<Conta> executar(Date data_vencimento, String descricao, Pageable pageable);
}
