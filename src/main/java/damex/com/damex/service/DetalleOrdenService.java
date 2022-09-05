package damex.com.damex.service;

import damex.com.damex.model.DetalleOrden;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleOrdenService {
    DetalleOrden save(DetalleOrden detalleOrden);

}
