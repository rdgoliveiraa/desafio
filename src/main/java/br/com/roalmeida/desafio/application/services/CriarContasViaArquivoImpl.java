package br.com.roalmeida.desafio.application.services;

import java.io.InputStream;
import java.util.List;
import br.com.roalmeida.desafio.application.domain.Conta;
import br.com.roalmeida.desafio.application.domain.ContaRepository;
import br.com.roalmeida.desafio.application.usecase.CriarContasViaArquivoUseCase;
import br.com.roalmeida.desafio.infrastructure.utils.ImportarContasCsv;

public class CriarContasViaArquivoImpl implements CriarContasViaArquivoUseCase {

    private final ContaRepository contaRepository;
    
    public CriarContasViaArquivoImpl(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    @Override
    public void executar(InputStream inputStream) {
        List<Conta> contas = ImportarContasCsv.lerArquivo(inputStream);
        contas.forEach(this.contaRepository::save);
    }

}
