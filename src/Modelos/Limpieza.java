package Modelos;

public class Limpieza {
	private int cod_lim,cod_usu,cod_hab,cod_emp;
	private boolean est_lim;
	private String fec_lim;
	public int getCod_lim() {
		return cod_lim;
	}
	public void setCod_lim(int cod_lim) {
		this.cod_lim = cod_lim;
	}
	public int getCod_usu() {
		return cod_usu;
	}
	public void setCod_usu(int cod_usu) {
		this.cod_usu = cod_usu;
	}
	public int getCod_hab() {
		return cod_hab;
	}
	public void setCod_hab(int cod_hab) {
		this.cod_hab = cod_hab;
	}
	public int getCod_emp() {
		return cod_emp;
	}
	public void setCod_emp(int cod_emp) {
		this.cod_emp = cod_emp;
	}
	public boolean isEst_lim() {
		return est_lim;
	}
	public void setEst_lim(boolean est_lim) {
		this.est_lim = est_lim;
	}
	public String getFec_lim() {
		return fec_lim;
	}
	public void setFec_lim(String fec_lim) {
		this.fec_lim = fec_lim;
	}
	
}
