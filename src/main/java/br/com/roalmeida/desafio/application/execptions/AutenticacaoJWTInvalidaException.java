package br.com.roalmeida.desafio.application.execptions;

import org.springframework.security.core.AuthenticationException;

public class AutenticacaoJWTInvalidaException extends AuthenticationException {
    public AutenticacaoJWTInvalidaException(String message) {
        super(message);
    }
}
