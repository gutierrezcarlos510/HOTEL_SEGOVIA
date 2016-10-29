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
import org.springframework.web.multipart.MultipartFile;

import Modelos.Dato;
import Modelos.Persona;
import Servicios.DatoS;
import Servicios.UsuarioS;
import Utiles.Utils;

@Controller
@RequestMapping("/usuario/*")
public class usuario {
	
	@Autowired
	UsuarioS usuarioS;
	@Autowired
	DatoS datoS;
	@RequestMapping("gestion")
	public String gestion(){
		return "usuario/gestion";
	}
	@SuppressWarnings("unchecked")
	@RequestMapping("lista")
	public @ResponseBody Map<?, ?> lista(HttpServletRequest request, Integer draw, Integer start, boolean estado,int length)throws IOException{
		Map<String, Object> Data = new HashMap<String, Object>();
		String search = request.getParameter("search[value]");
		List<?> lista=usuarioS.listar(start, estado, search,length);
		String total=lista==null||lista.size()==0?"0":((Map<String, Object>) lista.get(0)).get("Tot").toString();	
		Data.put("draw", draw);
		Data.put("recordsTotal", total);
		Data.put("data", lista);
		Data.put("recordsFiltered", !search.equals("")?lista.size():total);
		return Data;
	}
	@RequestMapping("guardar")
	public @ResponseBody Map<String, Object> guardar(HttpServletRequest request,Persona user,MultipartFile foto1)throws IOException{
		Map<String, Object> Data = new HashMap<String, Object>();
		if(foto1!=null && foto1.getSize()>0){
			String nombre=user.getCi_per()+foto1.getOriginalFilename().substring(foto1.getOriginalFilename().lastIndexOf('.'));
			Utils.SubirArchivo(foto1, nombre, "img/fotos", request);
			user.setFot_per(nombre);
		}else{
			user.setFot_per("user.png");
		}
		Data.put("status", usuarioS.adicionar(user));
		return Data;		
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("actualizar")
	public @ResponseBody Map<String, Object> actualizar(HttpServletRequest request,Persona user,MultipartFile foto1)throws IOException{
		Map<String, Object> Data = new HashMap<String, Object>();
		Map<String, Object> usuario=usuarioS.obtener(user.getCod_per());
		Map<String, Object> us=(Map<String, Object>) request.getSession().getAttribute("user");
		if(foto1!=null && foto1.getSize()>0){
			String nombre=user.getCod_per( )+foto1.getOriginalFilename().substring(foto1.getOriginalFilename().lastIndexOf('.'));
			if(!usuario.get("fot_per").toString().equals("user.png"))Utils.EliminarArchivo(request, "img/fotos", usuario.get("fot_per").toString());
			Utils.SubirArchivo(foto1, nombre, "img/fotos", request);
			user.setFot_per(nombre);
		}else{
			user.setFot_per(usuario.get("fot_per").toString());
		}
		if(Integer.parseInt(us.get("cod_per").toString())==user.getCod_per())
			Data.put("data", usuarioS.obtener(user.getCod_per()));
		else 
			Data.put("data", null);
		Data.put("status", usuarioS.modificar(user));
		return Data;		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("eliminar")
	public @ResponseBody Map<String, Object> eliminar(HttpServletRequest request,int cod_per)throws IOException{
		Map<String, Object> us=(Map<String, Object>) request.getSession().getAttribute("user");
		Map<String, Object> Data = new HashMap<String, Object>();
		if(Integer.parseInt(us.get("cod_per").toString())==cod_per)
			Data.put("status", true);
		else{
			Map<String, Object> usuario=usuarioS.obtener(cod_per);
		if(!usuario.get("fot_per").toString().equals("user.png"))Utils.EliminarArchivo(request, "fotos", usuario.get("fot_per").toString());
		Data.put("status", usuarioS.eliminar(cod_per));
		}
		return Data;
	}
	@RequestMapping("activar")
	public @ResponseBody Map<String, Object> activar(int cod_per)throws IOException{
		Map<String, Object> Data = new HashMap<String, Object>();
		Data.put("status", usuarioS.activar(cod_per));
		return Data;
	}
	@RequestMapping("validar")
	public @ResponseBody Map<String, Object> validarCi(String ci){
		Map<String, Object> Data = new HashMap<String, Object>();
		Data.put("status", usuarioS.validarCi(ci));
		return Data;
	}
	@RequestMapping("obtener")
	public @ResponseBody Map<String, Object> obtener(int cod_per){
		Map<String, Object> Data = new HashMap<String, Object>();
		try {
			Map<String, Object> us=usuarioS.obtener(cod_per);
			Map<String, Object> dat=datoS.obtenerDato(cod_per);
			List<Map<String, Object>> lista= usuarioS.obtenerRoles(cod_per);
			Data.put("data", us);
			Data.put("roles",lista);
			Data.put("datos", dat);
			Data.put("status", true);
		} catch (Exception e) {
			Data.put("status", false);
			System.out.println("error al obtener="+e.toString());
		}
		return Data;
	}
	@RequestMapping("guardarAsignacion")
	public @ResponseBody Map<String, Object> guardarAsignacion(Integer roles[],Dato d,String cla1){
		Map<String, Object> Data = new HashMap<String, Object>();
		Data.put("status", datoS.adicionarDatos(d, roles));
		return Data;
	}
	@RequestMapping("cambiarClave")
	public @ResponseBody Map<String, Object> cambiarClave(Dato d,String cla1){
		Map<String, Object> Data = new HashMap<String, Object>();
		Data.put("status", datoS.adicionar(d.getCod_per(), d.getLog_dat(), cla1));
		return Data;
	}
	@RequestMapping("buscarci")
	public @ResponseBody Map<String, Object> buscarci(String ci){
		Map<String, Object> Data = new HashMap<String, Object>();
		try {
			Data.put("status", !ci.equals(""));
			Data.put("data", usuarioS.buscarCi(ci));
		} catch (Exception e) {
			Data.put("status", false);
		}
		return Data;
	}
	@RequestMapping("buscar_nombres")
	public @ResponseBody Map<String, Object> buscar_nombres(HttpServletRequest request,String cad){
		Map<String, Object> Data = new HashMap<String, Object>();
		try {
			Data.put("data", usuarioS.buscar_nombres(cad));
			Data.put("status", true);
		} catch (Exception e) {
			Data.put("status", false);
		}
		return Data;
	}
}