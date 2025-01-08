package br.com.roalmeida.desafio.infrastructure.outbound.conta.repositories;

import java.util.Date;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.repository.query.Param;
import br.com.roalmeida.desafio.infrastructure.outbound.conta.entities.JpaContaEntity;

public interface JpaContaRepository extends JpaRepository<JpaContaEntity, Long> {

    Page<JpaContaEntity> findByDataVencimentoAndDescricao(Date dataVencimento, String descricao, Pageable pageable);

    @NativeQuery("select sum(c.valor) from Conta c where c.situacao = '1' and c.data_vencimento between :dataInicio and :dataFim")
    Double findTotalPagoPorPeriodo(@Param("dataInicio") Date dataInicio, @Param("dataFim") Date dataFim);
}
