package pe.BoraBora.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "usuarios") 
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column()
	private String nombres;
	@Column()
	private String apellidos;
	@Column()
	private Integer docIdentidad;
	@Column() 
	private Integer telefono;
	@Column()
	private String email;
	@Column()
	private String contrasena ;
	
	public User() { }

	public User(String email, String contrasena) {
		super();
		this.email = email;
		this.contrasena = contrasena;
	}

	public User(Integer id, String nombres, String apellidos, Integer docIdentidad, Integer telefono, String email,
			String contrasena) {
		super();
		this.id = id;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.docIdentidad = docIdentidad;
		this.telefono = telefono;
		this.email = email;
		this.contrasena = contrasena;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Integer getDocIdentidad() {
		return docIdentidad;
	}

	public void setDocIdentidad(Integer docIdentidad) {
		this.docIdentidad = docIdentidad;
	}

	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}