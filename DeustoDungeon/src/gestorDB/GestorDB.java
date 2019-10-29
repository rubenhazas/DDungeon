package gestorDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import datos.Unidad;

public class GestorDB {


	private Connection conn;
	
	public void conectar() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		
		conn= DriverManager.getConnection("jdbc:sqlite:DB/DB.db");
	}
	
	public void desconectar() throws SQLException{
		
		conn.close();
	}
	
	public void guardarUnidad(Unidad unidad) throws SQLException {
		String sql = "INSERT INTO unidad (nombre, descripcion, nivel, atkFis, arkMag, vida, defFis, defMag) VALUES(?,?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, unidad.getNom());
		
	}
	static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
