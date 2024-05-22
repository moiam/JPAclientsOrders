package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gc.bd.BdOperaciones;
import gc.beans.Pedido;
import gui.ejecutarJF.EjecutarJF;
import gui.principal.Login;

public class ModifPedido extends JPanel {

	private JPanel contentPane;
	private JTextField tFDNI;
	private JLabel lblNPedido;
	private JTextField tFNPedido;
	private JTextField tFNewDetalle;
	private JLabel lblImporte;
	private JTextField tFImporte;
	private JButton btnModificarPedido;
	private JButton btnBuscar;
	
	public ModifPedido() {

    	setLayout(null);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBackground(Color.WHITE);
		contentPane.setBounds(0, 0, 672, 514);
		add(contentPane);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(0, 0, 672, 514);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		tFDNI = new JTextField();
		tFDNI.setFont(new Font("Geomanist", Font.PLAIN, 13));
		tFDNI.setBounds(326, 183, 130, 26);
		panel_2.add(tFDNI);
		tFDNI.setColumns(10);
		
		tFNPedido = new JTextField();
		tFNPedido.setFont(new Font("Geomanist", Font.PLAIN, 13));
		tFNPedido.setBounds(326, 216, 130, 26);
		panel_2.add(tFNPedido);
		tFNPedido.setColumns(10);
		
		tFNewDetalle = new JTextField();
		tFNewDetalle.setFont(new Font("Geomanist", Font.PLAIN, 13));
		tFNewDetalle.setBounds(326, 272, 130, 26);
		panel_2.add(tFNewDetalle);
		tFNewDetalle.setColumns(10);
		
		tFImporte = new JTextField();
		tFImporte.setFont(new Font("Geomanist", Font.PLAIN, 13));
		tFImporte.setBounds(326, 305, 130, 26);
		panel_2.add(tFImporte);
		tFImporte.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Geomanist", Font.PLAIN, 15));
		btnBuscar.setBounds(468, 201, 86, 29);
		panel_2.add(btnBuscar);
		
		btnModificarPedido = new JButton("Confirmar Modificación");
		btnModificarPedido.setFont(new Font("Geomanist", Font.BOLD, 15));
		btnModificarPedido.setBounds(414, 344, 174, 47);
		panel_2.add(btnModificarPedido);
		btnModificarPedido.setForeground(new Color(218, 180, 5));
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 319, 514);
		panel_2.add(panel);
		panel.setBackground(new Color(173, 189, 220));
		panel.setForeground(new Color(151, 255, 255));
		panel.setLayout(null);
		
		JLabel lblDNI = new JLabel("DNI del Cliente a modificar");
		lblDNI.setBounds(128, 191, 185, 16);
		panel.add(lblDNI);
		lblDNI.setFont(new Font("Geomanist", Font.BOLD, 16));
		
		JLabel lblNewDetalle = new JLabel("Nuevo Detalle Pedido");
		lblNewDetalle.setBounds(154, 280, 159, 16);
		panel.add(lblNewDetalle);
		lblNewDetalle.setFont(new Font("Geomanist", Font.BOLD, 16));
		
		lblImporte = new JLabel("Nuevo Importe");
		lblImporte.setBounds(194, 308, 119, 16);
		panel.add(lblImporte);
		lblImporte.setFont(new Font("Geomanist", Font.BOLD, 16));
		
		lblNPedido = new JLabel("NºPedido a modificar");
		lblNPedido.setBounds(163, 219, 150, 16);
		panel.add(lblNPedido);
		lblNPedido.setFont(new Font("Geomanist", Font.BOLD, 16));
		
		JLabel lblNewLabel1 = new JLabel("");
		lblNewLabel1.setIcon(new ImageIcon(Login.class.getResource("/Imagenes/plano-vertical-estructura-geometrica-3.jpg")));
		lblNewLabel1.setBounds(318, 0, 354, 514);
		panel_2.add(lblNewLabel1);
		
	      agregarListeners();
    }
	public void agregarListeners() {
		
		btnModificarPedido.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            modificarPedido();
	        }
	    });

	    btnBuscar.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            buscarPedido();
	        }
	    });
	}
	public void modificarPedido(){
    	BdOperaciones bdOperaciones = new BdOperaciones();
    	bdOperaciones.abrirConexion();
    	
    	String dni = tFDNI.getText();
        String nPedido = tFNPedido.getText();
        String newDetalle = tFNewDetalle.getText();
        String importe=tFImporte.getText();
      
        String nombreTemporal = tFNPedido.getText();
        String apellido1Temporal = tFNewDetalle.getText();
        String apellido2Temporal = tFImporte.getText();
        
        int newPedido=0;
        float nuevoImporte=0;
       
        boolean camposValidos = true;
        
        if (dni.isEmpty() || nPedido.isEmpty() || newDetalle.isEmpty() || importe.isEmpty()) {
        
        	tFNPedido.setBackground(new Color(255, 0, 0, 100));
        	tFNewDetalle.setBackground(new Color(255, 0, 0, 100));
        	tFImporte.setBackground(new Color(255, 0, 0, 100));
            camposValidos = false;
          
           
        } else {
       	 try {	newPedido = Integer.parseInt(tFNPedido.getText());
	            nuevoImporte = Float.parseFloat(tFImporte.getText());
	            
	        } catch (NumberFormatException ex) {
	        	JOptionPane.showMessageDialog(null, "El importe no es un número válido"); 
	        }

       	 	tFNewDetalle.setBackground(new Color(0, 255, 0, 100));  
       	 	tFImporte.setBackground(new Color(0, 255, 0, 100));  
            
        
            tFNewDetalle.setText("");
            tFImporte.setText("");
           
        }
					     
        
        if (camposValidos) {
            Pedido pedido = new Pedido(dni, newPedido, newDetalle, nuevoImporte);
            bdOperaciones.abrirTransaccion();
            bdOperaciones.modifPedido(pedido);
            bdOperaciones.hacerCommit();
            bdOperaciones.cerrarConexion();
        }else {
        	tFNPedido.setText(nombreTemporal);
        	tFNewDetalle.setText(apellido1Temporal);
        	tFImporte.setText(apellido2Temporal);
        }	 
		
	}
	public void buscarPedido() {
        
        BdOperaciones bdOperaciones = new BdOperaciones();
        bdOperaciones.abrirConexion();
        
        String dni = tFDNI.getText();
        String nPedido = tFNPedido.getText();

        if (dni.isEmpty() || nPedido.isEmpty()) {
            tFDNI.setBackground(new Color(255, 0, 0, 100)); 
            tFNPedido.setBackground(new Color(255, 0, 0, 100)); 
            JOptionPane.showMessageDialog(null, "Espacios incompletos");
        } else {
            int numPedido = Integer.parseInt(nPedido);
            Pedido pedido = bdOperaciones.consultaPedidoPorDniNumPedido(dni, numPedido);
            String dni1 = pedido.getDni();
            
            if (dni1 == null) {  
                        JOptionPane.showMessageDialog(null, "DNI o pedido incorrectos:");
                        tFNPedido.setBackground(new Color(255, 0, 0, 100)); 
                        tFNPedido.setText("");      
                        tFNPedido.setBackground(new Color(255, 0, 0, 100)); 
                        tFNPedido.setText("");    
                        tFNewDetalle.setText("");
            }else {
                    tFDNI.setBackground(new Color(0, 255, 0, 100));  
                    tFNPedido.setBackground(new Color(0, 255, 0, 100));  
                   
                    tFNewDetalle.setText(pedido.getDetallePedido());
                    tFImporte.setText(String.valueOf(pedido.getImporte()));

                    
                   
                    bdOperaciones.cerrarConexion();                      
        }
        }
		
	}
	
	}


