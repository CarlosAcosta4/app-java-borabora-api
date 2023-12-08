package pe.BoraBora.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "compras")
public class Compra implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column
    private Double total;

    @Column
    private Double igv;

    @Column
    private Double subtotal;

    @Column
    private String metodopago;
    
    @DateTimeFormat(pattern="yyyy-MM-dd",iso=ISO.DATE)
	private LocalDate fcompra;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonBackReference  //-----
    private User user;

    @OneToMany(mappedBy = "compra")
    @JsonManagedReference  //-----
    private List<CompraProducto> compraProductos = new ArrayList<>();

	public Compra() {
	}
	
	public Compra(Integer id, Double total, Double igv, Double subtotal, String metodopago, LocalDate fcompra,
			User user, List<CompraProducto> compraProductos) {
		this.id = id;
		this.total = total;
		this.igv = igv;
		this.subtotal = subtotal;
		this.metodopago = metodopago;
		this.fcompra = fcompra;
		this.user = user;
		this.compraProductos = compraProductos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getIgv() {
		return igv;
	}

	public void setIgv(Double igv) {
		this.igv = igv;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public String getMetodopago() {
		return metodopago;
	}

	public void setMetodopago(String metodopago) {
		this.metodopago = metodopago;
	}

	public LocalDate getFcompra() {
		return fcompra;
	}

	public void setFcompra(LocalDate fcompra) {
		this.fcompra = fcompra;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<CompraProducto> getCompraProductos() {
		return compraProductos;
	}

	public void setCompraProductos(List<CompraProducto> compraProductos) {
		this.compraProductos = compraProductos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
