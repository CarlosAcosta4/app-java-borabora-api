package pe.BoraBora.request;

import java.time.LocalDate;
import java.util.List;

import pe.BoraBora.entity.CompraProducto;


public class CompraRequest {

    private Double total;

    private Double igv;

    private Double subtotal;

    private String metodopago;

	private LocalDate fcompra;
	
	private Integer userId;
	
	private List<CompraProducto> productos;

	public CompraRequest(Double total, Double igv, Double subtotal, String metodopago, LocalDate fcompra,
			Integer userId, List<CompraProducto> productos) {
		super();
		this.total = total;
		this.igv = igv;
		this.subtotal = subtotal;
		this.metodopago = metodopago;
		this.fcompra = fcompra;
		this.userId = userId;
		this.productos = productos;
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public List<CompraProducto> getProductos() {
		return productos;
	}

	public void setProductos(List<CompraProducto> productos) {
		this.productos = productos;
	}
}
