package Servicios;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import Modelos.Rol;
import Utiles.Db_Coneccion;
import Utiles.Letra;
import Utiles.Vectores;

@Service
public class RolS extends Db_Coneccion {
	public List<Map<String, Object>> obtenerRolesXcodper(int cod_per){
		return db.queryForList("select * from rol_obtenerxcodper(?)",cod_per);
	}

	public List<Map<String, Object>> listar(int start,boolean estado,String search,int length){
		if(search==null)search="";
		return db.queryForList("select * from rol_lista(?,?,?,?)"+as_object_add(as_rol, "RN BIGINT,Tot INT,menus INT"),start,length,search,estado);
	}
	
	
	public Map<String, Object> obtener(int cod_rol){
		try {
			return db.queryForMap("select * from rol_obtener(?)",cod_rol);
		} catch (Exception e) {
			System.out.println("error obtenerRol="+e.toString());
			return null;
		}
	}
	public List<Map<String, Object>> obtenerMenus(int cod_rol){
		try {
			return db.queryForList("select * from rol_obtenermenus(?)"+as_object_add(as_menu, "tipo varchar(8)"),cod_rol);
		} catch (Exception e) {
			System.out.println("error obtenerMenus="+e.toString());
			return null;
		}
	}
	public int adicionar(Rol r){
		try {
			Letra l=new Letra();
			return db.queryForObject("select rol_adicionar(?);",Integer.class,l.Primera_Mayuscula(r.getNom_rol()));
		} catch (Exception e) {
			System.out.println("error al adicionar rol="+e.toString());
			return -1;
		}
	}
	public boolean modificar(Rol r){
		try {
			Letra l=new Letra();
			return db.queryForObject("select rol_modificar(?,?);",Boolean.class,l.Primera_Mayuscula(r.getNom_rol()),r.getCod_rol());
		} catch (Exception e) {
			System.out.println("error al modificar rol="+e.toString());
			return false;
		}
	}
	public boolean darEstado(int cod_rol,boolean est){
		try {
			return db.queryForObject("select rol_darestado(?,?);",Boolean.class,cod_rol,est);
		} catch (Exception e) {
			System.out.println("error al eliminar rol="+e.toString());
			return false;
		}
	}
	public boolean adicionarRolMenu(Integer codr,int obtenidos[]){
		try {
			return db.queryForObject("select rolmen_adicionar(?,\'"+new Vectores().convertir_Int_a_String(obtenidos)+"\')",Boolean.class,codr);
		} catch (Exception e) {
			System.out.println("error al adicionar RolMen="+e.toString());
			return false;
		}
	}
	public boolean validarNom(String nom){
		return db.queryForObject("select rol_validar(?)", Boolean.class,nom);
	}
}
