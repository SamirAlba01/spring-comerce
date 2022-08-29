package damex.com.damex.controller;

import damex.com.damex.model.Producto;
import damex.com.damex.model.Usuario;
import damex.com.damex.service.ProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("productos")
public class ProductoController {
    private final Logger REGISTRO= LoggerFactory.getLogger(ProductoController.class);
    @Autowired
    private ProductoService productoService;


    @GetMapping("")
    public String show(){
        return "productos/show";
    }
    @GetMapping("/create")
    public String create(){
        return "productos/create";
    }
    @PostMapping("/save")
    public String save(Producto producto){
        Usuario user= new Usuario(1,"","","","","","","");
        producto.setUsuario(user);
        productoService.add(producto);
        REGISTRO.info("Este el objeto producto {}",producto);
        return "redirect:/productos";
    }

}
