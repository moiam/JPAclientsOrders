package gc.xml;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

public class ExportadorXML {

    public void exportarClientes(ClientesContainer container, String nombreArchivo) {
        try {
            File archivo = new File(nombreArchivo);

            JAXBContext jaxbContext = JAXBContext.newInstance(ClientesContainer.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(container, archivo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void exportarPedidos(PedidosContainer container, String nombreArchivo) {
        try {
            File archivo = new File(nombreArchivo);

            JAXBContext jaxbContext = JAXBContext.newInstance(PedidosContainer.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(container, archivo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void exportarTodo(ClientePedidoContainer container, String nombreArchivo) {
    	 try {
             File archivo = new File(nombreArchivo);

             JAXBContext jaxbContext = JAXBContext.newInstance(ClientePedidoContainer.class);
             Marshaller marshaller = jaxbContext.createMarshaller();
             marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
             marshaller.marshal(container, archivo);
         } catch (Exception e) {
             e.printStackTrace();
         }
    	
    }
    
}