package pe.BoraBora.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.BoraBora.entity.Producto;
import pe.BoraBora.repository.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService{
	
	@Autowired
	private ProductoRepository repository;
	
	public ProductoServiceImpl() {
	}
	
	@Override 
	@Transactional
	public void insert(Producto producto) {
		repository.save(producto);
	}
	
	@Override 
	@Transactional
	public void update(Producto producto) {
		repository.save(producto);
	}
	
	@Override
	@Transactional
	public void delete(Integer id) {
		repository.deleteById(id);
	}
	
	@Override 
	@Transactional(readOnly=true)
	public Producto findById(Integer id) {
		return repository.findById(id).orElse(null);
	}
	
	@Override 
	@Transactional(readOnly=true)
	public Collection<Producto> findAll() {
		return repository.findAll();
	}
	
	@Override
    public List<Producto> findProductosByCategoriaId(Integer categoriaId) {
        return repository.findByCategoriaId(categoriaId);
    }
}
