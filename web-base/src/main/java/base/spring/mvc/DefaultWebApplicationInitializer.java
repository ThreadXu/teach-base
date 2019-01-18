package base.spring.mvc;

import javax.servlet.FilterRegistration;
import javax.servlet.Registration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class DefaultWebApplicationInitializer implements org.springframework.web.WebApplicationInitializer {

    public void onStartup(ServletContext servletContext) throws ServletException {
        // 添加一个自定义Filter
        Registration.Dynamic dynamic = servletContext.addFilter("SimpleFilter", SimpleFilter.class);
        ((FilterRegistration.Dynamic) dynamic).addMappingForUrlPatterns(null, false, "/service/");
    }
}
