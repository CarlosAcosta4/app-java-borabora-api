package pe.BoraBora.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import pe.BoraBora.entity.Compra;
import pe.BoraBora.entity.CompraProducto;
import pe.BoraBora.entity.User;

public interface CompraService {
	
    //--Operaciones CRUD	
	public abstract void insert(Compra compra);
	public abstract void update(Compra compra);
	public abstract void delete(Integer id);
    public abstract Compra findById(Integer id);
	public abstract Collection<Compra> findAll();
	
	//--Operación de búsqueda por ID de usuario
    public abstract List<Compra> findComprasByUserId(Integer userId);
    
    //--Operación de búsqueda por ID de compra
    public abstract Compra getCompraById(Integer compraId);
    
    //--Funcionalidad de crear una compra y calcular el subtotal, IGV y total
    Compra crearCompra(User usuario, List<CompraProducto> productos, String metodopago, LocalDate fcompra);
}
