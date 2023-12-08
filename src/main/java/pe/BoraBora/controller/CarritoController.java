package pe.BoraBora.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.BoraBora.entity.Carrito;
import pe.BoraBora.entity.Producto;
import pe.BoraBora.repository.CarritoRepository;
import pe.BoraBora.response.ProductoCarritoResponse;

@RestController
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    private CarritoRepository carritoRepository;

    //-- LISTAR PRODUCTOS POR USER ID
    @GetMapping("/productos/{userId}")
    public ResponseEntity<List<ProductoCarritoResponse>> getCarritoProductos(@PathVariable Integer userId) {
        Carrito carrito = carritoRepository.findByUserId(userId);
        if (carrito != null) {
            List<ProductoCarritoResponse> responses = carrito.getCarritoProductos().stream()
                .map(productoCarrito -> {
                    Producto producto = productoCarrito.getProducto();
                    ProductoCarritoResponse response = new ProductoCarritoResponse();
                    response.setMessage("Producto encontrado");
                    response.setStatus(HttpStatus.OK);
                    response.setIdCarrito(carrito.getId());
                    response.setCantidad(productoCarrito.getCantidad());
                    response.setUserId(userId);
                    response.setIdProducto(producto.getId());
                    response.setNombre(producto.getNombre());
                    response.setMarca(producto.getMarca());
                    response.setPrecio(producto.getPrecio());
                    response.setImagen(producto.getImagen());
                    return response;
                })
                .collect(Collectors.toList());
            return new ResponseEntity<>(responses, HttpStatus.OK);
        } else {
            ProductoCarritoResponse carritoResponse = new ProductoCarritoResponse();
            carritoResponse.setMessage("No se encontró carrito para el usuario");
            carritoResponse.setStatus(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(List.of(carritoResponse), HttpStatus.NOT_FOUND);
        }
    }
}