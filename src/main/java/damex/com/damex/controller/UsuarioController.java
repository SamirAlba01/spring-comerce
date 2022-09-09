package damex.com.damex.controller;

import damex.com.damex.model.Producto;
import damex.com.damex.model.Usuario;
import damex.com.damex.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    private final Logger REGISTRO= LoggerFactory.getLogger(UsuarioController.class);
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/registro")
    public String create(){

        return "usuario/registro";
    }

    @PostMapping("/save")
    public String save(Usuario usuario){
        usuarioService.save(usuario);
        return "redirect:/";
    }

}
