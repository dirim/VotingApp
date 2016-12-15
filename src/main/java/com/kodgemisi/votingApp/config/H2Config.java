package com.kodgemisi.votingApp.config;

import org.h2.server.web.WebServlet;
import org.h2.tools.Server;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ozge on 21.08.2016.
 */
@Configuration
public class H2Config {

	@Bean
	public ServletRegistrationBean h2servletRegistration() {
		ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
		registration.addUrlMappings("/console/*");
		return registration;
	}

	//http://stackoverflow.com/questions/26220083/h2-database-console-spring-boot-load-denied-by-x-frame-options
//	@Bean
//	org.h2.tools.Server h2Server() {
//		Server server = new Server();
//		try {
//			server.runTool("-tcp");
//			server.runTool("-tcpAllowOthers");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return server;
//
//	}
}
