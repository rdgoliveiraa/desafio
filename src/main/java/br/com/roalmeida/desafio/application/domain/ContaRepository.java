package br.com.roalmeida.desafio.application.domain;

import java.util.Date;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContaRepository {

    Conta save(Conta conta);

    Conta findById(Long id);

    Conta update(Conta conta);

    Page<Conta> findByDataVencimentoAndDescricao(Date data_vencimento, String descricao, Pageable pageable);

    Double findTotalPagoPorPeriodo(Date dataInicio, Date dataFim);
}
