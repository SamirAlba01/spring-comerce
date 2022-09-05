package damex.com.damex.service;

import damex.com.damex.model.Orden;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface OrdenService {
    Orden save(Orden orden);
}
