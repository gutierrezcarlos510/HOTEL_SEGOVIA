package Servicios;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Service;

import Modelos.Tiposervicio;
import Utiles.Db_Coneccion;
import Utiles.Letra;
@Service
public class TipoServicioS extends Db_Coneccion{
	public class a_objeto implements ParameterizedRowMapper<Tiposervicio>{
		@Override
		public Tiposervicio mapRow(ResultSet set, int fila) throws SQLException {
			Tiposervicio th=new Tiposervicio();
			th.setCod_tipser(set.getInt("cod_tipser"));
			th.setNom_tipser(set.getString("nom_tipser"));
			th.setEst_tipser(set.getBoolean("est_tipser"));
			return th;
		}
	}
	
	public List<Map<String, Object>> listar(int start,boolean estado,String search,int length){
		if(search==null)search="";
		try{
			return db.queryForList("select * from tiposervicio_lista(?,?,?,?)"+as_object_add(as_tiposervicio, "RN bigint,Tot int"),start,length,search,estado);
		}catch(Exception e){
			System.out.println("error listarTiposervicio"+e.toString());
			return null;
		}
	}
	public Map<String, Object> obtener(int cod_tipser){
		try {
			return db.queryForMap("select * from tiposervicio_obtener(?)"+as_tiposervicio,cod_tipser);
		} catch (Exception e) {
			System.out.println("error obtenerTiposervicio"+e.toString());
			return null;
		}
	}
	public boolean adicionar (Tiposervicio ts){
		try {
			Letra l=new Letra();
			return db.queryForObject("select tiposervicio_adicionar(?)", Boolean.class,l.Primera_Mayuscula(ts.getNom_tipser()));
		} catch (Exception e) {
			System.out.println("error adicionarTiposervicio"+e.toString());
			return false;
		}
	}
	public boolean modificar (Tiposervicio ts){
		try {
			Letra l=new Letra();
			return db.queryForObject("select tiposervicio_modificar(?,?)", Boolean.class,l.Primera_Mayuscula(ts.getNom_tipser()),ts.getCod_tipser());
		} catch (Exception e) {
			System.out.println("error modificarTiposervicio"+e.toString());
			return false;
		}
	}
	public boolean darestado (int cod_tipser,boolean est_tipser){
		try {
			return db.queryForObject("select tiposervicio_darestado(?,?)", Boolean.class,cod_tipser,est_tipser);
		} catch (Exception e) {
			System.out.println("error darestadoTiposervicio"+e.toString());
			return false;
		}
	}
	public boolean validarNom (String nom){
		return db.queryForObject("select tiposervicio_validar", Boolean.class,nom);
	}
}
