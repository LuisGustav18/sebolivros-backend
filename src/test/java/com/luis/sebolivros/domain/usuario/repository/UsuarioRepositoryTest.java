package com.luis.sebolivros.domain.usuario.repository;

import com.luis.sebolivros.domain.cliente.dto.ClienteDTO;
import com.luis.sebolivros.domain.cliente.entity.Cliente;
import com.luis.sebolivros.domain.sebo.dto.SeboDTO;
import com.luis.sebolivros.domain.sebo.entity.Sebo;
import com.luis.sebolivros.domain.usuario.entity.Usuario;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

@DataJpaTest // Indica pro Spring que essa classe e de test
@ActiveProfiles("test") // Ativa o h2 de test com junit
class UsuarioRepositoryTest {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    EntityManager entityManager; // Para criar o autor, vem do jpa

    @Test
    @DisplayName("Should get Cliente successfully from data base")
    void findClienteByEmailSuccessfully() {
        String email = "Marcos@gmail.com";
        ClienteDTO data = new ClienteDTO(
                "Marcos",
                email,
                "123",
                "12312312311"
        );
        this.createUsuario(data);

        Optional<Usuario> result = this.usuarioRepository.findByEmail(email);

        assertThat(result.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Should get Sebo successfully from data base")
    void findSeboByEmailSuccessfully() {
        String email = "Marcos@gmail.com";
        SeboDTO data = new SeboDTO(
                null,
                "Marcos",
                email,
                "123",
                "1234-1234",
                "12345678000642"
        );
        this.createUsuario(data);

        Optional<Usuario> result = this.usuarioRepository.findByEmail(email);

        assertThat(result.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Should not get Usuario successfully from data base")
    void findUsuarioByEmailError() {
        String email = "Marcos@gmail.com";

        Optional<Usuario> result = this.usuarioRepository.findByEmail(email);

        assertThat(result.isEmpty()).isTrue();
    }


    private Cliente createUsuario(ClienteDTO data){
        Cliente obj = new Cliente(data);
        this.entityManager.persist(obj);
        return obj;
    }

    private Sebo createUsuario(SeboDTO data){
        Sebo obj = new Sebo(data);
        this.entityManager.persist(obj);
        return obj;
    }
}