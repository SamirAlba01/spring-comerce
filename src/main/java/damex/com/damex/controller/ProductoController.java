package damex.com.damex.controller;

import damex.com.damex.model.Producto;
import damex.com.damex.model.Usuario;
import damex.com.damex.service.ProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("productos")
public class ProductoController {
    private final Logger REGISTRO= LoggerFactory.getLogger(ProductoController.class);
    @Autowired
    private ProductoService productoService;
    @GetMapping("")
    public String show(Model model){
        model.addAttribute("productos",productoService.findAll());
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
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id,Model model){
        Producto producto= new Producto();

        Optional<Producto> optional = productoService.get(id);
        producto=optional.get();
        model.addAttribute("producto",producto);
        REGISTRO.info("Producto Buscado: {}",producto);


        return "productos/edit";
    }
    @PostMapping("/update")
    public String update(Producto producto){
        productoService.update(producto);
        return "redirect:/productos";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){

        productoService.delete(id);
        return "redirect:/productos";
    }


}
