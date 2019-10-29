package datos;

public class Aliado extends Unidad{
	
	public Arma arma;
	public Casco casco;
	public Pechera pechera;

	public Aliado(String nom, String descripcion,int nivel, int atkFis, int atkMag, int vida, int defFis, int defMag,String pasivaRacial, Arma arma, Casco casco, Pechera pechera) {
		super(nom,descripcion,nivel,atkFis,atkMag,vida,defFis,defMag,pasivaRacial);
		this.arma= arma;
		this.casco=casco;
		this.pechera=pechera;
		
	}

	public Aliado() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Arma getArma() {
		return arma;
	}

	public void setArma(Arma arma) {
		this.arma = arma;
	}

	public Casco getCasco() {
		return casco;
	}

	public void setCasco(Casco casco) {
		this.casco = casco;
	}

	public Pechera getPechera() {
		return pechera;
	}

	public void setPechera(Pechera pechera) {
		this.pechera = pechera;
	}
	
}
