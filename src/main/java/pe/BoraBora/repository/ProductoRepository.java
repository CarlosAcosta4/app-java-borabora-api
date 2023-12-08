package pe.BoraBora.repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.BoraBora.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer>{
	List<Producto> findByCategoriaId(Integer categoriaId);
	
	@Query("SELECT cp.producto FROM CompraProducto cp GROUP BY cp.producto ORDER BY SUM(cp.cantidad) DESC")
	List<Producto> findTopProductos(Pageable pageable);
}
