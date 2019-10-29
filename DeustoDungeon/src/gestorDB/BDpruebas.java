package gestorDB;

import java.sql.Connection;
import java.sql.SQLException;

import datos.Unidad;

public class BDpruebas {

	static GestorDB miDB;
	public static void main(String[] args)throws SQLException, ClassNotFoundException {
		
		miDB= new GestorDB();
		miDB.conectar();
		Unidad unidad;
		
		unidad= new Unidad("prueba","descripcion de prueba", 1, 1, 1, 100,10, 10, "prueba de pasiva" );
		miDB.guardarUnidad(unidad);
		System.out.println("guardado");	
		miDB.desconectar();
	}

}
