package Servicios;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Service;

import Modelos.Servicio;
import Utiles.Db_Coneccion;
import Utiles.Letra;
@Service
public class ServicioS extends Db_Coneccion{
	public class a_objeto implements ParameterizedRowMapper<Servicio>{
		@Override
		public Servicio mapRow(ResultSet set, int fila) throws SQLException {
			Servicio s=new Servicio();
			s.setCod_ser(set.getInt("cod_ser"));
			s.setNom_ser(set.getString("nom_ser"));
			s.setDes_ser(set.getString("des_ser"));
			s.setCod_tipser(set.getInt("cod_tipser"));
			s.setEst_ser(set.getBoolean("est_ser"));
			s.setPre_ser(set.getFloat("pre_ser"));
			return s;
		}
	}
	
	public List<Map<String, Object>> listar(int start,boolean estado,String search,int length){
		if(search==null)search="";
		try{
			return db.queryForList("select * from servicio_lista(?,?,?,?)",start,length,search,estado);
		}catch(Exception e){
			System.out.println("error listarServicio"+e.toString());
			return null;
		}
	}
	public Map<String, Object> obtener(int cod_ser){
		try {
			return db.queryForMap("select * from servicio_obtener(?)",cod_ser);
		} catch (Exception e) {
			System.out.println("error obtenerServicio"+e.toString());
			return null;
		}
	}
	public boolean adicionar (Servicio s){
		try {
			Letra l=new Letra();
			return db.queryForObject("select servicio_adicionar(?,?,?,?)", Boolean.class,l.Primera_Mayuscula(s.getNom_ser()),s.getDes_ser(),s.getCod_tipser(),s.getPre_ser());
		} catch (Exception e) {
			System.out.println("error adicionarServicio"+e.toString());
			return false;
		}
	}
	public boolean modificar (Servicio s){
		try {
			Letra l=new Letra();
			return db.queryForObject("select servicio_modificar(?,?,?,?,?)", Boolean.class,l.Primera_Mayuscula(s.getNom_ser()),s.getDes_ser(),s.getCod_tipser(),s.getPre_ser(),s.getCod_ser());
		} catch (Exception e) {
			System.out.println("error modificarServicio"+e.toString());
			return false;
		}
	}
	public boolean darestado (int cod_ser,boolean est_ser){
		try {
			return db.queryForObject("select servicio_darestado(?,?)", Boolean.class,cod_ser,est_ser);
		} catch (Exception e) {
			System.out.println("error darestadoServicio"+e.toString());
			return false;
		}
	}
	public boolean validarNom (String nom){
		return db.queryForObject("select servicio_validar", Boolean.class,nom);
	}
}
