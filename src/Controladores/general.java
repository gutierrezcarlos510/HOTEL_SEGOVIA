package Controladores;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import Modelos.General;
import Modelos.Persona;
import Servicios.GeneralS;
import Servicios.MenuS;

@Controller
@RequestMapping("/general/*")
public class general {
	
	@Autowired
	GeneralS generalS;
	
	@Autowired
	MenuS menuS;
	
	@RequestMapping("cambiar")
	public String cambiar(HttpServletRequest request,Model model,int gestion){
		HttpSession sesion=request.getSession();
		Persona user=(Persona)sesion.getAttribute("user");
		model.addAttribute("user",user);
		model.addAttribute("menus",menuS.obtenerXusuario(user.getCod_per()));
		model.addAttribute("msg","Bienvenido, "+user.getNom_per()+" "+user.getPriape_per());
		model.addAttribute("gestion",generalS.obtener(gestion));
		model.addAttribute("gestiones",generalS.listar(-1, true, "",0));
		sesion.setAttribute("gestion", gestion);
		return "principal/principal";
	}
	@RequestMapping("gestion")
	public String gestion(HttpServletRequest request,Model model){
		return "general/gestion";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("lista")
	public @ResponseBody Map<?, ?> lista(HttpServletRequest request, Integer draw, Integer start, boolean estado,int length)throws IOException{
		String total;
		Map<String, Object> Data = new HashMap<String, Object>();
		String search = request.getParameter("search[value]");
		List<?> lista=generalS.listar(start, estado, search,length);
		try {
			total=((Map<String, Object>) lista.get(0)).get("Tot").toString();			
		} catch (Exception e) {
			total="0";
		}
		Data.put("draw", draw);
		Data.put("recordsTotal", total);
		Data.put("data", lista);
		if(!search.equals(""))
			Data.put("recordsFiltered", lista.size());
		else
			Data.put("recordsFiltered", total);
		return Data;

	}
	@RequestMapping("guardar")
	public @ResponseBody Map<String, Object> guardar(HttpServletRequest request,Model model,General g,MultipartFile logtex_gen1,MultipartFile logsintex_gen1)throws IOException{
		Map<String, Object> Data = new HashMap<String, Object>();
		try {
			if(logtex_gen1!=null&&!logtex_gen1.getOriginalFilename().equals("")){
				String archivo="logo-"+g.getGes_gen()+logtex_gen1.getOriginalFilename().substring(logtex_gen1.getOriginalFilename().lastIndexOf("."),logtex_gen1.getOriginalFilename().length());
				//archivo=Utils.SubirArchivo(logtex_gen1,archivo,request.getSession().getServletContext().getRealPath("/general")+"/");
				g.setLogtex_gen(archivo);
			}
			if(logsintex_gen1!=null&&!logsintex_gen1.getOriginalFilename().equals("")){
				String archivo="loguito-"+g.getGes_gen()+logsintex_gen1.getOriginalFilename().substring(logsintex_gen1.getOriginalFilename().lastIndexOf("."),logsintex_gen1.getOriginalFilename().length());
				//archivo=Utils.SubirArchivo(logsintex_gen1,archivo,request.getSession().getServletContext().getRealPath("/general")+"/");
				g.setLogsintex_gen(archivo);
			}
			Data.put("status", generalS.adicionar(g));
		} catch (Exception e) {
			System.out.println("error al guardar="+e.toString());
			Data.put("status", false);
		}
		return Data;		
	}
	@RequestMapping("actualizar")
	public @ResponseBody Map<String, Object> actualizar(HttpServletRequest request,Model model,General g,MultipartFile logtex_gen1,MultipartFile logsintex_gen1)throws IOException{
		Map<String, Object> Data = new HashMap<String, Object>();
		try {
			Map<String, Object> gen=generalS.obtener(g.getGes_gen());
			if(logtex_gen1!=null&&!logtex_gen1.getOriginalFilename().equals("")){
				String archivo="logo-"+g.getGes_gen()+logtex_gen1.getOriginalFilename().substring(logtex_gen1.getOriginalFilename().lastIndexOf("."),logtex_gen1.getOriginalFilename().length());
				//archivo=Utils.SubirArchivo(logtex_gen1,archivo,request.getSession().getServletContext().getRealPath("/general")+"/");
				g.setLogtex_gen(archivo);
			}else
				g.setLogtex_gen(gen.get("logtex_gen").toString());
			if(logsintex_gen1!=null&&!logsintex_gen1.getOriginalFilename().equals("")){
				String archivo="loguito-"+g.getGes_gen()+logsintex_gen1.getOriginalFilename().substring(logsintex_gen1.getOriginalFilename().lastIndexOf("."),logsintex_gen1.getOriginalFilename().length());
				//archivo=Utils.SubirArchivo(logsintex_gen1,archivo,request.getSession().getServletContext().getRealPath("/general")+"/");
				g.setLogsintex_gen(archivo);
			}else
				g.setLogsintex_gen(gen.get("logsintex_gen").toString());
			Data.put("status", generalS.modificar(g));
		} catch (Exception e) {
			System.out.println("error al actualizar="+e.toString());
			Data.put("status", false);				
		}
		return Data;		
	}
	@RequestMapping("eliminar")
	public @ResponseBody Map<String, Object> eliminar(HttpServletRequest request,Model model,int ges_gen)throws IOException{
		Map<String, Object> Data = new HashMap<String, Object>();
		Data.put("status", generalS.darEstado(ges_gen, false));
		return Data;
	}
	@RequestMapping("activar")
	public @ResponseBody Map<String, Object> activar(HttpServletRequest request,Model model,int ges_gen)throws IOException{
		Map<String, Object> Data = new HashMap<String, Object>();
		Data.put("status", generalS.darEstado(ges_gen, true));
		return Data;
	}
	@RequestMapping("validar")
	public @ResponseBody Map<String, Object> validarCi(HttpServletRequest request,int ges_gen){
		Map<String, Object> Data = new HashMap<String, Object>();
		Data.put("status", generalS.validarGestion(ges_gen));
		return Data;
	}
	@RequestMapping("obtener")
	public @ResponseBody Map<String, Object> obtener(HttpServletRequest request,int ges_gen){
		Map<String, Object> Data = new HashMap<String, Object>();
		try {
			Data.put("data", generalS.obtener(ges_gen));
			Data.put("status", true);
		} catch (Exception e) {
			Data.put("status", false);
			System.out.println("error al obtener="+e.toString());
		}
		return Data;
	}
}