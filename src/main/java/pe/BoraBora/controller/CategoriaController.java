package pe.BoraBora.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.BoraBora.response.CategoriaResponse;
import pe.BoraBora.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;
    
    @GetMapping("/listar")
    public List<CategoriaResponse> getAllCategorias() {
        return categoriaService.findAll().stream()
            .map(categoria -> {
                CategoriaResponse categoriaResponse = new CategoriaResponse();
                categoriaResponse.setMessage("Categoria encontrada");
                categoriaResponse.setStatus(HttpStatus.OK);
                categoriaResponse.setId(categoria.getId());
                categoriaResponse.setNombre(categoria.getNombre());
                categoriaResponse.setImagen(categoria.getImagen());
                return categoriaResponse;
            })
            .collect(Collectors.toList());
    }
}
