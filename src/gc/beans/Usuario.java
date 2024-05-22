package gc.beans;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



@Entity
public class Usuario implements Serializable {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	private String nombreUsuario;
	private String contrasena;
	
	@ManyToOne
	@JoinColumn(name="FK_rol")
	private Rol unRol;


	public Usuario() {
		super();
		// TODO Ap�ndice de constructor generado autom�ticamente
	}
	
	public Usuario(String nombreUsuario, String contrasena,Rol unRol) {
		super();
		
		
		this.nombreUsuario = nombreUsuario;
		this.contrasena = contrasena;
		this.unRol = unRol;
		
	}
	
    public Rol getUnRol() {
        return unRol;
    }

    public void setUnRol(Rol unRol) {
        this.unRol = unRol;
    }
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena ) {
		this.contrasena = contrasena;
	}


}
