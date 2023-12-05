package pe.BoraBora.response;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;

public class ProductoResponse {
	
	private String message;
    private HttpStatus status;
    private Integer id; 
	private String nombre;	
	private String descripcion;
	private String marca;
	private Double precio;
	private Integer stock;
	private LocalDate fvencimiento;
	
	private Integer categoriaId;
	private String categoriaNombre;
	
	public ProductoResponse() {
	}

	public ProductoResponse(String message, HttpStatus status, Integer id, String nombre, String descripcion,
			String marca, Double precio, Integer stock, LocalDate fvencimiento, Integer categoriaId,
			String categoriaNombre) {
		this.message = message;
		this.status = status;
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.marca = marca;
		this.precio = precio;
		this.stock = stock;
		this.fvencimiento = fvencimiento;
		this.categoriaId = categoriaId;
		this.categoriaNombre = categoriaNombre;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public LocalDate getFvencimiento() {
		return fvencimiento;
	}

	public void setFvencimiento(LocalDate fvencimiento) {
		this.fvencimiento = fvencimiento;
	}

	public Integer getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Integer categoriaId) {
		this.categoriaId = categoriaId;
	}

	public String getCategoriaNombre() {
		return categoriaNombre;
	}

	public void setCategoriaNombre(String categoriaNombre) {
		this.categoriaNombre = categoriaNombre;
	}
}
