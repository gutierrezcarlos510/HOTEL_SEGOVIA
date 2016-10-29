package Servicios;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Service;

import Modelos.Habitacion;
import Utiles.Db_Coneccion;
import Utiles.Letra;
@Service
public class HabitacionS extends Db_Coneccion{
	public class a_objeto implements ParameterizedRowMapper<Habitacion>{
		@Override
		public Habitacion mapRow(ResultSet set, int fila) throws SQLException {
			Habitacion h=new Habitacion();
			h.setCod_hab(set.getInt("cod_hab"));
			h.setCod_tiphab(set.getInt("cod_tiphab"));
			h.setNom_hab(set.getString("nom_hab"));
			h.setDes_hab(set.getString("des_hab"));
			h.setEst_hab(set.getBoolean("est_hab"));
			h.setCod_mod(set.getInt("cod_mod"));
			h.setEstado(set.getInt("estado"));
			return h;
		}
	}
	
	public List<Map<String, Object>> listar(int start,boolean estado,String search,int length){
		if(search==null)search="";
		try{
			return db.queryForList("select * from habitacion_lista(?,?,?,?)",start,length,search,estado);
		}catch(Exception e){
			System.out.println("error listarHabitacion"+e.toString());
			return null;
		}
	}
	public Map<String, Object> obtener(int cod_tiphab){
		try {
			return db.queryForMap("select * from habitacion_obtener(?)",cod_tiphab);
		} catch (Exception e) {
			System.out.println("error obtenerHabitacion"+e.toString());
			return null;
		}
	}
	public boolean adicionar (Habitacion h){
		try {
			Letra l=new Letra();
			return db.queryForObject("select habitacion_adicionar(?,?,?,?,?)", Boolean.class,l.Primera_Mayuscula(h.getNom_hab()),h.getDes_hab(),h.getCod_tiphab(),h.getCod_mod(),h.getEstado());
		} catch (Exception e) {
			System.out.println("error adicionarHabitacion"+e.toString());
			return false;
		}
	}
	public boolean modificar (Habitacion h){
		try {
			Letra l=new Letra();
			return db.queryForObject("select habitacion_modificar(?,?,?,?,?,?)", Boolean.class,l.Primera_Mayuscula(h.getNom_hab()),h.getDes_hab(),h.getCod_tiphab(),h.getCod_mod(),h.getEstado(),h.getCod_hab());
		} catch (Exception e) {
			System.out.println("error modificarHabitacion"+e.toString());
			return false;
		}
	}
	public boolean darestado (int cod_hab,boolean est_hab){
		try {
			return db.queryForObject("select habitacion_darestado(?,?)", Boolean.class,cod_hab,est_hab);
		} catch (Exception e) {
			System.out.println("error darestadoHabitacion"+e.toString());
			return false;
		}
	}
	public boolean validarNom (String nom){
		return db.queryForObject("select habitacion_validar", Boolean.class,nom);
	}
}
