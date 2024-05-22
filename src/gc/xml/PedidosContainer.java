package gc.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import gc.beans.Pedido;

@XmlRootElement
public class PedidosContainer {
    private List<Pedido> pedidos;

    public PedidosContainer() {
        super();
    }

    public PedidosContainer(List<Pedido> pedidos) {
        super();
        this.pedidos = pedidos;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

}
