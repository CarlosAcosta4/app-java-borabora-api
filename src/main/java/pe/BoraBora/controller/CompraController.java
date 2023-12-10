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

import pe.BoraBora.entity.Compra;
import pe.BoraBora.entity.CompraProducto;
import pe.BoraBora.entity.Producto;
import pe.BoraBora.entity.User;
import pe.BoraBora.model.ProductoNotFoundException;
import pe.BoraBora.repository.UserRepository;
import pe.BoraBora.request.CompraRequest;
import pe.BoraBora.response.ApiResponse;
import pe.BoraBora.response.HistorialComprasResponse;
import pe.BoraBora.response.ProductoCompraResponse;
import pe.BoraBora.service.CompraService;


@RestController
@RequestMapping("/compras")
public class CompraController {

    @Autowired
    private CompraService compraService;
    
    @Autowired
    private UserRepository userRepository;
    
    //--BUSQUEDAD DE LA COMPRA DE UN USUARIO POR SU ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<HistorialComprasResponse>> getComprasUser(@PathVariable Integer userId) {
        List<Compra> compras = compraService.findComprasByUserId(userId);
        if (!compras.isEmpty()) {
            // Convertir la lista de Compra a CompraResponse
            List<HistorialComprasResponse> historialCompraResponses = compras.stream()
                .map(compra -> {
                    HistorialComprasResponse histCompraResponse = new HistorialComprasResponse();
                    histCompraResponse.setMessage("Compra encontrada");
                    histCompraResponse.setStatus(HttpStatus.OK);
                    histCompraResponse.setId(compra.getId());
                    histCompraResponse.setTotal(compra.getTotal());
                    histCompraResponse.setIgv(compra.getIgv());
                    histCompraResponse.setSubtotal(compra.getSubtotal());
                    histCompraResponse.setMetodopago(compra.getMetodopago());
                    histCompraResponse.setFcompra(compra.getFcompra());
                    histCompraResponse.setUserId(compra.getUser().getId());
                    return histCompraResponse;
                })
                .collect(Collectors.toList());
            return new ResponseEntity<>(historialCompraResponses, HttpStatus.OK);
        } else {	
            HistorialComprasResponse histCompraResponse = new HistorialComprasResponse();
            histCompraResponse.setMessage("No se encontraron compras para el usuario");
            histCompraResponse.setStatus(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(List.of(histCompraResponse), HttpStatus.NOT_FOUND);
        }
    }

    //-- LISTAR PRODUCTOS DE LA COMPRA DE UN USUARIO por compraID
    @GetMapping("/productos/{compraId}")
    public ResponseEntity<List<ProductoCompraResponse>> getCompraProductos(@PathVariable Integer compraId) {
        Compra compra = compraService.findById(compraId);
        if (compra != null) {
            List<ProductoCompraResponse> responses = compra.getCompraProductos().stream()
                .map(productoCompra -> {
                    Producto producto = productoCompra.getProducto();
                    ProductoCompraResponse response = new ProductoCompraResponse();
                    response.setMessage("Lista de productos obtenida");
                    response.setStatus(HttpStatus.OK);
                    response.setIdCompra(compra.getId());
                    response.setCantidad(productoCompra.getCantidad());
                    response.setUserId(compra.getUser().getId());
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
        	ProductoCompraResponse compraResponse = new ProductoCompraResponse();
        	compraResponse.setMessage("No se encontró la compra");
        	compraResponse.setStatus(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(List.of(compraResponse), HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/info/{compraId}")
    public ResponseEntity<HistorialComprasResponse> getInfoCompra(@PathVariable Integer compraId) {
        Compra compra = compraService.findById(compraId);
        if (compra != null) {
            HistorialComprasResponse response = new HistorialComprasResponse();
            response.setMessage("Compra obtenida");
            response.setStatus(HttpStatus.OK);
            response.setUserId(compra.getUser().getId());
            response.setId(compra.getId());
            response.setFcompra(compra.getFcompra());
            response.setTotal(compra.getTotal());
            response.setIgv(compra.getIgv());
            response.setSubtotal(compra.getSubtotal());
            response.setMetodopago(compra.getMetodopago());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            HistorialComprasResponse response = new HistorialComprasResponse();
            response.setMessage("No se encontró la compra");
            response.setStatus(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
    
    //-------- REGISTRAR UNA COMPRA POR USER ID-------- 
    
    //--JSON PARA PROBAR EN POSTMAN
    /*{
        "metodopago": "tarjeta de crédito",
        "fcompra": "2022-01-01",
        "userId": 8,
        "productos": [
            {
                "producto": {
                    "id": 1
                },
                "cantidad": 2
            },
            {
                "producto": {
                    "id": 2
                },
                "cantidad": 1
            }
        ]
    }*/
    @PostMapping("/register/{userId}")
    public ResponseEntity<ApiResponse> postInsertCompra(@PathVariable Integer userId, @RequestBody CompraRequest compraRequest) {
        try {
        	if (userRepository.existsById(userId)) {
                User user = userRepository.findById(userId).get();
                List<CompraProducto> productos = compraRequest.getProductos(); 
                Compra compra = compraService.crearCompra(user, productos, compraRequest.getMetodopago(), compraRequest.getFcompra());
                return new ResponseEntity<>(new ApiResponse("Compra insertada con éxito", HttpStatus.CREATED), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(new ApiResponse("El usuario no existe", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
            }
        } catch (ProductoNotFoundException e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse("Error al insertar la compra. Detalles: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
