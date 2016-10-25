package com.tesis.clinicapp.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SecurityController {
	
	private static final String URL = "/login.htm";

	@RequestMapping(method = RequestMethod.GET, value = URL)
	public String get(HttpServletRequest request){
		request.setAttribute("title", "Logueo");
		return "login";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/denied.htm")
	public String getDenied(HttpServletRequest request){
		request.setAttribute("title", "Acceso Denegado");
		return "denied";
	}
	
}
