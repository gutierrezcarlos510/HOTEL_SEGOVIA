package Controladores;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import Modelos.Persona;
import Servicios.DatoS;
import Servicios.GeneralS;
import Servicios.UsuarioS;
import Utiles.GeneradorReportes;

@Controller
@RequestMapping("/dato/*")
public class dato {
	@Autowired
	GeneralS generalS;
	@Autowired
	UsuarioS usuarioS;
	@Autowired
	DatoS datoS;
	@Autowired
	DataSource datasource;
	@RequestMapping("gestion")
	public String gestion(HttpServletRequest request,Model model){
		return "dato/gestion";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("lista")
	public @ResponseBody Map<?, ?> lista(HttpServletRequest request, Integer draw, Integer start, boolean estado,int length)throws IOException{
		String total;
		Map<String, Object> Data = new HashMap<String, Object>();
		String search = (request.getParameter("search[value]")==null?"":request.getParameter("search[value]"));
		List<?> lista=usuarioS.listar(start, estado, search,length);
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
//	@RequestMapping("eliminar")
//	public @ResponseBody Map<String, Object> eliminar(HttpServletRequest request,Model model,int cod_per)throws IOException{
//		Map<String, Object> Data = new HashMap<String, Object>();
//		Data.put("status", datoS.eliminar(cod_per));
//		return Data;
//	}
	@RequestMapping("generar")
	public @ResponseBody Map<String, Object> generar(HttpServletRequest request,Model model,int cod_per)throws IOException{
		Map<String, Object> Data = new HashMap<String, Object>();
		Data.put("status", datoS.adicionar(cod_per, "user-"+cod_per, "123456789"));
		return Data;
	}
	@RequestMapping("validarLogin")
	public @ResponseBody Map<String, Object> validarLogin(HttpServletRequest request,String log_dat){
		Map<String, Object> Data = new HashMap<String, Object>();
		Data.put("status", datoS.validarLogin(log_dat));
		return Data;
	}
	@RequestMapping("ver")
	public void ver(HttpServletRequest request,HttpServletResponse response,int cod_per){
		try {
			int gestion=(int)request.getSession().getAttribute("gestion");
			Map<String, Object> general=generalS.obtener(gestion);
			String nombre="dato_"+cod_per+"_"+gestion,tipo="pdf",estado="inline";
			Map<String, Object> persona=usuarioS.obtener(cod_per);
			Persona us=(Persona)request.getSession().getAttribute("user");
			String reportUrl="/Reportes/dato_ver.jasper";
			Map<String, Object> parametros=new HashMap<String, Object>();
			parametros.put("usuario", us.toString());
			parametros.put("cod_per", cod_per);
			parametros.put("des_gen", general.get("des_gen").toString());
			parametros.put("dir_gen", general.get("dir_gen").toString());
			parametros.put("tel_gen", general.get("tel_gen").toString());
			parametros.put("lug_gen", general.get("lug_gen").toString());
			parametros.put("gestion", Integer.parseInt(general.get("ges_gen").toString()));
			parametros.put("persona", persona.get("nom_per").toString()+" "+persona.get("priape_per".toString())+persona.get("segape_per").toString());
			GeneradorReportes generador=new GeneradorReportes();
			generador.generarReporte(response, getClass().getResource(reportUrl), tipo,parametros,datasource.getConnection() ,nombre, estado);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error rep="+e.toString());
		}
	}
}
