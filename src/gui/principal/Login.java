package gui.principal;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import gc.bd.BdOperaciones;
import gc.beans.Controladora;
import gc.beans.Usuario;
import gui.ejecutarJF.EjecutarJF;
import persistencia.ControladoraPersistencia;

import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Font;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField tFUserName;
	private JPasswordField passwordField;
	private JButton btnLogin;
	private String placeholder = "Introduzca usuario";
	Controladora control;
	
	
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {	
			
			public void run() {
				try {
					Login frame = new Login();
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 677, 483);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(173, 189, 220));
		panel.setForeground(new Color(151, 255, 255));
		panel.setBounds(0, 0, 319, 466);
		contentPane.add(panel);
		panel.setLayout(null);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Geomanist", Font.ITALIC, 13));
		passwordField.setForeground(new Color(72, 72, 72));
		passwordField.setToolTipText("");
		passwordField.setBackground(new Color(238, 240, 240));
		passwordField.setBounds(142, 210, 142, 26);
		panel.add(passwordField);
		
		tFUserName = new JTextField();
		tFUserName.setFont(new Font("Geomanist", Font.ITALIC, 13));
		tFUserName.setForeground(new Color(72, 72, 72));
		tFUserName.setBounds(142, 172, 142, 26);
		panel.add(tFUserName);
		tFUserName.setColumns(10);
		
		btnLogin = new JButton("Iniciar Sesión");
		btnLogin.setFont(new Font("Geomanist", Font.PLAIN, 13));
		btnLogin.setForeground(new Color(19, 19, 19));
		btnLogin.setBackground(new Color(18, 18, 18));
		btnLogin.setBounds(152, 248, 117, 29);
		panel.add(btnLogin);
		
		JLabel lblPassword = new JLabel("Contraseña");
		lblPassword.setFont(new Font("Geomanist", Font.BOLD, 16));
		lblPassword.setBounds(41, 216, 89, 16);
		panel.add(lblPassword);
		
		JLabel lblUserName = new JLabel("Usuario");
		lblUserName.setFont(new Font("Geomanist", Font.BOLD, 16));
		lblUserName.setForeground(new Color(18, 18, 18));
		lblUserName.setBounds(68, 178, 62, 16);
		panel.add(lblUserName);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(0, 0, 677, 455);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/Imagenes/plano-vertical-estructura-geometrica-3.jpg")));
		lblNewLabel.setBounds(318, 0, 359, 465);
		panel_2.add(lblNewLabel);
		
		control = new Controladora();
		
		agregarListeners();
		
		tFUserName.addFocusListener(new FocusListener() {
          
            public void focusGained(FocusEvent e) {
                // Borrar el texto de marcador de posición cuando el usuario haga clic en el TextField
                if (tFUserName.getText().equals(placeholder)) {
                	tFUserName.setText("");
                }
            }
            
            public void focusLost(FocusEvent e) {
                // Restaurar el texto de marcador de posición si el TextField está vacío
                if (tFUserName.getText().isEmpty()) {
                	tFUserName.setText(placeholder);
                }
            }
        });
	    
	}
	public void agregarListeners() {
		
		btnLogin.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            login();
	        }
	    });
	}
	public void login() {
	    String username = tFUserName.getText();
	    String password = new String(passwordField.getPassword());
	    Usuario usr = control.validarUsuario(username, password);

	    if (usr != null) {
	        String rol = usr.getUnRol().getNombreRol();

	        if (rol.equals("admin")) {
	        	 dispose(); 
	            JOptionPane.showMessageDialog(null, "Eres admin");
	            BdOperaciones bdOperaciones = new BdOperaciones();
	            bdOperaciones.abrirConexion();
	            EjecutarJF ejecutar = new EjecutarJF();
	            
	            ejecutar.setVisible(true);
	        } else if (rol.equals("user")) {
	            JOptionPane.showMessageDialog(null, "Eres user");
	        } else {
	            JOptionPane.showMessageDialog(null, "Rol no reconocido");
	        }
	    } else {
        	tFUserName.setBackground(new Color(255, 0, 0, 100));
        	passwordField.setBackground(new Color(255, 0, 0, 100));
        	
            JOptionPane.showMessageDialog(null, "Credenciales inválidas. Inténtalo de nuevo.");
	    }

		
	}
	public void volver() {
		EjecutarJF volver = new EjecutarJF();
		volver.setVisible(true);
		
		dispose();
}
	
	}


