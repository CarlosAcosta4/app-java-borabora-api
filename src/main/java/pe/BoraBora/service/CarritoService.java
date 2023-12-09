package pe.BoraBora.service;

import java.util.Collection;

import pe.BoraBora.entity.Carrito;
import pe.BoraBora.entity.User;

public interface CarritoService {
	
	//--Operaciones CRUD
	public abstract void delete(Integer id);
    public abstract Carrito findById(Integer id);
	public abstract Collection<Carrito> findAll();
	public abstract Carrito save(Carrito carrito);
	
	// Método para buscar un carrito por usuario
    public abstract Carrito findByUser(User user);
}

