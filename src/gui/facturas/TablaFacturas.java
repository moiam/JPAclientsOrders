package gui.facturas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import gc.bd.BdOperaciones;
import gc.beans.Cliente;
import gc.beans.Pedido;
import gui.ListaCliente;

import gui.ejecutarJF.EjecutarJF;
import gui.graficador.Media;
import gui.principal.Login;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;

public class TablaFacturas extends JPanel {

	private JPanel contentPane;
	private JTextField tFMediana;
	private JTextField tFVarianza;


	public TablaFacturas() {
		
		setLayout(null);
		contentPane = new JPanel();
		contentPane.setBounds(0, 0, 810, 441);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.WHITE);
		add(contentPane);
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(54, 51, 572, 258);
		contentPane.add(scrollPane_1);
		
		JTable table = new JTable();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 178, 542, 168);
		contentPane.add(scrollPane_1);
		
		JButton btnVolver = new JButton("");
		btnVolver.setContentAreaFilled(false);
		btnVolver.setPressedIcon(new ImageIcon(ListaCliente.class.getResource("/Imagenes/back_arrow_14429.png")));
		btnVolver.setIcon(new ImageIcon(ListaCliente.class.getResource("/Imagenes/back_arrow_14429.png")));
		btnVolver.setBounds(6, 0, 48, 43);
		contentPane.add(btnVolver);
		
		table = new JTable();
		scrollPane_1.setViewportView(table);
		
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("DNI");
		model.addColumn("Nombre");
		model.addColumn("Apellido 1");
		model.addColumn("Apellido 2");
		model.addColumn("Importe(€)");
		table.setModel(model);
		
		JButton btnMedia = new JButton("Media");
		btnMedia.setFont(new Font("Geomanist", Font.BOLD, 15));
		btnMedia.setBounds(94, 342, 117, 36);
		contentPane.add(btnMedia);
		
		JButton btnMediana = new JButton("Mediana");
		btnMediana.setFont(new Font("Geomanist", Font.BOLD, 15));
		btnMediana.setBounds(280, 342, 117, 36);
		contentPane.add(btnMediana);
		
		JButton btnVarianza = new JButton("Varianza");
		btnVarianza.setFont(new Font("Geomanist", Font.BOLD, 15));
		btnVarianza.setBounds(469, 342, 117, 36);
		contentPane.add(btnVarianza);
		
		tFMediana = new JTextField();
		tFMediana.setFont(new Font("Geomanist", Font.PLAIN, 13));
		tFMediana.setBounds(279, 378, 130, 26);
		contentPane.add(tFMediana);
		tFMediana.setColumns(10);
		
		tFVarianza = new JTextField();
		tFVarianza.setFont(new Font("Geomanist", Font.PLAIN, 13));
		tFVarianza.setBounds(467, 378, 130, 26);
		contentPane.add(tFVarianza);
		tFVarianza.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(0, 0, 677, 455);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 319, 466);
		panel_2.add(panel);
		panel.setBackground(new Color(173, 189, 220));
		panel.setForeground(new Color(151, 255, 255));
		panel.setLayout(null);
		
		JLabel lblNewLabel1 = new JLabel("");
		lblNewLabel1.setIcon(new ImageIcon(Login.class.getResource("/Imagenes/plano-vertical-estructura-geometrica-3.jpg")));
		lblNewLabel1.setBounds(318, 0, 359, 465);
		panel_2.add(lblNewLabel1);
		
		
		BdOperaciones bdOperaciones = new BdOperaciones();
		bdOperaciones.abrirConexion();

		try {
			List<Cliente>listClientes = bdOperaciones.listadoClientes();
			List<Pedido> listPedidos = bdOperaciones.listadoPedidos();

		    HashMap<String, Float> importesPorCliente = new HashMap<>();

		    for (Pedido pedido : listPedidos) {
		        String dniCliente = pedido.getDni();

		        // Verificar si el cliente ya se ha visto anteriormente
		        if (importesPorCliente.containsKey(dniCliente)) {
		            // Obtener el importe acumulado del cliente
		            float importeAcumulado = importesPorCliente.get(dniCliente);

		            // Sumar el importe actual del pedido
		            importeAcumulado += pedido.getImporte();

		            // Actualizar el importe acumulado del cliente en el mapa
		            importesPorCliente.put(dniCliente, importeAcumulado);
		        } else {
		            // Guardar el importe del pedido como importe acumulado del cliente
		            importesPorCliente.put(dniCliente, pedido.getImporte());
		        }
		    }

		    // Obtener el importe acumulado de cada uno
		    for (Cliente cliente : listClientes) {
		        String dniCliente = cliente.getDni();

		        // Verificar si el cliente tiene importe acumulado en el mapa
		        if (importesPorCliente.containsKey(dniCliente)) {
		            // Obtener el importe acumulado del cliente
		            float importeAcumulado = importesPorCliente.get(dniCliente);

		            // Añadir los datos del cliente a la tabla con el importe acumulado
		            Object[] rowData = new Object[5];
		            rowData[0] = dniCliente;
		            rowData[1] = cliente.getNombre();
		            rowData[2] = cliente.getApellido1();
		            rowData[3] = cliente.getApellido2();
		            rowData[4] = importeAcumulado;
		            model.addRow(rowData);
		        }
		    }
		} catch (Exception e) {
		    bdOperaciones.hacerRollback();
		    e.printStackTrace();
		} finally {
		    bdOperaciones.cerrarConexion();
		}



	
	btnMedia.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
		Media media = new Media();
		
		
		 media.setVisible(true);
			
		}
	});
	
	btnMediana.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			bdOperaciones.abrirConexion();
			List<Pedido> listPedidos = bdOperaciones.listadoPedidos();
			HashMap<String, Float> importesPorCliente = new HashMap<>();

			for (Pedido pedido : listPedidos) {
			    String dniCliente = pedido.getDni();

			    if (importesPorCliente.containsKey(dniCliente)) {
			        float importeAcumulado = importesPorCliente.get(dniCliente);
			        importeAcumulado += pedido.getImporte();
			        importesPorCliente.put(dniCliente, importeAcumulado);
			    } else {
			        importesPorCliente.put(dniCliente, pedido.getImporte());
			    }
			}

			List<Float> importesAcumulados = new ArrayList<>(importesPorCliente.values());
			Collections.sort(importesAcumulados);
			float mediana;
			int tamaño = importesAcumulados.size();
			if (tamaño % 2 == 0) {
			    float valorCentral1 = importesAcumulados.get(tamaño / 2 - 1);
			    float valorCentral2 = importesAcumulados.get(tamaño / 2);
			    mediana = (valorCentral1 + valorCentral2) / 2;
			} else {
			    mediana = importesAcumulados.get(tamaño / 2);
			}

			String medianaTexto = String.valueOf(mediana);
			tFMediana.setText(medianaTexto);
			
		}
	});
	btnVarianza.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			bdOperaciones.abrirConexion();
			List<Pedido> listPedidos = bdOperaciones.listadoPedidos();
			HashMap<String, Float> importesPorCliente = new HashMap<>();

			for (Pedido pedido : listPedidos) {
			    String dniCliente = pedido.getDni();

			    if (importesPorCliente.containsKey(dniCliente)) {
			        float importeAcumulado = importesPorCliente.get(dniCliente);
			        importeAcumulado += pedido.getImporte();
			        importesPorCliente.put(dniCliente, importeAcumulado);
			    } else {
			        importesPorCliente.put(dniCliente, pedido.getImporte());
			    }
			}

			List<Float> importesAcumulados = new ArrayList<>(importesPorCliente.values());

			float sumaImportes = 0;

			for (float importe : importesAcumulados) {
			    sumaImportes += importe;
			}

			float media = sumaImportes / importesAcumulados.size();

			float sumaDiferenciasCuadrados = 0;

			for (float importe : importesAcumulados) {
			    float diferencia = importe - media;
			    sumaDiferenciasCuadrados += diferencia * diferencia;
			}

			float varianza = sumaDiferenciasCuadrados / importesAcumulados.size();

			String varianzaTexto = String.valueOf(varianza);
			tFVarianza.setText(varianzaTexto);
		}
	});

    btnVolver.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					
					EjecutarJF volver = new EjecutarJF();
					volver.setVisible(true);
				
				}
			});

	    		}       
}
