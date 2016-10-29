package Modelos;

public class Producto {
	private int cod_pro,can_pro,cod_tippro;
	private String nom_pro;
	private float precom_pro,preven_pro,gan_pro;
	private boolean est_pro;
	public int getCod_pro() {
		return cod_pro;
	}
	public void setCod_pro(int cod_pro) {
		this.cod_pro = cod_pro;
	}
	public int getCan_pro() {
		return can_pro;
	}
	public void setCan_pro(int can_pro) {
		this.can_pro = can_pro;
	}
	public int getCod_tippro() {
		return cod_tippro;
	}
	public void setCod_tippro(int cod_tippro) {
		this.cod_tippro = cod_tippro;
	}
	public String getNom_pro() {
		return nom_pro;
	}
	public void setNom_pro(String nom_pro) {
		this.nom_pro = nom_pro;
	}
	public float getPrecom_pro() {
		return precom_pro;
	}
	public void setPrecom_pro(float precom_pro) {
		this.precom_pro = precom_pro;
	}
	public float getPreven_pro() {
		return preven_pro;
	}
	public void setPreven_pro(float preven_pro) {
		this.preven_pro = preven_pro;
	}
	public float getGan_pro() {
		return gan_pro;
	}
	public void setGan_pro(float gan_pro) {
		this.gan_pro = gan_pro;
	}
	public boolean isEst_pro() {
		return est_pro;
	}
	public void setEst_pro(boolean est_pro) {
		this.est_pro = est_pro;
	}
}
