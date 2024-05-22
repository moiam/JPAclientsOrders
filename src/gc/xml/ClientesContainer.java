package gc.xml;

import gc.beans.Cliente;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ClientesContainer {
    private List<Cliente> clientes;

    public ClientesContainer() {
        super();
    }

    public ClientesContainer(List<Cliente> clientes) {
        super();
        this.clientes = clientes;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
}
