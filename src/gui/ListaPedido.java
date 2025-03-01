package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
import gc.beans.Pedido;
import gui.ejecutarJF.EjecutarJF;
import gui.principal.Login;

public class ListaPedido extends JPanel {
	
	private JPanel contentPane;
	private JTable table;
	private JTextField tFClienteDni;
	private JTextField tFNPedido;
	private JLabel lblNewLabel1;
	private JButton btnBuscarporDNI;
	private JButton btnObtenerClientes;
	private DefaultTableModel model;
	
	private String placeholder = "Introduce DNI";
	private String placeholder2 = "NºPedido";
	
	public ListaPedido() {
		setLayout(null);
		
		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBackground(Color.WHITE);
		contentPane.setBounds(0, 0, 672, 514);
		add(contentPane);
		
		model = new DefaultTableModel();
		model.addColumn("DNI");
		model.addColumn("NºPedido");
		model.addColumn("Detalle del pedido");
		model.addColumn("Importe(€)");
		

		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(0, 0, 672, 514);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(69, 280, 542, 168);
		panel_2.add(scrollPane_1);
						
		table = new JTable();
		scrollPane_1.setViewportView(table);
		table.setModel(model);
								
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 319, 514);
		panel_2.add(panel);
		panel.setBackground(new Color(173, 189, 220));
		panel.setForeground(new Color(151, 255, 255));
		panel.setLayout(null);
						
		JLabel lblObtenerClientes = new JLabel("Listado completo de Pedidos");
		lblObtenerClientes.setFont(new Font("Geomanist", Font.BOLD, 16));
		lblObtenerClientes.setBounds(106, 218, 207, 16);
		panel.add(lblObtenerClientes);
						
		JLabel lblClienteDNI = new JLabel("Encuentra por DNI y NºPedido");
		lblClienteDNI.setFont(new Font("Geomanist", Font.BOLD, 16));
		lblClienteDNI.setBounds(101, 151, 212, 16);
		panel.add(lblClienteDNI);
						
		tFNPedido = new JTextField();
		tFNPedido.setBounds(461, 145, 66, 26);
		panel_2.add(tFNPedido);
		tFNPedido.setFont(new Font("Geomanist", Font.ITALIC, 12));
		tFNPedido.setColumns(10);
				
		tFClienteDni =  new JTextField();
		tFClienteDni.setBounds(331, 145, 120, 26);
		panel_2.add(tFClienteDni);
		tFClienteDni.setFont(new Font("Geomanist", Font.ITALIC, 12));
		tFClienteDni.setColumns(10);
		
				
		btnBuscarporDNI = new JButton("Buscar");
		btnBuscarporDNI.setFont(new Font("Geomanist", Font.BOLD, 15));
		btnBuscarporDNI.setBounds(533, 144, 117, 29);
		panel_2.add(btnBuscarporDNI);
		
		
		btnObtenerClientes = new JButton("Obtener");
		btnObtenerClientes.setFont(new Font("Geomanist", Font.BOLD, 15));
		btnObtenerClientes.setBounds(331, 211, 117, 29);
		panel_2.add(btnObtenerClientes);
		
		lblNewLabel1 = new JLabel("");
		lblNewLabel1.setIcon(new ImageIcon(Login.class.getResource("/Imagenes/plano-vertical-estructura-geometrica-3.jpg")));
		lblNewLabel1.setBounds(318, 0, 354, 514);
		panel_2.add(lblNewLabel1);
		
		agregarListeners();
		
		tFClienteDni.addFocusListener(new FocusListener() {
	          
            public void focusGained(FocusEvent e) {
                // Borrar el texto de marcador de posición cuando el usuario haga clic en el TextField
                if (tFClienteDni.getText().equals(placeholder)) {
                	tFClienteDni.setText("");
                }
            }
            
            public void focusLost(FocusEvent e) {
                // Restaurar el texto de marcador de posición si el TextField está vacío
                if (tFClienteDni.getText().isEmpty()) {
                	tFClienteDni.setText(placeholder);
                }
            }
        });
				tFNPedido.addFocusListener(new FocusListener() {
			          
		            public void focusGained(FocusEvent e) {
		                // Borrar el texto de marcador de posición cuando el usuario haga clic en el TextField
		                if (tFNPedido.getText().equals(placeholder2)) {
		                	tFNPedido.setText("");
		                }
		            }
		            
		      
		            public void focusLost(FocusEvent e) {
		                // Restaurar el texto de marcador de posición si el TextField está vacío
		                if (tFNPedido.getText().isEmpty()) {
		                	tFNPedido.setText(placeholder2);
		                }
		            }
		        });
}
	
	public void agregarListeners() {
		
		btnBuscarporDNI.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            buscarPorDNI();
	        }
	    });
		btnObtenerClientes.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            obtenerClientes();
	        }
	    });
	}
	
	public void buscarPorDNI() {
		model.setRowCount(0);
		String dni = tFClienteDni.getText();
		String pedido = tFNPedido.getText();
		
		BdOperaciones bdOperaciones = new BdOperaciones();
		bdOperaciones.abrirConexion();
		if (dni.isEmpty()||dni.equals(placeholder)) {
			
			tFClienteDni.setBackground(new Color(255, 0, 0, 100)); 
			JOptionPane.showMessageDialog(null, "Debe introducir un DNI");
        } else if (pedido.isEmpty()||pedido.equals(placeholder2)){
        	JOptionPane.showMessageDialog(null,"Debe introducir NºdePedido");
        	
        }
        else {
        
	
        	int numPedido = Integer.parseInt(pedido);
		try {	
				
		    Pedido pedidoDniNum = bdOperaciones.consultaPedidoPorDniNumPedido(dni,numPedido);
		   
		    if(bdOperaciones.verificarPedidoExistente(dni, numPedido)) {
        
		    	 Object[] rowData = new Object[4];
			        rowData[0] = pedidoDniNum.getDni();
			        rowData[1] = pedidoDniNum.getNumPedido();
			        rowData[2] = pedidoDniNum.getDetallePedido();
			        rowData[3] = pedidoDniNum.getImporte();
			        model.addRow(rowData);
	            
	            tFClienteDni.setBackground(new Color(0, 255, 0, 100)); 
	        	tFNPedido.setBackground(new Color(0, 255, 0, 100)); 
	        	tFClienteDni.setText("");
	        	tFNPedido.setText("");
		    
		     } else {
	          
	        	 JOptionPane.showMessageDialog(null, "El DNI no se asocia con este Nº de pedido en la base de datos");
	        	 tFClienteDni.setBackground(new Color(255, 0, 0, 100)); 
	        	 tFNPedido.setBackground(new Color(255, 0, 0, 100)); 
	        	
	        }

		} catch (Exception e) {
		    bdOperaciones.hacerRollback();
		    e.printStackTrace();
		} finally {
		    bdOperaciones.cerrarConexion();
		}	
		
	}
		
		
	}
	public void obtenerClientes() {
		model.setRowCount(0);
		
		BdOperaciones bdOperaciones = new BdOperaciones();

		bdOperaciones.abrirConexion();
		bdOperaciones.abrirTransaccion();
		
		
		
		try {
		List<Pedido> listPedidos = bdOperaciones.listadoPedidos(); // Llamada a método para obtener la lista de clientes
		
		for (Pedido pedidos : listPedidos) {
		    Object[] rowData = new Object[4];
		    rowData[0] = pedidos.getDni();
		    rowData[1] = pedidos.getNumPedido();
		    rowData[2] = pedidos.getDetallePedido();
		    rowData[3] = pedidos.getImporte();
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
