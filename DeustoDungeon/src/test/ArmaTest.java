package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import datos.Arma;

public class ArmaTest {
	private Arma a;

	@Before
	public void setUp() {
		a = new Arma();
		a.setNom("espada magica");
		a.setBuffAtkFis(10);
	}

	@Test
	public void testGetNom() {
		assertEquals("espada magica", a.getNom());
	}

	@Test
	public void testGetBuffAtkFis() {
		assertEquals(10, a.getBuffAtkFis());
	}
}
