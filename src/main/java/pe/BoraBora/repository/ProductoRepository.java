package pe.BoraBora.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.BoraBora.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer>{
	List<Producto> findByCategoriaId(Integer categoriaId);
}
