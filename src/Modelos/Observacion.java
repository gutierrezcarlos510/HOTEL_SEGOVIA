package Modelos;

public class Observacion {
	private int cod_hos,cod_obs;
	private String des_obs,nom_obs;
	private boolean est_obs;
	public int getCod_hos() {
		return cod_hos;
	}
	public void setCod_hos(int cod_hos) {
		this.cod_hos = cod_hos;
	}
	public int getCod_obs() {
		return cod_obs;
	}
	public void setCod_obs(int cod_obs) {
		this.cod_obs = cod_obs;
	}
	public String getDes_obs() {
		return des_obs;
	}
	public void setDes_obs(String des_obs) {
		this.des_obs = des_obs;
	}
	public String getNom_obs() {
		return nom_obs;
	}
	public void setNom_obs(String nom_obs) {
		this.nom_obs = nom_obs;
	}
	public boolean isEst_obs() {
		return est_obs;
	}
	public void setEst_obs(boolean est_obs) {
		this.est_obs = est_obs;
	}
}
