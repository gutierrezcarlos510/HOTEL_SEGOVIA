package Modelos;

public class Venta {
	private int cod_ven,cod_per,ges_gen,cod_arqcaj,cod_detarq,tip_ven,cod_hos;
	private float tot_ven;
	private String fec_ven;
	private boolean est_ven;
	public int getCod_ven() {
		return cod_ven;
	}
	public void setCod_ven(int cod_ven) {
		this.cod_ven = cod_ven;
	}
	public int getCod_per() {
		return cod_per;
	}
	public void setCod_per(int cod_per) {
		this.cod_per = cod_per;
	}
	public int getGes_gen() {
		return ges_gen;
	}
	public void setGes_gen(int ges_gen) {
		this.ges_gen = ges_gen;
	}
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
	public int getTip_ven() {
		return tip_ven;
	}
	public void setTip_ven(int tip_ven) {
		this.tip_ven = tip_ven;
	}
	public int getCod_hos() {
		return cod_hos;
	}
	public void setCod_hos(int cod_hos) {
		this.cod_hos = cod_hos;
	}
	public float getTot_ven() {
		return tot_ven;
	}
	public void setTot_ven(float tot_ven) {
		this.tot_ven = tot_ven;
	}
	public String getFec_ven() {
		return fec_ven;
	}
	public void setFec_ven(String fec_ven) {
		this.fec_ven = fec_ven;
	}
	public boolean isEst_ven() {
		return est_ven;
	}
	public void setEst_ven(boolean est_ven) {
		this.est_ven = est_ven;
	}
}
