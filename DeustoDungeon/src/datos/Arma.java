package datos;

public class Arma implements Atacante{
	
	public String nom;
	public String tipo;
	public String descripcion;
	public int buffAtkFis;
	public int buffAtkMag;
	
	public Arma(String nom,String tipo, String descripcion, int buffAtkFis, int buffAtkMag) {
		this.nom = nom;
		this.tipo= tipo;
		this.descripcion = descripcion;
		this.buffAtkFis = buffAtkFis;
		this.buffAtkMag = buffAtkMag;
	}
	public Arma() {
		
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

	public int getBuffAtkFis() {
		return buffAtkFis;
	}

	public void setBuffAtkFis(int buffAtkFis) {
		this.buffAtkFis = buffAtkFis;
	}

	public int getBuffAtkMag() {
		return buffAtkMag;
	}

	public void setBuffAtkMag(int buffAtkMag) {
		this.buffAtkMag = buffAtkMag;
	}
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public void ataque1() {
		
	}
	@Override
	public void ataque2() {
		
	}

	
	
}
