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

import Modelos.Menu;
import Servicios.MenuS;
import Servicios.ProcesoS;

@Controller
@RequestMapping("/menu/*")
public class menu {
	
	@Autowired
	MenuS menuS;
	@Autowired
	ProcesoS procesoS;
	
	@RequestMapping("gestion")
	public String gestion(){
		return "menu/gestion";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("lista")
	public @ResponseBody Map<?, ?> lista(HttpServletRequest request, Integer draw, Integer start, boolean estado,int length)throws IOException{
		Map<String, Object> Data = new HashMap<String, Object>();
		String search = request.getParameter("search[value]");
		List<?> lista=menuS.listar(start, estado, search,length);
		String total=lista==null||lista.size()==0?"0":((Map<String, Object>) lista.get(0)).get("Tot").toString();	
		Data.put("draw", draw);
		Data.put("recordsTotal", total);
		Data.put("data", lista);
		Data.put("recordsFiltered", !search.equals("")?lista.size():total);
		return Data;

	}
	@RequestMapping("guardar")
	public @ResponseBody Map<String, Object> guardar(Menu m)throws IOException{
		Map<String, Object> Data = new HashMap<String, Object>();
		Data.put("status", menuS.adicionar(m));
		return Data;		
	}
	@RequestMapping("actualizar")
	public @ResponseBody Map<String, Object> actualizar(Menu m)throws IOException{
		Map<String, Object> Data = new HashMap<String, Object>();
		Data.put("status", menuS.modificar(m));
		return Data;		
	}
	@RequestMapping("eliminar")
	public @ResponseBody Map<String, Object> eliminar(int cod_men)throws IOException{
		Map<String, Object> Data = new HashMap<String, Object>();
		Data.put("status", menuS.darEstado(cod_men,false));
		return Data;
	}
	@RequestMapping("activar")
	public @ResponseBody Map<String, Object> activar(int cod_men)throws IOException{
		Map<String, Object> Data = new HashMap<String, Object>();
		Data.put("status", menuS.darEstado(cod_men,true));
		return Data;
	}
	@RequestMapping("validar")
	public @ResponseBody Map<String, Object> validarCi(String nom_men){
		Map<String, Object> Data = new HashMap<String, Object>();
		Data.put("status", menuS.validarNom(nom_men));
		return Data;
	}
	@RequestMapping("obtener")
	public @ResponseBody Map<String, Object> obtener(int cod_men){
		Map<String, Object> Data = new HashMap<String, Object>();
		try {
			Data.put("data", menuS.obtener(cod_men));
			Data.put("procesos",procesoS.obtenerprocesos(cod_men));
			Data.put("status", true);
		} catch (Exception e) {
			Data.put("status", false);
			System.out.println("error al obtener="+e.toString());
		}
		return Data;
	}
	@RequestMapping("guardarAsignacion")
	public @ResponseBody Map<String, Object> guardarAsignacion(int cod_men,Integer procesos[]){
		Map<String, Object> Data = new HashMap<String, Object>();
		Data.put("status", menuS.adicionarMenuProceso(cod_men, procesos));
		return Data;
	}
}