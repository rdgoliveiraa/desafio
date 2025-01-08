package br.com.roalmeida.desafio.infrastructure.outbound.autenticacao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import br.com.roalmeida.desafio.infrastructure.outbound.autenticacao.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u WHERE u.username =:username")
    Usuario findByUsername(@Param("username") String username);
}

