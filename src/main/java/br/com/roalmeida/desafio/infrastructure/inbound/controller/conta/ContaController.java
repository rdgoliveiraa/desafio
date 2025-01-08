package br.com.roalmeida.desafio.infrastructure.inbound.controller.conta;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Objects;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import br.com.roalmeida.desafio.application.domain.Conta;
import br.com.roalmeida.desafio.application.domain.Situacao;
import br.com.roalmeida.desafio.application.services.AlterarSituacaoContaImpl;
import br.com.roalmeida.desafio.application.services.AtualizarContaImpl;
import br.com.roalmeida.desafio.application.services.CriarContaImpl;
import br.com.roalmeida.desafio.application.services.CriarContasViaArquivoImpl;
import br.com.roalmeida.desafio.application.services.ObterContasAPagarImpl;
import br.com.roalmeida.desafio.application.services.ObterContasPorIdImpl;
import br.com.roalmeida.desafio.application.services.ObterValorTotalPagoPorPeriodoImpl;
import br.com.roalmeida.desafio.infrastructure.inbound.controller.conta.request.AtualizarContaDTO;
import br.com.roalmeida.desafio.infrastructure.inbound.controller.conta.request.CriarContaDTO;



@RestController
@RequestMapping("/api/contas/v1")
public class ContaController {

    private final CriarContaImpl criarConta;
    private final AtualizarContaImpl atualizarConta;
    private final ObterContasPorIdImpl obterContasPorId;
    private final AlterarSituacaoContaImpl alterarSituacaoConta;
    private final ObterContasAPagarImpl obterContasAPagar;
    private final ObterValorTotalPagoPorPeriodoImpl obterValorTotalPagoPorPeriodo;
    private final CriarContasViaArquivoImpl criarContasViaArquivo;

    public ContaController(CriarContaImpl criarConta, AtualizarContaImpl atualizarConta,
            ObterContasPorIdImpl obterContasPorId, AlterarSituacaoContaImpl alterarSituacaoConta,
            ObterContasAPagarImpl obterContasAPagar,
            ObterValorTotalPagoPorPeriodoImpl obterValorTotalPagoPorPeriodo,
            CriarContasViaArquivoImpl criarContasViaArquivo) {
        this.criarConta = Objects.requireNonNull(criarConta);
        this.atualizarConta = Objects.requireNonNull(atualizarConta);
        this.obterContasPorId = Objects.requireNonNull(obterContasPorId);
        this.alterarSituacaoConta = Objects.requireNonNull(alterarSituacaoConta);
        this.obterContasAPagar = Objects.requireNonNull(obterContasAPagar);
        this.obterValorTotalPagoPorPeriodo = Objects.requireNonNull(obterValorTotalPagoPorPeriodo);
        this.criarContasViaArquivo = Objects.requireNonNull(criarContasViaArquivo);
    }

    @PostMapping()
    public Conta criarConta(@RequestBody CriarContaDTO criarContaDTO) {
        Conta conta = new Conta(criarContaDTO.data_vencimento(), criarContaDTO.data_pagamento(),
                criarContaDTO.valor(), criarContaDTO.descricao(),
                Situacao.fromString(criarContaDTO.situacao()));
        return criarConta.executar(conta);
    }

    @PutMapping("/{id}")
    public Conta atualizarConta(@PathVariable Long id,
            @RequestBody AtualizarContaDTO atualizarContaDTO) {
        Conta conta = new Conta(id, atualizarContaDTO.data_vencimento(),
                atualizarContaDTO.data_pagamento(), atualizarContaDTO.valor(),
                atualizarContaDTO.descricao());
        return atualizarConta.executar(id, conta);
    }

    @GetMapping("/{id}")
    public Conta obterContaPorId(@PathVariable Long id) {
        return obterContasPorId.executar(id);
    }

    @PutMapping("/{id}/situacao/{situacao}")
    public Conta alterarSituacao(@PathVariable Long id, @PathVariable String situacao) {
        return alterarSituacaoConta.executar(id, Situacao.fromString(situacao));
    }

    @GetMapping("/data-vencimento-descricao")
    public Page<Conta> obterContasPorDataVencimentoEDescricao(
            @PageableDefault(page = 0, size = 10) @SortDefault(sort = "name",
                    direction = Sort.Direction.DESC) @SortDefault(sort = "id",
                            direction = Sort.Direction.ASC) @RequestParam @DateTimeFormat(
                                    iso = DateTimeFormat.ISO.DATE) Date dataVencimento,
            @RequestParam String descricao, Pageable pageable) {
        return obterContasAPagar.executar(dataVencimento, descricao, pageable);
    }

    @GetMapping("/total-pago-periodo")
    public Double obterTotalPagoPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dataFim) {
        return obterValorTotalPagoPorPeriodo.executar(dataInicio, dataFim);
    }

    @PostMapping("via-arquivo")
    public void postMethodName(@RequestParam("file") MultipartFile file) {
        
        try (InputStream inputStream = file.getInputStream()) {
            criarContasViaArquivo.executar(inputStream);
        } catch (IOException e) {
           throw new RuntimeException(e);
        }
        
        
    }
    
}
