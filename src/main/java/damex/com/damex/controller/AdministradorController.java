package damex.com.damex.controller;

import java.util.List;

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
@RequestMapping("/administrador")
public class AdministradorController {

	@Autowired
	private ProductoService productoService;
	@GetMapping("")
	public String home(Model model){
		List<Producto> productos=productoService.findAll();
		model.addAttribute("productos",productos);
		return "administrador/home";

	}


	
	
}
