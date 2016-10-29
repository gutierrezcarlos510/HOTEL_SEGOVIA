package Modelos;

public class Reserva {
	private int cod_res,cod_usu,cod_hue,tip_res;
	private String fec_res,fes_hos,arc_res;
	private boolean est_res;
	public int getCod_res() {
		return cod_res;
	}
	public void setCod_res(int cod_res) {
		this.cod_res = cod_res;
	}
	public int getCod_usu() {
		return cod_usu;
	}
	public void setCod_usu(int cod_usu) {
		this.cod_usu = cod_usu;
	}
	public int getCod_hue() {
		return cod_hue;
	}
	public void setCod_hue(int cod_hue) {
		this.cod_hue = cod_hue;
	}
	public int getTip_res() {
		return tip_res;
	}
	public void setTip_res(int tip_res) {
		this.tip_res = tip_res;
	}
	public String getFec_res() {
		return fec_res;
	}
	public void setFec_res(String fec_res) {
		this.fec_res = fec_res;
	}
	public String getFes_hos() {
		return fes_hos;
	}
	public void setFes_hos(String fes_hos) {
		this.fes_hos = fes_hos;
	}
	public String getArc_res() {
		return arc_res;
	}
	public void setArc_res(String arc_res) {
		this.arc_res = arc_res;
	}
	public boolean isEst_res() {
		return est_res;
	}
	public void setEst_res(boolean est_res) {
		this.est_res = est_res;
	}
}
