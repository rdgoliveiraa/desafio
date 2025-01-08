package br.com.roalmeida.desafio.infrastructure.inbound.controller.autorizacao;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import br.com.roalmeida.desafio.infrastructure.outbound.autenticacao.repositories.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = usuarioRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username " + username + " not found");
        } else {
            return user;
        }
    }
}
