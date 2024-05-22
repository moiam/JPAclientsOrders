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

public class AltaPedido extends JPanel {

	private JPanel contentPane;
	private JTextField tFDni;
	private JTextField tFNPedido;
	private JTextField tFDetallePedido;
	private JTextField tFImporte;
	private JButton btnCargarAlta;
	public AltaPedido() {
		setLayout(null);
		
		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBackground(Color.WHITE);
		contentPane.setBounds(0, 0, 672, 514);
		add(contentPane);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(0, 0, 677, 514);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblImporte = new JLabel("Importe");
		lblImporte.setFont(new Font("Geomanist", Font.BOLD, 16));
		lblImporte.setBounds(248, 303, 61, 16);
		panel_2.add(lblImporte);
		
		tFImporte = new JTextField();
		tFImporte.setFont(new Font("Geomanist", Font.PLAIN, 13));
		tFImporte.setBounds(321, 298, 130, 26);
		panel_2.add(tFImporte);
		tFImporte.setColumns(10);
		
		JLabel lbl€ = new JLabel("€");
		lbl€.setFont(new Font("Geomanist", Font.BOLD, 16));
		lbl€.setBounds(455, 303, 13, 16);
		panel_2.add(lbl€);
		
		tFDetallePedido = new JTextField();
		tFDetallePedido.setFont(new Font("Geomanist", Font.PLAIN, 13));
		tFDetallePedido.setBounds(321, 250, 130, 26);
		panel_2.add(tFDetallePedido);
		tFDetallePedido.setColumns(10);
		
		JLabel lblDetallePedido = new JLabel("Detalle del pedido");
		lblDetallePedido.setFont(new Font("Geomanist", Font.BOLD, 16));
		lblDetallePedido.setBounds(179, 255, 130, 16);
		panel_2.add(lblDetallePedido);
		
		JLabel lblNpedido = new JLabel("NºPedido");
		lblNpedido.setFont(new Font("Geomanist", Font.BOLD, 16));
		lblNpedido.setBounds(241, 227, 68, 16);
		panel_2.add(lblNpedido);
		
		tFNPedido = new JTextField();
		tFNPedido.setFont(new Font("Geomanist", Font.PLAIN, 13));
		tFNPedido.setBounds(321, 222, 130, 26);
		panel_2.add(tFNPedido);
		tFNPedido.setColumns(10);
		
		tFDni = new JTextField();
		tFDni.setFont(new Font("Geomanist", Font.PLAIN, 13));
		tFDni.setBounds(321, 194, 130, 26);
		panel_2.add(tFDni);
		tFDni.setColumns(10);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setFont(new Font("Geomanist", Font.BOLD, 16));
		lblDni.setBounds(284, 199, 25, 16);
		panel_2.add(lblDni);
		
		btnCargarAlta = new JButton("Cargar Alta");
		btnCargarAlta.setFont(new Font("Geomanist", Font.BOLD, 17));
		btnCargarAlta.setBounds(334, 341, 117, 29);
		panel_2.add(btnCargarAlta);
		
		JLabel lblNewLabel1 = new JLabel("");
		lblNewLabel1.setIcon(new ImageIcon(Login.class.getResource("/Imagenes/plano-vertical-estructura-geometrica-3.jpg")));
		lblNewLabel1.setBounds(318, 0, 353, 514);
		panel_2.add(lblNewLabel1);

		agregarListeners();
		
	}
	public void agregarListeners() {
		
		btnCargarAlta.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            cargarAlta();
	        }
	    });
	}
	public void cargarAlta() {
    	
        String dni = tFDni.getText();
        String  numeroPedido = tFNPedido.getText();
        String detallePedido = tFDetallePedido.getText();
        String importe = tFImporte.getText();
        
        BdOperaciones bdOperaciones = new BdOperaciones();
        bdOperaciones.abrirConexion();
        
        int nPedido=0;
        float importes = 0;
        
        if (dni.isEmpty() || numeroPedido.isEmpty()||detallePedido.isEmpty()) {
        	if(dni.isEmpty()){
        		 tFDni.setBackground(new Color(255, 0, 0, 100));
        	}
        	if(numeroPedido.isEmpty()) {
        		tFNPedido.setBackground(new Color(255, 0, 0, 100));
        	}
        	if(detallePedido.isEmpty()) {
        		 tFDetallePedido.setBackground(new Color(255, 0, 0, 100));
        	}
           
        } else {
        
        try {
            nPedido = Integer.parseInt(numeroPedido);        
            
        } catch (NumberFormatException ex) {
            tFNPedido.setBackground(new Color(255, 0, 0, 100));
            JOptionPane.showMessageDialog(null, "NºPedido no es un número entero válido");
            return;
        }
        try {
            importes = Float.parseFloat(importe);
            
        } catch (NumberFormatException ex) {
            tFNPedido.setBackground(new Color(255, 0, 0, 100));
            JOptionPane.showMessageDialog(null, "Importe no es un número válido");
            return;
        }
      
        if(!bdOperaciones.verificarClienteExistente(dni)){
        	JOptionPane.showMessageDialog(null, "No se puede dar de alta el pedido ya que el cliente no existe existe en la base de datos");
        	bdOperaciones.cerrarConexion();
        }else if (bdOperaciones.verificarPedidoExistente(dni, nPedido)) {
            JOptionPane.showMessageDialog(null, "El Cliente y el número de pedido ya existen en la base de datos");
            bdOperaciones.cerrarConexion();
        }else {
            // Lógica para guardar los datos en la base de datos
            Pedido nuevoPedido = new Pedido(dni, nPedido, detallePedido,importes);
            bdOperaciones.abrirTransaccion();
            bdOperaciones.altaPedido(nuevoPedido);
            bdOperaciones.hacerCommit();
            bdOperaciones.cerrarConexion();
            tFDni.setBackground(new Color(0, 255, 0, 100));   
        	tFNPedido.setBackground(new Color(0, 255, 0, 100));   
        	tFDetallePedido.setBackground(new Color(0, 255, 0, 100));   
        	tFImporte.setBackground(new Color(0, 255, 0, 100));   
            JOptionPane.showMessageDialog(null, "Pedido Dado de alta satisfactoriamente");
            tFDni.setBackground(Color.white);
        	tFNPedido.setBackground(Color.white);
        	tFDetallePedido.setBackground(Color.white);
        	tFImporte.setBackground(Color.white);
        	tFDni.setText("");
        	tFNPedido.setText("");
        	tFDetallePedido.setText("");
        	tFImporte.setText("");
        	
        }
        
    }
    }
}
