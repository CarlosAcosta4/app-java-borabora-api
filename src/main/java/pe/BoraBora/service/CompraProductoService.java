package pe.BoraBora.service;

import java.util.Collection;

import pe.BoraBora.entity.CompraProducto;

public interface CompraProductoService {
	
    //--Operaciones CRUD	
	public abstract void insert(CompraProducto compraproducto);
	public abstract void update(CompraProducto compraproducto);
	public abstract void delete(Integer id);
    public abstract CompraProducto findById(Integer id);
	public abstract Collection<CompraProducto> findAll();
}
