package Modelos;

public class Modelo {
	private int cod_mod;
	private float pre_mod,predes_mod;
	private String nom_mod;
	private boolean est_mod;
	public int getCod_mod() {
		return cod_mod;
	}
	public void setCod_mod(int cod_mod) {
		this.cod_mod = cod_mod;
	}

	public float getPre_mod() {
		return pre_mod;
	}
	public void setPre_mod(float pre_mod) {
		this.pre_mod = pre_mod;
	}
	public float getPredes_mod() {
		return predes_mod;
	}
	public void setPredes_mod(float predes_mod) {
		this.predes_mod = predes_mod;
	}
	public String getNom_mod() {
		return nom_mod;
	}
	public void setNom_mod(String nom_mod) {
		this.nom_mod = nom_mod;
	}
	public boolean isEst_mod() {
		return est_mod;
	}
	public void setEst_mod(boolean est_mod) {
		this.est_mod = est_mod;
	}
	
}
