package br.com.roalmeida.desafio.infrastructure.inbound.controller.autorizacao;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.roalmeida.desafio.infrastructure.inbound.controller.autorizacao.request.CrendenciaisDeUsuarioDTO;
import br.com.roalmeida.desafio.infrastructure.inbound.controller.autorizacao.response.TokenDTO;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    private final AutenticacaoService autenticacaoService;

    public AutenticacaoController(AutenticacaoService autenticacaoService) {
        this.autenticacaoService = autenticacaoService;
    }

    @PostMapping("/signin")
    public ResponseEntity<TokenDTO> signin(@RequestBody  CrendenciaisDeUsuarioDTO credenciais) {
        return ResponseEntity.ok(autenticacaoService.signIn(credenciais));
    }

    @PutMapping("/refresh/{username}")
    public ResponseEntity<TokenDTO> refreshToken(@PathVariable("username") String username, @RequestHeader("Authorization") String refreshToken) {
        return ResponseEntity.ok(autenticacaoService.refreshToken(username, refreshToken));
    }

}
