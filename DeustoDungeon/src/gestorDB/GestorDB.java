package gestorDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import datos.Aliado;
import datos.Arma;
import datos.Armadura;
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
	
	public void guardarAliado(Unidad unidad, String nombreArma, String tipoArma, String casco, String pechera) throws SQLException {
		String sql = "INSERT INTO unidad (nombre, descripcion, nivel, atkFis, atkMag, vida, defFis, defMag, nombreArma, tipoArma, casco, pechera) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1,unidad.getNom());
		stmt.setString(2,unidad.getDescripcion());
		stmt.setInt(3,unidad.getNivel());
		stmt.setInt(4,unidad.getAtkFis());
		stmt.setInt(5,unidad.getAtkMag());
		stmt.setInt(6,unidad.getVida());
		stmt.setInt(7,unidad.getDefFis());
		stmt.setInt(8,unidad.getDefMag());
		stmt.setString(9, nombreArma);
		stmt.setString(10, tipoArma);
		stmt.setString(11, casco);
		stmt.setString(12, pechera);
		
		stmt.executeUpdate();
		
		System.out.println("aliado guardado");
	
	}
	/*public void guardarCasco(Casco casco)throws SQLException{
		String sql = "INSERT INTO casco (nombre, descripcion, buffVida, buffDefFis, buffDefMag) VALUES(?,?,?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		
	}
	*/
	public void guardarArmadura(Armadura armadura,String tipo) throws SQLException{
		String sql = "INSERT INTO " +tipo+" (nombre, descripcion, buffVida, buffDefFis, buffDefMag) VALUES(?,?,?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		
			stmt.setString(1, armadura.getNom());
			stmt.setString(2, armadura.getDescripcion());
			stmt.setInt(3, armadura.getBuffVida());
			stmt.setInt(4, armadura.getBuffDefFis());
			stmt.setInt(5, armadura.getBuffDefMag());
		if(tipo=="pechera") {
			stmt.executeUpdate();
			System.out.println("pechera guardado");
		}else if(tipo=="casco") {
			stmt.executeUpdate();
			System.out.println("casco guardado");
		}else{
			System.out.println("ese tipo de armadura no existe");
		}
		
	}
	
	public void guardarArma(Arma arma, String tipo) throws SQLException {
		String sql = "INSERT INTO "+tipo+" (nombre, descripcion, buffAtkFis, buffAtkMag) VALUES (?,?,?,?)";
		
		PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, arma.getNom());
			stmt.setString(2, arma.getDescripcion());
			stmt.setInt(3, arma.getBuffAtkFis());
			stmt.setInt(4, arma.getBuffAtkMag());
		if(tipo=="baston") {
			stmt.execute();
			System.out.println("baston guardado");
		}else if(tipo=="espada"){
			stmt.execute();
			System.out.println("espada guardada");
		}else if(tipo=="arco") {
			stmt.execute();
			System.out.println("arco guardado");
		}else {
			System.out.println("no existe ese tipo de arma");
		}
	}
	
	public Aliado obtenerAliado () {
		
		
		
		
		return null;
	}
	
	public  Unidad obtenerUnidad(int i) {
		
		
		return null;
	}

	public Arma obtenerArma(String tipoArma, String nombreArma) {
		
		return null;
	}
	
	public Armadura obtenerArmadura(String tipoArmadura, String nombreArmadura) {
		
		return null;
	}
}
