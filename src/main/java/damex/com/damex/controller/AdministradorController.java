package damex.com.damex.controller;

import java.util.List;

import damex.com.damex.model.Orden;
import damex.com.damex.model.Producto;
import damex.com.damex.service.OrdenService;
import damex.com.damex.service.ProductoService;
import damex.com.damex.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/administrador")
public class AdministradorController {

	@Autowired
	private ProductoService productoService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private OrdenService ordenService;
	@GetMapping("")
	public String home(Model model){
		List<Producto> productos=productoService.findAll();
		model.addAttribute("productos",productos);
		return "administrador/home";

	}
	@GetMapping("/usuarios")
	public String usuarios(Model model){
		model.addAttribute("usuarios",usuarioService.findAll());
		return "administrador/usuarios";
	}
	@GetMapping("/ordenes")
	public String ordenes(Model model){
		model.addAttribute("ordenes",ordenService.findAll());
		return "administrador/ordenes";
	}
	@GetMapping("/detalle/{id}")
	public String detalle(@PathVariable Integer id,Model model){
		Orden orden= ordenService.findById(id).get();
		model.addAttribute("detalles",orden.getDetalleOrden());
		return "administrador/detalleorden";
	}

	
	
}
