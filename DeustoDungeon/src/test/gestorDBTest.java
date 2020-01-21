package test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import gestorDB.GestorDB;

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

}
