package damex.com.damex.controller;

import damex.com.damex.model.Producto;
import damex.com.damex.service.ProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    private final Logger REGISTRO= LoggerFactory.getLogger(HomeController.class);
    @Autowired
    private ProductoService productoService;

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

}