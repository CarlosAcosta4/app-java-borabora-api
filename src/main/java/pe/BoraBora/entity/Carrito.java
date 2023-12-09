package pe.BoraBora.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity 
@Table(name = "carrito")
public class Carrito implements Serializable{
	
	private static final long serialVersionUID = 1L;
		
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne 
	@JoinColumn(name = "usuario_id", nullable = false)	
	private User user;

	@OneToMany(mappedBy = "carrito")
	@JsonManagedReference("carrito-prodCarrito") 
	private Collection<ProductoCarrito> carritoProductos = new ArrayList<>();
	
	
	public Carrito() {
	}

	public Carrito(Integer id, User user) {
		super();
		this.id = id;
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Collection<ProductoCarrito> getCarritoProductos() {
		return carritoProductos;
	}

	public void setCarritoProductos(Collection<ProductoCarrito> carritoProductos) {
		this.carritoProductos = carritoProductos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
