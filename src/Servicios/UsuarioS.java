package Servicios;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import Modelos.Persona;
import Utiles.Db_Coneccion;
import Utiles.Letra;

@Service
public class UsuarioS extends Db_Coneccion{
	public Map<String, Object> iniciar_sesion(String login,String password){
		try {
			return db.queryForMap("select * from persona_iniciar_sesion(?,?)",login,password);
		} catch (Exception e) {System.out.println(e.toString());
			System.out.println("error al inciar sesion");
			return null;
		}
	}
	public boolean guardarFoto(String foto,int codper){
		try {
			db.queryForObject("select persona_guardarfoto(?,?) as resp",Integer.class,foto,codper);
			return true;
		} catch (Exception e) {
			System.out.println(""+e.toString());
			return false;
		}
	}
	public List<Map<String, Object>> listar(int start,boolean estado,String search,int length){
		return db.queryForList("select * from persona_lista(?,?,?,?)"+as_object_add(as_persona,(start<0?"":"RN BIGINT,Tot INT,log character varying(50)")),start,length,search,estado);
	}
	public Map<String, Object> obtener(int cod_per){
		try {
			return db.queryForMap("select * from persona_obtener(?)",cod_per);
		} catch (Exception e) {
			System.out.println("error obtenerUsuario="+e.toString());
			return null;
		}
	}
	public List<Map<String, Object>> obtenerRoles(int cod_per){
		try {
			return db.queryForList("select * from rol_obtenerroles(?)"+as_object_add(as_rol, "tipo VARCHAR(15)"),cod_per);
		} catch (Exception e) {
			System.out.println("error obtenerRoles="+e.toString());
			return null;
		}
	}
	public boolean adicionar(Persona us){
		try {
			Letra l=new Letra();
			return db.queryForObject("select persona_adicionar(?,?,?,?,?,?,?,?,?);",Integer.class,us.getCi_per(),l.Primera_Mayuscula(us.getNom_per()),l.Primera_Mayuscula(us.getPriape_per()),l.Primera_Mayuscula(us.getSegape_per()),us.getTel_per(),us.getDir_per(),us.getEma_per(),us.getFot_per(),us.isSex_per())>0;
		} catch (Exception e) {
			System.out.println("error al adicionar usuario="+e.toString());
			return false;
		}
	}
	public boolean modificar(Persona us){
		try {
			Letra l=new Letra();
			return db.queryForObject("select persona_modificar(?,?,?,?,?,?,?,?,?,?)",Boolean.class,us.getCi_per(),l.Primera_Mayuscula(us.getNom_per()),l.Primera_Mayuscula(us.getPriape_per()),l.Primera_Mayuscula(us.getSegape_per()),us.getDir_per(),us.getTel_per(),us.getEma_per(),us.getFot_per(),us.isSex_per(),us.getCod_per());
		} catch (Exception e) {
			System.out.println("error al modificar usuario="+e.toString());
			return false;
		}
	}
	public boolean eliminar(int cod){
		try {
			return db.queryForObject("select persona_eliminar(?)",Boolean.class,cod);
		} catch (Exception e) {
			System.out.println("error al eliminar usuario="+e.toString());
			return false;
		}
	}
	public boolean activar(int cod){
		try {
			return db.queryForObject("select persona_activar(?)",Boolean.class,cod);
		} catch (Exception e) {
			System.out.println("error al activar usuario="+e.toString());
			return false;
		}
	}
	public boolean validarCi(String ci){
		return db.queryForObject("select persona_validarci(?)", Boolean.class,ci);
	}
	public Map<String, Object> buscarCi(String ci){
		return db.queryForMap("select * from persona_buscar(?)",ci);
	}
	public Map<String, Object> buscar_nombres(String cad){
		return db.queryForMap("select * from persona_buscar_nombres(?)",cad);
	}
}