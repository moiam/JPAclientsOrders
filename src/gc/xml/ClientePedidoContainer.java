package gc.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import gc.beans.Cliente;
import gc.beans.Pedido;
@XmlRootElement
public class ClientePedidoContainer {
	    private List<Cliente> clientes;
	    private List<Pedido> pedidos;

	    public ClientePedidoContainer() {
	        super();
	    }

	    public ClientePedidoContainer(List<Cliente> clientes,List<Pedido> pedidos) {
	        super();
	        this.clientes = clientes;
	        this.pedidos = pedidos;
	    }

	    public List<Cliente> getCliente() {
	        return clientes;
	        
	    }
	    public List<Pedido> getPedido() {
	        return pedidos;
	        
	    }
	    public void setCliente(List<Cliente> clientes) {
	        this.clientes=clientes;
	        
	    }
	    public void  setPedido(List<Pedido> pedidos) {
	        this.pedidos=pedidos;
	        
	    }

}
