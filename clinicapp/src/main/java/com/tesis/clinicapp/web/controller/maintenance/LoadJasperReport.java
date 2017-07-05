package com.tesis.clinicapp.web.controller.maintenance;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.internal.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.springsource.tcserver.serviceability.request.DataSource;
import com.tesis.clinicapp.service.ItemsExamenService;
import com.tesis.clinicapp.service.PacienteService;
import com.tesis.clinicapp.web.form.maintenance.JasperInputForm;
import com.tesis.clinicapp.web.form.maintenance.citasMainForm;
import com.tesis.clinicapp.web.form.maintenance.reportForm;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleHtmlReportConfiguration;

@Controller
public class LoadJasperReport {
	
	JasperReport jasperReport;
    JasperPrint jasperPrint;
    
	private static final String URL = "/maintenance/reportes.htm";
	private static final String URL2 = "/maintenance/rportes.htm";
	private static final String URLreport = "/maintenance/reportes.txt";
	
	
	private static final String JSP = "/maintenance/reportes";
	private static final String FORM = "reportForm";
	
	private ItemsExamenService itemService;
	//@Autowired
	//SessionFactory sessionFactory;
	
//	SessionImplementor miSessionImplementor = (SessionImplementor) sessionFactory;
//	Connection conn2 = miSessionImplementor.connection();
	
	@RequestMapping(method = RequestMethod.GET, value = URL)
    public ModelAndView get(HttpServletRequest request){
		request.setAttribute("title", "Programacion de Citas");
		return new ModelAndView(JSP,FORM,new reportForm());
	}
	
	  @ModelAttribute("jasperRptFormats")
	    public ArrayList getJasperRptFormats()
	    {
	        ArrayList < String> jasperRptFormats = new ArrayList<String>();
	        jasperRptFormats.add("Html");
	        jasperRptFormats.add("PDF");
	        return jasperRptFormats;
	    }   
	  
	  //@RequestMapping(value = "/maintenance/reporteHtml.htm'", method = RequestMethod.POST)
	  @RequestMapping(method = RequestMethod.GET, value = URL2)
	  public ModelAndView generateReport(HttpServletRequest request,HttpServletResponse response,reportForm form,ModelAndView modelAndView) throws ParseException {
		  System.out.println("entro");
	      Connection conn = null;
	      try {
	          try {
	   
	               Class.forName("org.postgresql.Driver");
	              } catch (ClassNotFoundException e) {
	                  e.printStackTrace();
	              }  
	   
	           conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/clinica","postgres","postgres");
	           if (conn != null)
	       {
	           System.out.println("Database Connected");
	       }
	       else
	       {
	           System.out.println(" connection Failed ");
	       }
	   
	            String rptFormat = form.getRptFmt();
	            String noy = "3";
	   
	            System.out.println("rpt format " + rptFormat);
	            System.out.println("no of years " + noy);
	   
	             //Parameters as Map to be passed to Jasper
	             HashMap<String,Object> dataSource=new HashMap<String,Object>();
	   
	           //  dataSource.put("id", new Integer(noy));
	             dataSource.put("dataSource", conn);
	             
	   	   
	              //JasperReport jasperReport = getCompiledFile(reportFileName, request);
	        //      jasperReport = JasperCompileManager.compileReport("/resources/reports/reportFuncionando.jrxml");
	   
	          if (rptFormat.equalsIgnoreCase("html") ) {
	   
	        	  modelAndView = new ModelAndView("htmlReport", dataSource);
	            //  JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hmParams, conn);
	           //   generateReportHtml(jasperPrint, request, response); // For HTML report
	   
	          }
	   
	          else if  (rptFormat.equalsIgnoreCase("pdf") )  {
	   
	        	  modelAndView = new ModelAndView("pdfReport", dataSource);
	         //     generateReportPDF(response, hmParams, jasperReport, conn); // For PDF report
	   
	              }
	   
	         } catch (Exception sqlExp) {
	   
	             System.out.println( "Exception::" + sqlExp.toString());
	   
	         } finally {
	   
	              try {
	   
	              if (conn != null) {
	                  conn.close();
	                  conn = null;
	              }
	   
	              } catch (SQLException expSQL) {
	   
	                  System.out.println("SQLExp::CLOSING::" + expSQL.toString());
	   
	              }
	   
	       } 
	      
	      return  modelAndView;
	  }
	  
	/*  private void generateReportHtml( JasperPrint jasperPrint, HttpServletRequest req, HttpServletResponse resp) throws IOException, JRException {
	         HtmlExporter exporter=new HtmlExporter();
	         List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
	         jasperPrintList.add(jasperPrint);
	         exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrintList));
	         exporter.setExporterOutput( new SimpleHtmlExporterOutput(resp.getWriter()));
	         SimpleHtmlReportConfiguration configuration =new SimpleHtmlReportConfiguration();
	         exporter.setConfiguration(configuration);
	          exporter.exportReport();
	 
	    }
	 
	    private void generateReportPDF (HttpServletResponse resp, Map parameters, JasperReport jasperReport, Connection conn)throws JRException, NamingException, SQLException, IOException {
	        byte[] bytes = null;
	        bytes = JasperRunManager.runReportToPdf(jasperReport,parameters,conn);
	        resp.reset();
	        resp.resetBuffer();
	        resp.setContentType("application/pdf");
	        resp.setContentLength(bytes.length);
	        ServletOutputStream ouputStream = resp.getOutputStream();
	        ouputStream.write(bytes, 0, bytes.length);
	        ouputStream.flush();
	        ouputStream.close();
	    } */
}
