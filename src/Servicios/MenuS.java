package Servicios;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import Modelos.Menu;
import Utiles.Db_Coneccion;
import Utiles.Letra;

@Service
public class MenuS extends Db_Coneccion {
	
	String sql;
	public List<Map<String, Object>> obtenerXusuario(int cod){
		return db.queryForList("select * from menu_obtenerxusuario(?)",cod);
	}
	public List<Map<String, Object>> menu_obtener_x_codrol(int cod){
		try {
			List<Map<String, Object>> lista= db.queryForList("select * from menu_obtener_x_codrol(?)",cod);
			for (Map<String, Object> map : lista)
				map.put("procesos", db.queryForList("select * from proceso_obtenerxcodm(?)",Integer.parseInt(map.get("cod_men").toString())));
			return lista;
		} catch (Exception e) {
			return null;
		}
	}
	public List<Map<String, Object>> listar(int start,boolean estado,String search,int length){
		if(search==null)search="";
		return db.queryForList("select * from menu_lista(?,?,?,?)"+as_object_add(as_menu, "RN BIGINT,Tot INT,procesos INT"),start,length,search,estado);
	}
	
	public Map<String, Object> obtener(int cod){
		try {
			return db.queryForMap("select * from menu_obtener(?)",cod);
		} catch (Exception e) {
			System.out.println("error obtenerMenu="+e.toString());
			return null;
		}
	}
	
	public boolean adicionar(Menu m){
		try {
			Letra l=new Letra();
			return db.queryForObject("select menu_adicionar(?,?);",Boolean.class,l.Primera_Mayuscula(m.getNom_men()),m.getIco_men());
		} catch (Exception e) {
			System.out.println("error al adicionar menu="+e.toString());
			return false;
		}
	}
	public boolean modificar(Menu m){
		try {
			Letra l=new Letra();
			return db.queryForObject("select menu_modificar(?,?,?);",Boolean.class,l.Primera_Mayuscula(m.getNom_men()),m.getIco_men(),m.getCod_men());
		} catch (Exception e) {
			System.out.println("error al modificar menu="+e.toString());
			return false;
		}
	}
	public boolean darEstado(int cod,boolean est){
		try {
			return db.queryForObject("select menu_darestado(?,?);",Boolean.class,cod,est);
		} catch (Exception e) {
			System.out.println("error al eliminar menu="+e.toString());
			return false;
		}
	}
	public boolean adicionarMenuProceso(int cod,Integer obtenidos[]){
		try {
			db.update("delete from mepro where cod_men=?",cod);
			if(obtenidos!=null)
			for (int i = 0; i < obtenidos.length; i++)
					db.update("insert into mepro(cod_men,cod_pro) values(?,?)",cod,obtenidos[i]);
			return true;
		} catch (Exception e) {
			System.out.println("error al adicionar Mepro="+e.toString());
			return false;
		}
	}
	public boolean validarNom(String nom){
		return db.queryForObject("select menu_validar(?)", Boolean.class,nom);
	}
}