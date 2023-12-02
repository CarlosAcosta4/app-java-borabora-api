package pe.BoraBora.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.BoraBora.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer>{

}
