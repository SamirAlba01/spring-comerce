package damex.com.damex.controller;

import damex.com.damex.model.DetalleOrden;
import damex.com.damex.model.Orden;
import damex.com.damex.model.Producto;
import damex.com.damex.model.Usuario;
import damex.com.damex.service.DetalleOrdenService;
import damex.com.damex.service.OrdenService;
import damex.com.damex.service.ProductoService;
import damex.com.damex.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    private final Logger REGISTRO= LoggerFactory.getLogger(HomeController.class);
    @Autowired
    private ProductoService productoService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private OrdenService ordenService;
    @Autowired
    private DetalleOrdenService detalleOrdenService;


    private List<DetalleOrden> detalles= new ArrayList<>();
    private Orden orden= new Orden();


    @GetMapping("")
    public String home(Model model, HttpSession session){

        model.addAttribute("productos",productoService.findAll());
        model.addAttribute("sesion",session.getAttribute("idUsuario"));
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
    public String addCart(@RequestParam Integer id,@RequestParam Integer cantidad,Model model,HttpSession session){

        Producto producto=productoService.get(id).get();
        DetalleOrden detalleOrden= new DetalleOrden(producto,cantidad);
        detalles.add(detalleOrden);
        recalcularTotal();
        agregarDatosModelo(model,session);
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
    public String getCart(Model model,HttpSession session){
        agregarDatosModelo(model,session);
        return "usuario/carrito";
    }
    @GetMapping("/order")
    public String order(Model model,HttpSession session){
        Integer id=Integer.parseInt(session.getAttribute("idUsuario").toString());
        Usuario usuario=usuarioService.findById(id).get();
        model.addAttribute("usuario",usuario);
        agregarDatosModelo(model,session);
        return "usuario/resumenorden";
    }

    public void agregarDatosModelo(Model model,HttpSession session){
        model.addAttribute("session",session.getAttribute("idUsuario"));
        model.addAttribute("cart",detalles);
        model.addAttribute("orden",orden);
    }
    public void recalcularTotal(){
        double precioTotal=detalles.stream().mapToDouble(DetalleOrden::getTotal).sum();
        orden.setTotal(precioTotal);
    }
    @GetMapping("/saveOrder")
    public String saveOrder(HttpSession session){
        Integer id=Integer.parseInt(session.getAttribute("idUsuario").toString());
        orden.setFechaCreacion(new Date());
        orden.setNumero(ordenService.generarNumeroOrden());
        orden.setUsuario(usuarioService.findById(id).get());
        ordenService.save(orden);

        for(DetalleOrden detalleOrden:detalles){
            detalleOrden.setOrden(orden);
            detalleOrdenService.save(detalleOrden);
        }
        //Limpiar lista
        orden= new Orden();
        detalles.clear();
        return "redirect:/";
    }
    @PostMapping("/search")
    public String searchProduct(@RequestParam String nombre,Model model){

        List<Producto> productos=productoService.findByName(nombre);
        model.addAttribute("productos",productos);
        return "usuario/home";
    }


}
