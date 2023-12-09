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
	
	//--VERFICAR SI EL DOCUMENTO DE IDENTIDAD EXISTE EN LA BD
	boolean existsByDocIdentidad(Integer docIdentidad);

	//VERIFICAR SI EL TELEFONO EXISTE EN LA BD
	boolean existsByTelefono(Integer telefono);
}