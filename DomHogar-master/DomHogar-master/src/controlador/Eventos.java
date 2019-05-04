package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import tablas.Empleado;
import vista.Ventana;

public class Eventos implements ActionListener, MouseListener {
	
	private Ventana ventana;
	Connection conexion;

	public Eventos(Ventana ventana) {
		this.ventana = ventana;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Abrimos la conexion con la BBDD
		conexion = AccesoDB.conexion();				
		if (conexion == null)
		return;	
		
		//Evento LOGIN
		if (e.getSource() == ventana.getBotonLogin()) {
			
			//Creamos un ArrayList donde recogeremos el usuario y la contraseña de las tablas
			ArrayList<Empleado> lista_usuarios = new ArrayList<Empleado>();
			
			//Accedemos a la BBDD para recoger los datos del usuario y la contasena
			lista_usuarios = AccesoDB.datosEmpleado(conexion);
			
			//Recogemos los valores que introducimos en las cajas de texto
			String usuario = ventana.getCajaUser().getText();
			
			// Para recoger el password se regoje en una secuencia de chars
			char contrasenaArray[] = ventana.getCajaPass().getPassword();
			
			//Ahora lo pasamos a un String
			String contrasena = new String (contrasenaArray);
			
			if(usuario.equals("") && contrasena.equals("")) {
				ventana.getEtiquetaResulLogin().setText("Please, enter user and password");
			} else if(usuario.equals("")) {
				ventana.getEtiquetaResulLogin().setText("Please, enter user");
			} else if (contrasena.equals("")) {
				ventana.getEtiquetaResulLogin().setText("Please, enter password");
			} else {
				for (Empleado lista : lista_usuarios) {
					
					if(lista.getUsuario().equalsIgnoreCase(usuario) && lista.getContrasena().equalsIgnoreCase(contrasena) && lista.getPerfil().equalsIgnoreCase("general")) {
						//Ocultamos componentes de la pantalla de Login
						ventana.getImagenLogin().setVisible(false);
						ventana.getEtiquetaUser().setVisible(false);
						ventana.getEtiquetaPass().setVisible(false);
						ventana.getCajaUser().setVisible(false);
						ventana.getCajaPass().setVisible(false);
						ventana.getEtiquetaResulLogin().setVisible(false);
						ventana.getBotonLogin().setVisible(false);
						ventana.getBotonExit().setVisible(false);
						//Mostramos los componentes de la aplicacion y redimensionamos la pantalla
						//ventana.setSize(1200, 1000);
						ventana.setExtendedState(Ventana.MAXIMIZED_BOTH);
						ventana.setLayout(null);
						ventana.setLocationRelativeTo(null);
						ventana.getImagenInicio().setVisible(true);
						//ventana.getNombreApp().setBounds(0, 15, 1200, 40);
						ventana.getPanelIzq().setVisible(true);
						ventana.getPanelDer().setVisible(true); 
						ventana.getBotonHR().setVisible(false);
						return;
					} 
					
					else if(lista.getUsuario().equalsIgnoreCase(usuario) && lista.getContrasena().equalsIgnoreCase(contrasena) && lista.getPerfil().equalsIgnoreCase("administrador")) {
						//Ocultamos componentes de la pantalla de Login
						ventana.getImagenLogin().setVisible(false);
						ventana.getEtiquetaUser().setVisible(false);
						ventana.getEtiquetaPass().setVisible(false);
						ventana.getCajaUser().setVisible(false);
						ventana.getCajaPass().setVisible(false);
						ventana.getEtiquetaResulLogin().setVisible(false);
						ventana.getBotonLogin().setVisible(false);
						ventana.getBotonExit().setVisible(false);
						//Mostramos los componentes de la aplicacion y redimensionamos la pantalla
						//ventana.setSize(1200, 1000);
						ventana.setExtendedState(Ventana.MAXIMIZED_BOTH);
						ventana.setLayout(null);
						ventana.setLocationRelativeTo(null);
						ventana.getImagenInicio().setVisible(true);
						//ventana.getNombreApp().setBounds(0, 15, 1200, 40);
						ventana.getPanelIzq().setVisible(true);
						ventana.getPanelDer().setVisible(true); 
						ventana.getBotonHR().setVisible(true);
						return;
					
					}
					
					else {
						ventana.getEtiquetaResulLogin().setText("Incorrect user and/or password");
					}
				}
			}			
			
			//Ponemos foco en el usuarioit
			ventana.getCajaUser().requestFocus();
			return;			
		}	
		
		if (e.getSource() == ventana.getBotonExit()) {
			
			// Pasamos como argumento la conexion a cerrar.
			AccesoDB.cerrarConexion(conexion); 
			//Cerramos la aplicación
			System.exit(0);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource()==ventana.getBotonExitInit()) {
			// Pasamos como argumento la conexion a cerrar.
			AccesoDB.cerrarConexion(conexion); 
			//Cerramos la aplicación
			System.exit(0);
		}
		else if (e.getSource()==ventana.getBotonHR()) {
			ventana.getImagenInicio().setVisible(false);
			ventana.getBarraEmpleados().setVisible(true);
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource()==ventana.getBotonLogin()) {
			 ventana.getBotonLogin().setIcon(new ImageIcon("imagenes/login hover.png"));
			 
		}
		else if (e.getSource()==ventana.getBotonExit()) {
			ventana.getBotonExit().setIcon(new ImageIcon("imagenes/exit_login_hover.png"));
		}
		
		else if (e.getSource()==ventana.getBotonPurchases()) {
			ventana.getBotonPurchases().setIcon(new ImageIcon("img/purchases hover.png"));
			
		}
		
		else if (e.getSource()==ventana.getBotonSales()) {
			ventana.getBotonSales().setIcon(new ImageIcon("img/sales hover.png"));
		}
		
		else if (e.getSource()==ventana.getBotonSuppliers()) {
			ventana.getBotonSuppliers().setIcon(new ImageIcon("img/suppliers hover.png"));
		}
		
		else if (e.getSource()==ventana.getBotonCustomers()) {
			ventana.getBotonCustomers().setIcon(new ImageIcon("img/customers hover.png"));
		}
		
		else if (e.getSource()==ventana.getBotonServices()) {
			ventana.getBotonServices().setIcon(new ImageIcon("img/services hover.png"));
		}
		
		else if (e.getSource()==ventana.getBotonStock()) {
			ventana.getBotonStock().setIcon(new ImageIcon("img/stock hover.png"));
		}
		
		else if (e.getSource()==ventana.getBotonHR()) {
			ventana.getBotonHR().setIcon(new ImageIcon("img/human resources hover.png"));
		}
		
		else if (e.getSource()==ventana.getBotonUser()) {
			ventana.getBotonUser().setIcon(new ImageIcon("img/boton_user hover.png"));
		}
		
		else if (e.getSource()==ventana.getBotonLogout()) {
			ventana.getBotonLogout().setIcon(new ImageIcon("img/logout hover.png"));
		}
		
		else if (e.getSource()==ventana.getBotonExitInit()) {
			ventana.getBotonExitInit().setIcon(new ImageIcon("img/exit hover.png"));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource()==ventana.getBotonLogin()) {
			 ventana.getBotonLogin().setIcon(new ImageIcon("imagenes/login.png"));
		}
		else if (e.getSource()==ventana.getBotonExit()) {
			ventana.getBotonExit().setIcon(new ImageIcon("imagenes/exit_login.png"));
		}
		
		else if (e.getSource()==ventana.getBotonPurchases()) {
			ventana.getBotonPurchases().setIcon(new ImageIcon("img/purchases.png"));
		}
		
		else if (e.getSource()==ventana.getBotonSales()) {
			ventana.getBotonSales().setIcon(new ImageIcon("img/sales.png"));
		}
		
		else if (e.getSource()==ventana.getBotonSuppliers()) {
			ventana.getBotonSuppliers().setIcon(new ImageIcon("img/suppliers.png"));
		}
		
		else if (e.getSource()==ventana.getBotonCustomers()) {
			ventana.getBotonCustomers().setIcon(new ImageIcon("img/customers.png"));
		}
		
		else if (e.getSource()==ventana.getBotonServices()) {
			ventana.getBotonServices().setIcon(new ImageIcon("img/services.png"));
		}
		
		else if (e.getSource()==ventana.getBotonStock()) {
			ventana.getBotonStock().setIcon(new ImageIcon("img/stock.png"));
		}
		
		else if (e.getSource()==ventana.getBotonHR()) {
			ventana.getBotonHR().setIcon(new ImageIcon("img/human resources.png"));
		}
		
		else if (e.getSource()==ventana.getBotonUser()) {
			ventana.getBotonUser().setIcon(new ImageIcon("img/boton_user.png"));
		}
		
		else if (e.getSource()==ventana.getBotonLogout()) {
			ventana.getBotonLogout().setIcon(new ImageIcon("img/logout.png"));
		}
		
		else if (e.getSource()==ventana.getBotonExitInit()) {
			ventana.getBotonExitInit().setIcon(new ImageIcon("img/exit.png"));
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getSource()==ventana.getBotonLogin()) {		 
			 ventana.getBotonLogin().setIcon(new ImageIcon("imagenes/login press.png"));
			 ventana.getBotonLogin().setContentAreaFilled(false);
			 
			 
		}
		else if (e.getSource()==ventana.getBotonExit()) {
			ventana.getBotonExit().setIcon(new ImageIcon("imagenes/exit login press.png"));
			ventana.getBotonExit().setContentAreaFilled(false); 
			
		}
		
		else if (e.getSource()==ventana.getBotonPurchases()) {
			ventana.getBotonPurchases().setIcon(new ImageIcon("img/purchases press.png"));
			ventana.getBotonPurchases().setContentAreaFilled(false);
			
			
		}
		
		else if (e.getSource()==ventana.getBotonSales()) {
			ventana.getBotonSales().setIcon(new ImageIcon("img/sales press.png"));
			ventana.getBotonSales().setContentAreaFilled(false);
		}
		
		else if (e.getSource()==ventana.getBotonSuppliers()) {
			ventana.getBotonSuppliers().setIcon(new ImageIcon("img/suppliers press.png"));
			ventana.getBotonSuppliers().setContentAreaFilled(false);
		}
		
		else if (e.getSource()==ventana.getBotonCustomers()) {
			ventana.getBotonCustomers().setIcon(new ImageIcon("img/customers press.png"));
			ventana.getBotonCustomers().setContentAreaFilled(false);
		}
		
		else if (e.getSource()==ventana.getBotonServices()) {
			ventana.getBotonServices().setIcon(new ImageIcon("img/services press.png"));
			ventana.getBotonServices().setContentAreaFilled(false);
		}
		
		else if (e.getSource()==ventana.getBotonStock()) {
			ventana.getBotonStock().setIcon(new ImageIcon("img/stock press.png"));
			ventana.getBotonStock().setContentAreaFilled(false);
		}
		
		else if (e.getSource()==ventana.getBotonHR()) {
			ventana.getBotonHR().setIcon(new ImageIcon("img/human resources press.png"));
			ventana.getBotonHR().setContentAreaFilled(false);
		}
		
		else if (e.getSource()==ventana.getBotonUser()) {
			ventana.getBotonUser().setIcon(new ImageIcon("img/boton_user press.png"));
			ventana.getBotonUser().setContentAreaFilled(false);
		}
		
		else if (e.getSource()==ventana.getBotonLogout()) {
			ventana.getBotonLogout().setIcon(new ImageIcon("img/logout press.png"));
			ventana.getBotonLogout().setContentAreaFilled(false);
		}
		
		else if (e.getSource()==ventana.getBotonExitInit()) {
			ventana.getBotonExitInit().setIcon(new ImageIcon("img/exit press.png"));
			ventana.getBotonExitInit().setContentAreaFilled(false);
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getSource()==ventana.getBotonLogin()) {
			 ventana.getBotonLogin().setIcon(new ImageIcon("imagenes/login.png"));
		}
		else if (e.getSource()==ventana.getBotonExit()) {
			ventana.getBotonExit().setIcon(new ImageIcon("imagenes/exit_login.png"));
		}		
		else if (e.getSource()==ventana.getBotonPurchases()) {
			ventana.getBotonPurchases().setIcon(new ImageIcon("img/purchases.png"));
		}		
		else if (e.getSource()==ventana.getBotonSales()) {
			ventana.getBotonSales().setIcon(new ImageIcon("img/sales.png"));
		}		
		else if (e.getSource()==ventana.getBotonSuppliers()) {
			ventana.getBotonSuppliers().setIcon(new ImageIcon("img/suppliers.png"));
		}		
		else if (e.getSource()==ventana.getBotonCustomers()) {
			ventana.getBotonCustomers().setIcon(new ImageIcon("img/customers.png"));
		}		
		else if (e.getSource()==ventana.getBotonServices()) {
			ventana.getBotonServices().setIcon(new ImageIcon("img/services.png"));
		}		
		else if (e.getSource()==ventana.getBotonStock()) {
			ventana.getBotonStock().setIcon(new ImageIcon("img/stock.png"));
		}		
		else if (e.getSource()==ventana.getBotonHR()) {
			ventana.getBotonHR().setIcon(new ImageIcon("img/human resources.png"));
		}		
		else if (e.getSource()==ventana.getBotonUser()) {
			ventana.getBotonUser().setIcon(new ImageIcon("img/boton_user.png"));
		}		
		else if (e.getSource()==ventana.getBotonLogout()) {
			ventana.getBotonLogout().setIcon(new ImageIcon("img/logout.png"));
		}		
		else if (e.getSource()==ventana.getBotonExitInit()) {
			ventana.getBotonExitInit().setIcon(new ImageIcon("img/exit.png"));
		}		
	}	
}

