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
		Arma arma = new Arma("Arco elfico","arco","Arco elfico",6,0);
		
		miDB.guardarArma(arma);
		System.out.println("guardado");	
		miDB.desconectar();
		
	}

}
