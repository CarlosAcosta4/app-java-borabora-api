package pe.BoraBora.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.BoraBora.entity.Categoria;
import pe.BoraBora.repository.CategoriaRepository;

@Service
public class CategoriaServiceImpl  implements CategoriaService{
	
	@Autowired
	private CategoriaRepository repository;
	
	public CategoriaServiceImpl() {
	}
	
	@Override 
	@Transactional
	public void insert(Categoria categoria) {
		repository.save(categoria);
	}
	
	@Override 
	@Transactional
	public void update(Categoria categoria) {
		repository.save(categoria);
	}
	
	@Override
	@Transactional
	public void delete(Integer id) {
		repository.deleteById(id);
	}
	
	@Override 
	@Transactional(readOnly=true)
	public Categoria findById(Integer id) {
		return repository.findById(id).orElse(null);
	}
	
	@Override 
	@Transactional(readOnly=true)
	public Collection<Categoria> findAll() {
		return repository.findAll();
	}
}
