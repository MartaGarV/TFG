package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import tablas.Empleado;

public class AccesoDB {
	
	public static Connection conexion() {
		
		// Paso 1: Cargar el driver
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection con = null;

		// Paso 2: Establecer conexion con la base de datos
		try {
			con = DriverManager.getConnection("jdbc:mysql://85.10.205.173:3306/tfg_domhogar",
					"tfg_domhogar", "tfg_domhogar");			
		} catch (SQLException e) {			
			e.getMessage();
			return null;
		}		
		return  con;
	}

	public static void cerrarConexion(Connection conexion) {
		
		try {
			conexion.close();
		} catch (SQLException e) {
			e.getMessage();
			return;
		}		
	}
	/*private String nombre, apellidos, email, nif, usuario, contrasena;
	private int telefono;*/

	public static ArrayList<Empleado> datosEmpleado(Connection conexion ) {

		ArrayList<Empleado> lista_empleados = new ArrayList<Empleado>();
		
		Empleado empleado;
		
		try {			

			Statement sentencia = conexion.createStatement(); // Creamos sentencia con Statement
			// Consulta SQL con resulset
			ResultSet rs = sentencia.executeQuery("SELECT * FROM EMPLEADO");

			// Mientras haya registros anadimos al ArrayList
			while (rs.next()) { 
				
				String nombre = rs.getString("NOMBRE");
				String apellidos = rs.getString("APELLIDOS");
				String email = rs.getString("EMAIL");
				String nif = rs.getString("NIF_EMPLEADO");
				String usuario = rs.getString("USUARIO");
				String contrasena = rs.getString("CONTRASENA");
				String perfil = rs.getString("PERFIL");
				int telefono = rs.getInt("TELEFONO");
				
				empleado = new Empleado(nombre, apellidos, email, nif, usuario, contrasena, perfil, telefono);
				
				lista_empleados.add(empleado);				
			}
			
		} catch (SQLException e) {
			e.getMessage();
		}

		return lista_empleados;
	}

	public static String[][] obtenerMatrizEmpleados() {
		Connection conexion = AccesoDB.conexion();				
		
		ArrayList<Empleado> listaEmpleados = AccesoDB.datosEmpleado(conexion);
		
		String matrizInfo[][] = new String[listaEmpleados.size()][5];
		
		for (int i = 0; i < listaEmpleados.size(); i++) {
			matrizInfo[i][0] = listaEmpleados.get(i).getNombre()+"";
			matrizInfo[i][1] = listaEmpleados.get(i).getApellidos()+"";
			matrizInfo[i][2] = listaEmpleados.get(i).getEmail()+"";
			matrizInfo[i][3] = listaEmpleados.get(i).getNif()+"";
			matrizInfo[i][4] = listaEmpleados.get(i).getTelefono()+"";
		}		
		return matrizInfo;
	}	
}
