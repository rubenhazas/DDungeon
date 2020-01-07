package gestorDB;

import java.sql.Connection;
import java.sql.SQLException;

import datos.Arma;
import datos.Armadura;
import datos.Casco;
import datos.Unidad;

public class BDpruebas {

	static GestorDB miDB;
	public static void main(String[] args)throws SQLException, ClassNotFoundException {
		miDB= new GestorDB();
		miDB.conectar();
		Armadura casco = new Armadura("Pechera elfica","pechera elfica",40,3,5);
		
		miDB.guardarArmadura(casco,"casco");
		System.out.println("guardado");
		miDB.desconectar();
		
	}

}
