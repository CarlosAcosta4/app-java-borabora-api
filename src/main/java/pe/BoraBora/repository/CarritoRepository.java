package pe.BoraBora.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.BoraBora.entity.Carrito;


public interface CarritoRepository extends JpaRepository<Carrito, Integer> {
    Carrito findByUserId(Integer userId);
}
