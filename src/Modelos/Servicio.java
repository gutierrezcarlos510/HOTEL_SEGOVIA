package Modelos;

public class Servicio {
	private int cod_ser,cod_tipser;
	private String nom_ser,des_ser;
	private float pre_ser;
	private boolean est_ser;
	public int getCod_ser() {
		return cod_ser;
	}
	public void setCod_ser(int cod_ser) {
		this.cod_ser = cod_ser;
	}
	public int getCod_tipser() {
		return cod_tipser;
	}
	public void setCod_tipser(int cod_tipser) {
		this.cod_tipser = cod_tipser;
	}
	public String getNom_ser() {
		return nom_ser;
	}
	public void setNom_ser(String nom_ser) {
		this.nom_ser = nom_ser;
	}
	public String getDes_ser() {
		return des_ser;
	}
	public void setDes_ser(String des_ser) {
		this.des_ser = des_ser;
	}
	public float getPre_ser() {
		return pre_ser;
	}
	public void setPre_ser(float pre_ser) {
		this.pre_ser = pre_ser;
	}
	public boolean isEst_ser() {
		return est_ser;
	}
	public void setEst_ser(boolean est_ser) {
		this.est_ser = est_ser;
	}
}
