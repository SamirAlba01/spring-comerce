package damex.com.damex.controller;

import damex.com.damex.model.DetalleOrden;
import damex.com.damex.model.Orden;
import damex.com.damex.model.Producto;
import damex.com.damex.model.Usuario;
import damex.com.damex.service.ProductoService;
import damex.com.damex.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    private final Logger REGISTRO= LoggerFactory.getLogger(HomeController.class);
    @Autowired
    private ProductoService productoService;
    @Autowired
    private UsuarioService usuarioService;
    private List<DetalleOrden> detalles= new ArrayList<>();
    private Orden orden= new Orden();


    @GetMapping("")
    public String home(Model model){
        model.addAttribute("productos",productoService.findAll());
        return "usuario/home";
    }
    @GetMapping("productohome/{id}")
    public String productoHome(@PathVariable Integer id,Model model){
        Producto producto= productoService.get(id).get();
        model.addAttribute("producto",producto);
        REGISTRO.info("Product: {}",producto);
        return "usuario/productohome";
    }
    @PostMapping("/cart")
    public String addCart(@RequestParam Integer id,@RequestParam Integer cantidad,Model model){

        Producto producto=productoService.get(id).get();
        DetalleOrden detalleOrden= new DetalleOrden(producto,cantidad);
        detalles.add(detalleOrden);
        recalcularTotal();
        agregarDatosModelo(model);
        REGISTRO.info("El detalle de la orden es:{}",detalleOrden);

        return "usuario/carrito";
    }
    @GetMapping("delete/cart/{id}")
    public String deleteProductCart(@PathVariable Integer id,Model model){
        List<DetalleOrden> ordenesNuevo=new ArrayList<>();

        detalles.removeIf(detalleOrden -> detalleOrden.getProducto().getId().equals(id));
        recalcularTotal();
        model.addAttribute("cart",detalles);
        model.addAttribute("orden",orden);
        return "usuario/carrito";
    }
    @GetMapping("/getCart")
    public String getCart(Model model){
        agregarDatosModelo(model);
        return "usuario/carrito";
    }
    @GetMapping("/order")
    public String order(Model model){
        Usuario usuario=usuarioService.findById(1).get();
        model.addAttribute("usuario",usuario);
        agregarDatosModelo(model);
        return "usuario/resumenorden";
    }

    public void agregarDatosModelo(Model model){
        model.addAttribute("cart",detalles);
        model.addAttribute("orden",orden);
    }
    public void recalcularTotal(){
        double precioTotal=detalles.stream().mapToDouble(DetalleOrden::getTotal).sum();
        orden.setTotal(precioTotal);
    }

}
