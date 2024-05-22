package gc.bd;

import gc.beans.Cliente;
import gc.beans.Pedido;


import java.io.FileInputStream;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.JOptionPane;


public class BdOperaciones {

	public BdOperaciones() {
		
		super();
		// TODO Apéndice de constructor generado automaticamente
	}
	  private static String dbUrl;
	  private static String dbUser;
	  private static String dbPassword;



	private Connection conexion;

	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";//  

	 
	private String sentenciaSql;

	private PreparedStatement pStmt;



	public void abrirConexion() {
		
		try {
		    Properties propiedades = new Properties();
		    FileInputStream archivoConfiguracion = null;

		    try {
		        archivoConfiguracion = new FileInputStream("/Users/moi.am/eclipse-workspace/Gestión de clientes/GestionClientes/src/properties/practica.properties");
		        propiedades.load(archivoConfiguracion);
		        // Obtener los valores de configuración y asignarlos a las variables de instancia
		        dbUrl = propiedades.getProperty("db.url");
		        dbUser = propiedades.getProperty("db.user");
		        dbPassword = propiedades.getProperty("db.password");
		    } catch (IOException ex) {
		        ex.printStackTrace();
		        JOptionPane.showMessageDialog(null, "No se ha encontrado el archivo 'practica.properties' en la ruta", "Error", JOptionPane.WARNING_MESSAGE);

		    } finally {
		        if (archivoConfiguracion != null) {
		            try {
		                archivoConfiguracion.close();
		                
		            } catch (IOException ex) {
		                ex.printStackTrace();
		                JOptionPane.showMessageDialog(null, "'practica.properties' no contiene las credenciales de administrador para realizar conexión con la base de datos", "Advertencia", JOptionPane.WARNING_MESSAGE);
		            }
		        }
		    }
			Class.forName(DRIVER);
			conexion = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
		} catch (Exception e) {
			System.out.print("Error al cargar el controlador:");
			e.printStackTrace();
		}
	}
	

	public void cerrarConexion() {
		try {
			conexion.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void abrirTransaccion() {
		try {
			
		conexion.setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void hacerCommit() {
		try {
			conexion.commit();
			conexion.setAutoCommit(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void hacerRollback() {
		try {
			conexion.rollback();
			conexion.setAutoCommit(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void altaCliente(Cliente cliente) {
	
		sentenciaSql = "insert into clientes(dni, nombre, apellido1, apellido2, edad) values (?,?,?,?,?)";
		try {
			pStmt = conexion.prepareStatement(sentenciaSql);
			pStmt.setString(1, cliente.getDni());
			pStmt.setString(2, cliente.getNombre());
			pStmt.setString(3, cliente.getApellido1());
			pStmt.setString(4, cliente.getApellido2());
			pStmt.setInt(5, cliente.getEdad());
			pStmt.executeUpdate();
			pStmt.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
			
	
	
	

	public void modifCliente(Cliente cliente) {
		sentenciaSql = "update clientes set nombre=?, apellido1=?, apellido2=?, edad=? where dni=?";
		try {
			pStmt = conexion.prepareStatement(sentenciaSql);
			pStmt.setString(1, cliente.getNombre());
			pStmt.setString(2, cliente.getApellido1());
			pStmt.setString(3, cliente.getApellido2());
			pStmt.setInt(4, cliente.getEdad());
			pStmt.setString(5, cliente.getDni());
			pStmt.executeUpdate();
			pStmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void bajaCliente(String dni) {
		sentenciaSql = "delete from clientes where dni=?";
		try {
			pStmt = conexion.prepareStatement(sentenciaSql);
			pStmt.setString(1, dni);
			pStmt.executeUpdate();
			pStmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Cliente consultaClientePorDni(String dni) {
		sentenciaSql = "select dni, nombre, apellido1, apellido2, edad from clientes where dni=?";
		Cliente cliente = new Cliente();
		try {
			pStmt = conexion.prepareStatement(sentenciaSql);
			pStmt.setString(1, dni);
			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {
				cliente.setDni(rs.getString("dni"));
				cliente.setNombre(rs.getString("nombre"));
				cliente.setApellido1(rs.getString("apellido1"));
				cliente.setApellido2(rs.getString("apellido2"));
				cliente.setEdad(rs.getInt("edad"));
			}
			rs.close();
			pStmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cliente;
	}

	public List<Cliente> consultaClientePorApellido1(String apellido1) {
		sentenciaSql = "select dni, nombre, apellido1, apellido2, edad from clientes where apellido1 like ?";
		Cliente cliente = null;
		List<Cliente> listClientes = new ArrayList<Cliente>();
		try {
			String cadena = apellido1.concat("%");
			pStmt = conexion.prepareStatement(sentenciaSql);
			pStmt.setString(1, cadena);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				cliente = new Cliente();
				cliente.setDni(rs.getString("dni"));
				cliente.setNombre(rs.getString("nombre"));
				cliente.setApellido1(rs.getString("apellido1"));
				cliente.setApellido2(rs.getString("apellido2"));
				cliente.setEdad(rs.getInt("edad"));
				listClientes.add(cliente);
			}
			rs.close();
			pStmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listClientes;
	}

	public List<Cliente> listadoClientes() {
		sentenciaSql = "select dni, nombre, apellido1, apellido2, edad from clientes";
		Cliente cliente = null;
		List<Cliente> listClientes = new ArrayList<Cliente>();
		try {
			pStmt = conexion.prepareStatement(sentenciaSql);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				cliente = new Cliente();
				cliente.setDni(rs.getString("dni"));
				cliente.setNombre(rs.getString("nombre"));
				cliente.setApellido1(rs.getString("apellido1"));
				cliente.setApellido2(rs.getString("apellido2"));
				cliente.setEdad(rs.getInt("edad"));
				listClientes.add(cliente);
			}
			rs.close();
			pStmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listClientes;
	}
	
	public boolean verificarClienteExistente(String dni) {
	    try {
	        String sql = "SELECT COUNT(*) FROM clientes WHERE dni = ?";
	        PreparedStatement statement = conexion.prepareStatement(sql);
	        statement.setString(1, dni);
	        ResultSet resultSet = statement.executeQuery();
	        resultSet.next();
	        int count = resultSet.getInt(1);
	        resultSet.close();
	        statement.close();

	        if (count > 0) {
	            // El cliente ya existe
	            return true;
	        } else {
	            // El cliente no existe
	            return false;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public void altaPedido(Pedido pedido) {
		sentenciaSql = "insert into pedidos(dni, num_pedido, detalle_pedido, importe) values (?,?,?,?)";
		try {
			pStmt = conexion.prepareStatement(sentenciaSql);
			pStmt.setString(1, pedido.getDni());
			pStmt.setInt(2, pedido.getNumPedido());
			pStmt.setString(3, pedido.getDetallePedido());
			pStmt.setFloat(4, pedido.getImporte());
			pStmt.executeUpdate();
			pStmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}

	public void modifPedido(Pedido pedido) {
		sentenciaSql = "update pedidos set detalle_pedido=?, importe=? where dni=? and num_pedido=?";
		try {
			pStmt = conexion.prepareStatement(sentenciaSql);
	        pStmt.setString(1, pedido.getDetallePedido());
	        pStmt.setFloat(2, pedido.getImporte());
	        pStmt.setString(3, pedido.getDni());
	        pStmt.setInt(4, pedido.getNumPedido());
			pStmt.executeUpdate();
			pStmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void bajaPedido(String dni, int numPedido) {
		sentenciaSql = "delete from pedidos where dni=? and num_pedido=?";
		try {
			pStmt = conexion.prepareStatement(sentenciaSql);
			pStmt.setString(1, dni);
			pStmt.setInt(2, numPedido);
			pStmt.executeUpdate();
			pStmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Pedido consultaPedidoPorDniNumPedido(String dni, int numPedido) {
		sentenciaSql = "SELECT detalle_pedido, importe from pedidos where dni=? and num_pedido=?";
		Pedido pedido = new Pedido();
		try {
			pStmt = conexion.prepareStatement(sentenciaSql);
			pStmt.setString(1, dni);
			pStmt.setInt(2, numPedido);
			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {
				pedido.setDni(dni);
				pedido.setNumPedido(numPedido);
				pedido.setDetallePedido(rs.getString(1));
				pedido.setImporte(rs.getFloat(2));
			}
			rs.close();
			pStmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pedido;
	}

	public List<Pedido> consultaPedidoPorDni(String dni) {
	    sentenciaSql = "SELECT num_pedido, detalle_pedido, importe FROM pedidos WHERE dni=?";
	    List<Pedido> listPedidos = new ArrayList<Pedido>();
	    try {
	        pStmt = conexion.prepareStatement(sentenciaSql);
	        pStmt.setString(1, dni);

	        ResultSet rs = pStmt.executeQuery();
	        while (rs.next()) {
	            Pedido pedido = new Pedido();
	            pedido.setDni(dni);
	            pedido.setNumPedido(rs.getInt("num_pedido"));
	            pedido.setDetallePedido(rs.getString("detalle_pedido"));
	            pedido.setImporte(rs.getFloat("importe"));

	            listPedidos.add(pedido);
	        }
	        rs.close();
	        pStmt.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return listPedidos;
	}

	public List<Pedido> listadoPedidos() {
		sentenciaSql = "select dni, num_pedido, detalle_pedido, importe from pedidos";
		Pedido pedido = null;
		List<Pedido> listPedidos = new ArrayList<Pedido>();
		try {
			pStmt = conexion.prepareStatement(sentenciaSql);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				pedido = new Pedido();
				pedido.setDni(rs.getString("dni"));
				pedido.setNumPedido(rs.getInt("num_pedido"));
				pedido.setDetallePedido(rs.getString("detalle_pedido"));
				pedido.setImporte(rs.getFloat("importe"));
				listPedidos.add(pedido);
			}
			rs.close();
			pStmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listPedidos;
	}
	public List<Pedido> listadoImportePedidos() {
		sentenciaSql = "select importe from pedidos";
		Pedido pedido = null;
		List<Pedido> listPedidos = new ArrayList<Pedido>();
		try {
			pStmt = conexion.prepareStatement(sentenciaSql);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				pedido = new Pedido();
				pedido.setImporte(rs.getFloat("importe"));
				listPedidos.add(pedido);
			}
			rs.close();
			pStmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listPedidos;
	}


	public boolean verificarPedidoExistente(String dni, int numeroPedido) {
	    try {

	        String sql = "SELECT COUNT(*) FROM pedidos WHERE dni = ? AND num_pedido = ?";
	        PreparedStatement statement = conexion.prepareStatement(sql);
	        statement.setString(1, dni);
	        statement.setInt(2, numeroPedido);
	        ResultSet resultSet = statement.executeQuery();
	        resultSet.next();
	        int count = resultSet.getInt(1);
	        resultSet.close();
	        statement.close();

	        if (count > 0) {
	            // El pedido ya existe
	            return true;
	        } else {
	            // El cliente no existe en la base de datos
	            return false;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}



}




/*#Sun Jun 11 18:47:21 CEST 2023
db.url=jdbc\:mysql\://localhost/practica

db.user=root
db.password=admin123

db.user.invitado=invitado
db.password.invitado=invitado*/