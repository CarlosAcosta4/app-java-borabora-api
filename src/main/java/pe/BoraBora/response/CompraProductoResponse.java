package pe.BoraBora.response;

public class CompraProductoResponse {
	
    private Integer id;
    private Integer compraId;
    private Integer productoId;
    private int cantidad;

    public CompraProductoResponse() {
    }

    public CompraProductoResponse(Integer id, Integer compraId, Integer productoId, int cantidad) {
        this.id = id;
        this.compraId = compraId;
        this.productoId = productoId;
        this.cantidad = cantidad;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCompraId() {
		return compraId;
	}

	public void setCompraId(Integer compraId) {
		this.compraId = compraId;
	}

	public Integer getProductoId() {
		return productoId;
	}

	public void setProductoId(Integer productoId) {
		this.productoId = productoId;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}
