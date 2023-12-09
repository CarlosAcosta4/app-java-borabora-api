package pe.BoraBora.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.BoraBora.entity.Carrito;
import pe.BoraBora.entity.User;
import pe.BoraBora.repository.CarritoRepository;


@Service
public class CarritoServiceImpl  implements CarritoService{
	
	@Autowired
	private CarritoRepository repository;
	
	public CarritoServiceImpl() {
	}
	
	@Override
	@Transactional
	public void delete(Integer id) {
		repository.deleteById(id);
	}
	
	@Override 
	@Transactional(readOnly=true)
	public Carrito findById(Integer id) {
		return repository.findById(id).orElse(null);
	}
	
	@Override 
	@Transactional(readOnly=true)
	public Collection<Carrito> findAll() {
		return repository.findAll();
	}

	@Override
	public Carrito save(Carrito carrito) {
		return repository.save(carrito);
	}
	
	@Override
    public Carrito findByUser(User user) {
        return repository.findByUser(user);
    }
}
