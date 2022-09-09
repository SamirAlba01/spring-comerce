package damex.com.damex.service;

import damex.com.damex.model.Orden;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface OrdenService {
    Orden save(Orden orden);
    String generarNumeroOrden();
    List<Orden> findAll();
}
