package gui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import gc.bd.BdOperaciones;
import gc.beans.Pedido;
import gui.ejecutarJF.EjecutarJF;
import gui.principal.Login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class BajaPedido extends JPanel {

	
	private JPanel contentPane;
	private JTextField tFDniBaja;
	private JTextField tFNPedido;
	private JButton btnDarBaja;

	public BajaPedido() {
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
		
		tFDniBaja = new JTextField();
		tFDniBaja.setFont(new Font("Geomanist", Font.PLAIN, 13));
		tFDniBaja.setBounds(276, 208, 180, 26);
		panel_2.add(tFDniBaja);
		tFDniBaja.setColumns(10);
		
		tFNPedido = new JTextField();
		tFNPedido.setFont(new Font("Geomanist", Font.PLAIN, 13));
		tFNPedido.setBounds(276, 246, 180, 26);
		panel_2.add(tFNPedido);
		tFNPedido.setColumns(10);
		
		btnDarBaja = new JButton("Dar de Baja");
		btnDarBaja.setFont(new Font("Geomanist", Font.BOLD, 15));
		btnDarBaja.setBounds(371, 284, 133, 38);
		panel_2.add(btnDarBaja);
		btnDarBaja.setForeground(UIManager.getColor("InternalFrame.borderShadow"));
		
		JLabel lblNewLabel1 = new JLabel("");
		lblNewLabel1.setIcon(new ImageIcon(Login.class.getResource("/Imagenes/plano-vertical-estructura-geometrica-3.jpg")));
		lblNewLabel1.setBounds(318, 0, 354, 514);
		panel_2.add(lblNewLabel1);
		
		JLabel lblNpedido = new JLabel("NºPedido");
		lblNpedido.setBounds(194, 245, 70, 26);
		panel_2.add(lblNpedido);
		lblNpedido.setFont(new Font("Geomanist", Font.BOLD, 16));
		lblNpedido.setBackground(Color.WHITE);
		
		JLabel lblDniBaja = new JLabel("DNI Cliente");
		lblDniBaja.setBounds(184, 207, 80, 26);
		panel_2.add(lblDniBaja);
		lblDniBaja.setFont(new Font("Geomanist", Font.BOLD, 16));
		lblDniBaja.setBackground(new Color(255, 255, 255));
		
		agregarListeners();
		
	}
	public void agregarListeners() {
		
		btnDarBaja.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            darBaja();
	        }
	    });
	}
	public void darBaja(){
		
		BdOperaciones bdOperaciones = new BdOperaciones();
		bdOperaciones.abrirConexion();
		String dni = tFDniBaja.getText();
		String numeroPedido = tFNPedido.getText();
		 int nPedido=0;
		
	        
	        
	        if (dni.isEmpty() && numeroPedido.isEmpty()) {
	        	tFDniBaja.setBackground(new Color(255, 0, 0, 100));
	        	tFNPedido.setBackground(new Color(255, 0, 0, 100));
	        }else if(dni.isEmpty()){
	        	tFDniBaja.setBackground(new Color(255, 0, 0, 100));
	        	tFNPedido.setBackground(new Color(0, 255, 0, 100));   
	        }
	        else if(numeroPedido.isEmpty()) {
	        		tFNPedido.setBackground(new Color(255, 0, 0, 100));
	        		tFDniBaja.setBackground(new Color(0, 255, 0, 100));   

	  		           
	        } else {
	            try {
		            nPedido = Integer.parseInt(numeroPedido);
		            
		        } catch (NumberFormatException ex) {
		            tFNPedido.setBackground(new Color(255, 0, 0, 100));
		            JOptionPane.showMessageDialog(null, "NºPedido no es un número entero válido");
		            return;
		        }
	            if (bdOperaciones.verificarPedidoExistente(dni, nPedido)) {
	            
	            	bdOperaciones.abrirTransaccion();
		        	bdOperaciones.bajaPedido(dni,nPedido);
		        	bdOperaciones.hacerCommit();
		        	bdOperaciones.cerrarConexion();
		        	
		        	tFDniBaja.setBackground(new Color(0, 255, 0, 100));   
			        tFNPedido.setBackground(new Color(0, 255, 0, 100));   
		            JOptionPane.showMessageDialog(null, "Pedido Dado de baja satisfactoriamente");
		            tFDniBaja.setBackground(Color.white);
		        	tFNPedido.setBackground(Color.white);
		        	tFDniBaja.setText("");
		        	tFNPedido.setText("");
		           
		        } else {
		          
		        	 JOptionPane.showMessageDialog(null, "El DNI no se asocia con este Nº de pedido en la base de datos");
		        	 tFDniBaja.setBackground(new Color(255, 0, 0, 100)); 
		        	 tFNPedido.setBackground(new Color(255, 0, 0, 100)); 
		        	bdOperaciones.cerrarConexion();
		      
		        }
	        
	        
	        }
		
	}
	}


