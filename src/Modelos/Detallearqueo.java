package Modelos;

public class Detallearqueo {
	private int cod_arqcaj,cod_detarq,cod_cue;
	private String des_detarq,fec_detarq;
	private float mon_det;
	private boolean est_detarq;
	public int getCod_arqcaj() {
		return cod_arqcaj;
	}
	public void setCod_arqcaj(int cod_arqcaj) {
		this.cod_arqcaj = cod_arqcaj;
	}
	public int getCod_detarq() {
		return cod_detarq;
	}
	public void setCod_detarq(int cod_detarq) {
		this.cod_detarq = cod_detarq;
	}
	public int getCod_cue() {
		return cod_cue;
	}
	public void setCod_cue(int cod_cue) {
		this.cod_cue = cod_cue;
	}
	public String getDes_detarq() {
		return des_detarq;
	}
	public void setDes_detarq(String des_detarq) {
		this.des_detarq = des_detarq;
	}
	public String getFec_detarq() {
		return fec_detarq;
	}
	public void setFec_detarq(String fec_detarq) {
		this.fec_detarq = fec_detarq;
	}
	public float getMon_det() {
		return mon_det;
	}
	public void setMon_det(float mon_det) {
		this.mon_det = mon_det;
	}
	public boolean isEst_detarq() {
		return est_detarq;
	}
	public void setEst_detarq(boolean est_detarq) {
		this.est_detarq = est_detarq;
	}
}
