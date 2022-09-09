package damex.com.damex.service;

import damex.com.damex.model.DetalleOrden;
import damex.com.damex.model.Orden;
import damex.com.damex.repository.DetalleOrdenRepository;
import damex.com.damex.repository.OrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleOrdenServiceImpl implements DetalleOrdenService{
    @Autowired
    private DetalleOrdenRepository detalleOrdenRepository;

    @Override
    public DetalleOrden save(DetalleOrden detalleOrden) {
        return detalleOrdenRepository.save(detalleOrden);
    }
}
