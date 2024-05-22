package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;

import gc.bd.BdOperaciones;
import gc.beans.Cliente;
import gui.principal.Login;

public class AltaCliente extends JPanel {

		private JPanel contentPane;
	private JTextField tFDNI;
	private JTextField tFNombre;
	private JTextField tFApellido1;
	private JTextField tFApellido2;
	private JTextField tFEdad;
	private JButton btnCargarAlta;
	private JPanel panel_2;
	private JPanel panel;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	public AltaCliente() {
		setLayout(null);
		
		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBackground(Color.WHITE);
		contentPane.setBounds(0, 0, 692, 514);
		add(contentPane);
		
		panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(0, 0, 672, 514);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		btnCargarAlta = new JButton("Cargar Alta");
		btnCargarAlta.setFont(new Font("Geomanist", Font.PLAIN, 17));
		btnCargarAlta.setBounds(323, 318, 117, 29);
		panel_2.add(btnCargarAlta);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Edad");
		lblNewLabel_1_1_1_1.setFont(new Font("Geomanist", Font.BOLD, 16));
		lblNewLabel_1_1_1_1.setBounds(204, 286, 36, 16);
		panel_2.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Segundo apellido");
		lblNewLabel_1_1_1.setFont(new Font("Geomanist", Font.BOLD, 16));
		lblNewLabel_1_1_1.setBounds(115, 258, 125, 16);
		panel_2.add(lblNewLabel_1_1_1);
		
		
		JLabel lblNewLabel_1_1 = new JLabel("Primer apellido");
		lblNewLabel_1_1.setFont(new Font("Geomanist", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(136, 230, 104, 16);
		panel_2.add(lblNewLabel_1_1);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Geomanist", Font.BOLD, 16));
		lblNombre.setBounds(179, 202, 61, 16);
		panel_2.add(lblNombre);
		
		JLabel lblNewLabel = new JLabel("DNI");
		lblNewLabel.setFont(new Font("Geomanist", Font.BOLD, 16));
		lblNewLabel.setBounds(215, 174, 25, 16);
		panel_2.add(lblNewLabel);
		
		tFDNI = new JTextField();
		tFDNI.setFont(new Font("Geomanist", Font.PLAIN, 13));
		tFDNI.setBounds(261, 168, 179, 26);
		panel_2.add(tFDNI);
		tFDNI.setColumns(10);
		
		tFNombre = new JTextField();
		tFNombre.setFont(new Font("Geomanist", Font.PLAIN, 13));
		tFNombre.setBounds(261, 196, 179, 26);
		panel_2.add(tFNombre);
		tFNombre.setColumns(10);
		
		tFApellido1 = new JTextField();
		tFApellido1.setFont(new Font("Geomanist", Font.PLAIN, 13));
		tFApellido1.setBounds(261, 224, 179, 26);
		panel_2.add(tFApellido1);
		tFApellido1.setColumns(10);
		
		tFApellido2 = new JTextField();
		tFApellido2.setFont(new Font("Geomanist", Font.PLAIN, 13));
		tFApellido2.setBounds(261, 252, 179, 26);
		panel_2.add(tFApellido2);
		tFApellido2.setColumns(10);
		
		tFEdad = new JTextField();
		tFEdad.setFont(new Font("Geomanist", Font.PLAIN, 13));
		tFEdad.setBounds(261, 280, 179, 26);
		panel_2.add(tFEdad);
		tFEdad.setColumns(10);
		
		JLabel lblNewLabel1 = new JLabel("");
		lblNewLabel1.setBounds(320, 0, 672, 514);
		panel_2.add(lblNewLabel1);
		lblNewLabel1.setIcon(new ImageIcon(Login.class.getResource("/Imagenes/plano-vertical-estructura-geometrica-3.jpg")));
		
	
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
        String dni = tFDNI.getText();
        String nombre = tFNombre.getText();
        String apellido1 = tFApellido1.getText();
        String apellido2 = tFApellido2.getText();
        
		BdOperaciones bdOperaciones = new BdOperaciones();
		bdOperaciones.abrirConexion();
        
        boolean camposValidos = true;
        int edad = 0;
        try {
            edad = Integer.parseInt(tFEdad.getText());
        } catch (NumberFormatException ex) {
        	tFEdad.setBackground(new Color(255, 0, 0, 100));
        	JOptionPane.showMessageDialog(null, "La edad no es un número válido"); 
        	camposValidos=false;
        }

        
        if (dni.isEmpty() || nombre.isEmpty() || apellido1.isEmpty() || apellido2.isEmpty()) {
        	

        	tFEdad.setBackground(new Color(255, 0, 0, 100));
            tFNombre.setBackground(new Color(255, 0, 0, 100));
            tFApellido1.setBackground(new Color(255, 0, 0, 100));
            tFApellido2.setBackground(new Color(255, 0, 0, 100));
        } else {
    
        if (camposValidos) {
       	if(bdOperaciones.verificarClienteExistente(dni)) {
       		JOptionPane.showMessageDialog(null,"Cliente ya existente");
            
      
       	}else {
       	 Cliente nuevoCliente = new Cliente(dni, nombre, apellido1, apellido2, edad);
            bdOperaciones.abrirTransaccion();
            bdOperaciones.altaCliente(nuevoCliente);
            bdOperaciones.hacerCommit();
            bdOperaciones.cerrarConexion();
            tFDNI.setBackground(new Color(0, 255, 0, 100));
            tFNombre.setBackground(new Color(0, 255, 0, 100));
            tFApellido1.setBackground(new Color(0, 255, 0, 100));
            tFApellido2.setBackground(new Color(0, 255, 0, 100));
            tFEdad.setBackground(new Color(0, 255, 0, 100));
            JOptionPane.showMessageDialog(null,"Cliente dado de alta satisfactoriamente");
            
       	}
       	}
        }
    }
}
