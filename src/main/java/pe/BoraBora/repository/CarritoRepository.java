package pe.BoraBora.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.BoraBora.entity.Carrito;
import pe.BoraBora.entity.User;


public interface CarritoRepository extends JpaRepository<Carrito, Integer> {

	//-- BUSCAR CARRITO POR USUARIO
	Carrito findByUser(User user);
}
