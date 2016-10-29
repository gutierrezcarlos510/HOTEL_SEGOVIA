package Modelos;

public class Habitacion {
	private int cod_hab,cod_mod,cod_tiphab,estado;
	private boolean est_hab;
	private String nom_hab,des_hab;
	public int getCod_hab() {
		return cod_hab;
	}
	public void setCod_hab(int cod_hab) {
		this.cod_hab = cod_hab;
	}
	public int getCod_mod() {
		return cod_mod;
	}
	public void setCod_mod(int cod_mod) {
		this.cod_mod = cod_mod;
	}
	public int getCod_tiphab() {
		return cod_tiphab;
	}
	public void setCod_tiphab(int cod_tiphab) {
		this.cod_tiphab = cod_tiphab;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public boolean isEst_hab() {
		return est_hab;
	}
	public void setEst_hab(boolean est_hab) {
		this.est_hab = est_hab;
	}
	public String getNom_hab() {
		return nom_hab;
	}
	public void setNom_hab(String nom_hab) {
		this.nom_hab = nom_hab;
	}
	public String getDes_hab() {
		return des_hab;
	}
	public void setDes_hab(String des_hab) {
		this.des_hab = des_hab;
	}
	
}
