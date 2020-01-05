package gestorDB;

import java.sql.Connection;
import java.sql.SQLException;

import datos.Arma;
import datos.Unidad;

public class BDpruebas {

	static GestorDB miDB;
	public static void main(String[] args)throws SQLException, ClassNotFoundException {
		miDB= new GestorDB();
		miDB.conectar();
		Unidad unidad;
		
		//unidad= new Aliado("prueba2","descripcion de prueba 2", 1, 1, 1, 100,10, 10);
		miDB.guardarArma(new Arma("a","a","", 1,1), "espada");
		System.out.println("guardado");	
		miDB.desconectar();
	}

}
