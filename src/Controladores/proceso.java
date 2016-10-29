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

import Modelos.Proceso;
import Servicios.ProcesoS;

@Controller
@RequestMapping("/proceso/*")
public class proceso {
	
	@Autowired
	ProcesoS procesoS;
	
	@RequestMapping("gestion")
	public String gestion(){
		return "proceso/gestion";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("lista")
	public @ResponseBody Map<?, ?> lista(HttpServletRequest request, Integer draw, Integer start, boolean estado,int length)throws IOException{
		Map<String, Object> Data = new HashMap<String, Object>();
		String search = request.getParameter("search[value]");
		List<?> lista=procesoS.listar(start, estado, search,length);
		String total=lista==null||lista.size()==0?"0":((Map<String, Object>) lista.get(0)).get("Tot").toString();	
		Data.put("draw", draw);
		Data.put("recordsTotal", total);
		Data.put("data", lista);
		Data.put("recordsFiltered", !search.equals("")?lista.size():total);
		return Data;
	}
	
	@RequestMapping("guardar")
	public @ResponseBody Map<String, Object> guardar(Proceso p)throws IOException{
		Map<String, Object> Data = new HashMap<String, Object>();
		Data.put("status", procesoS.adicionar(p)>0);
		return Data;		
	}
	
	@RequestMapping("actualizar")
	public @ResponseBody Map<String, Object> actualizar(Proceso p,String ico_pro1)throws IOException{
		Map<String, Object> Data = new HashMap<String, Object>();
		p.setIco_pro(ico_pro1);
		Data.put("status", procesoS.modificar(p));
		return Data;		
	}
	
	@RequestMapping("eliminar")
	public @ResponseBody Map<String, Object> eliminar(int cod_pro)throws IOException{
		Map<String, Object> Data = new HashMap<String, Object>();
		Data.put("status", procesoS.darEstado(cod_pro,false));
		return Data;
	}
	
	@RequestMapping("activar")
	public @ResponseBody Map<String, Object> activar(int cod_pro)throws IOException{
		Map<String, Object> Data = new HashMap<String, Object>();
		Data.put("status", procesoS.darEstado(cod_pro,true));
		return Data;
	}
	
	@RequestMapping("validar")
	public @ResponseBody Map<String, Object> validarCi(String nom_pro){
		Map<String, Object> Data = new HashMap<String, Object>();
		Data.put("status", procesoS.validarNom(nom_pro));
		return Data;
	}
	
	@RequestMapping("obtener")
	public @ResponseBody Map<String, Object> obtener(int cod_pro){
		Map<String, Object> Data = new HashMap<String, Object>();
		try {
			Data.put("data", procesoS.obtener(cod_pro));
			Data.put("status", true);
		} catch (Exception e) {
			Data.put("status", false);
			System.out.println("error al obtener="+e.toString());
		}
		return Data;
	}
}