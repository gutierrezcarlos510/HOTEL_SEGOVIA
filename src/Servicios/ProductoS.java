package Servicios;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Service;

import Modelos.Producto;
import Utiles.Db_Coneccion;
import Utiles.Letra;
@Service
public class ProductoS extends Db_Coneccion{
	public class a_objeto implements ParameterizedRowMapper<Producto>{
		@Override
		public Producto mapRow(ResultSet set, int fila) throws SQLException {
			Producto p=new Producto();
			p.setCod_pro(set.getInt("cod_ser"));
			p.setNom_pro(set.getString("nom_ser"));
			p.setPrecom_pro(set.getFloat("des_ser"));
			p.setPreven_pro(set.getFloat("cod_tipser"));
			p.setGan_pro(set.getFloat("gan_pro"));
			p.setCan_pro(set.getInt("can_pro"));
			p.setEst_pro(set.getBoolean("est_ser"));
			p.setCod_tippro(set.getInt("cod_tippro"));
			return p;
		}
	}
	
	public List<Map<String, Object>> listar(int start,boolean estado,String search,int length){
		if(search==null)search="";
		try{
			return db.queryForList("select * from producto_lista(?,?,?,?)"+as_object_add(as_producto, "RN bigint,Tot int"),start,length,search,estado);
		}catch(Exception e){
			System.out.println("error listarProducto"+e.toString());
			return null;
		}
	}
	public Map<String, Object> obtener(int cod_pro){
		try {
			return db.queryForMap("select * from producto_obtener(?)"+as_producto,cod_pro);
		} catch (Exception e) {
			System.out.println("error obtenerProducto"+e.toString());
			return null;
		}
	}
	public boolean adicionar (Producto p){
		try {
			Letra l=new Letra();
			return db.queryForObject("select producto_adicionar(?,?,?,?,?,?)", Boolean.class,l.Primera_Mayuscula(p.getNom_pro()),p.getPrecom_pro(),p.getPreven_pro(),p.getGan_pro(),p.getCan_pro(),p.getCod_tippro());
		} catch (Exception e) {
			System.out.println("error adicionarProducto"+e.toString());
			return false;
		}
	}
	public boolean modificar (Producto p){
		try {
			Letra l=new Letra();
			return db.queryForObject("select producto_modificar(?,?,?,?,?,?,?)", Boolean.class,l.Primera_Mayuscula(p.getNom_pro()),p.getPrecom_pro(),p.getPreven_pro(),p.getGan_pro(),p.getCan_pro(),p.getCod_tippro(),p.getCod_pro());
		} catch (Exception e) {
			System.out.println("error modificarProducto"+e.toString());
			return false;
		}
	}
	public boolean darestado (int cod_pro,boolean est_pro){
		try {
			return db.queryForObject("select producto_darestado(?,?)", Boolean.class,cod_pro,est_pro);
		} catch (Exception e) {
			System.out.println("error darestadoProducto"+e.toString());
			return false;
		}
	}
	public boolean validarNom (String nom){
		return db.queryForObject("select producto_validar", Boolean.class,nom);
	}
}
