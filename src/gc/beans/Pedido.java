/*
 * Created on 25-abr-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gc.beans;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author Administrador
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Pedido {
	private String dni;
	private int numPedido;
	private String detallePedido;
    private float importe;
	public Pedido(){
		super();
	}
	public Pedido(String dni,int numPedido,String detallePedido, float importe)
	{
		super();
		this.dni=dni;
		this.numPedido=numPedido;
		this.detallePedido=detallePedido;
		this.importe=importe;
	}
	@XmlElement(name="DNI")
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	@XmlElement(name="NumPedido")
	public int getNumPedido() {
		return numPedido;
	}
	public void setNumPedido(int numPedido) {
		this.numPedido = numPedido;
	}
	@XmlElement(name="DetallePedido")
	public String getDetallePedido() {
		return detallePedido;
	}
	public void setDetallePedido(String detallePedido) {
		this.detallePedido = detallePedido;
	}
	@XmlElement(name="Importe")
	public float getImporte() {
		return importe;
	}
	public void setImporte(float importe) {
		this.importe=importe;
	}

	
}
