package Servicios;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Service;

import Modelos.Proveedor;
import Utiles.Db_Coneccion;
import Utiles.Letra;
@Service
public class ProveedorS extends Db_Coneccion{
	public class a_objeto implements ParameterizedRowMapper<Proveedor>{
		@Override
		public Proveedor mapRow(ResultSet set, int fila) throws SQLException {
			Proveedor p=new Proveedor();
			p.setCod_pro(set.getInt("cod_pro"));
			p.setNom_pro(set.getString("nom_pro"));
			p.setEst_pro(set.getBoolean("est_pro"));
			return p;
		}
	}
	
	public List<Map<String, Object>> listar(int start,boolean estado,String search,int length){
		if(search==null)search="";
		try{
			return db.queryForList("select * from proveedor_lista(?,?,?,?)",start,length,search,estado);
		}catch(Exception e){
			System.out.println("error listarProveedor"+e.toString());
			return null;
		}
	}
	public Map<String, Object> obtener(int cod_pro){
		try {
			return db.queryForMap("select * from proveedor_obtener(?)",cod_pro);
		} catch (Exception e) {
			System.out.println("error obtenerProveedor"+e.toString());
			return null;
		}
	}
	public boolean adicionar (Proveedor p){
		try {
			Letra l=new Letra();
			return db.queryForObject("select proveedor_adicionar(?)", Boolean.class,l.Primera_Mayuscula(p.getNom_pro()));
		} catch (Exception e) {
			System.out.println("error adicionarProveedor"+e.toString());
			return false;
		}
	}
	public boolean modificar (Proveedor p){
		try {
			Letra l=new Letra();
			return db.queryForObject("select proveedor_modificar(?,?,?)", Boolean.class,l.Primera_Mayuscula(p.getNom_pro()),p.getCod_pro());
		} catch (Exception e) {
			System.out.println("error modificarProveedor"+e.toString());
			return false;
		}
	}
	public boolean darestado (int cod_pro,boolean est_pro){
		try {
			return db.queryForObject("select proveedor_darestado(?,?)", Boolean.class,cod_pro,est_pro);
		} catch (Exception e) {
			System.out.println("error darestadoProveedor"+e.toString());
			return false;
		}
	}
	public boolean validarNom (String nom){
		return db.queryForObject("select proveedor_validar", Boolean.class,nom);
	}
}
