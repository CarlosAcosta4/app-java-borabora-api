package pe.BoraBora.model;

public class ProductoNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ProductoNotFoundException(Integer id) {
        super("No se pudo encontrar el producto con el ID: " + id);
    }
}