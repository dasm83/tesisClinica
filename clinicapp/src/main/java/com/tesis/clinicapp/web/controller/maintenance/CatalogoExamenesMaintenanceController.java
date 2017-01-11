package com.tesis.clinicapp.web.controller.maintenance;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.tesis.clinicapp.model.CatalogoExamen;
import com.tesis.clinicapp.model.CatalogoItemsExamen;
import com.tesis.clinicapp.model.Clasificacion;
import com.tesis.clinicapp.model.Examen;
import com.tesis.clinicapp.model.ItemsExamen;
import com.tesis.clinicapp.model.ItemsExamenId;
import com.tesis.clinicapp.model.Laboratorista;
import com.tesis.clinicapp.model.Paciente;
import com.tesis.clinicapp.service.CatExamenService;
import com.tesis.clinicapp.service.ClasificacionService;
import com.tesis.clinicapp.service.ItemsCatExamenService;
import com.tesis.clinicapp.util.TableData;
import com.tesis.clinicapp.web.form.maintenance.CatalogoExamDetail;
import com.tesis.clinicapp.web.form.maintenance.CatalogoExamDetailFormItem;
import com.tesis.clinicapp.web.form.maintenance.CatalogoExamenMainForm;
import com.tesis.clinicapp.web.form.maintenance.ExamDetailForm;


@Controller
public class CatalogoExamenesMaintenanceController {

	/**
	 * where the request is coming from
	 */
	private static final String URL = "/maintenance/catalogoExamenes.htm";
	private static final String URLj = "/maintenance/catalogoExam-ajax.json";
	private static final String URLexamCat = "/maintenance/examCat.txt";
	private static final String URLcatExam = "/maintenance/nuevoExamenCatalogo.htm";
	
	/**
	 * name of the jsp which corresponds to URL
	 */
	
	private static final String JSP = "/maintenance/catalogoExamenes";
	private static final String JSPdetCat = "/maintenance/nuevoExamenCatalogo";
	
	/**
	 * name of the form created in JSP
	 */
	private static final String FORM = "CatalogoExamenMainform";
	private static final String FORMdet = "CatalogoExamDetail";
	
	@Autowired
	private CatExamenService catExamService;
	
	@Autowired
	private ClasificacionService clasService;
	
	@Autowired
	private ItemsCatExamenService itemCatService;
	
	@RequestMapping(method = RequestMethod.GET, value = URL)
    public ModelAndView get(HttpServletRequest request){
		
		/// we have to set the view's title (text inserted on title html tag)
		request.setAttribute("title", "Catalogo de Examenes");	
		return new ModelAndView(JSP,FORM,new CatalogoExamenMainForm());
		
	}
	
	@RequestMapping(method = RequestMethod.POST, value = URLj, params = "draw", produces = "application/json")
	public @ResponseBody TableData dataTable(HttpServletRequest request){
		TableData json = new TableData();
		json.setDraw(Integer.parseInt(request.getParameter("draw")));
		json.setRecordsTotal(1);
		json.setRecordsFiltered(1);
		json.setData(getCatExamList(Integer.parseInt(request.getParameter("draw")),
				Integer.parseInt(request.getParameter("start")),
				Integer.parseInt(request.getParameter("length"))));
		return json;
	}
	
	@SuppressWarnings("unchecked")
	private List<Map<String,String>>  getCatExamList(int draw, int start, int length) {
		List<Map<String,String>> brief = new ArrayList<>();
		List<CatalogoExamen> catexams = catExamService.getFilteredList(draw,start,length);
		for(CatalogoExamen exam : catexams){
			Map<String,String> list = new HashMap<>();
			list.put("DT_RowId", exam.getId().toString());
			list.put("nombre", exam.getNombre().toString());
			brief.add(list);
		}
		return brief;
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = URLcatExam, params = "id")
	public ModelAndView getOldDetail(HttpServletRequest request){
			
		CatalogoExamen catEx=catExamService.findById(Long.parseLong(request.getParameter("id")));
		CatalogoExamDetail form = new CatalogoExamDetail();
		
		form.setExamCatId(catEx.getId());
		form.setNombre(catEx.getNombre().toString().toString());
		form.setDescripcion(catEx.getDescripcion().toString());
		
		List<CatalogoExamDetailFormItem> items = new ArrayList<>();
		catEx.getCatalogoItemsExamens().forEach(item->{
			CatalogoExamDetailFormItem i =new CatalogoExamDetailFormItem();
			i.setId(item.getId());
			i.setNombre(item.getNombre());
			i.setDescripcion(item.getDescripcion());
			items.add(i);
		});
		form.setItems(items);
		
		request.setAttribute("title", "Modiciacion de Examen de Catalogo ");
		return new ModelAndView(JSPdetCat,FORMdet,form); 
	}
	
	@RequestMapping(method = RequestMethod.GET, value = URLcatExam)
	public ModelAndView getNewExam(HttpServletRequest request){
		request.setAttribute("title", "Creacion de Nuevo Examen para Catalogo");
		return new ModelAndView(JSPdetCat,FORMdet,new CatalogoExamDetail()); 
	}
	
	@ModelAttribute(value="examClasificacion")
	private List<Map<String,String>> getExamTypes(){
		List<Map<String,String>> brief = new ArrayList<>();
		List<Clasificacion> types = clasService.findAll();
		
		types.forEach(type->{
			Map<String,String> list = new HashMap<>();
			list.put("id", type.getId().toString());
			list.put("name", type.getCategoria());
			brief.add(list);
		});
		
		return brief;
	}
	
	
	/**
	 * Insert or Update op
	 * 
	 * @param request
	 * @param form
	 * @return
	 * @throws ParseException 
	 */
	@SuppressWarnings("unused")
	@RequestMapping(method = RequestMethod.POST, value = URLexamCat, produces = "text/plain")
	public @ResponseBody String modCatExamTable(HttpServletRequest request, HttpServletResponse response, CatalogoExamDetail form) throws ParseException{
		
		Long id = form.getExamCatId();
		String msj= "Registro Guardado"; 
		
		if(id != null){
			Clasificacion Cla= clasService.findById(form.getClasificacion());
			CatalogoExamen catEx= catExamService.findById(id);
			Set<CatalogoItemsExamen> catItms = catEx.getCatalogoItemsExamens();
			
				form.getItems().forEach(formItem->{
					for(CatalogoItemsExamen exItem:catItms){
						if(exItem.getNombre().equals((formItem.getOldId()))){
							exItem.setNombre(formItem.getNombre());
							break;
						
						}else if(formItem.getOldId().equals("vacio")){
							CatalogoItemsExamen itemOnEx = new CatalogoItemsExamen();
							itemOnEx.setCatalogoExamen(catEx);
							itemOnEx.setNombre(formItem.getNombre());
							itemCatService.save(itemOnEx);
							break;
						}else if(formItem.getOldId().equals("delete")){
							CatalogoItemsExamen itemOnEx = itemCatService.findById(formItem.getId());
							itemCatService.delete(itemOnEx);
							break;
						}
						
				//	CatalogoItemsExamen itemOnEx = new CatalogoItemsExamen();
				//	itemOnEx.setCatalogoExamen(catEx);
				//	itemOnEx.setNombre(formItem.getNombre());
				//    itemCatService.save(itemOnEx);
				    
			//				catItm.setNombre(formItem.getNombre());
			//				catItms.add(e);
							
					}
				});
				
				catEx.setCatalogoItemsExamens(catItms);
				catEx.setDescripcion(form.getDescripcion());
				catEx.setNombre(form.getNombre());
				catEx.setClasificacion(Cla);
				catExamService.saveOrUpdate(catEx);
		}	
		
		else{			
		
			CatalogoExamen catEx=new CatalogoExamen();
			Clasificacion Cla= clasService.findById(form.getClasificacion());
			catEx.setNombre(form.getNombre());
			catEx.setDescripcion(form.getDescripcion());
			catEx.setClasificacion(Cla);
			catExamService.save(catEx);
			
			try{
				form.getItems().forEach(formItem->{ CatalogoItemsExamen itemOnEx = new CatalogoItemsExamen();
							
							itemOnEx.setCatalogoExamen(catEx);
							itemOnEx.setNombre(formItem.getNombre());
						    itemCatService.save(itemOnEx);
			  });
				
				
			}catch(Exception e){
				catExamService.delete(catEx);
				msj = e.getMessage();
				response.setStatus(500); 
				return msj;
			}
		}
		
		return msj;
	
	}
	
}
	

