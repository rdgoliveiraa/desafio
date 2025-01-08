package br.com.roalmeida.desafio.infrastructure.inbound.controller.autorizacao.response;

import java.util.Date;

public record TokenDTO(
        String username,
        Boolean authenticated,
        Date created,
        Date expiration,
        String accessToken,
        String refreshToken
    ) {
}
