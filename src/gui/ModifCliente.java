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
import gc.beans.Cliente;
import gui.ejecutarJF.EjecutarJF;
import gui.principal.Login;

public class ModifCliente extends JPanel {

	private JPanel contentPane;
	private JTextField tFDNI;
	private JLabel lblNombre;
	private JLabel lblApellido1;
	private JLabel lblApellido2;
	private JLabel lblEdad;
	private JTextField tFNombre;
	private JTextField tFApellido1;
	private JTextField tFApellido2;
	private JTextField tFEdad;
	private JButton btnModificarCliente;
	private JButton btnBuscar;
	
	public ModifCliente() {

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
		tFDNI.setBounds(331, 152, 130, 26);
		panel_2.add(tFDNI);
		tFDNI.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Geomanist", Font.BOLD, 15));
		btnBuscar.setBounds(473, 152, 86, 26);
		panel_2.add(btnBuscar);
		
		tFNombre = new JTextField();
		tFNombre.setFont(new Font("Geomanist", Font.PLAIN, 13));
		tFNombre.setBounds(331, 191, 130, 26);
		panel_2.add(tFNombre);
		tFNombre.setColumns(10);
		
		tFApellido1 = new JTextField();
		tFApellido1.setFont(new Font("Geomanist", Font.PLAIN, 13));
		tFApellido1.setBounds(331, 219, 130, 26);
		panel_2.add(tFApellido1);
		tFApellido1.setColumns(10);
		
		tFApellido2 = new JTextField();
		tFApellido2.setFont(new Font("Geomanist", Font.PLAIN, 13));
		tFApellido2.setBounds(331, 247, 130, 26);
		panel_2.add(tFApellido2);
		tFApellido2.setColumns(10);
		
		tFEdad = new JTextField();
		tFEdad.setFont(new Font("Geomanist", Font.PLAIN, 13));
		tFEdad.setBounds(331, 275, 130, 26);
		panel_2.add(tFEdad);
		tFEdad.setColumns(10);
		
		btnModificarCliente = new JButton("Confirmar Modificación");
		btnModificarCliente.setBounds(346, 315, 174, 47);
		panel_2.add(btnModificarCliente);
		btnModificarCliente.setForeground(new Color(218, 180, 5));
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 319, 514);
		panel_2.add(panel);
		panel.setBackground(new Color(173, 189, 220));
		panel.setForeground(new Color(151, 255, 255));
		panel.setLayout(null);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(254, 196, 59, 16);
		panel.add(lblNombre);
		lblNombre.setFont(new Font("Geomanist", Font.BOLD, 16));
		
		lblApellido1 = new JLabel("Primer apellido");
		lblApellido1.setBounds(209, 224, 104, 16);
		panel.add(lblApellido1);
		lblApellido1.setFont(new Font("Geomanist", Font.BOLD, 16));
		
		lblApellido2 = new JLabel("Segundo apellido");
		lblApellido2.setBounds(188, 252, 125, 16);
		panel.add(lblApellido2);
		lblApellido2.setFont(new Font("Geomanist", Font.BOLD, 16));
		
		lblEdad = new JLabel("Edad");
		lblEdad.setBounds(277, 281, 36, 16);
		panel.add(lblEdad);
		lblEdad.setFont(new Font("Geomanist", Font.BOLD, 16));
		
		JLabel lblNewLabel = new JLabel("DNI del Cliente a modificar");
		lblNewLabel.setBounds(128, 158, 185, 16);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Geomanist", Font.BOLD, 16));
		
		JLabel lblNewLabel1 = new JLabel("");
		lblNewLabel1.setIcon(new ImageIcon(Login.class.getResource("/Imagenes/plano-vertical-estructura-geometrica-3.jpg")));
		lblNewLabel1.setBounds(318, 0, 354, 514);
		panel_2.add(lblNewLabel1);
						
		agregarListeners();
	
	}
			public void agregarListeners() {
				
				btnModificarCliente.addActionListener(new ActionListener() {
			        public void actionPerformed(ActionEvent e) {
			            modificarCliente();
			        }
			    });
		
			    btnBuscar.addActionListener(new ActionListener() {
			        public void actionPerformed(ActionEvent e) {
			            buscarCliente();
			        }
			    });
			}
			
		    public void modificarCliente() {
		    
		    	BdOperaciones bdOperaciones = new BdOperaciones();
		    	bdOperaciones.abrirConexion();
		    	
		        String dni = tFDNI.getText();
		        String nombre = tFNombre.getText();
		        String apellido1 = tFApellido1.getText();
		        String apellido2 = tFApellido2.getText();
		        
		        String nombreTemporal = tFNombre.getText();
		        String apellido1Temporal = tFApellido1.getText();
		        String apellido2Temporal = tFApellido2.getText();
		        int edad = 0;
		        boolean camposValidos = true;
		        
		        if (dni.isEmpty() || nombre.isEmpty() || apellido1.isEmpty() || apellido2.isEmpty()) {
		        
		            tFNombre.setBackground(new Color(255, 0, 0, 100));
		            tFApellido1.setBackground(new Color(255, 0, 0, 100));
		            tFApellido2.setBackground(new Color(255, 0, 0, 100));
		            camposValidos = false;
		          
		           
		        } else {
		       	 try {
			            edad = Integer.parseInt(tFEdad.getText());
			        } catch (NumberFormatException ex) {
			        	JOptionPane.showMessageDialog(null, "La edad no es un número válido"); 
			        }
		
		            tFNombre.setBackground(new Color(0, 255, 0, 100));   
		            tFApellido1.setBackground(new Color(0, 255, 0, 100));   
		            tFApellido2.setBackground(new Color(0, 255, 0, 100));   
		            
		            tFNombre.setText("");
		            tFApellido1.setText("");
		            tFApellido2.setText("");
		           
		        }
		
		        if (edad < 1) {
		            tFEdad.setBackground(new Color(255, 0, 0, 100)); 
		            camposValidos = false;
		            tFEdad.setText("");
		        } else {
		            tFEdad.setBackground(new Color(0, 255, 0, 100));  
		            tFEdad.setText("");
		        }
		     
		        
		        if (camposValidos) {
		            Cliente nuevoCliente = new Cliente(dni, nombre, apellido1, apellido2, edad);
		            bdOperaciones.abrirTransaccion();
		            bdOperaciones.modifCliente(nuevoCliente);
		            bdOperaciones.hacerCommit();
		            bdOperaciones.cerrarConexion();
		        }else {
		        	    tFNombre.setText(nombreTemporal);
		        	    tFApellido1.setText(apellido1Temporal);
		        	    tFApellido2.setText(apellido2Temporal);
		        }
		    }
			public void buscarCliente() {
				  String dni = tFDNI.getText();
		  		BdOperaciones bdOperaciones = new BdOperaciones();
		  		bdOperaciones.abrirConexion();
		          Cliente cliente = bdOperaciones.consultaClientePorDni(dni);
		
		          if (cliente != null) {
		              // Se encontró el cliente, mostrar los datos en los JTextField correspondientes
		              tFNombre.setText(cliente.getNombre());
		              tFApellido1.setText(cliente.getApellido1());
		              tFApellido2.setText(cliente.getApellido2());
		              tFEdad.setText(String.valueOf(cliente.getEdad()));
		              bdOperaciones.cerrarConexion();
		          } else if (cliente==null) {
		              // No se encontró ningún cliente con el DNI proporcionado, mostrar un mensaje al usuario
		              JOptionPane.showMessageDialog(null, "No se encontró ningún cliente con el DNI " + dni);
		          }
		      }
		}
