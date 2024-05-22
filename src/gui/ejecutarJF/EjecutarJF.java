package gui.ejecutarJF;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;


import javax.swing.JOptionPane;


import gc.bd.BdOperaciones;
import gc.beans.Cliente;
import gc.beans.Pedido;
import gc.xml.ClientePedidoContainer;
import gc.xml.ClientesContainer;
import gc.xml.ExportadorXML;
import gc.xml.PedidosContainer;
import gui.AltaCliente;
import gui.AltaPedido;
import gui.BajaPedido;
import gui.BajaCliente;
import gui.ListaCliente;
import gui.ListaPedido;
import gui.ModifCliente;
import gui.ModifPedido;
import gui.facturas.TablaFacturas;
import gui.facturas.TablaFacturas2;
import gui.graficador.Media;
import gui.principal.Login;


import javax.swing.ImageIcon;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.JInternalFrame;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.MouseInfo;
import java.awt.CardLayout;
import javax.swing.border.CompoundBorder;
import java.awt.Cursor;



public class EjecutarJF extends JFrame {

	private JPanel contentPane;
	private JPanel panelMenu;
	private JPanel submenuClientes;
	private  JButton btnClientes,
					btnPedidos,
					btnFacturas
					;
	private JButton btnAltaCliente;
	private JButton btnBajaCliente;
	private JButton btnModificarCliente;
	private JButton btnListaClientes;

    
    private boolean submenuVisible = false;
    private int submenuWidth = 0;
    private Timer menuTimer;
    private Timer submenuTimer;
   
  
    private JPanel panelDinamico;
    private JPanel panelClientes,
    			   panelPedidos,
    			   panelFacturas;
    			   
    private JPanel panelAltaCliente;
    private JPanel panelBajaCliente;
    private JPanel panelModificarCliente;
    private JPanel panelListaClientes;
    private JPanel submenuPedidos;
    private JPanel panelAltaPedido;
    private JButton btnAltaPedido;
    private JPanel panelBajaPedido;
    private JButton btnBajaPedido;
    private JPanel panelModificarPedido;
    private JButton btnModificarPedido;
    private JPanel panelListaPedidos;
    private JButton btnListaPedidos;
    
    private JButton botonActual = null;
    private JPanel subpanelActual = null;
    private JPanel submenuFacturas;
    private JPanel panelTablaFacturas;
    private JButton btnTablaFacturas;
    private JPanel submenuGraficos;
    private JPanel panelGraficos;
    private JButton btnGraficos;
    private JPanel panelMedia;
    private JButton btnMedia;


	
	public EjecutarJF() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		setBounds(100, 100, 1100, 630);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArchivos = new JMenu("Archivos");
		menuBar.add(mnArchivos);
		
		JMenu mnNewMenu_1 = new JMenu("New menu");
		mnArchivos.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("New menu item");
		mnNewMenu_1.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("New menu item");
		mnNewMenu_1.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("New menu item");
		mnNewMenu_1.add(mntmNewMenuItem_2);
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		  
		  
		  panelDinamico = new JPanel();
		  panelDinamico.setBackground(new Color(255, 255, 255));
		  panelDinamico.setBounds(428, 66, 672, 514);
		  contentPane.add(panelDinamico);
		  panelDinamico.setLayout(null);
	
		  	    	    	    	  
		  panelMenu = new JPanel();
		  panelMenu.setBackground(new Color(60, 96, 158));
		  panelMenu.setBounds(0, 66, 225, 514);
		  contentPane.add(panelMenu);
		  panelMenu.setLayout(null);
		  
		  panelClientes = new JPanel();
		  panelClientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		  panelClientes.setBackground(new Color(60, 96, 158));
		  panelClientes.setBounds(0, 12, 225, 65);
		  panelMenu.add(panelClientes);
		  panelClientes.setLayout(new GridLayout(0, 1, 0, 0));
		  
		  
		  btnClientes = new JButton("Clientes");
		  btnClientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		  btnClientes.setContentAreaFilled(false);
		  btnClientes.setBorderPainted(false);
		  btnClientes.setBorder(null);
		  panelClientes.add(btnClientes);
		  btnClientes.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		  btnClientes.setIcon(new ImageIcon(EjecutarJF.class.getResource("/Imagenes/customer-3.png")));
		  
		  panelPedidos = new JPanel();
		  panelPedidos.setBackground(new Color(60, 96, 158));
		  panelPedidos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		  panelPedidos.setBounds(0, 84, 225, 65);
		  panelMenu.add(panelPedidos);
		  panelPedidos.setLayout(new GridLayout(0, 1, 0, 0));
		  
		  btnPedidos = new JButton("Pedidos");
		  btnPedidos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		  btnPedidos.setContentAreaFilled(false);
		  btnPedidos.setBorderPainted(false);
		  btnPedidos.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		  panelPedidos.add(btnPedidos);
		  btnPedidos.setIcon(new ImageIcon(EjecutarJF.class.getResource("/Imagenes/file-2.png")));
		  
		  panelFacturas = new JPanel();
		  panelFacturas.setBackground(new Color(60, 96, 158));
		  panelFacturas.setBounds(0, 159, 225, 65);
		  panelMenu.add(panelFacturas);
		  panelFacturas.setLayout(new GridLayout(0, 1, 0, 0));
		  
		  btnFacturas = new JButton("Facturas");
		  btnFacturas.setContentAreaFilled(false);
		  btnFacturas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		  btnFacturas.setBorderPainted(false);
		  btnFacturas.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		  panelFacturas.add(btnFacturas);
		  btnFacturas.setIcon(new ImageIcon(EjecutarJF.class.getResource("/Imagenes/bill.png")));
		  
		  panelGraficos = new JPanel();
		  panelGraficos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		  panelGraficos.setBackground(new Color(60, 96, 158));
		  panelGraficos.setBounds(0, 236, 225, 65);
		  panelMenu.add(panelGraficos);
		  panelGraficos.setLayout(new GridLayout(0, 1, 0, 0));
		  
		  btnGraficos = new JButton("Graficos");
		  btnGraficos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		  btnGraficos.setIcon(new ImageIcon(EjecutarJF.class.getResource("/Imagenes/bar-chart.png")));
		  panelGraficos.add(btnGraficos);
		  btnGraficos.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		  btnGraficos.setContentAreaFilled(false);
		  btnGraficos.setBorderPainted(false);
		  
		  submenuGraficos = new JPanel();
		  submenuGraficos.setLayout(null);
		  submenuGraficos.setBackground(new Color(103, 133, 185));
		  submenuGraficos.setBounds(224, 66, 204, 514);
		  contentPane.add(submenuGraficos);
		  submenuGraficos.setVisible(false);
		  
		  panelMedia = new JPanel();
		  panelMedia.setBackground(new Color(103, 133, 185));
		  panelMedia.setBounds(18, 34, 168, 42);
		  submenuGraficos.add(panelMedia);
		  panelMedia.setLayout(new GridLayout(1, 0, 0, 0));
		  
		  btnMedia = new JButton("Media");
		  btnMedia.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		  panelMedia.add(btnMedia);
		  btnMedia.setIconTextGap(20);
		  btnMedia.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		  btnMedia.setFocusPainted(false);
		  btnMedia.setContentAreaFilled(false);
		  btnMedia.setBorderPainted(false);
		  btnMedia.setBorder(null);
		  
		  
		  submenuFacturas = new JPanel();
		  submenuFacturas.setLayout(null);
		  submenuFacturas.setBackground(new Color(103, 133, 185));
		  submenuFacturas.setBounds(224, 66, 204, 525);
		  contentPane.add(submenuFacturas);
		  submenuFacturas.setVisible(false);
		  
		  panelTablaFacturas = new JPanel();
		  panelTablaFacturas.setBackground(new Color(103, 133, 185));
		  panelTablaFacturas.setBounds(6, 33, 186, 38);
		  submenuFacturas.add(panelTablaFacturas);
		  panelTablaFacturas.setLayout(new GridLayout(1, 0, 0, 0));
		  
		  btnTablaFacturas = new JButton("Tabla de Facturas");
		  btnTablaFacturas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		  btnTablaFacturas.setIconTextGap(20);
		  btnTablaFacturas.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		  btnTablaFacturas.setFocusPainted(false);
		  btnTablaFacturas.setContentAreaFilled(false);
		  btnTablaFacturas.setBorderPainted(false);
		  btnTablaFacturas.setBorder(null);
		  panelTablaFacturas.add(btnTablaFacturas);
		  
		  submenuPedidos = new JPanel();
		  submenuPedidos.setLayout(null);
		  submenuPedidos.setBackground(new Color(103, 133, 185));
		  submenuPedidos.setBounds(224, 66, 204, 525);
		  contentPane.add(submenuPedidos);
		  submenuPedidos.setVisible(false);
		  
		  panelAltaPedido = new JPanel();
		  panelAltaPedido.setBackground(new Color(103, 133, 185));
		  panelAltaPedido.setBounds(6, 33, 186, 38);
		  submenuPedidos.add(panelAltaPedido);
		  panelAltaPedido.setLayout(new GridLayout(1, 0, 0, 0));
		  
		  btnAltaPedido = new JButton("Alta de Pedido");
		  btnAltaPedido.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		  btnAltaPedido.setIconTextGap(20);
		  btnAltaPedido.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		  btnAltaPedido.setFocusPainted(false);
		  btnAltaPedido.setContentAreaFilled(false);
		  btnAltaPedido.setBorderPainted(false);
		  btnAltaPedido.setBorder(null);
		  panelAltaPedido.add(btnAltaPedido);
		  
		  panelBajaPedido = new JPanel();
		  panelBajaPedido.setBackground(new Color(103, 133, 185));
		  panelBajaPedido.setBounds(6, 75, 186, 38);
		  submenuPedidos.add(panelBajaPedido);
		  panelBajaPedido.setLayout(new GridLayout(1, 0, 0, 0));
		  
		  btnBajaPedido = new JButton("Baja de Pedido");
		  btnBajaPedido.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		  btnBajaPedido.setIconTextGap(20);
		  btnBajaPedido.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		  btnBajaPedido.setContentAreaFilled(false);
		  btnBajaPedido.setBorderPainted(false);
		  btnBajaPedido.setBorder(null);
		  panelBajaPedido.add(btnBajaPedido);
		  
		  panelModificarPedido = new JPanel();
		  panelModificarPedido.setBackground(new Color(103, 133, 185));
		  panelModificarPedido.setBounds(6, 116, 186, 38);
		  submenuPedidos.add(panelModificarPedido);
		  panelModificarPedido.setLayout(new GridLayout(1, 0, 0, 0));
		  
		  btnModificarPedido = new JButton("Modificación Pedido");
		  btnModificarPedido.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		  btnModificarPedido.setIconTextGap(8);
		  btnModificarPedido.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		  btnModificarPedido.setContentAreaFilled(false);
		  btnModificarPedido.setBorderPainted(false);
		  btnModificarPedido.setBorder(null);
		  panelModificarPedido.add(btnModificarPedido);
		  
		  panelListaPedidos = new JPanel();
		  panelListaPedidos.setBackground(new Color(103, 133, 185));
		  panelListaPedidos.setBounds(6, 157, 186, 38);
		  submenuPedidos.add(panelListaPedidos);
		  panelListaPedidos.setLayout(new GridLayout(1, 0, 0, 0));
		  
		  btnListaPedidos = new JButton("Lista de Pedidos");
		  btnListaPedidos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		  btnListaPedidos.setIconTextGap(20);
		  btnListaPedidos.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		  btnListaPedidos.setContentAreaFilled(false);
		  btnListaPedidos.setBorder(null);
		  panelListaPedidos.add(btnListaPedidos);
		  
		  
		  submenuClientes = new JPanel(false);
		  submenuClientes.setLayout(null);
		  submenuClientes.setBackground(new Color(103, 133, 185));
		  submenuClientes.setBounds(224, 66, 204, 514);
		  contentPane.add(submenuClientes);
		  submenuClientes.setVisible(false);
		  
		  panelAltaCliente = new JPanel();
		  panelAltaCliente.setBackground(new Color(103, 133, 185));
		  panelAltaCliente.setBounds(6, 33, 186, 38);
		  submenuClientes.add(panelAltaCliente);
		  panelAltaCliente.setLayout(new GridLayout(1, 0, 0, 0));
		  
		  btnAltaCliente = new JButton("Alta de Cliente");
		  panelAltaCliente.add(btnAltaCliente);
		  btnAltaCliente.setIconTextGap(20);
		  btnAltaCliente.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		  btnAltaCliente.setIcon(new ImageIcon(EjecutarJF.class.getResource("/Imagenes/add-user.png")));
		  btnAltaCliente.setFocusPainted(false);
		  btnAltaCliente.setContentAreaFilled(false);
		  btnAltaCliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		  btnAltaCliente.setBorderPainted(false);
		  btnAltaCliente.setBorder(null);
		  
		  
		  panelBajaCliente = new JPanel();
		  panelBajaCliente.setBackground(new Color(103, 133, 185));
		  panelBajaCliente.setBounds(6, 75, 186, 38);
		  submenuClientes.add(panelBajaCliente);
		  panelBajaCliente.setLayout(new GridLayout(1, 0, 0, 0));
		  
		  btnBajaCliente = new JButton("Baja de Cliente");
		  btnBajaCliente.setIconTextGap(20);
		  btnBajaCliente.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		  btnBajaCliente.setIcon(new ImageIcon(EjecutarJF.class.getResource("/Imagenes/user.png")));
		  btnBajaCliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		  btnBajaCliente.setContentAreaFilled(false);
		  btnBajaCliente.setBorderPainted(false);
		  btnBajaCliente.setBorder(null);
		  panelBajaCliente.add(btnBajaCliente);
		  
		  panelModificarCliente = new JPanel();
		  panelModificarCliente.setBackground(new Color(103, 133, 185));
		  panelModificarCliente.setBounds(6, 116, 186, 38);
		  submenuClientes.add(panelModificarCliente);
		  panelModificarCliente.setLayout(new GridLayout(1, 0, 0, 0));
		  
		  btnModificarCliente = new JButton("Modificación Cliente");
		  btnModificarCliente.setIconTextGap(8);
		  btnModificarCliente.setIcon(new ImageIcon(EjecutarJF.class.getResource("/Imagenes/edit-profile.png")));
		  btnModificarCliente.setContentAreaFilled(false);
		  btnModificarCliente.setBorderPainted(false);
		  btnModificarCliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		  btnModificarCliente.setBorder(null);
		  btnModificarCliente.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		  panelModificarCliente.add(btnModificarCliente);
		  
		  panelListaClientes = new JPanel();
		  panelListaClientes.setBackground(new Color(103, 133, 185));
		  panelListaClientes.setBounds(6, 157, 186, 38);
		  submenuClientes.add(panelListaClientes);
		  panelListaClientes.setLayout(new GridLayout(1, 0, 0, 0));
		  
		  btnListaClientes = new JButton("Lista de Clientes");
		  btnListaClientes.setIconTextGap(20);
		  btnListaClientes.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		  btnListaClientes.setIcon(new ImageIcon(EjecutarJF.class.getResource("/Imagenes/customer.png")));
		  btnListaClientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		  btnListaClientes.setBorder(null);
		  btnListaClientes.setContentAreaFilled(false);
		  panelListaClientes.add(btnListaClientes);
		  
		  JPanel panel = new JPanel();
		  panel.setBackground(new Color(234, 234, 234));
		  panel.setBounds(0, 0, 1100, 66);
		  contentPane.add(panel);
		  
	    
	  
	  agregarListeners();
	  
	}
	

	public void agregarListeners() {
		

		Map<JButton, Runnable> submenuJbuttons = new HashMap<>();
		submenuJbuttons.put(btnAltaCliente, () -> altaClientes());
		submenuJbuttons.put(btnBajaCliente, () -> bajaClientes());
		submenuJbuttons.put(btnModificarCliente, () -> modificarCliente());
		submenuJbuttons.put(btnListaClientes, () -> listaCliente());
		
		submenuJbuttons.put(btnAltaPedido, () -> altaPedido());
		submenuJbuttons.put(btnBajaPedido, () -> bajaPedido());
		submenuJbuttons.put(btnModificarPedido, () -> modificarPedido());
		submenuJbuttons.put(btnListaPedidos, () -> listaPedido());
		
		submenuJbuttons.put(btnTablaFacturas, () -> tablaFacturas());
		
		submenuJbuttons.put(btnMedia, () -> media());


		

		

		
		Map<JButton, Runnable> menuJbuttons = new HashMap<>();
		//Cuando cursor en categoría Clientes,Pedidos, Facturas o Gráficos se abre el submenú correspondiente
		menuJbuttons.put(btnClientes, () -> configurarMouseEventsMenu(btnClientes,submenuClientes));
		menuJbuttons.put(btnPedidos, () -> configurarMouseEventsMenu(btnPedidos,submenuPedidos));
		menuJbuttons.put(btnFacturas, () -> configurarMouseEventsMenu(btnFacturas,submenuFacturas));
		menuJbuttons.put(btnGraficos, () -> configurarMouseEventsMenu(btnGraficos,submenuGraficos));
	
		
		Map<JButton, JPanel> botonApanel = new HashMap<>();//cambios de color al tocar botones de menu
		botonApanel.put(btnClientes,panelClientes);
		botonApanel.put(btnPedidos, panelPedidos);
		botonApanel.put(btnFacturas, panelFacturas);
		botonApanel.put(btnGraficos, panelGraficos);
		
		Map<JButton, JPanel> subBotonaPanel = new HashMap<>();//cambios de color al tocar botones de submenu
        subBotonaPanel.put(btnAltaCliente, panelAltaCliente);
        subBotonaPanel.put(btnBajaCliente, panelBajaCliente);
        subBotonaPanel.put(btnModificarCliente, panelModificarCliente);
        subBotonaPanel.put(btnListaClientes, panelListaClientes);
        subBotonaPanel.put(btnAltaPedido, panelAltaPedido);
        subBotonaPanel.put(btnBajaPedido, panelBajaPedido);
        subBotonaPanel.put(btnModificarPedido, panelModificarPedido);
        subBotonaPanel.put(btnTablaFacturas, panelTablaFacturas);
        subBotonaPanel.put(btnMedia, panelMedia);
       
        
        
        
        
        for (JButton boton : botonApanel.keySet()) {
            JPanel panelActual = botonApanel.get(boton);
            configurarMenuColores(boton, panelActual);
            
        }

	        // Configura los eventos mouseEntered y mouseExited para todos los botones
	        for (JButton boton : subBotonaPanel.keySet()) {
	            JPanel panelActual = subBotonaPanel.get(boton);
	            configurarSubmenuColores(boton, panelActual);//panelActual son los paneles de los botones
	        }
	    
        MouseListener actionListenerAccion = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                JButton source = (JButton) e.getSource();
                Runnable action = menuJbuttons.get(source);
                if (action != null) {
                    action.run();
                }
            }
        };



        ActionListener actionListener = e -> {
            JButton source = (JButton) e.getSource();
            Runnable action = submenuJbuttons.get(source);
            if (action != null) {
                action.run();
            }
        };
        
        btnClientes.addMouseListener(actionListenerAccion);//activa la apertura del submenu al pulsar botones de menú
        btnPedidos.addMouseListener(actionListenerAccion);
        btnFacturas.addMouseListener(actionListenerAccion);
        btnGraficos.addMouseListener(actionListenerAccion);

        

        
        btnAltaCliente.addActionListener(actionListener);
        btnBajaCliente.addActionListener(actionListener);
        btnModificarCliente.addActionListener(actionListener);
        btnListaClientes.addActionListener(actionListener);
        
        btnAltaPedido.addActionListener(actionListener);
        btnBajaPedido.addActionListener(actionListener);
        btnModificarPedido.addActionListener(actionListener);
        btnListaPedidos.addActionListener(actionListener);
        
        btnTablaFacturas.addActionListener(actionListener);
        btnMedia.addActionListener(actionListener);
        

		
		
	}

	public void abrirSubmenu (JPanel submenuPanel) {//recibe el botón del menú y panel del submenú
		submenuPanel.setVisible(true);

	}
	
	private void cerrarSubmenu(JPanel submenuPanel) {
	
		submenuPanel.setVisible(false);
	}

	public void configurarMouseEventsMenu(JButton botonMenu, JPanel submenuPanel) {
	    botonMenu.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseEntered(MouseEvent e) {
	        	
	            abrirSubmenu(submenuPanel);
	        	
	        }

	        @Override
	        public void mouseExited(MouseEvent e) {
	        
	                cerrarSubmenu(submenuPanel);
	            
	        }
	    });

	    // Configurar eventos de mouse para el submenú
	    configurarMouseEventsSubMenu(submenuPanel);

	}
	public void configurarMouseEventsSubMenu(JPanel submenuPanel) {
	    submenuPanel.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseEntered(MouseEvent e) {
	            abrirSubmenu(submenuPanel);
	        }

	        @Override
	        public void mouseExited(MouseEvent e) {
	            if (!estaEnSubmenu(e)) {
	                cerrarSubmenu(submenuPanel);
	            }
	        }
	    });
		
	}

	// Método para determinar si el mouse se encuentra dentro del submenú o alguno de sus componentes
	private boolean estaEnSubmenu(MouseEvent e) {
	    Component c = e.getComponent();
	    java.awt.Point p = e.getPoint();
	    return c.contains(p);
	}


	public  void configurarMenuColores(JButton botonMenu, JPanel submenuPanel) {//cambios de color de los paneles cuando se cambia entre botones, RECIBE BOTON Y PANELES DE BOTONES DEL MENU

		
		botonMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                cambiarColorYVisibleMenu(submenuPanel, new Color(117, 152, 211));
               	
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                restaurarColorOriginalMenu(submenuPanel);

                }
        });
    }

	public void configurarSubmenuColores(JButton boton, JPanel panelesDeBotones) { //panelActual son los paneles de los botones

		boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                cambiarColorYVisiblesubMenu(panelesDeBotones, new Color(117, 152, 211));
                
             
            }

            @Override
            public void mouseExited(MouseEvent e) {
                restaurarColorOriginalsubMenu(panelesDeBotones);

            }
            
        });
		
	}


	public void cambiarColorYVisibleMenu(JPanel panelActual, Color color) {
		panelActual.setBackground(color);
	    
	}
	public void cambiarColorYVisiblesubMenu(JPanel panelActual, Color color) {
		panelActual.setBackground(color);
	   
	    
	}
	public void restaurarColorOriginalMenu(JPanel panelActual) {
		Color originalBackground = new Color(60,96,158);
		panelActual.setBackground(originalBackground);
	}
	
	public void restaurarColorOriginalsubMenu(JPanel panelActual) {
		Color originalBackground = new Color(103, 133, 185);
		panelActual.setBackground(originalBackground);
	}

	public void agregarPanel(JPanel panelActual) {
		
	    panelDinamico.removeAll(); // Limpia cualquier contenido previo en el panel dinámico
	    panelDinamico.add(panelActual);
	    panelDinamico.revalidate();
	    panelDinamico.repaint();

    
	}

	public void altaClientes() {
		
		 AltaCliente altaCliente = new AltaCliente();
    	 altaCliente.setSize(672, 514);
 	    altaCliente.setLocation(0,0);
 	   agregarPanel(altaCliente);
 	   
	}
	public void altaPedido() {
		
		 AltaPedido altaPedido = new AltaPedido();
		 altaPedido.setSize(672, 514);
		 altaPedido.setLocation(0,0);
	   agregarPanel(altaPedido);
	   
	}
	public void bajaClientes() {
		
		 BajaCliente bajaCliente = new BajaCliente();
		 bajaCliente.setSize(672, 514);
		 bajaCliente.setLocation(0,0);
		 agregarPanel(bajaCliente);
	   
	}
	public void bajaPedido() {
		
		 BajaPedido bajaPedido = new BajaPedido();
		 bajaPedido.setSize(672, 514);
		 bajaPedido.setLocation(0,0);
		 agregarPanel(bajaPedido);
	   
	}
	public void listaCliente() {
		
		 ListaCliente listaCliente = new ListaCliente();
		 listaCliente.setSize(672, 514);
		 listaCliente.setLocation(0,0);
		 agregarPanel(listaCliente);
	   
	}
	public void listaPedido() {
		
		 ListaPedido listaPedido = new ListaPedido();
		 listaPedido.setSize(672, 514);
		 listaPedido.setLocation(0,0);
		 agregarPanel(listaPedido);
	   
	}
	public void modificarCliente() {
		
		 ModifCliente modificarCliente = new ModifCliente();
		 modificarCliente.setSize(672, 514);
		 modificarCliente.setLocation(0,0);
		 agregarPanel(modificarCliente);
	   
	}
	public void modificarPedido() {
		
		 ModifPedido modificarPedido = new ModifPedido();
		 modificarPedido.setSize(672, 514);
		 modificarPedido.setLocation(0,0);
		 agregarPanel(modificarPedido);
	   
	}
	public void tablaFacturas() {
		
		TablaFacturas2 tablaFacturas = new TablaFacturas2();
		tablaFacturas.setSize(672, 514);
		tablaFacturas.setLocation(0,0);
		agregarPanel(tablaFacturas);
		
		
	}
	public void media() {
		Media media = new Media();
		media.setVisible(true);
		
		
		
	}
}