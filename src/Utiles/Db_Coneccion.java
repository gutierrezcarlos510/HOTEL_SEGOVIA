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
	
	public String as_tipo=" as (id integer,nombre text,descripcion text,activo boolean,creacion timestamp,modificacion timestamp)";
	public String as_establecimiento_salud=" as (id integer,comunidadid integer,nivel text,nombre text,tipo integer,creacion timestamp,modificacion timestamp,activo boolean)";
	public String as_departamento=" as (id integer,nombre text,creacion timestamp,modificacion timestamp,activo boolean)";
	public String as_red_sanitaria=" as (id integer,nombre text,creacion timestamp,modificacion timestamp,activo boolean)";
	public String as_municipio=" as (id integer,red_sanitariaid integer,departamentoid integer,nombre text,creacion timestamp,modificacion timestamp,activo boolean)";
	public String as_comunidad=" as (id integer,nombre text,creacion timestamp,modificacion timestamp,municipioid integer,activo boolean)";
	public String as_prestacion=" as (id integer,nombre text,descripcion text,activo boolean)";
	public String as_etnia=" as (id integer,nombre text,creacion timestamp,modificacion timestamp,activo boolean)";
	public String as_usuario=" as (personaid integer,rol text,alcance character varying(1),login character varying(12),passwd text,activo boolean)";
	public String as_control=" as (id integer,personaid integer,embarazoid integer,recien_nacidoid integer,fecha_control date,tipo_control text,observaciones text,numero_control integer,asistido boolean)";
	public String as_embarazo=" as (id integer,parto_prob date,parto_inst date,creado timestamp,modificado timestamp,activo boolean,inmersion date,observacion text)";
	public String as_recien_nacido=" as (id integer,embarazoid integer,nombres text,apellidos text,sexo character(1),peso real,notas text,defuncion date)";
	
	public String as_object_add(String tabla,String add){
		if(add.equals(""))
			return tabla;
		else
			return tabla.substring(0,tabla.length()-1)+","+add+")";
	}
}
