package Servicios;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Service;

import Modelos.Modelo;
import Utiles.Db_Coneccion;
import Utiles.Letra;
@Service
public class ModeloS extends Db_Coneccion{
	public class a_objeto implements ParameterizedRowMapper<Modelo>{
		@Override
		public Modelo mapRow(ResultSet set, int fila) throws SQLException {
			Modelo m=new Modelo();
			m.setCod_mod(set.getInt("cod_mod"));
			m.setNom_mod(set.getString("nom_mod"));
			m.setPre_mod(set.getFloat("pre_mod"));
			m.setPredes_mod(set.getFloat("predes_mod"));
			m.setEst_mod(set.getBoolean("est_mod"));
			return m;
		}
	}
	
	public List<Map<String, Object>> listar(int start,boolean estado,String search,int length){
		if(search==null)search="";
		try{
			return db.queryForList("select * from modelo_lista(?,?,?,?)",start,length,search,estado);
		}catch(Exception e){
			System.out.println("error listarModelo"+e.toString());
			return null;
		}
	}
	public Map<String, Object> obtener(int cod_mod){
		try {
			return db.queryForMap("select * from modelo_obtener(?)",cod_mod);
		} catch (Exception e) {
			System.out.println("error obtenerModelo"+e.toString());
			return null;
		}
	}
	public boolean adicionar (Modelo m){
		try {
			Letra l=new Letra();
			return db.queryForObject("select modelo_adicionar(?,?,?)", Boolean.class,l.Primera_Mayuscula(m.getNom_mod()),m.getPre_mod(),m.getPredes_mod());
		} catch (Exception e) {
			System.out.println("error adicionarModelo"+e.toString());
			return false;
		}
	}
	public boolean modificar (Modelo m){
		try {
			Letra l=new Letra();
			return db.queryForObject("select modelo_modificar(?,?,?,?)", Boolean.class,l.Primera_Mayuscula(m.getNom_mod()),m.getPre_mod(),m.getPredes_mod(),m.getCod_mod());
		} catch (Exception e) {
			System.out.println("error modificarModelo"+e.toString());
			return false;
		}
	}
	public boolean darestado (int cod_mod,boolean est_mod){
		try {
			return db.queryForObject("select modelo_darestado(?,?)", Boolean.class,cod_mod,est_mod);
		} catch (Exception e) {
			System.out.println("error darestadoModelo"+e.toString());
			return false;
		}
	}
	public boolean validarNom (String nom){
		return db.queryForObject("select modelo_validar", Boolean.class,nom);
	}
}
