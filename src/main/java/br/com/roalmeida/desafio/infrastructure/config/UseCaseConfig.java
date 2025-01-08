package br.com.roalmeida.desafio.infrastructure.config;

import java.util.Objects;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import br.com.roalmeida.desafio.application.domain.ContaRepository;
import br.com.roalmeida.desafio.application.services.AlterarSituacaoContaImpl;
import br.com.roalmeida.desafio.application.services.AtualizarContaImpl;
import br.com.roalmeida.desafio.application.services.CriarContaImpl;
import br.com.roalmeida.desafio.application.services.CriarContasViaArquivoImpl;
import br.com.roalmeida.desafio.application.services.ObterContasAPagarImpl;
import br.com.roalmeida.desafio.application.services.ObterContasPorIdImpl;
import br.com.roalmeida.desafio.application.services.ObterValorTotalPagoPorPeriodoImpl;

@Configuration
public class UseCaseConfig {

    private final ContaRepository contaRepository;

    public UseCaseConfig(ContaRepository contaRepository) {
        this.contaRepository = Objects.requireNonNull(contaRepository);
    }

    @Bean
    public CriarContaImpl criarContaImpl() {
        return new CriarContaImpl(contaRepository);
    }

    @Bean
    public AlterarSituacaoContaImpl alterarSituacaoContaImpl() {
        return new AlterarSituacaoContaImpl(contaRepository);
    }

    @Bean
    public AtualizarContaImpl atualizarContaImpl() {
        return new AtualizarContaImpl(contaRepository);
    }

    @Bean
    public ObterContasPorIdImpl obterContasPorIdImpl() {
        return new ObterContasPorIdImpl(contaRepository);
    }

    @Bean ObterContasAPagarImpl obterContasAPagarImpl() {
        return new ObterContasAPagarImpl(contaRepository);
    }

    @Bean ObterValorTotalPagoPorPeriodoImpl obterValorTotalPagoPorPeriodoImpl() {
        return new ObterValorTotalPagoPorPeriodoImpl(contaRepository);
    }

    @Bean CriarContasViaArquivoImpl criarContasViaArquivoImpl() {
        return new CriarContasViaArquivoImpl(contaRepository);
    }

}
