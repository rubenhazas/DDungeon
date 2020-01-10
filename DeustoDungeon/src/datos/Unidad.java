package datos;

public class Unidad implements Atacante {
	
	public static int id;
	public String nom;
	public String raza;
	public String descripcion;
	public int atkFis;
	public int atkMag;
	public int vida;
	public int defFis;
	public int defMag;
	
	public Unidad(String nom, String raza,String descripcion,int atkFis, int atkMag, int vida, int defFis, int defMag) {
		this.nom = nom;
		this.raza=raza;
		this.descripcion = descripcion;
		this.atkFis = atkFis;
		this.atkMag = atkMag;
		this.vida = vida;
		this.defFis = defFis;
		this.defMag = defMag;
	}

	public Unidad() {
		this.nom = "";
		this.atkFis = 0;
		this.descripcion = "";
		this.vida = 0;
		this.atkMag = 0;
		this.defFis = 0;
		this.defMag = 0;
	}


	public static int getId() {
		return id;
	}


	public static void setId(int id) {
		Unidad.id = id;
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


	public int getAtkFis() {
		return atkFis;
	}


	public void setAtkFis(int atkFis) {
		this.atkFis = atkFis;
	}


	public int getVida() {
		return vida;
	}


	public void setVida(int vida) {
		this.vida = vida;
	}


	public int getAtkMag() {
		return atkMag;
	}


	public void setAtkMag(int atkMag) {
		this.atkMag = atkMag;
	}


	public int getDefFis() {
		return defFis;
	}


	public void setDefFis(int defFis) {
		this.defFis = defFis;
	}


	public int getDefMag() {
		return defMag;
	}


	public void setDefMag(int defMag) {
		this.defMag = defMag;
	}
	
	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}
	
	
	@Override
	public int ataque1(int i) {
		int daño=0;
		int probabilidad= (int)(Math.random()*((100-1)+1))+1;
		if(probabilidad<= 90) {
			
			daño = (int)(Math.random()*((i-1)+1))+1;
			
		}
		
		return daño;
		
	}

	@Override
	public int ataque2(int i) {
		int daño=0;
		int probabilidad= (int)(Math.random()*((100-1)+1))+1;
		if(probabilidad<= 40) {
			
			daño = (int)((Math.random()*((i-1)+1))+1)*2;
			
		}
		
		return daño;
	}

	

	
	
	
	
	
	
	
	
	
	

}
