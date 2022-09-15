package damex.com.damex.repository;

import damex.com.damex.model.Orden;
import damex.com.damex.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdenRepository extends JpaRepository<Orden,Integer> {
    List<Orden> findAllByUsuario(Usuario usuario);
}
