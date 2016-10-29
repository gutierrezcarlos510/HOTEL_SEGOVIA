package Modelos;


public class Persona {
	private int cod_per;
	private String ci_per,nom_per,priape_per,segape_per,tel_per,dir_per,ema_per,fot_per;
	private boolean est_per,sex_per;
	public int getCod_per() {
		return cod_per;
	}
	public void setCod_per(int cod_per) {
		this.cod_per = cod_per;
	}
	public String getCi_per() {
		return ci_per;
	}
	public void setCi_per(String ci_per) {
		this.ci_per = ci_per;
	}
	public String getNom_per() {
		return nom_per;
	}
	public void setNom_per(String nom_per) {
		this.nom_per = nom_per;
	}
	public String getPriape_per() {
		return priape_per;
	}
	public void setPriape_per(String priape_per) {
		this.priape_per = priape_per;
	}
	public String getSegape_per() {
		return segape_per;
	}
	public void setSegape_per(String segape_per) {
		this.segape_per = segape_per;
	}
	public String getTel_per() {
		return tel_per;
	}
	public void setTel_per(String tel_per) {
		this.tel_per = tel_per;
	}
	public String getDir_per() {
		return dir_per;
	}
	public void setDir_per(String dir_per) {
		this.dir_per = dir_per;
	}
	public String getEma_per() {
		return ema_per;
	}
	public void setEma_per(String ema_per) {
		this.ema_per = ema_per;
	}
	public String getFot_per() {
		return fot_per;
	}
	public void setFot_per(String fot_per) {
		this.fot_per = fot_per;
	}
	public boolean isEst_per() {
		return est_per;
	}
	public void setEst_per(boolean est_per) {
		this.est_per = est_per;
	}
	public boolean isSex_per() {
		return sex_per;
	}
	public void setSex_per(boolean sex_per) {
		this.sex_per = sex_per;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.nom_per+" "+this.priape_per+" "+(this.segape_per!=null?this.segape_per:"");
	}
	public String getIniciales(){
		return this.nom_per.substring(0, 1)+"."+this.priape_per.substring(0, 1)+"."+(this.segape_per!=null?this.segape_per.substring(0, 1):"");
	}
}
