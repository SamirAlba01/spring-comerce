package damex.com.damex.service;

import damex.com.damex.model.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    Producto add(Producto producto);
    Optional<Producto> get(Integer id);
    void update(Producto producto);
    void delete(Integer id);
    public List<Producto> findAll();
}
