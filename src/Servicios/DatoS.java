package Servicios;

import java.util.Map;

import org.springframework.stereotype.Service;

import Modelos.Dato;
import Utiles.Db_Coneccion;

@Service
public class DatoS extends Db_Coneccion{
	public Map<String, Object> obtenerDato(int cod){
		try {
			Map<String, Object> dato=db.queryForMap("select * from dato_obtener(?)",cod);
			return dato;
		} catch (Exception e) {
			return null;
		}
	}
	public boolean adicionarDatos(Dato d,Integer obtenidos[]){
		try {
			if(db.queryForObject("select dato_adicionar(?,?,?)",Boolean.class,d.getCod_per(),d.getLog_dat(),d.getCla_dat())){
				db.update("delete from usurol where cod_per=?",d.getCod_per());
				for (int i = 0; i < obtenidos.length; i++) {
					db.queryForObject("select usurol_adicionar(?,?)",Boolean.class,d.getCod_per(),obtenidos[i]);
				}
			};
			return true;
		} catch (Exception e) {
			System.out.println("error al adicionar datos="+e.toString());
			return false;
		}
	}

	public boolean adicionar(int cod,String log,String cla){
		try {
			return db.queryForObject("select dato_adicionar(?,?,?)",Boolean.class,cod,log,cla);
		} catch (Exception e) {
			System.out.println("error al eliminar dato="+e.toString());
			return false;
		}
	}
	public boolean validarLogin(String login){
		return db.queryForObject("select dato_validarlogin(?)", Boolean.class,login);
	}
}