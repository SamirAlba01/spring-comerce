package damex.com.damex.service;

import damex.com.damex.controller.UsuarioController;
import damex.com.damex.model.Usuario;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UsuarioService {
    Optional<Usuario> findById(Integer id);
    Optional<Usuario> findByEmail(String email);
    Usuario save(Usuario usuario);
    List<Usuario> findAll();

}
