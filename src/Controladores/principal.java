package Controladores;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import Servicios.MenuS;
import Servicios.RolS;
import Servicios.UsuarioS;
import Utiles.fechas;

@Controller
@RequestMapping("/principal/*")
public class principal {
	@Autowired
	UsuarioS usuarioS;
	@Autowired
	MenuS menuS;
	@Autowired
	RolS rolS;
	
	@RequestMapping("login")
	public String login(Model model){
		return "principal/login";
	}
	@RequestMapping("validar")
	public String validar(HttpServletRequest request,Model model,String login,String password){
		try {
			Map<String, Object> usuario=usuarioS.iniciar_sesion(login, password);
			if(usuario==null){
				model.addAttribute("msg","Usuario incorrecto, por favor verifique login y clave.");
				return "principal/login";
			}else{
				List<Map<String, Object>> roles=rolS.obtenerRolesXcodper(Integer.parseInt(usuario.get("cod_per").toString()));
				Map<String, Object> rol=roles.get(0);
				HttpSession sesion=request.getSession();
				model.addAttribute("user",usuario);
				model.addAttribute("menus",menuS.menu_obtener_x_codrol(Integer.parseInt(rol.get("cod_rol").toString())));
				model.addAttribute("rol",rol);
				model.addAttribute("roles",roles);
				model.addAttribute("msg","Bienvenido, "+usuario.get("nom_per").toString()+" "+usuario.get("priape_per").toString());
				model.addAttribute("fecha",new fechas().obtenerFechaActualEspaniol());
				sesion.setAttribute("user", usuario);
				return "principal/principal";
			}
		} catch (Exception e) {
			System.out.println("error:"+e.toString());
		}
		return "";
	}
	@SuppressWarnings("unchecked")
	@RequestMapping("cambiarRol")
	public String cambiarRol(HttpServletRequest request,Model model,int cod_rol){
		try {
			Map<String, Object> usuario=((Map<String, Object>) request.getSession().getAttribute("user"));
			List<Map<String, Object>> roles=rolS.obtenerRolesXcodper(Integer.parseInt(usuario.get("cod_per").toString()));
			Map<String, Object> rol=rolS.obtener(cod_rol);
			model.addAttribute("user",usuario);
			model.addAttribute("menus",menuS.menu_obtener_x_codrol(cod_rol));
			model.addAttribute("rol",rol);
			model.addAttribute("roles",roles);
			model.addAttribute("fecha",new fechas().obtenerFechaActualEspaniol());
			model.addAttribute("msg","Bienvenido, "+usuario.get("nom_per").toString()+" "+usuario.get("priape_per").toString());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "principal/principal";
	}
	
	@RequestMapping("salir")
	public String salir(HttpServletRequest request,Model model){
		HttpSession sesion=request.getSession();
		sesion.invalidate();
		model.addAttribute("msg","Gracias por utilizar el sistema");
		return "principal/login";
	}
	@RequestMapping("blanco")
	public String blanco(HttpServletRequest request,Model model){
		return "principal/blanco";
	}
	@RequestMapping("menu")
	public String menu(HttpServletRequest request,Model model){
		return "principal/menu";
	}
	@RequestMapping("inicio")
	public String inicio(HttpServletRequest request,Model model){
		return "principal/datos";
	}
}
