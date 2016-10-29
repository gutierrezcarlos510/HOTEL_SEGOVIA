package Utiles;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public abstract class Utils {

	public static String SubirArchivo(MultipartFile archivo,String nombre,String ubicacion,HttpServletRequest request){
		File repositorio=new File(request.getSession().getServletContext().getRealPath("/"+ubicacion)+"/"+nombre);
		if(archivo!=null && archivo.getSize()>0){
			try {
  	              archivo.transferTo(repositorio);
  	    	   	  return nombre;
			} catch (IllegalStateException|IOException e) {
  	    	   		e.printStackTrace();	
  	    	}
		}
		return null;
	}
	
	public static void EliminarArchivo(HttpServletRequest r,String ubicacion,String filename) {
		new File(r.getSession().getServletContext().getRealPath("/"+ubicacion) + "/" + filename).delete();	
	}	
}

