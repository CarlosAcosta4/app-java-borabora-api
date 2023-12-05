package pe.BoraBora.response;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;


public class CompraResponse {
	
	private String message;
    private HttpStatus status;
    
    private Integer userId;
    private Integer id;
    private Double total;
    private Double igv;
    private Double subtotal;
    private String metodopago;
    private LocalDate fcompra;
    
	public CompraResponse() {
	}

	public CompraResponse(String message, HttpStatus status, Integer userId, Integer id, Double total, Double igv,
			Double subtotal, String metodopago, LocalDate fcompra) {
		this.message = message;
		this.status = status;
		this.userId = userId;
		this.id = id;
		this.total = total;
		this.igv = igv;
		this.subtotal = subtotal;
		this.metodopago = metodopago;
		this.fcompra = fcompra;
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


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
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
}
