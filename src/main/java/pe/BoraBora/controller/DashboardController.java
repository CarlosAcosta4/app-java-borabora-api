package pe.BoraBora.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import java.util.List;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.BoraBora.entity.Producto;
import pe.BoraBora.repository.ProductoRepository;
import pe.BoraBora.response.TopProductosResponse;

@RestController
public class DashboardController {

    private final ProductoRepository productoRepository;

    public DashboardController(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }
    
    @GetMapping("/topProductos")
    public List<TopProductosResponse> findTopProductos() {
        Pageable topSix = PageRequest.of(0, 6);
        List<Producto> productos = productoRepository.findTopProductos(topSix);
        List<TopProductosResponse> productoResponses = new ArrayList<>();
        for (int i = 0; i < productos.size(); i++) {
            Producto producto = productos.get(i);
            TopProductosResponse response = new TopProductosResponse();
            response.setTop(i + 1);  
            response.setId(producto.getId());
            response.setNombre(producto.getNombre());
            response.setDescripcion(producto.getDescripcion());
            response.setMarca(producto.getMarca());
            response.setPrecio(producto.getPrecio());
            response.setImagen(producto.getImagen());
            productoResponses.add(response);
        }
        return productoResponses;
    }
}