package gc.beans;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.swing.JPanel;
import javax.xml.bind.annotation.XmlElement;

@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String dni;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private int edad;
	
	
	
	private JPanel panelActual;
	
	

	public Cliente() {
		super();
		// TODO Ap�ndice de constructor generado autom�ticamente
	}
	
	public Cliente(String dni, String nombre, String apellido1,
			String apellido2, int edad) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.edad = edad;
	}
	
	
	
	
	
	public Cliente(JPanel panel) {
		super();
		this.panelActual=panel; 
	}
	
	
	
	
	
	@XmlElement(name="Apellido1")
	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	@XmlElement(name="Apellido2")
	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	@XmlElement(name="Dni")
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}
	@XmlElement(name="Edad")
	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}
	@XmlElement(name="Nombre")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
	
	public JPanel getPanel() {
		return panelActual;
	}
	public void setPanel(JPanel panel) {
		this.panelActual=panel;
	}
	
	
	
}

