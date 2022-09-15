package damex.com.damex.controller;

import damex.com.damex.enumeration.ERol;
import damex.com.damex.model.Orden;
import damex.com.damex.model.Rol;
import damex.com.damex.model.Usuario;
import damex.com.damex.service.OrdenService;
import damex.com.damex.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    private final Logger REGISTRO= LoggerFactory.getLogger(UsuarioController.class);
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private OrdenService ordenService;


    @GetMapping("/registro")
    public String create(){

        return "usuario/registro";
    }
    @GetMapping("/login")
    public String login(){
        return "usuario/login";
    }
    @PostMapping("/save")
    public String save(Usuario usuario){
        Rol rolUsuario= new Rol(ERol.USUARIO);
        Argon2PasswordEncoder encoder= new Argon2PasswordEncoder();

        usuario.setRoles(List.of(rolUsuario));
        usuario.setPassword(encoder.encode(usuario.getPassword()));

        REGISTRO.info(usuario.getPassword());

        usuarioService.save(usuario);
        return "redirect:/";
    }
    @PostMapping("/acceder")
    public String acceder(Usuario usuario, HttpSession session){
        usuario=usuarioService.findByEmail(usuario.getEmail()).get();

        session.setAttribute("idUsuario",usuario.getId_user());
        REGISTRO.info(usuario.getRoles().toString());

        return "administrador/home";




    }
    @GetMapping("/compras")
    public String obtenerCompras(Model model,HttpSession session){
        model.addAttribute("session",session.getAttribute("idUsuario"));
        Usuario user= usuarioService.findById(Integer.parseInt(session.getAttribute("idUsuario").toString())).get();
        List<Orden> ordenes=ordenService.findByUsuario(user);
        model.addAttribute("ordenes",ordenes);
        REGISTRO.info("LISTA{}",ordenes);
        return "usuario/compras";
    }
    @GetMapping("detalle/{id}")
    public String detalleCompra(@PathVariable Integer id,HttpSession session,Model model){

        Orden orden=ordenService.findById(id).get();
        model.addAttribute("session",session.getAttribute("idUsuario"));
        model.addAttribute("detalles",orden.getDetalleOrden());
        return "usuario/detallecompra";
    }
    @GetMapping("/cerrar")
    public String cerrarSesion(HttpSession session){
        session.removeAttribute("idUsuario");
        return "redirect:/";
    }
}
