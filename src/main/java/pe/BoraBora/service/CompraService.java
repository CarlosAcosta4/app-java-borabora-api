package pe.BoraBora.service;

import java.util.Collection;

import pe.BoraBora.entity.Compra;

public interface CompraService {
	
    //--Operaciones CRUD	
	public abstract void insert(Compra compra);
	public abstract void update(Compra compra);
	public abstract void delete(Integer id);
    public abstract Compra findById(Integer id);
	public abstract Collection<Compra> findAll();
}
