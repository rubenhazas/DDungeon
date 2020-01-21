package datos;

public class Armadura {

	public String nom;
	public String descripcion;
	public int buffVida;
	public int buffDefFis;
	public int buffDefMag;

	public Armadura(String nom, String descripcion, int buffVida, int buffDefFis, int buffDefMag) {

		this.nom = nom;
		this.descripcion = descripcion;
		this.buffVida = buffVida;
		this.buffDefFis = buffDefFis;
		this.buffDefMag = buffDefMag;
	}

	public Armadura() {

	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getBuffVida() {
		return buffVida;
	}

	public void setBuffVida(int buffVida) {
		this.buffVida = buffVida;
	}

	public int getBuffDefFis() {
		return buffDefFis;
	}

	public void setBuffDefFis(int buffDefFis) {
		this.buffDefFis = buffDefFis;
	}

	public int getBuffDefMag() {
		return buffDefMag;
	}

	public void setBuffDefMag(int buffDefMag) {
		this.buffDefMag = buffDefMag;
	}

}
