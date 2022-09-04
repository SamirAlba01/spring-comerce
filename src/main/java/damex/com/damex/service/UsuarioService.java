package damex.com.damex.service;

import damex.com.damex.model.Usuario;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UsuarioService {
    Optional<Usuario> findById(Integer id);

}
