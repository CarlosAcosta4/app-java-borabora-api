package pe.BoraBora.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.BoraBora.entity.Carrito;
import pe.BoraBora.entity.Producto;
import pe.BoraBora.entity.ProductoCarrito;
import pe.BoraBora.entity.User;
import pe.BoraBora.request.ProductoCarritoRequest;
import pe.BoraBora.response.ApiResponse;
import pe.BoraBora.response.ProductoCarritoResponse;
import pe.BoraBora.service.CarritoService;
import pe.BoraBora.service.ProductoCarritoService;
import pe.BoraBora.service.ProductoService;
import pe.BoraBora.service.UserService;

@RestController
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ProductoCarritoService productoCarritoService;
    
    @Autowired
    private ProductoService productoService;
    
    
    //---- TABLA CARRITO ----
    
    //-- CREAR UN CARRITO SEGUN EL USERID
    @PostMapping("/crear/{userId}")
    public ResponseEntity<ApiResponse> createCarrito(@PathVariable Integer userId) {
        User user = userService.getUserById(userId);
        if (user != null) {
            // Comprueba si el usuario ya tiene un carrito
            Carrito existingCarrito = carritoService.findByUser(user);
            if (existingCarrito != null) {
                return new ResponseEntity<>(new ApiResponse("El usuario ya tiene un carrito", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
            }

            Carrito carrito = new Carrito();
            carrito.setUser(user);
            carritoService.save(carrito);
            return new ResponseEntity<>(new ApiResponse("Carrito creado con éxito", HttpStatus.CREATED), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(new ApiResponse("Usuario no encontrado", HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
        }
    }
    
    
    //---- TABLA PRODUCTOS CARRITO ----
    
    //-- LISTAR PRODUCTOS POR USER ID
    @GetMapping("/productos/{userId}")
    public ResponseEntity<List<ProductoCarritoResponse>> getCarritoProductos(@PathVariable Integer userId) {
        Carrito carrito = carritoService.findById(userId);
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
   

    //-- AGREGAR DATOS SEGUN EL CARRITO ID
    @PostMapping("/agregar/{carritoId}")
    public ResponseEntity<ApiResponse> agregarProducto(@PathVariable Integer carritoId, @RequestBody ProductoCarritoRequest productoCarritoRequest) {
        Carrito carrito = carritoService.findById(carritoId);
        Producto producto = productoService.findById(productoCarritoRequest.getProductoId());
        if (carrito != null && producto != null) {
            ProductoCarrito productoCarrito = new ProductoCarrito();
            productoCarrito.setCarrito(carrito);
            productoCarrito.setProducto(producto);
            productoCarrito.setCantidad(productoCarritoRequest.getCantidad());
            productoCarritoService.save(productoCarrito);
            return new ResponseEntity<>(new ApiResponse("Producto agregado al carrito con éxito", HttpStatus.CREATED), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(new ApiResponse("Carrito no encontrado", HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
        }
    }
}