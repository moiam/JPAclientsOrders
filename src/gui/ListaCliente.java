package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import gc.bd.BdOperaciones;
import gc.beans.Cliente;
import gui.ejecutarJF.EjecutarJF;
import gui.principal.Login;

public class ListaCliente extends JPanel {

	private JPanel contentPane;
    private JTable table_3;
    private JTextField tFClienteDNI;
    private JTextField tFLetrasApellido;
    private JButton btnBuscarporDNI;
    private JButton btnObtenerPorApellido;
    private JButton btnObtenerClientes;
    private DefaultTableModel model; 
    
    public ListaCliente() {
    	
    	setLayout(null);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBackground(Color.WHITE);
		contentPane.setBounds(0, 0, 672, 514);
		add(contentPane);
		
    	
		model = new DefaultTableModel();
		model.addColumn("DNI");
		model.addColumn("Nombre");
		model.addColumn("Apellido 1");
		model.addColumn("Apellido 2");
		model.addColumn("Edad");
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(0, 0, 672, 514);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		tFClienteDNI = new JTextField();
		tFClienteDNI.setFont(new Font("Geomanist", Font.PLAIN, 13));
		tFClienteDNI.setBounds(323, 104, 130, 26);
		panel_2.add(tFClienteDNI);
		tFClienteDNI.setColumns(10);
		
		btnBuscarporDNI = new JButton("Buscar");
		btnBuscarporDNI.setFont(new Font("Geomanist", Font.BOLD, 15));
		btnBuscarporDNI.setBounds(459, 103, 117, 29);
		panel_2.add(btnBuscarporDNI);
		
		btnObtenerPorApellido = new JButton("Buscar");
		btnObtenerPorApellido.setFont(new Font("Geomanist", Font.BOLD, 15));
		btnObtenerPorApellido.setBounds(459, 153, 117, 29);
		panel_2.add(btnObtenerPorApellido);
		
		tFLetrasApellido = new JTextField();
		tFLetrasApellido.setFont(new Font("Geomanist", Font.PLAIN, 13));
		tFLetrasApellido.setBounds(323, 154, 130, 26);
		panel_2.add(tFLetrasApellido);
		tFLetrasApellido.setColumns(10);
		
		btnObtenerClientes = new JButton("Obtener");
		btnObtenerClientes.setFont(new Font("Geomanist", Font.BOLD, 15));
		btnObtenerClientes.setBounds(323, 205, 117, 29);
		panel_2.add(btnObtenerClientes);
				
		JScrollPane scrollPane_1_1 = new JScrollPane();
		scrollPane_1_1.setBounds(69, 280, 542, 168);
		panel_2.add(scrollPane_1_1);
				
		table_3 = new JTable();
		scrollPane_1_1.setViewportView(table_3);
		table_3.setModel(model);
		
		JLabel lblNewLabel1 = new JLabel("");
		lblNewLabel1.setIcon(new ImageIcon(Login.class.getResource("/Imagenes/plano-vertical-estructura-geometrica-3.jpg")));
		lblNewLabel1.setBounds(318, 0, 354, 514);
		panel_2.add(lblNewLabel1);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 319, 514);
		panel_2.add(panel);
		panel.setBackground(new Color(173, 189, 220));
		panel.setForeground(new Color(151, 255, 255));
		panel.setLayout(null);
		
		JLabel lblClienteDNI = new JLabel("Encuentra Cliente por DNI");
		lblClienteDNI.setFont(new Font("Geomanist", Font.BOLD, 16));
		lblClienteDNI.setBounds(128, 109, 185, 16);
		panel.add(lblClienteDNI);
		
		JLabel lblObtenerClientes = new JLabel("Listado completo de clientes");
		lblObtenerClientes.setFont(new Font("Geomanist", Font.BOLD, 16));
		lblObtenerClientes.setBounds(112, 211, 203, 16);
		panel.add(lblObtenerClientes);
		
		JLabel lblObtenerPorApellido = new JLabel("Listado clientes por primer apellido");
		lblObtenerPorApellido.setBounds(69, 156, 246, 16);
		panel.add(lblObtenerPorApellido);
		lblObtenerPorApellido.setFont(new Font("Geomanist", Font.BOLD, 16));
		
		agregarListeners();
	}
	public void agregarListeners() {
		
		btnBuscarporDNI.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            buscarPorDNI();
	        }
	    });
		btnObtenerPorApellido.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            porApellido();
	        }
	    });
		btnObtenerClientes.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            obtenerClientes();
	        }
	    });
	}
	public void buscarPorDNI(){
		
		model.setRowCount(0);	
		BdOperaciones bdOperaciones = new BdOperaciones();
			bdOperaciones.abrirConexion();
		
		bdOperaciones.abrirTransaccion();
		
		String dni = tFClienteDNI.getText();
		
		if (dni.equals("")) {
			tFClienteDNI.setBackground(new Color(255, 0, 0, 100));  
			JOptionPane.showMessageDialog(null, "Debe introducir un DNI");
			
        } else {

			    Cliente cliente = bdOperaciones.consultaClientePorDni(dni); 

				
			    if(cliente.getNombre()!=null) {
			   
			        Object[] rowData = new Object[5];
			        rowData[0] = cliente.getDni();
			        rowData[1] = cliente.getNombre();
			        rowData[2] = cliente.getApellido1();
			        rowData[3] = cliente.getApellido2();
			        rowData[4] = cliente.getEdad();
			        model.addRow(rowData);
			    
			        tFClienteDNI.setBackground(new Color(0, 255, 0, 100));   

				    bdOperaciones.cerrarConexion();
			    }else {
			    	
			    	JOptionPane.showMessageDialog(null, "Cliente no encontrado", "Aviso", JOptionPane.WARNING_MESSAGE);
			    	
			    }
        	}
		
	}
		public void porApellido() {
			model.setRowCount(0);
			
			BdOperaciones bdOperaciones = new BdOperaciones();

				bdOperaciones.abrirConexion();
			bdOperaciones.abrirTransaccion();
			
			String apellido = tFLetrasApellido.getText();
			
			if (apellido.equals("")) {
				tFLetrasApellido.setBackground(new Color(255, 0, 0, 100));  
				JOptionPane.showMessageDialog(null, "Debe introducir algun dato");
				bdOperaciones.cerrarConexion();
	        } else {
	        	try {
					
				    List<Cliente> listClientes = bdOperaciones.consultaClientePorApellido1(apellido); 
				    
				    if (!listClientes.isEmpty()) {
					    for (Cliente clientes : listClientes) {
					    	
					        Object[] rowData = new Object[5];
					        rowData[0] = clientes.getDni();
					        rowData[1] = clientes.getNombre();
					        rowData[2] = clientes.getApellido1();
					        rowData[3] = clientes.getApellido2();
					        rowData[4] = clientes.getEdad();
					        model.addRow(rowData);
					    	}
				    	bdOperaciones.cerrarConexion();
				    
				    }else {
				    	     JOptionPane.showMessageDialog(null, "Cliente no encontrado", "Aviso", JOptionPane.WARNING_MESSAGE);
				        	bdOperaciones.cerrarConexion();
				        }
				    
	
				   
				}catch (Exception e) {
				    bdOperaciones.hacerRollback();
				    e.printStackTrace();
				} finally {
				    bdOperaciones.cerrarConexion();
				}
	        	
	        	tFLetrasApellido.setBackground(Color.white);
	        	
	        	}
		}
		public void obtenerClientes() {
			model.setRowCount(0);
			
			BdOperaciones bdOperaciones = new BdOperaciones();
		
				bdOperaciones.abrirConexion();
		
				bdOperaciones.abrirTransaccion();
			
			
			try {
			    List<Cliente> listClientes = bdOperaciones.listadoClientes();
			    for (Cliente clientes : listClientes) {
					Object[] rowData = new Object[5];
					rowData[0] = clientes.getDni();
					rowData[1] = clientes.getNombre();
					rowData[2] = clientes.getApellido1();
					rowData[3] = clientes.getApellido2();
					rowData[4] = clientes.getEdad();
					model.addRow(rowData);
			    }
			
			    bdOperaciones.hacerCommit();
			} catch (Exception e) {
			    bdOperaciones.hacerRollback();
			    e.printStackTrace();
			} finally {
			    bdOperaciones.cerrarConexion();
			}
			
		}


}
