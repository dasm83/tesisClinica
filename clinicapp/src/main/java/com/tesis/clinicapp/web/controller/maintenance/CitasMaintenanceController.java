package com.tesis.clinicapp.web.controller.maintenance;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tesis.clinicapp.model.CatalogoExamen;
import com.tesis.clinicapp.model.CatalogoItemsExamen;
import com.tesis.clinicapp.model.Citas;
import com.tesis.clinicapp.model.Examen;
import com.tesis.clinicapp.model.Paciente;
import com.tesis.clinicapp.service.CitasService;
import com.tesis.clinicapp.service.PacienteService;
import com.tesis.clinicapp.web.form.maintenance.ExamDetailForm;
import com.tesis.clinicapp.web.form.maintenance.citasMainForm;
import com.tesis.clinicapp.web.form.maintenance.laboratoristaMainForm;

@Controller
public class CitasMaintenanceController {
	
	/**
	 * where the request is coming from
	 */
	private static final String URL = "/maintenance/citas.htm";
	private static final String URLcitasj = "/maintenance/citas-ajax.json";
	private static final String URLcitaJson = "/maintenance/date-ajax.json";
	private static final String URLmaintenance ="/maintenance/citasMainteanace.txt";
	/**
	 * name of the jsp which corresponds to URL
	 */
	private static final String JSP = "/maintenance/citas";
	/**
	 * name of the form created in JSP
	 */
	private static final String FORM = "citasMainForm";
	
	@Autowired
	private CitasService citService;
	@Autowired
	private PacienteService pacientService;
	
	@RequestMapping(method = RequestMethod.GET, value = URL)
    public ModelAndView get(HttpServletRequest request){
		request.setAttribute("title", "Programacion de Citas");
		return new ModelAndView(JSP,FORM,new citasMainForm());
	}
	
	@RequestMapping(method = RequestMethod.POST, value = URLcitasj)
	public @ResponseBody List<Map<String,String>> referenceData(HttpServletRequest request){
		 List<Citas> C= citService.findAll();
		 List<Map<String,String>> brief = new ArrayList<>();
		 
		 for(Citas cit : C){
				Map<String,String> list = new HashMap<>();
				list.put("date", cit.getFechaReserva().toString());
				list.put("type","funciono");
				list.put("title","Examen de heces");
				list.put("description", "examen en ayunas");
				list.put("url",cit.getId().toString());
				brief.add(list);
			}
         return brief;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = URLcitaJson, params = "op=itms")
	public @ResponseBody List<Map<String,String>> referenceCita(HttpServletRequest request){
		 List<Map<String,String>> brief = new ArrayList<>();
		 Map<String,String> list = new HashMap<>();
		 Citas cita = citService.findById(new Long(request.getParameter("id")));
		 list.put("id", cita.getId().toString());
		 list.put("date",cita.getFechaReserva().toString());
		 list.put("descripcion",cita.getDescripcion());
		 list.put("paciente", cita.getPaciente().getNombres()+" "+cita.getPaciente().getApellidos());
		 brief.add(list);
		 return brief;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = URLmaintenance, params = "op=del", produces = "text/plain")
	public @ResponseBody String delCita(HttpServletRequest request){
		Long id = new Long((request.getParameter("id")));
		System.out.println("entro a borrar"+ request.getParameter("id").toString());
		Citas cita = citService.findById(id);
		//citService.delete(cita);
		return "Registro eliminado";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = URLmaintenance, params = "op=iou",produces = "text/plain")
	public @ResponseBody String inserOrupdateCita(HttpServletRequest request,citasMainForm form){
		Long id = form.getIdcita();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Paciente pt = pacientService.getByExactName(form.getNamePacient());
		String msj = "";
		
		if(id != null){ //si es diferente a null es una actualizacion, el registro ya existe
				
			Citas cita = citService.findById(id);
			cita.setDescripcion(form.getDescription());
			cita.setPaciente(pt);
			cita.setFechaReserva(formatFecha(form.getDate()));
	//		citService.saveOrUpdate(cita);
			
		}else{
			
			Citas ci=new Citas();
			String fecha= form.getDate();
			
			try {
				Date d= formatter.parse(fecha);
				ci.setFechaReserva(d);
			}catch(ParseException e) {
				e.printStackTrace();
			}
			
	     	ci.setDescripcion(form.getDescription());
			ci.setPaciente(pt);
			citService.save(ci);
			msj="Cita Guardada";
		}
		
		return msj;
	}
	
	private Date formatFecha(String f){
		Date fecha=new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		try {
			fecha= formatter.parse(f);
		}catch(ParseException e) {
			e.printStackTrace();
		}
		return fecha;
	}
	
}
