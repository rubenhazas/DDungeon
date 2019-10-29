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
		String sql = "INSERT INTO unidad (nombre, descripcion, nivel, atkFis, atkMag, vida, defFis, defMag) VALUES(?,?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1,unidad.getNom());
		stmt.setString(2,unidad.getDescripcion());
		stmt.setInt(3,unidad.getNivel());
		stmt.setInt(4,unidad.getAtkFis());
		stmt.setInt(5,unidad.getAtkMag());
		stmt.setInt(6,unidad.getVida());
		stmt.setInt(7,unidad.getDefFis());
		stmt.setInt(8,unidad.getDefMag());
	}
	

}
