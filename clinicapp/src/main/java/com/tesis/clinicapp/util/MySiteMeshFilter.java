package com.tesis.clinicapp.util;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.sitemesh.content.tagrules.html.DivExtractingTagRuleBundle;

public class MySiteMeshFilter extends ConfigurableSiteMeshFilter {
	
	@Override
	protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) { 
		builder.addTagRuleBundles(new DivExtractingTagRuleBundle())
		.addDecoratorPaths("/index.htm", "/WEB-INF/decorators/baseforIndexOnly.jsp","/WEB-INF/decorators/base_layout.jsp")
		.addDecoratorPaths("/maintenance/*", "/WEB-INF/decorators/general_layout.jsp","/WEB-INF/decorators/base_layout.jsp")
		.addDecoratorPaths("/admin/*", "/WEB-INF/decorators/configure_layout.jsp","/WEB-INF/decorators/base_layout.jsp")
		.addExcludedPath("*-ajax.htm")
        .addExcludedPath("/login.htm");
	}

}
