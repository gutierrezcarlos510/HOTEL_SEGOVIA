package Utiles;

import java.sql.CallableStatement;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class Db_Coneccion {
	
	protected JdbcTemplate db;
	protected Integer listlength = 10;
	@Resource
	protected DataSource ds;
	protected CallableStatement cstmt;
	@Autowired
	
	public void setJdbcTemplate(DataSource dataSource) {
		this.db = new JdbcTemplate(dataSource);	
	}
	public String as_general=" as (ges_gen integer,des_gen character varying(100),est_gen boolean,nom_gen character varying(50),logtex_gen character varying(50),logsintex_gen character varying(50),tel_gen character varying(50),dir_gen character varying(100),lug_gen character varying(100),nit_gen character varying(25))";
	public String as_dato=" as (log_dat character varying(50),cla_dat character varying(50),cod_per integer)";
	public String as_persona=" as (ci_per character varying(15) ,nom_per character varying(30) ,priape_per character varying(30) ,segape_per character varying(30),tel_per character varying(30),dir_per character varying(100),ema_per character varying(30),fot_per character varying(30),est_per boolean ,sex_per boolean,cod_per bigint)";
	public String as_menu=" as (cod_men integer ,nom_men character varying(100) ,ico_men character varying(100),est_men boolean)";
	public String as_proceso=" as (cod_pro integer,nom_pro character varying(50),ico_pro character varying(50),est_pro boolean,url_pro character varying(70))";
	public String as_rol=" as (cod_rol integer,nom_rol character varying(100),est_rol boolean)";
	
	public String as_arqueocaja=" as (cod_arqcaj integer,del_arqcaj bigint,cusini_arqcaj bigint,fini_arqcaj timestamp,monini_arqcaj real,ffin_arqcaj timestamp,monfin_arqcaj real,est_arqcaj boolean,monrea_arqcaj real,ges_gen integer,des_arqcaj character varying(1500),cusfin_arqcaj bigint)";
	public String as_compra=" as (cod_com bigint,cod_per bigint,cod_pro integer,fec_com date,obs_com character varying(500),tot_com real,est_com boolean,des_com real,ges_gen integer,cod_arqcaj integer,cod_detarq integer)";
	public String as_cuenta=" as (cod_cue integer,nom_cue character varying(50),des_cue character varying(500),tip_cue boolean,est_cue boolean)";
	public String as_detallearqueo=" as (cod_arqcaj integer,cod_detarq integer,des_detarq character varying(500),mon_detarq real,est_detarq boolean,cod_cue integer)";
	public String as_detallecompra=" as (cod_com bigint,cod_detcom integer,cod_pro integer,pre_detcom real,can_detcom integer,des_detcom real)";
	public String as_detallereserva=" as (cos_res bigint,cod_hab integer,pre_hab real)";
	public String as_detalleventa=" as (cod_ven bigint,cod_detven integer,cod_pro integer,pre_detven real,can_detven integer)";
	public String as_habitacion=" as (cod_hab integer,nom_hab character varying(25),des_hab character varying(500),cod_tiphab integer,est_hab boolean,cod_mod integer,estado integer)";
	public String as_hospedados=" as (cod_hos bigint,cod_hue bigint)";
	public String as_hospedaje=" as (cod_hos bigint,cod_usu bigint,cod_hue bigint,fecinireg_sis timestamp,fecini_hos timestamp,fecfinreg_sis timestamp,fecfin_hos timestamp,obs_res character varying(500),tot_hos real,tip_hos integer,est_hos boolean,cod_res bigint,cod_hab integer,cod_arqcaj integer,cod_detcaj integer)";
	public String as_huesped=" as (cod_per bigint,cod_hue bigint,fecnac_hue date,proc_hue character varying(150),numdoc_hue character varying(25),estciv_hue character varying(15),ocu_hue character varying(50),est_hue boolean)";
	public String as_limpieza=" as (cod_lim bigint,cod_usu bigint,cod_hab integer,fec_lim timestamp,est_lim boolean,cod_emp bigint)";
	public String as_modelo="as (cod_mod integer,nom_mod character varying(25),pre_mod real,est_mod boolean,predes_mod real)";
	public String as_observacion=" as (cod_hos bigint,cod_obs integer,des_obs character varying(500),est_obs boolean,nom_obs character varying(50))";
	public String as_prestacion=" as (cod_pre bigint,cod_per bigint,fec_pre timestamp,obs_pre character varying(500),des_pre real,ges_gen integer,est_pre boolean,cod_arqcaj integer,cod_detarq integer,cod_ser integer,pre_ser real,cod_hos bigint)";
	public String as_producto=" as (cod_pro integer,nom_pro character varying(100),precom_pro real,preven_pro real,gan_pro real,can_pro integer,est_pro boolean,cod_tippro integer)";
	public String as_proveedor=" as (cod_pro integer,nom_pro character varying(150),est_pro boolean)";
	public String as_reserva=" as (cod_pro integer,nom_pro character varying(150),est_pro boolean)";
	public String as_servicio=" as (cod_ser integer,nom_ser character varying(150),des_ser character varying(500),est_ser boolean,cod_tipser,pre_ser real)";
	public String as_tipohabitacion=" as (cod_tiphab integer,nom_tiphab character varying(50),des_tiphab character varying(500),est_tiphab boolean)";
	public String as_tipoproducto=" as (cod_tippro integer,nom_tippro character varying(100),des_tippro character varying(250),est_tippro boolean)";
	public String as_tiposervicio=" as (cod_tipser integer,nom_tipser character varying(150),est_tipser boolean)";
	public String as_venta=" as (cod_ven bigint,cod_per integer,fec_ven timestamp,tot_ven real,ges_gen integer,est_ven boolean,cod_arqcaj integer,cod_detarq integer,tip_ven integer,cod_hos bigint)";
	
	public String as_object_add(String tabla,String add){
		if(add.equals(""))
			return tabla;
		else
			return tabla.substring(0,tabla.length()-1)+","+add+")";
	}
}
