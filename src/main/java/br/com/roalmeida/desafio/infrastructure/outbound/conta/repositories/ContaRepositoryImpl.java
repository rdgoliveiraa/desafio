package br.com.roalmeida.desafio.infrastructure.outbound.conta.repositories;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import br.com.roalmeida.desafio.application.domain.Conta;
import br.com.roalmeida.desafio.application.domain.ContaRepository;
import br.com.roalmeida.desafio.infrastructure.outbound.conta.entities.JpaContaEntity;

@Component
public class ContaRepositoryImpl implements ContaRepository {

    private final JpaContaRepository jpaContaRepository;

    public ContaRepositoryImpl(JpaContaRepository jpaContaRepository) {
        this.jpaContaRepository = Objects.requireNonNull(jpaContaRepository);
    }

    @Override
    public Conta save(Conta conta) {
        JpaContaEntity jpaContaEntity = new JpaContaEntity(conta);
        this.jpaContaRepository.save(jpaContaEntity);
        return new Conta(jpaContaEntity.getId(), jpaContaEntity.getDataVencimento(), jpaContaEntity.getDataPagamento(), jpaContaEntity.getValor(), jpaContaEntity.getDescricao(), jpaContaEntity.getSituacao());
    }

    @Override
    public Conta findById(Long id) {
        Optional<JpaContaEntity> jpaContaEntity = this.jpaContaRepository.findById(id);
        return jpaContaEntity.map(conta -> new Conta(conta.getId(), conta.getDataVencimento(), conta.getDataPagamento(), conta.getValor(), conta.getDescricao(), conta.getSituacao())).orElse(null);
    }

    @Override
    public Conta update(Conta conta) {
        Optional<JpaContaEntity> jpaContaEntity = this.jpaContaRepository.findById(conta.getId());
        if(jpaContaEntity.isPresent()) {
            JpaContaEntity jpaContaEntityToUpdate = jpaContaEntity.get();
            jpaContaEntityToUpdate.setDataVencimento(conta.getDataVencimento());
            jpaContaEntityToUpdate.setDataPagamento(conta.getDataPagamento());
            jpaContaEntityToUpdate.setValor(conta.getValor());
            jpaContaEntityToUpdate.setDescricao(conta.getDescricao());
            jpaContaEntityToUpdate.setSituacao(conta.getSituacao());
            this.jpaContaRepository.save(jpaContaEntityToUpdate);
        }
        return new Conta(conta.getId(), conta.getDataVencimento(), conta.getDataPagamento(), conta.getValor(), conta.getDescricao(), conta.getSituacao());
    }

    @Override
    public Page<Conta> findByDataVencimentoAndDescricao(Date dataVencimento, String descricao, Pageable pageable) {
        Page<JpaContaEntity> jpaContaEntities = this.jpaContaRepository.findByDataVencimentoAndDescricao(dataVencimento, descricao, pageable);
        return jpaContaEntities.map(conta -> new Conta(conta.getId(), conta.getDataVencimento(), conta.getDataPagamento(), conta.getValor(), conta.getDescricao(), conta.getSituacao()));
    }

    @Override
    public Double findTotalPagoPorPeriodo(Date dataInicio, Date dataFim) {
        return this.jpaContaRepository.findTotalPagoPorPeriodo(dataInicio, dataFim);
    }
    

}
