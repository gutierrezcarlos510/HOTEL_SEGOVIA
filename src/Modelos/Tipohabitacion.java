package Modelos;

public class Tipohabitacion {
	private int cod_tiphab;
	private String nom_tiphab,des_tiphab;
	private boolean est_tiphab;
	public int getCod_tiphab() {
		return cod_tiphab;
	}
	public void setCod_tiphab(int cod_tiphab) {
		this.cod_tiphab = cod_tiphab;
	}
	public String getNom_tiphab() {
		return nom_tiphab;
	}
	public void setNom_tiphab(String nom_tiphab) {
		this.nom_tiphab = nom_tiphab;
	}
	public String getDes_tiphab() {
		return des_tiphab;
	}
	public void setDes_tiphab(String des_tiphab) {
		this.des_tiphab = des_tiphab;
	}
	public boolean isEst_tiphab() {
		return est_tiphab;
	}
	public void setEst_tiphab(boolean est_tiphab) {
		this.est_tiphab = est_tiphab;
	}
}
