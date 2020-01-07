package gestorDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import datos.Aliado;
import datos.Arma;
import datos.Armadura;
import datos.Casco;
import datos.Pechera;
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
		String sql = "INSERT INTO aliado (nombre, raza, descripcion, atkFis, atkMag, vida, defFis, defMag, nombreArma, tipoArma, casco, pechera) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1,unidad.getNom());
		stmt.setString(2, unidad.getRaza());
		stmt.setString(3,unidad.getDescripcion());
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
		String sql = "INSERT INTO armas (nombre, tipo, descripcion, buffAtkFis, buffAtkMag) VALUES (?,?,?,?,?)";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, arma.getNom());
			stmt.setString(2, tipo);
			stmt.setString(3, arma.getDescripcion());
			stmt.setInt(4, arma.getBuffAtkFis());
			stmt.setInt(5, arma.getBuffAtkMag());
			stmt.execute();
	}
	
	public Aliado obtenerAliado (String nombre) throws SQLException {
		String sql = ("SELECT * FROM aliado where nombre = ?");
		PreparedStatement stmt= conn.prepareStatement(sql);
		
		Aliado a = new Aliado();
		try {
		stmt.setString(1,nombre);
		
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			a.setNom(rs.getString("nombre"));
			a.setDescripcion(rs.getString("descripcion"));
			a.setAtkFis(rs.getInt("atkFis"));
			a.setAtkMag(rs.getInt("atkMag"));
			a.setVida(rs.getInt("vida"));
			a.setDefFis(rs.getInt("defFis"));
			a.setDefMag(rs.getInt("defMag"));
			a.setArma(obtenerArma(rs.getString("nombreArma")));
			a.setCasco(obtenerCasco(rs.getString("casco")));
			a.setPechera(obtenerPechera(rs.getString("pechera")));
		}else {
			return null;
		}
		} catch (Exception e) {
			throw e;
		}finally{
			stmt.close();
		}
		return a;
	}
	
	public Unidad obtenerUnidad(int i) throws SQLException {
		
		Unidad u = new Unidad(); 
		
		String sql =("SELECT * FROM unidad WHERE index = ?");
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		try {
			stmt.setInt(1, i);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				u.setNom(rs.getString("nombre"));
				u.setRaza(rs.getString("raza"));
				u.setDescripcion(rs.getString("descripcion"));
				u.setAtkFis(rs.getInt("atkFis"));
				u.setAtkMag(rs.getInt("atkMag"));
				u.setVida(rs.getInt("vida"));
				u.setDefFis(rs.getInt("defFis"));
				u.setDefMag(rs.getInt("defMag"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally {
			stmt.close();
		}
		return u;
	}

	public Arma obtenerArma(String nombreArma) throws SQLException {
		Arma a = new Arma();
		String sql = ("SELECT * FROM armas WHERE nombre = ?" );
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		try {
			stmt.setString(1, nombreArma);
			
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				a.setNom(rs.getString("nombre"));
				a.setTipo(rs.getString("tipo"));
				a.setDescripcion(rs.getString("descripcion"));
				a.setBuffAtkFis(rs.getInt("buffAtkFis"));
				a.setBuffAtkMag(rs.getInt("buffAtkMag"));
				
			}else {
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally {
			stmt.close();
		}
		return a;
	}
	
	public Casco obtenerCasco( String nombreArmadura) throws SQLException {
		Casco c =new Casco();
		
		String sql = ("SELECT * FROM casco WHERE nombre = ?");
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		try {
			stmt.setString(1, nombreArmadura);
			
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				c.setNom(rs.getString("nombre"));
				c.setDescripcion(rs.getString("descripcion"));
				c.setBuffVida(rs.getInt("buffVida"));
				c.setBuffDefFis(rs.getInt("buffDefFis"));
				c.setBuffDefMag(rs.getInt("buffDefMag"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally {
			stmt.close();
		}
		return c;
	}
	
	public Pechera obtenerPechera(String nombrePechera) throws SQLException {
		Pechera p = new Pechera();
		
		String sql=("SELECT * FROM pechera WHERE nombre = ?");
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		try {
			stmt.setString(1, nombrePechera);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				p.setNom(rs.getString("nombre"));
				p.setDescripcion(rs.getString("descripcion"));
				p.setBuffVida(rs.getInt("buffVida"));
				p.setBuffDefFis(rs.getInt("buffDefFis"));
				p.setBuffDefMag(rs.getInt("buffDefMag"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally {
			stmt.close();
		}
		return p;
	}
}
