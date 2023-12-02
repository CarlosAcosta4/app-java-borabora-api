package pe.BoraBora.model;

import org.springframework.http.HttpStatus;

public class ApiResponse {
	
    private String message;
    private HttpStatus status;
    private Object data;

    public ApiResponse(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public ApiResponse(String message, HttpStatus status, Object data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}

