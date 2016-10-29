package Modelos;

public class Cuenta {
	private int cod_cue;
	private String nom_cue,des_cue;
	private boolean tip_cue,est_cue;
	public int getCod_cue() {
		return cod_cue;
	}
	public void setCod_cue(int cod_cue) {
		this.cod_cue = cod_cue;
	}
	public String getNom_cue() {
		return nom_cue;
	}
	public void setNom_cue(String nom_cue) {
		this.nom_cue = nom_cue;
	}
	public String getDes_cue() {
		return des_cue;
	}
	public void setDes_cue(String des_cue) {
		this.des_cue = des_cue;
	}
	public boolean isTip_cue() {
		return tip_cue;
	}
	public void setTip_cue(boolean tip_cue) {
		this.tip_cue = tip_cue;
	}
	public boolean isEst_cue() {
		return est_cue;
	}
	public void setEst_cue(boolean est_cue) {
		this.est_cue = est_cue;
	}
	
}
