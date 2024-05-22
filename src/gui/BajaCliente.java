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
import javax.swing.JTextField;
import javax.swing.UIManager;

import gc.bd.BdOperaciones;
import gc.beans.Pedido;
import gui.ejecutarJF.EjecutarJF;
import gui.principal.Login;

public class BajaCliente extends JPanel {

	
	private JPanel contentPane;
	private JTextField tFDniBaja;
	private JButton btnDarBaja;
	
	public BajaCliente() {
		
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
		
		JLabel lblDniBaja = new JLabel("DNI Cliente");
		lblDniBaja.setFont(new Font("Geomanist", Font.BOLD, 16));
		lblDniBaja.setBounds(277, 208, 78, 32);
		panel_2.add(lblDniBaja);
		lblDniBaja.setBackground(new Color(255, 255, 255));
		
		tFDniBaja = new JTextField();
		tFDniBaja.setFont(new Font("Geomanist", Font.PLAIN, 13));
		tFDniBaja.setBounds(222, 238, 180, 26);
		panel_2.add(tFDniBaja);
		tFDniBaja.setColumns(10);
		
		btnDarBaja = new JButton("Dar de Baja");
		btnDarBaja.setFont(new Font("Geomanist", Font.BOLD, 15));
		btnDarBaja.setBounds(328, 289, 133, 38);
		panel_2.add(btnDarBaja);
		btnDarBaja.setForeground(UIManager.getColor("InternalFrame.borderShadow"));
		
		
		JLabel lblNewLabel1 = new JLabel("");
		lblNewLabel1.setIcon(new ImageIcon(Login.class.getResource("/Imagenes/plano-vertical-estructura-geometrica-3.jpg")));
		lblNewLabel1.setBounds(318, 0, 354, 514);
		panel_2.add(lblNewLabel1);
		
		agregarListeners();

		 }
		 
	public void agregarListeners() {
		
		btnDarBaja.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            darBaja();
	        }
	    });
	}
		
	public void darBaja() {
		BdOperaciones bdOperaciones = new BdOperaciones();
		bdOperaciones.abrirConexion();
		
		String dni = tFDniBaja.getText();
		
		
		if(dni.isEmpty()) {
			tFDniBaja.setBackground(new Color(255, 0, 0, 100)); 
			JOptionPane.showMessageDialog(null, "Debe introducir un DNI");
        bdOperaciones.cerrarConexion();
		}else{
			try {
			    if (bdOperaciones.verificarClienteExistente(dni)) {
			        List<Pedido> listPedidos = bdOperaciones.consultaPedidoPorDni(dni);

			        if (!listPedidos.isEmpty()) {
			            // Mostrar los pedidos pendientes en una ventana emergente
			            StringBuilder mensaje = new StringBuilder();
			            mensaje.append("Pedidos pendientes del cliente con DNI ").append(dni).append(":\n");
			            for (Pedido pedido : listPedidos) {
			                mensaje.append("Número de pedido: ").append(pedido.getNumPedido()).append("\n");
			                mensaje.append("Detalle del pedido: ").append(pedido.getDetallePedido()).append("\n");
			                mensaje.append("\n");
			            }
			            JOptionPane.showMessageDialog(null, mensaje.toString(), "Pedidos pendientes", JOptionPane.INFORMATION_MESSAGE);
			            bdOperaciones.cerrarConexion();
			        } else {
			            // No hay pedidos pendientes, proceder con la eliminación del cliente
			        	bdOperaciones.abrirTransaccion();
			            bdOperaciones.bajaCliente(dni);
			            bdOperaciones.hacerCommit();
			            bdOperaciones.cerrarConexion();
			            tFDniBaja.setBackground(new Color(0, 255, 0, 100)); 
			            JOptionPane.showMessageDialog(null, "Cliente dado de baja satisfactoriamente");
			  
			        }
			    } else {
			        tFDniBaja.setBackground(new Color(255, 0, 0, 100)); 
			        JOptionPane.showMessageDialog(null, "Cliente no existe en la base de datos");
			        bdOperaciones.cerrarConexion();
			    }
			} catch (NumberFormatException ex) {
			    ex.printStackTrace();
			    return;
			} catch (Exception et) {
			    et.printStackTrace();
			    return;
			}

		}
	}

	
	}
