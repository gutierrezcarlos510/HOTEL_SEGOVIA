package Servicios;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import Modelos.General;
import Utiles.Db_Coneccion;
@Service
public class GeneralS extends Db_Coneccion{
	String sql;
	public Map<String, Object> obtenerGestionActual(){
		try {
			return db.queryForMap("select * from general_obtenerActual();");
		} catch (Exception e) {
			return null;
		}
	}
	public Map<String, Object> obtener(int gestion){
		try {
			return db.queryForMap("select * from general_obtener(?)",gestion);
		} catch (Exception e) {
			return null;
		}
	}
	public List<Map<String, Object>> listar(int start,boolean estado,String search,int length){
		if(search==null)search="";
		return db.queryForList("select * from general_lista(?,?,?,?)"+as_object_add(as_general, (start<0?"":"RN BIGINT,Tot INT")),start,length,search,estado);
	}
	
	public boolean adicionar(General g){
		try {
			return db.queryForObject("select general_adicionar(?,?,?,?,?,?,?,?,?)",Boolean.class,g.getGes_gen(),g.getDes_gen(),g.getNom_gen(),g.getLogtex_gen(),g.getLogsintex_gen(),g.getTel_gen(),g.getDir_gen(),g.getLug_gen(),g.getNit_gen());
		} catch (Exception e) {
			System.out.println("error al adicionar general="+e.toString());
			return false;
		}
	}
	public boolean modificar(General g){
		try {
			return db.queryForObject("select general_modificar(?,?,?,?,?,?,?,?);",Boolean.class,g.getDes_gen(),g.getNom_gen(),g.getLogtex_gen(),g.getLogsintex_gen(),g.getTel_gen(),g.getDir_gen(),g.getLug_gen(),g.getGes_gen());
		} catch (Exception e) {
			System.out.println("error al modificar general="+e.toString());
			return false;
		}
	}
	public boolean darEstado(int gestion,boolean estado){
		try {
			
			return db.queryForObject("select general_darestado(?,?)",Boolean.class, gestion,estado);
		} catch (Exception e) {
			System.out.println("error al eliminar general="+e.toString());
			return false;
		}
	}
	public boolean validarGestion(int gestion){
		return db.queryForObject("select general_validar(?);", Boolean.class,gestion);
	}
}
