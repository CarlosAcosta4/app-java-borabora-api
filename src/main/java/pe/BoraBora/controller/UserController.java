package pe.BoraBora.controller;


import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.BoraBora.entity.User;
import pe.BoraBora.request.LoginRequest;
import pe.BoraBora.request.UpdatePasswordRequest;
import pe.BoraBora.request.UpdateUserRequest;
import pe.BoraBora.response.ApiResponse;
import pe.BoraBora.response.PerfilResponse;
import pe.BoraBora.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    //--LOGIN
    @PostMapping("/login")
    public ResponseEntity<PerfilResponse> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        try {
            User user = userService.login(loginRequest.getEmail(), loginRequest.getContrasena());

            if (user != null) {
                session.setAttribute("user", user);
                PerfilResponse response = new PerfilResponse("Inicio de sesión correcto", HttpStatus.OK, user.getId(), user.getNombres(), user.getApellidos(), user.getDocIdentidad(), user.getTelefono(), user.getEmail());
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                PerfilResponse response = new PerfilResponse( "Usuario y/o contraseña incorrecta", HttpStatus.UNAUTHORIZED, null, null, null, null, null, null);
                return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            PerfilResponse response = new PerfilResponse("Error al intentar iniciar sesión. Detalles: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null, null, null, null, null, null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //--REGISTRAR UN USUARIO
    @PostMapping("/register")
    public ResponseEntity<ApiResponse> createUser(@RequestBody User user) {
        try {
            if (user == null || StringUtils.isBlank(user.getNombres()) || StringUtils.isBlank(user.getApellidos()) || user.getDocIdentidad() == null || user.getTelefono() == null || StringUtils.isBlank(user.getEmail()) || StringUtils.isBlank(user.getContrasena())) {
                return new ResponseEntity<>(new ApiResponse("Por favor, complete todos los campos obligatorios", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
            }

            if (userService.emailExists(user.getEmail())) {
                return new ResponseEntity<>(new ApiResponse("El correo electrónico ya está registrado. Por favor, ingrese otro", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
            }
            
            if (userService.docIdentidadExists(user.getDocIdentidad())) {
                return new ResponseEntity<>(new ApiResponse("El documento de identidad ya está registrado. Por favor, ingrese otro", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
            }

            if (userService.telefonoExists(user.getTelefono())) {
                return new ResponseEntity<>(new ApiResponse("El teléfono ya está registrado. Por favor, ingrese otro", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
            }

            userService.insert(user);
            return new ResponseEntity<>(new ApiResponse("Cuenta creada con éxito", HttpStatus.CREATED), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse("Error al crear la cuenta. Detalles: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //--ACTUALIZAR PASSWORD DEL USUARIO
    @PostMapping("/update-password")
    public ResponseEntity<ApiResponse> updatePassword(@RequestBody UpdatePasswordRequest request) {
        try {
            String email = request.getEmail();
            String oldPass = request.getOldPassword();
            String newPass = request.getNewPassword();

            if (!userService.emailExists(email)) {
                return new ResponseEntity<>(new ApiResponse("El correo electrónico no existe", HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
            }

            User updatedUser = userService.updatePassword(email, newPass);

            if (updatedUser != null) {
                return new ResponseEntity<>(new ApiResponse("Contraseña restablecida con éxito", HttpStatus.OK), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ApiResponse("No se pudo restablecer la contraseña", HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse("Error al restablecer la contraseña. Detalles: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //ACTUALIZAR DATOS DEL USUARIO
    @PutMapping("/update-user/{id}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable Integer id, @RequestBody UpdateUserRequest request) {
        try {
            if (!userService.existsById(id)) {
                return new ResponseEntity<>(new ApiResponse("El usuario no existe", HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
            }

            User currentUser = userService.getUserById(id);

            if (userService.emailExists(request.getEmail()) && !currentUser.getEmail().equals(request.getEmail())) {
                return new ResponseEntity<>(new ApiResponse("El correo electrónico ya está registrado. Por favor, ingrese otro", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
            }

            if (userService.docIdentidadExists(request.getDocIdentidad()) && !currentUser.getDocIdentidad().equals(request.getDocIdentidad())) {
                return new ResponseEntity<>(new ApiResponse("El documento de identidad ya está registrado. Por favor, ingrese otro", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
            }

            if (userService.telefonoExists(request.getTelefono()) && !currentUser.getTelefono().equals(request.getTelefono())) {
                return new ResponseEntity<>(new ApiResponse("El teléfono ya está registrado. Por favor, ingrese otro", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
            }

            currentUser.setNombres(request.getNombres());
            currentUser.setApellidos(request.getApellidos());
            currentUser.setDocIdentidad(request.getDocIdentidad());
            currentUser.setTelefono(request.getTelefono());
            currentUser.setEmail(request.getEmail());

            userService.updateUser(currentUser);
            return new ResponseEntity<>(new ApiResponse("Usuario actualizado con éxito", HttpStatus.OK), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse("Error al actualizar el usuario. Detalles: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //--ESTE CONTROLADOR NO SE ESTA USANDO
    //--BUSQUEDA DE USUARIO POR ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Integer id) {
        try {
            User user = userService.getUserById(id);
            if (user != null) {
                PerfilResponse perfilResponse = new PerfilResponse();
                perfilResponse.setUserId(user.getId());
                perfilResponse.setNombres(user.getNombres());
                perfilResponse.setApellidos(user.getApellidos());
                perfilResponse.setDocIdentidad(user.getDocIdentidad());
                perfilResponse.setTelefono(user.getTelefono());
                perfilResponse.setEmail(user.getEmail());
                perfilResponse.setMessage("Usuario encontrado");
                perfilResponse.setStatus(HttpStatus.OK);
                return new ResponseEntity<>(perfilResponse, HttpStatus.OK);
            } else {
                PerfilResponse perfilResponse = new PerfilResponse();
                perfilResponse.setMessage("Usuario no encontrado");
                perfilResponse.setStatus(HttpStatus.NOT_FOUND);
                return new ResponseEntity<>(perfilResponse, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            PerfilResponse perfilResponse = new PerfilResponse();
            perfilResponse.setMessage("Error interno del servidor");
            perfilResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(perfilResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}



