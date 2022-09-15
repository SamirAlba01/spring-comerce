package damex.com.damex.service;

import damex.com.damex.model.Orden;
import damex.com.damex.model.Usuario;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdenService {
    Orden save(Orden orden);
    String generarNumeroOrden();
    List<Orden> findAll();
    List<Orden> findByUsuario(Usuario usuario);
    Optional<Orden> findById(Integer id);
}
