package pe.BoraBora.service;

import java.util.Collection;

import pe.BoraBora.entity.Categoria;

public interface CategoriaService {
	
	//--Operaciones CRUD	
	public abstract void insert(Categoria categoria);
	public abstract void update(Categoria categoria);
	public abstract void delete(Integer id);
    public abstract Categoria findById(Integer id);
	public abstract Collection<Categoria> findAll();
}
