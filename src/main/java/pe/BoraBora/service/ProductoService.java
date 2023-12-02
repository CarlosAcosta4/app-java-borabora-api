package pe.BoraBora.service;

import java.util.Collection;

import pe.BoraBora.entity.Producto;

public interface ProductoService {
	
	//--Operaciones CRUD	
		public abstract void insert(Producto producto);
		public abstract void update(Producto producto);
		public abstract void delete(Integer id);
	    public abstract Producto findById(Integer id);
		public abstract Collection<Producto> findAll();
}
