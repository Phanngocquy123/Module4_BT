package com.example.jpa_2.config;

import com.example.jpa_2.filter.AuthenticationFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(SpringConfig.class);

        ServletRegistration.Dynamic dispatcher = servletContext
                .addServlet("SpringDispatcherServlet", new DispatcherServlet(appContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setForceEncoding(true);
        encodingFilter.setEncoding("UTF-8");
        FilterRegistration.Dynamic registrationEncoding = servletContext.addFilter("encodingFilter", encodingFilter);
        registrationEncoding.addMappingForUrlPatterns(null, true, "/*");

        FilterRegistration.Dynamic registrationAuth = servletContext.addFilter("authenticationFilter", new AuthenticationFilter());
        registrationAuth.addMappingForUrlPatterns(null, true, "/admin/*");
    }
}
