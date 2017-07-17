package com.tesis.clinicapp.web.controller.maintenance;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
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
import com.tesis.clinicapp.model.ItemsValoresReferencia;
import com.tesis.clinicapp.model.Laboratorista;
import com.tesis.clinicapp.model.Paciente;
import com.tesis.clinicapp.service.CatExamenService;
import com.tesis.clinicapp.service.ClasificacionService;
import com.tesis.clinicapp.service.ItemsCatExamenService;
import com.tesis.clinicapp.service.ItemsExamenService;
import com.tesis.clinicapp.service.ItemsValoresRefService;
import com.tesis.clinicapp.util.TableData;
import com.tesis.clinicapp.web.form.maintenance.CatalogoExamDetail;
import com.tesis.clinicapp.web.form.maintenance.CatalogoExamDetailFormItem;
import com.tesis.clinicapp.web.form.maintenance.CatalogoExamVrDetailsFormItem;
import com.tesis.clinicapp.web.form.maintenance.CatalogoExamVrMainForm;
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
	private static final String URLexamItems = "/maintenance/exam.txt";
	private static final String URLexamItemsj = "/maintenance/exam-ajax.json";
	private static final String URLcatExam = "/maintenance/nuevoExamenCatalogo.htm";
	private static final String URLcatExamVR = "/maintenance/valoresReferencia.htm";
	private static final String URLcatExamItemsVR = "/maintenance/examCatVr.txt";	
	/**
	 * name of the jsp which corresponds to URL
	 */
	
	private static final String JSP = "/maintenance/catalogoExamenes";
	private static final String JSPdetCat = "/maintenance/nuevoExamenCatalogo";
	private static final String JSPItemsCatVR ="/maintenance/valoresReferencia";
	/**
	 * name of the form created in JSP
	 */
	private static final String FORM = "CatalogoExamenMainform";
	private static final String FORMdet = "CatalogoExamDetail";
	private static final String FORMdetItem = "CatalogoExamVrMainForm";      
	
	@Autowired
	private CatExamenService catExamService;
	
	@Autowired
	private ClasificacionService clasService;
	
	@Autowired
	private ItemsCatExamenService itemCatService;
	
	@Autowired
	private ItemsValoresRefService valoresService;
	
	@Autowired
	private ItemsExamenService itemService;
	
	
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
	
	@RequestMapping(method = RequestMethod.POST, value = URLcatExamVR, params = "type")
	public ModelAndView getItemsDetail(HttpServletRequest request){
		CatalogoItemsExamen itemOnEx =  itemCatService.findById(new Long(request.getParameter("type")));
		CatalogoExamVrMainForm form = new CatalogoExamVrMainForm();
		
		request.setAttribute("title","Valores de Referencia "+itemOnEx.getNombre());
			form.setItemId(itemOnEx.getId());
			form.setNombre(itemOnEx.getNombre().toString());
			
			List<CatalogoExamVrDetailsFormItem> vr = new ArrayList<>();
			itemOnEx.getItemsValoresReferencias().forEach(item->{
				CatalogoExamVrDetailsFormItem i =new CatalogoExamVrDetailsFormItem();
				i.setId(item.getId());
				i.setMinAge((item.getEdadMinima()));
				i.setMaxAge((item.getEdadMaxima()));
				i.setSex((item.getSexo()));
				i.setTypeRango(item.getTipoRango());
			//	i.setvRMax(item.getValorRefMaximo());
			//	i.setvRMin(item.getValorRefMinimo());
				
				if(item.getValorRefMaximo()==-1){
					System.out.println("comparo y entro");
					i.setvRMax("V.N.");
				}else{
					i.setvRMax(String.valueOf(item.getValorRefMaximo()));
				}
				
				if(item.getValorRefMinimo()==-1){
					i.setvRMin("V.N.");
				}else{
					i.setvRMin(String.valueOf(item.getValorRefMinimo()));
				}
				vr.add(i);
			});
			form.setItems(vr);
			
		return new ModelAndView(JSPItemsCatVR,FORMdetItem,form);
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
		form.setNombre(catEx.getNombre().toString());
		form.setDescripcion(catEx.getDescripcion().toString());
		
		List<CatalogoExamDetailFormItem> items = new ArrayList<>();
		catEx.getCatalogoItemsExamens().forEach(item->{
			CatalogoExamDetailFormItem i =new CatalogoExamDetailFormItem();
			i.setId(item.getId());
			i.setNombre(item.getNombre());
			i.setDescripcion(item.getDescripcion());
			i.setUnidad(item.getUnidad());
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
	
	@ModelAttribute(value="unidadSelec")
	private List<Map<String,String>> getUnidades(){
		List<Map<String,String>> brief = new ArrayList<>();
	    String[] unidades= {"UI/L","UI/ml","g/L","g/dL","mEq","mEq/L","mg","mg/L","mg/dL","mL","U/L","--","U/mL","+/-"}; 
	   
	    for (String elemento: unidades){
			Map<String,String> map = new HashMap<>();
			map.put("name",elemento);
			brief.add(map);
	    }
		
		return brief;
	}
	
	/**
	 * Delete Catalogo exam op
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = URLexamItems, params = "op=del", produces = "text/plain")
	public @ResponseBody String delExam(HttpServletRequest request){
		Long id = new Long(request.getParameter("id"));
		CatalogoExamen ex = catExamService.findById(id);
		 Set<CatalogoItemsExamen> catItms = ex.getCatalogoItemsExamens();
		 
		 for(CatalogoItemsExamen exItem:catItms){
			 Set<ItemsValoresReferencia> itemsVR=exItem.getItemsValoresReferencias();	 
			 Set<ItemsExamen> itemsVal=exItem.getItemsExamens();
			 
			 for(ItemsValoresReferencia itemVR:itemsVR){
				 itemCatService.delete(itemVR);
		     }
			 
			 for(ItemsExamen itemVal:itemsVal){
				 itemService.delete(itemVal);
		     }
		  itemCatService.delete(exItem);	 
	    }
	
		 catExamService.delete(ex); 
		 
		return "Registro eliminado";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = URLexamItemsj, params = "op=itms")
	public @ResponseBody List<Map<String,String>> referenceData(HttpServletRequest request){
		 List<Map<String,String>> brief = new ArrayList<>();
		 CatalogoExamen catEx=catExamService.findById(Long.parseLong(request.getParameter("id")));
		 Set<CatalogoItemsExamen> catItms = catEx.getCatalogoItemsExamens();
         
		 for(CatalogoItemsExamen exItem:catItms){
			  Map<String,String> map = new HashMap<>();
			 	map.put("id",exItem.getId().toString());
			 	map.put("nombre",exItem.getNombre());
			 	brief.add(map);
				}
		 
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
							exItem.setUnidad(formItem.getUnidad());
							break;
						
						}else if(formItem.getOldId().equals("vacio")){
							CatalogoItemsExamen itemOnEx = new CatalogoItemsExamen();
							itemOnEx.setCatalogoExamen(catEx);
							itemOnEx.setNombre(formItem.getNombre());
							itemOnEx.setUnidad(formItem.getUnidad());
							System.out.println("entro a crear");
							itemCatService.save(itemOnEx);
							break;
							
						}else if(formItem.getOldId().equals("delete")){
							CatalogoItemsExamen itemOnEx = itemCatService.findById(formItem.getId());
							itemCatService.delete(itemOnEx);
							break;
						}
							
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
				form.getItems().forEach(formItem->{ 
					
					        CatalogoItemsExamen itemOnEx = new CatalogoItemsExamen();
							itemOnEx.setCatalogoExamen(catEx);
							itemOnEx.setNombre(formItem.getNombre());
							
							if(formItem.getUnidad().equals("--")){
							itemOnEx.setUnidad("");
							}else{
							itemOnEx.setUnidad(formItem.getUnidad());
							}
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
	
	/**
	 * Insert or Update ItemsVr
	 * 
	 * @param request
	 * @param form
	 * @return
	 * @throws ParseException 
	 */

	@SuppressWarnings("unused")
	@RequestMapping(method = RequestMethod.POST, value = URLcatExamItemsVR, produces = "text/plain")
	public @ResponseBody String CatExamItemsVr(HttpServletRequest request, HttpServletResponse response, CatalogoExamVrMainForm form) throws ParseException{
		
		Long id = form.getItemId();
		String msj= "Registro Guardado";
		System.out.println("entra al controlador");
		 CatalogoItemsExamen item= itemCatService.findById(id);
		 Set<ItemsValoresReferencia> ItmsVr=item.getItemsValoresReferencias();
		 System.out.println("entra al controlador");
		
		 form.getItems().forEach(formItem->{
			 
			 System.out.println("entra al foreach");		
		 	
			 if(formItem.getId()== null){
		 			System.out.println("entra al if");
		 			ItemsValoresReferencia itemVr= new ItemsValoresReferencia();
		 			itemVr.setEdadMinima(formItem.getMinAge());
		 			itemVr.setEdadMaxima(formItem.getMaxAge());
		 			itemVr.setSexo(formItem.getSex());
		 			itemVr.setTipoRango(formItem.getTypeRango());
		 			System.out.println(formItem.getvRMax().toString());
		 			System.out.println(formItem.getvRMax());
		 			System.out.println(formItem.getvRMin());
		 			
		 			itemVr.setValorRefMaximo(Integer.parseInt(formItem.getvRMax()));
		 			
		 			
		 			if(formItem.getvRMin().equals("V.N.")){
		 				System.out.println("entreo Maximo");
		 				 itemVr.setValorRefMinimo(-1);
		 			}else{
		 				itemVr.setValorRefMinimo(Integer.parseInt(formItem.getvRMin()));
		 			}
		 			
		 			itemVr.setCatalogoItemsExamen(item);
		 			valoresService.save(itemVr);
		 		}
		 		
		 		if(formItem.getEstado().equals("delete")){
                 	ItemsValoresReferencia itemVrDel = valoresService.findById(formItem.getId());
	 				valoresService.delete(itemVrDel);
		 			
		 		}
		 		
		 		if(formItem.getEstado().equals("")){
		 		for(ItemsValoresReferencia exItem:ItmsVr){
		 		
                       if(formItem.getId()==exItem.getId()){
                    	   
		 				exItem.setEdadMaxima(formItem.getMaxAge());
		 				exItem.setEdadMinima(formItem.getMinAge());
		 				exItem.setSexo(formItem.getSex());
		 				exItem.setTipoRango(formItem.getTypeRango());
		 				
		 				if(formItem.getvRMax().equals("V.N.")){
		 					exItem.setValorRefMaximo(-1);
		 				}else{
		 				exItem.setValorRefMaximo(Integer.parseInt(formItem.getvRMax()));
		 				}
		 				
		 				if(formItem.getvRMin().equals("V.N.")){
		 					exItem.setValorRefMinimo(-1);
		 				}else{
		 				exItem.setValorRefMinimo(Integer.parseInt(formItem.getvRMin()));
		 				}
		 				exItem.setCatalogoItemsExamen(item);
		 				valoresService.saveOrUpdate(exItem);
		 				break;	
		 			}      
                       
		 		}
		 	  }
		 	});		
		
		return msj;
	}
}
	

