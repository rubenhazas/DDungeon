package gestorDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import objetos.Arma;
import objetos.Armadura;
import objetos.Casco;
import objetos.Pechera;
import unidades.Aliado;
import unidades.Unidad;
import usuario.User;

public class GestorDB {

	private Logger logger = Logger.getLogger(GestorDB.class.getName());

	private Connection conn;

	public void conectar() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");

		logger.log(Level.INFO, "Conectado con la BD");

		conn = DriverManager.getConnection("jdbc:sqlite:DB/DB.db");

	}

	public void desconectar() throws SQLException {
		conn.close();
	}

	/*
	 * Metodo para guardar aliados en la BD
	 */
	public void guardarAliado(Unidad unidad, String nombreArma, String tipoArma, String casco, String pechera)
			throws SQLException {
		String sql = "INSERT INTO aliado (nombre, raza, descripcion, atkFis, atkMag, vida, defFis, defMag, nombreArma, tipoArma, casco, pechera) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, unidad.getNom());
		stmt.setString(2, unidad.getRaza());
		stmt.setString(3, unidad.getDescripcion());
		stmt.setInt(4, unidad.getAtkFis());
		stmt.setInt(5, unidad.getAtkMag());
		stmt.setInt(6, unidad.getVida());
		stmt.setInt(7, unidad.getDefFis());
		stmt.setInt(8, unidad.getDefMag());
		stmt.setString(9, nombreArma);
		stmt.setString(10, tipoArma);
		stmt.setString(11, casco);
		stmt.setString(12, pechera);

		stmt.executeUpdate();

		logger.log(Level.INFO, "Aliado guardado");

	}

	/*
	 * Metodo para guardar enemigos en la BD
	 */
	public void guardarUnidad(Unidad unidad, int i) throws SQLException {

		String sql = "INSERT INTO unidad (numero, nombre, descripcion, atkFis, atkMag, vida, defFis, defMag, raza) VALUES (?,?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, i);
		stmt.setString(2, unidad.getNom());
		stmt.setString(3, unidad.getDescripcion());
		stmt.setInt(4, unidad.getAtkFis());
		stmt.setInt(5, unidad.getAtkMag());
		stmt.setInt(6, unidad.getVida());
		stmt.setInt(7, unidad.getDefFis());
		stmt.setInt(8, unidad.getDefMag());
		stmt.setString(9, unidad.getRaza());
		stmt.executeUpdate();

		logger.log(Level.INFO, "Enemigo guardado");
	}

	/*
	 * Metodo para rellenar el JList de aliados en la ventana de seleccion de
	 * personaje
	 */
	public List<String> obtenerAliados() throws SQLException {
		String nombre = "";
		List<String> nombres = new ArrayList<String>();
		String sql = "SELECT nombre FROM aliado";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			nombre = rs.getString("nombre");
			nombres.add(nombre);
		}
		logger.log(Level.INFO, "Obteniendo aliados");
		return nombres;
	}

	/*
	 * Metodo para eliminar personajes
	 */
	public void eliminarAliado(String nombre) throws SQLException {
		String sql = "DELETE FROM aliado WHERE nombre = ?";

		PreparedStatement stmt = conn.prepareStatement(sql);

		stmt.setString(1, nombre);

		stmt.executeUpdate();
		logger.log(Level.INFO, "Personaje eliminado");
	}

	/*
	 * Metodo para eliminar enemigos
	 */
	public void eliminarUnidad(String nombre) throws SQLException {
		String sql = "DELETE FROM unidad WHERE nombre = ?";

		PreparedStatement stmt = conn.prepareStatement(sql);

		stmt.setString(1, nombre);

		stmt.executeUpdate();
		logger.log(Level.INFO, "Enemigo eliminado");
	}

	/*
	 * Metodo para rellenar el JList de enemigos en la ventana de admin
	 */
	public List<String> obtenerEnemigos() throws SQLException {
		String nombre = "";
		List<String> nombres = new ArrayList<String>();
		String sql = "SELECT nombre FROM unidad";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			nombre = rs.getString("nombre");
			nombres.add(nombre);
		}
		logger.log(Level.INFO, "Obteniendo enemigos");
		return nombres;
	}

	/*
	 * Metodo para rellenar el JList de armas de la ventana que muestra las armas de
	 * la BD
	 */
	public List<String> getArmas() throws SQLException {
		String nombre = "";
		List<String> nombres = new ArrayList<String>();
		String sql = "SELECT nombre FROM armas";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			nombre = rs.getString("nombre");
			nombres.add(nombre);
		}
		logger.log(Level.INFO, "Obteniendo armas");
		return nombres;

	}

	/*
	 * Metodo para rellenar el JList de cascos de la ventana que muestra los cascos
	 * de la BD
	 */
	public List<String> getCascos() throws SQLException {
		String nombre = "";
		List<String> nombres = new ArrayList<String>();
		String sql = "SELECT nombre FROM casco";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			nombre = rs.getString("nombre");
			nombres.add(nombre);
		}
		logger.log(Level.INFO, "Obteniendo cascos");
		return nombres;

	}

	/*
	 * Metodo para rellenar el JList de pecheras de la ventana que muestra las
	 * pecheras de la BD
	 */
	public List<String> getPecheras() throws SQLException {
		String nombre = "";
		List<String> nombres = new ArrayList<String>();
		String sql = "SELECT nombre FROM pechera";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			nombre = rs.getString("nombre");
			nombres.add(nombre);
		}
		logger.log(Level.INFO, "Obteniendo pecheras");
		return nombres;

	}

	/*
	 * Metodo para guardar armaduras en la BD El metodo recibe una armadura que es
	 * la clase de la que heredan los dos tipos de armaduras y el tipo de la
	 * armadura ya sea casco o pechera
	 */
	public void guardarArmadura(Armadura armadura, String tipo) throws SQLException {
		String sql = "INSERT INTO " + tipo
				+ " (nombre, descripcion, buffVida, buffDefFis, buffDefMag) VALUES(?,?,?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(sql);

		stmt.setString(1, armadura.getNom());
		stmt.setString(2, armadura.getDescripcion());
		stmt.setInt(3, armadura.getBuffVida());
		stmt.setInt(4, armadura.getBuffDefFis());
		stmt.setInt(5, armadura.getBuffDefMag());
		if (tipo == "pechera") {
			stmt.executeUpdate();
			logger.log(Level.INFO, "Pechera guardada");
		} else if (tipo == "casco") {
			stmt.executeUpdate();
			logger.log(Level.INFO, "Casco guardado");
		} else {
			logger.log(Level.WARNING, "No existe ese tipo de armadura");
		}

	}

	/*
	 * Metodo para guardar armas en la BD
	 */
	public void guardarArma(Arma arma) throws SQLException {
		String sql = "INSERT INTO armas (nombre, tipo, descripcion, buffAtkFis, buffAtkMag) VALUES (?,?,?,?,?)";

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, arma.getNom());
		stmt.setString(2, arma.getTipo());
		stmt.setString(3, arma.getDescripcion());
		stmt.setInt(4, arma.getBuffAtkFis());
		stmt.setInt(5, arma.getBuffAtkMag());
		stmt.execute();
		logger.log(Level.INFO, "Arma guardada");
	}

	/*
	 * Metodo para obtener aliados de la BD
	 */
	public Aliado obtenerAliado(String nombre) throws SQLException {
		String sql = ("SELECT nombre, descripcion, atkFis, atkMag, vida, defFis, defMag, nombreArma, casco, pechera FROM aliado WHERE nombre = ?");
		PreparedStatement stmt = conn.prepareStatement(sql);

		Aliado a = new Aliado();
		try {
			stmt.setString(1, nombre);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
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
			} else {
				logger.log(Level.INFO, "El aliado que quieres obtener no existe");
				return null;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			stmt.close();
		}
		logger.log(Level.INFO, "Obteniendo aliado");
		return a;
	}

	/*
	 * Metodo para obtener los enemigos de la BD
	 */
	public Unidad obtenerUnidad(int i) throws SQLException {

		Unidad u = new Unidad();

		String sql = ("SELECT nombre, raza, descripcion, atkFis, atkMag, vida, defFis, defMag FROM unidad WHERE numero = ?");
		PreparedStatement stmt = conn.prepareStatement(sql);

		try {
			stmt.setInt(1, i);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
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
			throw e;
		} finally {
			stmt.close();
		}
		logger.log(Level.INFO, "Obteniendo enemigo");
		return u;
	}

	/*
	 * Metodo para obtener un arma de la BD
	 */
	public Arma obtenerArma(String nombreArma) throws SQLException {
		Arma a = new Arma();
		String sql = ("SELECT nombre, tipo, descripcion, buffAtkFis, buffAtkMag FROM armas WHERE nombre = ?");
		PreparedStatement stmt = conn.prepareStatement(sql);

		try {
			stmt.setString(1, nombreArma);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				a.setNom(rs.getString("nombre"));
				a.setTipo(rs.getString("tipo"));
				a.setDescripcion(rs.getString("descripcion"));
				a.setBuffAtkFis(rs.getInt("buffAtkFis"));
				a.setBuffAtkMag(rs.getInt("buffAtkMag"));

			} else {
				return null;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			stmt.close();
		}
		logger.log(Level.INFO, "Obteniendo arma");
		return a;
	}

	/*
	 * Metodo para obtener un casco de la BD
	 */
	public Casco obtenerCasco(String nombreArmadura) throws SQLException {
		Casco c = new Casco();

		String sql = ("SELECT nombre, descripcion, buffVida, buffDefFis, buffDefMag FROM casco WHERE nombre = ?");
		PreparedStatement stmt = conn.prepareStatement(sql);

		try {
			stmt.setString(1, nombreArmadura);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				c.setNom(rs.getString("nombre"));
				c.setDescripcion(rs.getString("descripcion"));
				c.setBuffVida(rs.getInt("buffVida"));
				c.setBuffDefFis(rs.getInt("buffDefFis"));
				c.setBuffDefMag(rs.getInt("buffDefMag"));
			}
		} catch (Exception e) {
			throw e;
		} finally {
			stmt.close();
		}
		logger.log(Level.INFO, "Obteniendo casco");
		return c;
	}

	/*
	 * Metodo para obtener una pechera de la BD
	 */
	public Pechera obtenerPechera(String nombrePechera) throws SQLException {
		Pechera p = new Pechera();

		String sql = ("SELECT nombre, descripcion, buffVida, buffDefFis, buffDefMag FROM pechera WHERE nombre = ?");

		PreparedStatement stmt = conn.prepareStatement(sql);

		try {
			stmt.setString(1, nombrePechera);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				p.setNom(rs.getString("nombre"));
				p.setDescripcion(rs.getString("descripcion"));
				p.setBuffVida(rs.getInt("buffVida"));
				p.setBuffDefFis(rs.getInt("buffDefFis"));
				p.setBuffDefMag(rs.getInt("buffDefMag"));
			}
		} catch (Exception e) {
			throw e;
		} finally {
			stmt.close();
		}
		logger.log(Level.INFO, "Obteniendo pechera");
		return p;
	}

	/*
	 * Metodo para guardar Usuarios en la BD
	 */
	public void guardarUser(User user) throws SQLException {
		String sql = ("INSERT INTO users (nombre, password, admin) VALUES (?,?,?)");

		PreparedStatement stmt = conn.prepareStatement(sql);
		
		if(existeUser(user)== true) {
			logger.log(Level.INFO, "El usuario ya existe");
		}else {
			stmt.setString(1, user.getUser());
			stmt.setString(2, user.getPass());
			stmt.setInt(3, user.isAdmin());
			stmt.executeUpdate();
			logger.log(Level.INFO, "Usuario guardado");
		}
	}
	public boolean existeUser(User u) throws SQLException {
		List<String> nombres = new ArrayList<String>();
		String sql = "SELECT nombre FROM users";
		boolean existe = false;
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			nombres.add(rs.getString("nombre"));
		}
		for(int i=0; i<nombres.size();i++) {
			if(u.getUser() == nombres.get(i)) {
				existe = true;
			}
		}
		if(existe == true) {
			return true;
		}else {
			return false;
		}
		}
	
		
	
	/*
	 * Metodo para comprobar usuarios de la BD
	 */
	public int comprobarUser(String user, String pass) throws SQLException {
		User u = new User();

		String sql = "SELECT nombre, password, admin FROM users WHERE nombre = ?";

		PreparedStatement stmt = conn.prepareStatement(sql);

		try {
			stmt.setString(1, user);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				u.setUser(rs.getString("nombre"));
				u.setPass(rs.getString("password"));
				u.setAdmin(rs.getInt("admin"));
			} else {
				logger.log(Level.INFO, "Usuario no encontrado");
				return 0;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		if (u.isAdmin() == 1) {
			logger.log(Level.INFO, "Usuario administrador encontrado");
			return 2;
		} else {
			logger.log(Level.INFO, "Usuario encontrado");
			return 1;
		}

	}
}
