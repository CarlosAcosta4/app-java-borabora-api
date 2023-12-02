package pe.BoraBora.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.BoraBora.entity.User;
import pe.BoraBora.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;
	
	public UserServiceImpl() {}
	
	//--INICIAR SESION
	@Override
	@Transactional(readOnly = true)
	public User login(String email, String pass) {
	    return repository.findByEmailAndContrasena(email, pass);
	}
	
	//--CREAR CUENTA USUARIO
	@Override
	@Transactional
	public void insert(User user) {
	    repository.save(user);
	}

	//VERIFICAR SI EL EMAIL EXISTE EN LA BD
	@Override
	@Transactional(readOnly = true)
	public boolean emailExists(String email) {
	    return repository.existsByEmail(email);
	}
	
	//--ACTUALIZAR CONTRASENA
	@Override 
	@Transactional
	public User updatePassword(String email, String pass) {    
	    User cuenta = repository.findByEmail(email);
	    if (cuenta != null) {
	        cuenta.setContrasena(pass);
	        return repository.save(cuenta);
	    }
	    return null;
	}
	
	//--VERIFICAR CONTRASENA
    @Override 
    @Transactional(readOnly = true)
    public boolean checkPassword(String email, String pass) {    
        User cuenta = repository.findByEmail(email);
        if (cuenta != null) {
            return cuenta.getContrasena().equals(pass);
        }
        return false;
    }
	
    //VERIFICAR SI EL USUARIOL EXISTE EN LA BD
  	@Override
  	@Transactional(readOnly = true)
  	public boolean existsById(Integer id) {
  	    return repository.existsById(id);
  	}
}