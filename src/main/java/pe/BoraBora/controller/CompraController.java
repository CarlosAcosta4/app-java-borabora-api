package pe.BoraBora.controller;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.BoraBora.entity.Compra;
import pe.BoraBora.entity.User;
import pe.BoraBora.model.ApiResponse;
import pe.BoraBora.repository.UserRepository;
import pe.BoraBora.service.CompraService;

@RestController
@RequestMapping("/compras")
public class CompraController {

    @Autowired
    private CompraService compraService;
    
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> insert(@RequestBody Compra compra, HttpSession session) {
        try {
            User user = (User) session.getAttribute("user");
            if (user != null && userRepository.existsById(user.getId())) {
                compra.setUser(user);
                compraService.insert(compra);
                return new ResponseEntity<>(new ApiResponse("Compra insertada con éxito", HttpStatus.CREATED), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(new ApiResponse("El usuario no existe o no ha iniciado sesión", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse("Error al insertar la compra. Detalles: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable Integer id) {
        Compra compra = compraService.findById(id);
        if (compra != null) {
            return new ResponseEntity<>(new ApiResponse("Compra encontrada", HttpStatus.OK, compra), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponse("Compra no encontrada", HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping()
    public ResponseEntity<ApiResponse> findAll() {
        Collection<Compra> compras = compraService.findAll();
        return new ResponseEntity<>(new ApiResponse("Lista de compras obtenida con éxito", HttpStatus.OK, compras), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Integer id, @RequestBody Compra compra,HttpSession session) {
    	try {
            User user = (User) session.getAttribute("user");
	        if (compraService.findById(id) != null) {
	        	compra.setUser(user);
	            compraService.update(compra);
	            return new ResponseEntity<>(new ApiResponse("Compra actualizada con éxito", HttpStatus.OK, compra), HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(new ApiResponse("Compra no encontrada", HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
	        }
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse("Error al actualizar la compra. Detalles: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }  
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id) {
        if (compraService.findById(id) != null) {
            compraService.delete(id);
            return new ResponseEntity<>(new ApiResponse("Compra eliminada con éxito", HttpStatus.OK), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponse("Compra no encontrada", HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
        }
    }
}
