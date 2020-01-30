package test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import gestorDB.GestorDB;
import objetos.Armadura;
import unidades.Unidad;
import usuario.User;

public class gestorDBTest {
	private GestorDB db;

	@Before
	public void setUp() throws SQLException, ClassNotFoundException {
		db = new GestorDB();
		db.conectar();

	}

	@Test
	public void testObtenerAliado() throws SQLException {

		assertEquals("Javi", db.obtenerAliado("Javi").getNom());
	}

	@Test
	public void testGuardarUnidad() throws SQLException {
		Unidad u = new Unidad();
		u.setNom("prueba");
		u.setDescripcion("prueba");
		u.setAtkFis(1);
		u.setAtkMag(1);
		u.setVida(10);
		u.setDefFis(1);
		u.setDefMag(1);
		u.setRaza("orco");

		db.guardarUnidad(u, 10);

		assertEquals(u.getNom(), db.obtenerUnidad(10).getNom());
	}

	@Test
	public void testGuardarAliado() throws SQLException {
		Unidad u = new Unidad();
		u.setNom("prueba");
		u.setDescripcion("prueba");
		u.setAtkFis(1);
		u.setAtkMag(1);
		u.setVida(10);
		u.setDefFis(1);
		u.setDefMag(1);
		u.setRaza("humano");
		db.guardarAliado(u, "Katana", "espada", "Casco de hierro", "Pechera de hierro");

		assertEquals(u.getNom(), db.obtenerAliado("prueba").getNom());
	}

	@Test
	public void testBorrarUnidad() throws SQLException {
		db.eliminarUnidad("prueba");

	}

	@Test
	public void testBorrarAliado() throws SQLException {
		db.eliminarAliado("prueba");
	}

	@Test
	public void testGuardarArmadura() throws SQLException {
		Armadura armadura = new Armadura();
		armadura.setNom("pruebaArmadura");
		armadura.setDescripcion("prueba");
		armadura.setBuffVida(10);
		armadura.setBuffDefFis(1);
		armadura.setBuffDefMag(1);
		db.guardarArmadura(armadura, "casco");

		assertEquals(armadura.getNom(), db.obtenerCasco("prueba").getNom());
	}

	@Test
	public void testGuardarUser() throws SQLException {
		User usuario = new User();
		usuario.setUser("prueba");
		usuario.setPass("prueba");
		usuario.setAdmin(0);

		assertEquals(1, db.comprobarUser(usuario.getUser(), usuario.getPass()));
	}

}
