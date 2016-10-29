package Modelos;

public class Proceso {
	private int cod_pro;
	private String nom_pro,ico_pro,url_pro;
	private boolean est_pro;
	public int getCod_pro() {
		return cod_pro;
	}
	public void setCod_pro(int cod_pro) {
		this.cod_pro = cod_pro;
	}
	public String getNom_pro() {
		return nom_pro;
	}
	public void setNom_pro(String nom_pro) {
		this.nom_pro = nom_pro;
	}
	public String getIco_pro() {
		return ico_pro;
	}
	public void setIco_pro(String ico_pro) {
		this.ico_pro = ico_pro;
	}
	public String getUrl_pro() {
		return url_pro;
	}
	public void setUrl_pro(String url_pro) {
		this.url_pro = url_pro;
	}
	public boolean isEst_pro() {
		return est_pro;
	}
	public void setEst_pro(boolean est_pro) {
		this.est_pro = est_pro;
	}
}