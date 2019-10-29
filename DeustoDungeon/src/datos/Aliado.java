package datos;

public class Aliado extends Unidad{
	
	public Arma arma;
	public Casco casco;
	public Pechera pechera;

	public Aliado(String nom, String descripcion,int nivel, int atkFis, int atkMag, int vida, int defFis, int defMag, Arma arma, Casco casco, Pechera pechera) {
		super(nom,descripcion,nivel,atkFis,atkMag,vida,defFis,defMag);
		this.arma= arma;
		this.casco=casco;
		this.pechera=pechera;
	}
}
