package gui.facturas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import gc.bd.BdOperaciones;
import gc.beans.Cliente;
import gc.beans.Pedido;

public class TablaFacturas2 extends JPanel {

    private JPanel contentPane;
    private BdOperaciones bdOperaciones;
    private DefaultTableModel model;
    private JTable table;
    private JComboBox<String> comboBox; // Cambié el nombre de la variable
    private JTextField textField;
    private JTextField textField_1;
    private JTextField subtotalField;
    private JTextField ivaField;
  
    private JPanel panel;
    private JTextField precioFinalField;
    

    public TablaFacturas2() {
        setLayout(null);
        contentPane = new JPanel();
        contentPane.setBounds(0, 0, 672, 514);
        contentPane.setLayout(null);
        contentPane.setBackground(Color.WHITE);
        add(contentPane);
        

        JPanel panel_2 = new JPanel();
        panel_2.setBackground(new Color(255, 255, 255));
        panel_2.setBounds(0, 0, 672, 514);
        contentPane.add(panel_2);
        panel_2.setLayout(null);
        

        
        JLabel label = new JLabel("Subtotal: ");
        label.setBounds(379, 300, 60, 16);
        panel_2.add(label);
        
        JLabel label_1 = new JLabel("IVA (16%): ");
        label_1.setBounds(379, 333, 66, 16);
        panel_2.add(label_1);
      
        JLabel label_2 = new JLabel("Precio Final: ");
        label_2.setBounds(365, 366, 80, 16);
        panel_2.add(label_2);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(33, 81, 611, 202);
        panel_2.add(scrollPane_1);

        table = new JTable();
        scrollPane_1.setViewportView(table);
        
		model = new DefaultTableModel();
		
		model.addColumn("Pedidos");
		model.addColumn("Importe(€)");
		table.setModel(model);
		

        table.setModel(model);


        // Aquí es donde debes agregar el JComboBox al panel_2
        comboBox = new JComboBox<>();
        comboBox.setBounds(150, 42, 160, 27);
        panel_2.add(comboBox);

        // Resto del código...

        cargarClientesEnComboBox(comboBox);
        
        JLabel lblNewLabel = new JLabel("Seleccione Cliente:");
        lblNewLabel.setBounds(33, 46, 118, 16);
        panel_2.add(lblNewLabel);
        
        textField = new JTextField();
        textField.setBounds(343, 41, 130, 26);
        panel_2.add(textField);
        textField.setColumns(10);
        
        textField_1 = new JTextField();
        textField_1.setEditable(false); 
        textField_1.setColumns(10);
        textField_1.setBounds(524, 41, 130, 26);
        panel_2.add(textField_1);
        
        JLabel lblNewLabel_1 = new JLabel("DNI");
        lblNewLabel_1.setBounds(317, 46, 24, 16);
        panel_2.add(lblNewLabel_1);
        
        JLabel lblNewLabel_1_1 = new JLabel("Fecha");
        lblNewLabel_1_1.setBounds(485, 46, 36, 16);
        panel_2.add(lblNewLabel_1_1);
        
        subtotalField = new JTextField(10);
        subtotalField.setBounds(450, 295, 147, 26);
        panel_2.add(subtotalField);
        ivaField = new JTextField(10);
        ivaField.setBounds(450, 328, 147, 26);
        panel_2.add(ivaField);
        precioFinalField = new JTextField();
        precioFinalField.setBounds(450, 361, 147, 26);
        panel_2.add(precioFinalField);
        precioFinalField.setColumns(10);

       
        

        subtotalField.setEditable(false);
        ivaField.setEditable(false);
        precioFinalField.setEditable(false);
        

       
        /*facturaCliente();*/
		
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el cliente seleccionado del JComboBox
                String clienteSeleccionado = (String) comboBox.getSelectedItem();
                if (clienteSeleccionado != null) {
                    // Obtener y mostrar los datos del cliente y los detalles del pedido
                    ClienteSeleccionado(clienteSeleccionado);
                    calcularTotal();
                }
            }
        });
        Timer timer = new Timer(1000, e -> actualizarFecha());
        timer.start();
        
        
    }
    private void calcularTotal() {
        double subtotal = 0;

        // Calcular el subtotal sumando los importes de la tabla
        for (int row = 0; row < table.getRowCount(); row++) {
            double importe = Double.parseDouble(table.getValueAt(row, 1).toString());
            subtotal += importe;
        }

        double iva = subtotal * 0.16; // IVA (16%)
        double precioFinal = subtotal + iva;

        // Formatear los valores
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String subtotalStr = decimalFormat.format(subtotal);
        String ivaStr = decimalFormat.format(iva);
        String precioFinalStr = decimalFormat.format(precioFinal);

        // Mostrar los valores en los campos correspondientes
        subtotalField.setText(subtotalStr);
        ivaField.setText(ivaStr);
        precioFinalField.setText(precioFinalStr);
    }
    private void actualizarFecha() {
        // Obtiene la fecha y la hora actual
        LocalDateTime fechaActual = LocalDateTime.now();

        // Define el formato de fecha que deseas, por ejemplo "dd/MM/yyyy HH:mm:ss"
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Formatea la fecha y hora actual como una cadena
        String fechaFormateada = fechaActual.format(formato);

        // Establece la fecha formateada en el TextField
        textField_1.setText(fechaFormateada);
    }
    /*	public void instaurarClientes() {
            BdOperaciones bdOperaciones = new BdOperaciones();
            bdOperaciones.abrirConexion();
            
        	// Llamamos al método para cargar los clientes en el JComboBox
        	
           
    		
    		
    	}*/


		public static void cargarClientesEnComboBox(JComboBox<String> comboBox) {
	        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();

	        try {
	            // Establece la conexión a la base de datos
	            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/practica", "root", "admin123");

	            // Define la consulta SQL para obtener los nombres completos de los clientes
	            String consulta = "SELECT CONCAT(nombre, ' ', apellido1, ' ', apellido2) AS nombre_completo FROM clientes";
	            PreparedStatement stmt = conn.prepareStatement(consulta);

	            // Ejecuta la consulta
	            ResultSet rs = stmt.executeQuery();

	            // Agrega los resultados al modelo del JComboBox
	            while (rs.next()) {
	                modelo.addElement(rs.getString("nombre_completo"));
	            }

	            // Cierra las conexiones
	            rs.close();
	            stmt.close();
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        // Asigna el modelo al JComboBox
	        comboBox.setModel(modelo);
	        
			
	    }

		
		
		public void ClienteSeleccionado(String nombreCliente) {
		    model.setRowCount(0);

		    try {
		        // Establecer la conexión a la base de datos usando try-with-resources
		        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/practica", "root", "admin123")) {
		            // Consulta SQL para obtener los datos del cliente
		            String consultaCliente = "SELECT dni, nombre, apellido1, apellido2 FROM clientes WHERE CONCAT(nombre, ' ', apellido1, ' ', apellido2) = ?";
		            try (PreparedStatement stmtCliente = conn.prepareStatement(consultaCliente)) {
		                stmtCliente.setString(1, nombreCliente);
		                try (ResultSet rsCliente = stmtCliente.executeQuery()) {
		                    // Mostrar los datos del cliente en la tabla
		                    while (rsCliente.next()) {
		                        String dni = rsCliente.getString("dni");
		                        textField.setText(dni);
		                    }
		                }
		            }

		            // Consulta SQL para obtener los detalles del pedido
		            String consultaPedido = "SELECT detalle_pedido, importe FROM pedidos WHERE dni = ?";
		            try (PreparedStatement stmtPedido = conn.prepareStatement(consultaPedido)) {
		                int idCliente = obtenerIdCliente(nombreCliente);
		                stmtPedido.setInt(1, idCliente);
		                try (ResultSet rsPedido = stmtPedido.executeQuery()) {
		                    // Mostrar los detalles del pedido en la tabla
		                    while (rsPedido.next()) {
		                        String detallePedido = rsPedido.getString("detalle_pedido");
		                        double importe = rsPedido.getDouble("importe");
		                        model.addRow(new Object[]{detallePedido, importe});
		                    }
		                }
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}

		
private int obtenerIdCliente(String nombreCliente) {
    int idCliente = -1; // Valor predeterminado en caso de que no se encuentre el cliente

    try {
        // Establecer la conexión a la base de datos
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/practica", "root", "admin123");

        // Consulta SQL para obtener el ID del cliente a partir de su nombre
        String consulta = "SELECT dni FROM clientes WHERE CONCAT(nombre, ' ', apellido1, ' ', apellido2) = ?";
        PreparedStatement stmt = conn.prepareStatement(consulta);
        stmt.setString(1, nombreCliente);
        ResultSet rs = stmt.executeQuery();

        // Verificar si se encontró el cliente
        if (rs.next()) {
            idCliente = rs.getInt("dni");
        }

        // Cerrar la consulta
        rs.close();
        stmt.close();

        // Cerrar la conexión a la base de datos
        conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return idCliente;
    // Aquí debes implementar la lógica para obtener el ID del cliente a partir de su nombre
    // Puedes hacer una consulta a la base de datos o utilizar alguna otra fuente de datos
    // En este ejemplo, simplemente devolvemos un valor fijo de 1

}
}
