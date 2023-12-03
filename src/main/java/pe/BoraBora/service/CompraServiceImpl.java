package pe.BoraBora.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.BoraBora.entity.Compra;
import pe.BoraBora.repository.CompraRepository;


@Service
public class CompraServiceImpl implements CompraService{
	
	@Autowired
	private CompraRepository repository;
	
	public CompraServiceImpl() {
	}
	
	@Override 
	@Transactional
	public void insert(Compra compra) {
		repository.save(compra);
	}
	
	@Override 
	@Transactional
	public void update(Compra compra) {
		repository.save(compra);
	}
	
	@Override
	@Transactional
	public void delete(Integer id) {
		repository.deleteById(id);
	}
	
	@Override 
	@Transactional(readOnly=true)
	public Compra findById(Integer id) {
		return repository.findById(id).orElse(null);
	}
	
	@Override 
	@Transactional(readOnly=true)
	public Collection<Compra> findAll() {
		return repository.findAll();
	}
	
	@Override 
    @Transactional(readOnly=true)
    public List<Compra> findComprasByUserId(Integer userId) {
        return repository.findComprasByUserId(userId);
    }
}

