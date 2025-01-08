package br.com.roalmeida.desafio.infrastructure.utils;

import static org.junit.jupiter.api.Assertions.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Date;
import org.junit.jupiter.api.Test;
import br.com.roalmeida.desafio.application.domain.Situacao;

class ImportarContasCsvTest {

    @Test
    void lerArquivoClientes() throws Exception {

        var inputArquivo = lerArquivoDeTeste("contas.csv");
        var contas = ImportarContasCsv.lerArquivo(inputArquivo);

        assertAll(
                () -> assertEquals(1, contas.size()),
                () -> assertEquals(Date.valueOf("2025-01-01"), contas.get(0).getDataVencimento()),
                () -> assertEquals(Date.valueOf("2025-01-01"), contas.get(0).getDataPagamento()),
                () -> assertEquals(Double.valueOf(200), contas.get(0).getValor()),
                () -> assertEquals(Situacao.PAGO, contas.get(0).getSituacao())
        );
        inputArquivo.close();
    }

    @Test
    void arquivoComLinhaInvalida() throws Exception {
        var inputArquivo = lerArquivoDeTeste("contas_com_linha_invalida.csv");

        var excecaoEsperada = assertThrows(IllegalArgumentException.class,
                () -> ImportarContasCsv.lerArquivo(inputArquivo));
        assertEquals("Campos com formato inválido", excecaoEsperada.getMessage());
        inputArquivo.close();
    }

    @Test
    void arquivoSemCabecalho() throws Exception {
        var inputArquivo = lerArquivoDeTeste("contas_sem_cabecalho.csv");
        var excecaoEsperada = assertThrows(IllegalArgumentException.class,
                () -> ImportarContasCsv.lerArquivo(inputArquivo));
        assertEquals("Arquivo inválido", excecaoEsperada.getMessage());
        inputArquivo.close();
    }


    private InputStream lerArquivoDeTeste(final String nomeArquivo) throws Exception {
        var pathArquivo = ClassLoader.getSystemResource(nomeArquivo).toURI().getPath();
        return new FileInputStream(pathArquivo);
    }
}
