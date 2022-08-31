package damex.com.damex.controller;

import damex.com.damex.model.Producto;
import damex.com.damex.model.Usuario;
import damex.com.damex.service.ProductoService;
import damex.com.damex.service.UploadFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("productos")
public class ProductoController {
    private final Logger REGISTRO= LoggerFactory.getLogger(ProductoController.class);
    @Autowired
    private ProductoService productoService;
    @Autowired
    private UploadFileService uploadFileService;

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
    public String save(Producto producto,@RequestParam("img") MultipartFile file) throws IOException {
        REGISTRO.info("Este el objeto producto {}",producto);
        Usuario user= new Usuario(1,"","","","","","","");
        producto.setUsuario(user);
        if(producto.getId()==null){
            String name=uploadFileService.saveImage(file);
            producto.setImagen(name);
        }
        productoService.add(producto);

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
    public String update(Producto producto,@RequestParam("img") MultipartFile file) throws IOException {
        Producto aux=new Producto();
        aux=productoService.get(producto.getId()).get();
        if(file.isEmpty()){

            producto.setImagen(aux.getImagen());
        }else{

            if(!aux.getImagen().equals("default.jpg")){
                uploadFileService.deleteImage(aux.getImagen());
            }
            String name=uploadFileService.saveImage(file);
            producto.setImagen(name);
        }
        producto.setUsuario(aux.getUsuario());
        productoService.update(producto);
        return "redirect:/productos";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        Producto producto= new Producto();
        producto=productoService.get(id).get();
        if(!producto.getImagen().equals("default.jpg")){
            uploadFileService.deleteImage(producto.getImagen());
        }
        productoService.delete(id);
        return "redirect:/productos";
    }


}
