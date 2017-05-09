package com.tesis.clinicapp.web.controller.maintenance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tesis.clinicapp.model.Rol;
import com.tesis.clinicapp.model.Usuario;
import com.tesis.clinicapp.service.RolService;
import com.tesis.clinicapp.service.UsuarioService;
import com.tesis.clinicapp.util.TableData;
import com.tesis.clinicapp.web.form.maintenance.UsuariosMainForm;

@Controller
public class UsuarioMaintenanceController {

	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(UsuarioMaintenanceController.class);
	
	private static final String URL = "/admin/usuario.htm";
	private static final String URLj = "/admin/user-ajax.json";
	private static final String URLops = "/admin/userOp.txt";
	
	private static final String JSP = "/admin/usuario";
	
	private static final String FORM = "UsuariosMainForm";
	
	@Autowired
	private UsuarioService userService;
	
	@Autowired
	private RolService rolService;
	
	@ModelAttribute(value = "roles")
	public List<Rol> getRoles(){
		return rolService.findAll();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = URL)
	public ModelAndView get(HttpServletRequest request){
		request.setAttribute("title", "Usuarios");
		return new ModelAndView(JSP,FORM,new UsuariosMainForm());
	}
	
	@RequestMapping(method = RequestMethod.POST, value = URLj, produces="application/json")
	public @ResponseBody TableData dataTable(HttpServletRequest request, @RequestParam(value="order[0][column]") int col,
			@RequestParam(value="order[0][dir]") String dir){
		TableData json = new TableData();
		json.setDraw(Integer.parseInt(request.getParameter("draw")));
		json.setRecordsFiltered(userService.count());
		json.setRecordsTotal(userService.count());
		json.setData(getUsersList(
									Integer.parseInt(request.getParameter("start")),
									Integer.parseInt(request.getParameter("length")),
									col,
									dir
								));
		
		return json;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = URLops, produces = "text/plain")
	public @ResponseBody String postX(HttpServletRequest request, HttpServletResponse response, UsuariosMainForm form){
		Usuario user = new Usuario();
		String msj = null;
		
		if(form.getAction().equals("I") || form.getAction().equals("U")){ /// doing insert or update; creating or modifying
			user.setNombre(form.getUsername());
			user.setNombres(form.getNames());
			user.setApellidos(form.getSurnames());
			user.setContrasenia(form.getPassword());
			user.setHabilitado(form.isEnabled());
			user.setRol(new Rol(form.getRole()));
			
			userService.saveOrUpdate(user);
			msj = "Usuario guardado";
		} else if(form.getAction().equals("D")){ // doing delete
			user = userService.findById(form.getUsername());
			userService.delete(user);
			msj = "Usuario eliminado";
		}
		
		return msj;
	}
	
	public List<Map<String,String>> getUsersList(int start,int length,int col,String order){
		List<Map<String,String>> brief = new ArrayList<>();
		List<Usuario> users = userService.getFilteredList(start,length,col,order);
		
		for(Usuario u : users){
			Map<String,String> list = new HashMap<>();
			list.put("DT_RowId", u.getNombre());
			list.put("usuario", u.getNombre());
			list.put("contrasena", u.getContrasenia());
			list.put("nombres", u.getNombres());
			list.put("apellidos", u.getApellidos());
			list.put("rol", u.getRol().getId());
			list.put("habilitado", String.valueOf(u.isHabilitado()));
			
			brief.add(list);
		}
		
		return brief;
	}
	
}
