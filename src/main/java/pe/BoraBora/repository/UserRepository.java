package pe.BoraBora.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import pe.BoraBora.entity.User;


public interface UserRepository extends JpaRepository<User, Integer> {
	
	//--LOGIN
	User findByEmailAndContrasena(String email, String contrasena);
	
	//--VERIFICAR SI EL EMAIL EXISTE EN LA BD
	boolean existsByEmail(String email);
		
	//--RESET PASSWORD
	User findByEmail(String email);
	
	//--VERIFICAR SI EL ID DEL USUARIO EXISTE
	boolean existsById(Integer id);
	
	boolean existsByDocIdentidad(Integer docIdentidad);

	boolean existsByTelefono(Integer telefono);
}