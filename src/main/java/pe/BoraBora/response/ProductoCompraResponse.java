package pe.BoraBora.response;

import org.springframework.http.HttpStatus;

public class ProductoCompraResponse {

	private String message;
    private HttpStatus status; 
    private Integer idCompra;
    private Integer cantidad;
    private Integer userId;
    private Integer idProducto;
    private String nombre;
    private String marca;
    private Double precio;
    private String imagen;
    
	public ProductoCompraResponse() {
	}

	public ProductoCompraResponse(String message, HttpStatus status, Integer idCompra, Integer cantidad,
			Integer userId, Integer idProducto, String nombre, String marca, Double precio, String imagen) {
		super();
		this.message = message;
		this.status = status;
		this.idCompra = idCompra;
		this.cantidad = cantidad;
		this.userId = userId;
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.marca = marca;
		this.precio = precio;
		this.imagen = imagen;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public Integer getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(Integer idCompra) {
		this.idCompra = idCompra;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	} 
}
