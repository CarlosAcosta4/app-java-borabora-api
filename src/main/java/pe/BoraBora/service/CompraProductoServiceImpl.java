package pe.BoraBora.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.BoraBora.entity.CompraProducto;
import pe.BoraBora.repository.CompraProductoRepository;

@Service
public class CompraProductoServiceImpl implements CompraProductoService{
	
	@Autowired
	private CompraProductoRepository repository;
	
	public CompraProductoServiceImpl() {
	}
	
	@Override 
	@Transactional
	public void insert(CompraProducto compraProducto) {
		repository.save(compraProducto);
	}
	
	@Override 
	@Transactional
	public void update(CompraProducto compraProducto) {
		repository.save(compraProducto);
	}
	
	@Override
	@Transactional
	public void delete(Integer id) {
		repository.deleteById(id);
	}
	
	@Override 
	@Transactional(readOnly=true)
	public CompraProducto findById(Integer id) {
		return repository.findById(id).orElse(null);
	}
	
	@Override 
	@Transactional(readOnly=true)
	public Collection<CompraProducto> findAll() {
		return repository.findAll();
	}
}