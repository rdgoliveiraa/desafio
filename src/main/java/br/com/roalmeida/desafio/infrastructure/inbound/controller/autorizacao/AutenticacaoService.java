package br.com.roalmeida.desafio.infrastructure.inbound.controller.autorizacao;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import br.com.roalmeida.desafio.infrastructure.inbound.controller.autorizacao.request.CrendenciaisDeUsuarioDTO;
import br.com.roalmeida.desafio.infrastructure.inbound.controller.autorizacao.response.TokenDTO;
import br.com.roalmeida.desafio.infrastructure.outbound.autenticacao.repositories.UsuarioRepository;
import br.com.roalmeida.desafio.infrastructure.security.jwt.JwtTokenProvider;

@Service
public class AutenticacaoService {
    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;

    public AutenticacaoService(JwtTokenProvider tokenProvider, AuthenticationManager authenticationManager,
            UsuarioRepository userRepository) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
        this.usuarioRepository = userRepository;
    }

     public TokenDTO signIn(CrendenciaisDeUsuarioDTO credentials) {
        try {
            var username = credentials.username();
            var password = credentials.password();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            var user = usuarioRepository.findByUsername(username);

            if (user == null) {
                throw new UsernameNotFoundException("Username " + username + "not found");
            }

            return tokenProvider.createAccessToken(username, user.getRoles());
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }

    public TokenDTO refreshToken(String username,String refreshToken) {
        var user = usuarioRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Username " + username + " not found");
        }

        return tokenProvider.refreshToken(refreshToken);
    }
}
