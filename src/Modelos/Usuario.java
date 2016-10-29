package Modelos;

public class Usuario {
	private Integer personaid;
	private String 	rol,alcance,login,passwd;
	private boolean activo;
	public Integer getPersonaid() {
		return personaid;
	}
	public void setPersonaid(Integer personaid) {
		this.personaid = personaid;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public String getAlcance() {
		return alcance;
	}
	public void setAlcance(String alcance) {
		this.alcance = alcance;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}

}
