package Servicios;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import Modelos.Proceso;
import Utiles.Db_Coneccion;
import Utiles.Letra;

@Service
public class ProcesoS extends Db_Coneccion {
	public List<Map<String, Object>> obtenerprocesos(int cod){
		try {
			return db.queryForList("select * from menu_obtenerprocesos(?)"+as_object_add(as_proceso, "tipo varchar(8)"),cod);
		} catch (Exception e) {
			System.out.println("error obtenerProcesos="+e.toString());
			return null;
		}
	}
	public List<Map<String, Object>> obtenerXcodm(int cod){
		try {
			return db.queryForList("select * from proceso_obtenerxcodm(?)",cod);
		} catch (Exception e) {
			System.out.println("error obtenerProcesos="+e.toString());
			return null;
		}
	}
	public List<Map<String, Object>> listar(int start,boolean estado,String search,int length){
		if(search==null)search="";
		try {
			return db.queryForList("select * from proceso_lista(?,?,?,?)"+as_object_add(as_proceso, "RN BIGINT,Tot INT"),start,length,search,estado);
		} catch (Exception e) {
			return null;
		}
	}
	public Map<String, Object> obtener(int cod){
		try {
			return db.queryForMap("select * from proceso_obtener(?)",cod);
		} catch (Exception e) {
			System.out.println("error obtenerProceso="+e.toString());
			return null;
		}
	}
	public int adicionar(Proceso p){
		try {
			Letra l=new Letra();			
			return db.queryForObject("select proceso_adicionar(?,?,?)",Integer.class,l.Primera_Mayuscula(p.getNom_pro()),p.getIco_pro(),p.getUrl_pro());
		} catch (Exception e) {
			System.out.println("error al adicionar proceso="+e.toString());
			return -2;
		}
	}
	public boolean modificar(Proceso p){
		try {
			Letra l=new Letra();
			return db.queryForObject("select proceso_modificar(?,?,?,?);",Boolean.class,l.Primera_Mayuscula(p.getNom_pro()),p.getIco_pro(),p.getUrl_pro(),p.getCod_pro());
		} catch (Exception e) {
			System.out.println("error al modificar proceso="+e.toString());
			return false;
		}
	}
	public boolean darEstado(int codpro,boolean estado){
		try {
			return db.queryForObject("select proceso_darestado(?,?)",Boolean.class,codpro,estado);
		} catch (Exception e) {
			System.out.println("error al activar proceso="+e.toString());
			return false;
		}
	}
	public boolean validarNom(String nom){
		return db.queryForObject("select proceso_validar(?)", Boolean.class,nom);
	}
}