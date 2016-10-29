package Servicios;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Service;

import Modelos.Tipoproducto;
import Utiles.Db_Coneccion;
import Utiles.Letra;
@Service
public class TipoProductoS extends Db_Coneccion{
	public class a_objeto implements ParameterizedRowMapper<Tipoproducto>{
		@Override
		public Tipoproducto mapRow(ResultSet set, int fila) throws SQLException {
			Tipoproducto tp=new Tipoproducto();
			tp.setCod_tippro(set.getInt("cod_tippro"));
			tp.setNom_tippro(set.getString("nom_tippro"));
			tp.setDes_tippro(set.getString("des_tippro"));
			tp.setEst_tippro(set.getBoolean("est_tippro"));
			return tp;
		}
	}
	
	public List<Map<String, Object>> listar(int start,boolean estado,String search,int length){
		if(search==null)search="";
		try{
			return db.queryForList("select * from tipoproducto_lista(?,?,?,?)"+as_object_add(as_tipoproducto, "RN bigint,Tot int"),start,length,search,estado);
		}catch(Exception e){
			System.out.println("error listarTipoproducto"+e.toString());
			return null;
		}
	}
	public Map<String, Object> obtener(int cod_tippro){
		try {
			return db.queryForMap("select * from tipoproducto_obtener(?)"+as_tipoproducto,cod_tippro);
		} catch (Exception e) {
			System.out.println("error obtenerTipoproducto"+e.toString());
			return null;
		}
	}
	public boolean adicionar (Tipoproducto tp){
		try {
			Letra l=new Letra();
			return db.queryForObject("select tipoproducto_adicionar(?,?)", Boolean.class,l.Primera_Mayuscula(tp.getNom_tippro()),tp.getDes_tippro());
		} catch (Exception e) {
			System.out.println("error adicionarTipoproducto"+e.toString());
			return false;
		}
	}
	public boolean modificar (Tipoproducto tp){
		try {
			Letra l=new Letra();
			return db.queryForObject("select tipoproducto_modificar(?,?,?)", Boolean.class,l.Primera_Mayuscula(tp.getNom_tippro()),tp.getDes_tippro(),tp.getCod_tippro());
		} catch (Exception e) {
			System.out.println("error modificarTipoproducto"+e.toString());
			return false;
		}
	}
	public boolean darestado (int cod_tippro,boolean est_tippro){
		try {
			return db.queryForObject("select tipoproducto_darestado(?,?)", Boolean.class,cod_tippro,est_tippro);
		} catch (Exception e) {
			System.out.println("error darestadoTipoproducto"+e.toString());
			return false;
		}
	}
	public boolean validarNom (String nom){
		return db.queryForObject("select tipoproducto_validar", Boolean.class,nom);
	}
}
