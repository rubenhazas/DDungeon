package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import datos.Aliado;
import datos.Arma;
import datos.Casco;
import datos.Pechera;

public class AliadoTest {

	private Aliado a;
	private Arma c;

	@Before
	public void setUp() {
		a = new Aliado();
		a.setNom("Pepe");
		a.setDescripcion("descripcion");
		a.setRaza("humano");
		a.setAtkFis(1);
		a.setAtkMag(1);
		a.setDefFis(1);
		a.setDefMag(1);
		a.setVida(10);
		a.setArma(new Arma("espada", "espada", "", 3, 3));
		a.setCasco(new Casco("casco", "casco", 20, 2, 2));
		a.setPechera(new Pechera("pechera", "pechera", 20, 2, 2));

	}

	@Test
	public void testGetArma() {
		Arma c = new Arma("espada", "espada", "", 3, 3);
		assertEquals(c.getNom(), a.getArma().getNom());
	}

	@Test
	public void testGetNombre() {
		assertEquals("Pepe", a.getNom());
	}

	@Test
	public void testGetCasco() {
		Casco c = new Casco("casco", "casco", 20, 2, 2);
		assertEquals(c.getNom(), a.getCasco().getNom());
	}

	@Test
	public void testGetatkFis() {
		assertEquals(1, a.getAtkFis());
	}

	@Test
	public void testGetPechera() {
		Pechera c = new Pechera("pechera", "pechera", 20, 2, 2);
		assertEquals(c.getNom(), a.getPechera().getNom());
	}

	@Test
	public void testGetVida() {
		assertEquals(10, a.getVida());
	}

}
