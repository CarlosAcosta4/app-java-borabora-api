package pe.BoraBora.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.BoraBora.entity.ProductoCarrito;
import pe.BoraBora.repository.ProductoCarritoRepository;

@Service
public class ProductoCarritoServiceImpl  implements ProductoCarritoService{
	
	@Autowired
	private ProductoCarritoRepository repository;
	
	public ProductoCarritoServiceImpl() {
	}
	
	@Override
	public ProductoCarrito save(ProductoCarrito productoCarrito) {
		return repository.save(productoCarrito);
	}
}
