package Controladores;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import Modelos.Rol;
import Servicios.RolS;

@Controller
@RequestMapping("/rol/*")
public class rol {
	
	@Autowired
	RolS rolS;
	
	@RequestMapping("gestion")
	public String gestion(){
		return "rol/gestion";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("lista")
	public @ResponseBody Map<?, ?> lista(HttpServletRequest request, Integer draw, Integer start, boolean estado,int length)throws IOException{
		Map<String, Object> Data = new HashMap<String, Object>();
		String search = request.getParameter("search[value]");
		List<?> lista=rolS.listar(start, estado, search,length);
		String total=lista==null||lista.size()==0?"0":((Map<String, Object>) lista.get(0)).get("Tot").toString();	
		Data.put("draw", draw);
		Data.put("recordsTotal", total);
		Data.put("data", lista);
		Data.put("recordsFiltered", !search.equals("")?lista.size():total);
		return Data;
	}
	@RequestMapping("guardar")
	public @ResponseBody Map<String, Object> guardar(Rol r)throws IOException{
		Map<String, Object> Data = new HashMap<String, Object>();
		Data.put("status", rolS.adicionar(r)>0);
		return Data;		
	}
	@RequestMapping("actualizar")
	public @ResponseBody Map<String, Object> actualizar(Rol r)throws IOException{
		Map<String, Object> Data = new HashMap<String, Object>();
		Data.put("status", rolS.modificar(r));
		return Data;		
	}
	@RequestMapping("eliminar")
	public @ResponseBody Map<String, Object> eliminar(int cod_rol)throws IOException{
		Map<String, Object> Data = new HashMap<String, Object>();
		Data.put("status", rolS.darEstado(cod_rol,false));
		return Data;
	}
	@RequestMapping("activar")
	public @ResponseBody Map<String, Object> activar(int cod_rol)throws IOException{
		Map<String, Object> Data = new HashMap<String, Object>();
		Data.put("status", rolS.darEstado(cod_rol,true));
		return Data;
	}
	@RequestMapping("validar")
	public @ResponseBody Map<String, Object> validarCi(String nom_rol){
		Map<String, Object> Data = new HashMap<String, Object>();
		Data.put("status", rolS.validarNom(nom_rol));
		return Data;
	}
	@RequestMapping("obtener")
	public @ResponseBody Map<String, Object> obtener(int cod_rol){
		Map<String, Object> Data = new HashMap<String, Object>();
		try {
			Data.put("data", rolS.obtener(cod_rol));
			Data.put("menus",rolS.obtenerMenus(cod_rol));
			Data.put("status", true);
		} catch (Exception e) {
			Data.put("status", false);
			System.out.println("error al obtener="+e.toString());
		}
		return Data;
	}
	@RequestMapping("guardarAsignacion")
	public @ResponseBody Map<String, Object> guardarAsignacion(int cod_rol,int menus[]){
		Map<String, Object> Data = new HashMap<String, Object>();
		Data.put("status", rolS.adicionarRolMenu(cod_rol, menus));
		return Data;
	}
}