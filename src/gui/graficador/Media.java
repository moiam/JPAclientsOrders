package gui.graficador;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import gc.bd.BdOperaciones;
import gc.beans.Cliente;
import gc.beans.Pedido;
import gui.ListaCliente;
import gui.facturas.TablaFacturas;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Media extends JFrame {

	private JPanel contentPane;


	public Media() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 400, 488, 353);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnVolver = new JButton("");
		btnVolver.setContentAreaFilled(false);
		btnVolver.setPressedIcon(new ImageIcon(ListaCliente.class.getResource("/Imagenes/back_arrow_14429.png")));
		btnVolver.setIcon(new ImageIcon(ListaCliente.class.getResource("/Imagenes/back_arrow_14429.png")));
		btnVolver.setBounds(6, 0, 48, 43);
		contentPane.add(btnVolver);
		
		JPanel jPanel1 = new JPanel();
		contentPane.add(jPanel1);
		
		BdOperaciones bdOperaciones = new BdOperaciones();
		bdOperaciones.abrirConexion();
		List<Pedido> listPedidos = bdOperaciones.listadoPedidos();
		List<Cliente> listClientes = bdOperaciones.listadoClientes();
		DefaultCategoryDataset datos = new DefaultCategoryDataset();
		try {
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
		    float sumaImportes = 0;
		    int numElementos = importesPorCliente.size();
		    // Iterar sobre la lista de clientes y obtener el importe acumulado de cada uno
		    for (Cliente cliente : listClientes) {
		        String dniCliente = cliente.getDni();

		        // Verificar si el cliente tiene importe acumulado en el mapa
		        if (importesPorCliente.containsKey(dniCliente)) {
		            // Obtener el importe acumulado del cliente
		            float importeAcumulado = importesPorCliente.get(dniCliente);
		            sumaImportes +=importeAcumulado;
				    datos.addValue(importeAcumulado, "Importe", cliente.getNombre());
		        }
		    }
		    float media = sumaImportes / numElementos;

			JFreeChart grafico_barras = ChartFactory.createBarChart3D(
			    "Media: "+media,
			    "Pedidos",
			    "Calificación",
			    datos,
			    PlotOrientation.VERTICAL,
			    true,
			    true,
			    false
			);

			ChartPanel panel = new ChartPanel(grafico_barras);
			panel.setDomainZoomable(true);
			panel.setDisplayToolTips(true);
			panel.setMouseWheelEnabled(true);
			panel.setPreferredSize(new Dimension(600, 400));
			jPanel1.setLayout(new BorderLayout());
			jPanel1.add(panel, BorderLayout.NORTH);
			pack();
			repaint();
		} catch (Exception e) {
		    bdOperaciones.hacerRollback();
		    e.printStackTrace();
		}

		


	
	 btnVolver.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				
				TablaFacturas volver = new TablaFacturas();
				volver.setVisible(true);
				
				dispose();
			}
		});
}
}

	  


	