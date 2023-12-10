package pe.BoraBora.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.BoraBora.entity.Compra;
import pe.BoraBora.entity.CompraProducto;
import pe.BoraBora.entity.Producto;
import pe.BoraBora.entity.User;
import pe.BoraBora.model.ProductoNotFoundException;
import pe.BoraBora.repository.CompraProductoRepository;
import pe.BoraBora.repository.CompraRepository;
import pe.BoraBora.repository.ProductoRepository;


@Service
public class CompraServiceImpl implements CompraService{
	
	@Autowired
	private CompraRepository repository;
	
	@Autowired
	private ProductoRepository prodRepository;
	
	@Autowired
	private CompraProductoRepository compraProdRepository;
	
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
	
	@Override 
    @Transactional(readOnly=true)
    public Compra getCompraById(Integer compraId) {
        return repository.findById(compraId).orElse(null);
    }
	
	@Override
	@Transactional
	public Compra crearCompra(User usuario, List<CompraProducto> productos, String metodopago, LocalDate fcompra) {
	    double subtotal = 0;
	    for (CompraProducto producto : productos) {
	        Optional<Producto> productoOpt = prodRepository.findById(producto.getProducto().getId());
	        if (productoOpt.isPresent()) {
	            Producto productoExistente = productoOpt.get();
	            double precio = productoExistente.getPrecio();
	            subtotal += precio * producto.getCantidad();
	            productoExistente.setStock(productoExistente.getStock() - producto.getCantidad()); // Reduce el stock del producto
	            prodRepository.save(productoExistente); // Guarda los cambios en el producto
	        } else {
	            throw new ProductoNotFoundException(producto.getProducto().getId()); // Lanza la excepción cuando el producto no se encuentra
	        }
	    }
        double igv = subtotal * 0.18;
        double total = subtotal + igv;
        Compra compra = new Compra(total, igv, subtotal, metodopago, fcompra, usuario, productos);
        Compra compraGuardada = repository.save(compra);
        for (CompraProducto producto : productos) {
            producto.setCompra(compraGuardada); // Establece la compra en cada CompraProducto
            compraProdRepository.save(producto); // Guarda cada CompraProducto en la base de datos
        }
        return compraGuardada;
    }
}

