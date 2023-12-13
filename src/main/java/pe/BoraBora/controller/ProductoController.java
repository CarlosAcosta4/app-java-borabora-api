package pe.BoraBora.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.BoraBora.entity.Producto;
import pe.BoraBora.response.ProductoResponse;
import pe.BoraBora.service.ProductoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;
    
    //--LISTAR PRODUCTOS
    @GetMapping("/listar")
    public List<ProductoResponse> getAllProductos() {
        return productoService.findAll().stream()
            .map(producto -> {
                ProductoResponse productoResponse = new ProductoResponse();
                productoResponse.setMessage("Producto encontrado");
                productoResponse.setStatus(HttpStatus.OK);
                productoResponse.setId(producto.getId());
                productoResponse.setNombre(producto.getNombre());
                productoResponse.setDescripcion(producto.getDescripcion());
                productoResponse.setMarca(producto.getMarca());
                productoResponse.setPrecio(producto.getPrecio());
                productoResponse.setStock(producto.getStock());
                productoResponse.setFvencimiento(producto.getFvencimiento());
                productoResponse.setImagen(producto.getImagen());
                productoResponse.setCategoriaId(producto.getCategoria().getId());
                productoResponse.setCategoriaNombre(producto.getCategoria().getNombre());
                return productoResponse;
            })
            .collect(Collectors.toList());
    }
    
    //--LISTAR PRODUCTOS X CATEGORIA
    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<ProductoResponse>> findByCategoriaId(@PathVariable Integer categoriaId) {
        List<Producto> productos = productoService.findProductosByCategoriaId(categoriaId);
        if (!productos.isEmpty()) {
            List<ProductoResponse> productoResponses = productos.stream()
                .map(producto -> {
                    ProductoResponse productoResponse = new ProductoResponse();
                    productoResponse.setMessage("Producto encontrado");
                    productoResponse.setStatus(HttpStatus.OK);
                    productoResponse.setId(producto.getId());
                    productoResponse.setNombre(producto.getNombre());
                    productoResponse.setDescripcion(producto.getDescripcion());
                    productoResponse.setMarca(producto.getMarca());
                    productoResponse.setPrecio(producto.getPrecio());
                    productoResponse.setStock(producto.getStock());
                    productoResponse.setFvencimiento(producto.getFvencimiento());
                    productoResponse.setImagen(producto.getImagen());
                    productoResponse.setCategoriaId(producto.getCategoria().getId());
                    productoResponse.setCategoriaNombre(producto.getCategoria().getNombre());
                    return productoResponse;
                })
                .collect(Collectors.toList());
            return new ResponseEntity<>(productoResponses, HttpStatus.OK);
        } else {    
            ProductoResponse productoResponse = new ProductoResponse();
            productoResponse.setMessage("No se encontraron productos para la categoría");
            productoResponse.setStatus(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(List.of(productoResponse), HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/buscarproducto/{id}")
    public ResponseEntity<ProductoResponse> getProductoById(@PathVariable Integer id) {
        Producto producto = productoService.findById(id);
        if (producto != null) {
            ProductoResponse productoResponse = new ProductoResponse();
            productoResponse.setMessage("Producto encontrado");
            productoResponse.setStatus(HttpStatus.OK);
            productoResponse.setId(producto.getId());
            productoResponse.setNombre(producto.getNombre());
            productoResponse.setDescripcion(producto.getDescripcion());
            productoResponse.setMarca(producto.getMarca());
            productoResponse.setPrecio(producto.getPrecio());
            productoResponse.setStock(producto.getStock());
            productoResponse.setFvencimiento(producto.getFvencimiento());
            productoResponse.setImagen(producto.getImagen());
            productoResponse.setCategoriaId(producto.getCategoria().getId());
            productoResponse.setCategoriaNombre(producto.getCategoria().getNombre());
            return new ResponseEntity<>(productoResponse, HttpStatus.OK);
        } else {
            ProductoResponse productoResponse = new ProductoResponse();
            productoResponse.setMessage("Producto no encontrado");
            productoResponse.setStatus(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(productoResponse, HttpStatus.NOT_FOUND);
        }
    }
}