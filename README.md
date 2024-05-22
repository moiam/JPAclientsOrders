# JPAclientsOrders

Aplicación para la gestión de clientes y pedidos, mediante el uso de MySQL como base de datos e implementación de JPA con Hibernate para la validación de usuarios y roles.

## Instalación

Es necesario y básico tener tu base de datos MySQL, así como configurar en el archivo practica.properties tu variables de entorno para la comunicación con tu base de datos (Url, user y password).


He adjuntado en libsJPA las librerías para JPA con Hibernate y dentro también he incluido el mysql-connector en su version 8.0.32.
En el proyecto se han incluido las librerías necesarias para la creación de gráficos.




## Uso

Una vez configurada la ruta de la base de datos y las variables de entorno, el código de momento está hecho para que puedas entrar con el rol admin.

## Capturas de Pantalla
El login
![Captura de Pantalla 1](/imagesREADME/login.png)
Sección de facturas
![Captura de Pantalla 2](/imagesREADME/Bills.png)
Interfaz para agregar clientes con opciones de eliminación, modificación y consulta.
![Captura de Pantalla 2](/imagesREADME/addClient.png)
Gráfico de barras con clientes y cantidad en € de pedidos 
![Captura de Pantalla 2](/imagesREADME/Graphs.png)

## Ejemplos de Código
En el login como puede verse se comprueba el rol correspondiente al usuario para el acceso en el programa. Aún no se han implementado las funciones de un usuario invitado, pero si sus validaciones.
```java
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
