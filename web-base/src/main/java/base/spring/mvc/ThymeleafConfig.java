package base.spring.mvc;

import base.spring.consts.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@Configuration
public class ThymeleafConfig {

    @Autowired
    private Environment env;

    @Bean
    public ServletContextTemplateResolver templateResolver() {

        WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(context.getServletContext());

        templateResolver.setPrefix("classpath:/template/");
        templateResolver.setSuffix(".html");
        templateResolver.setCacheable(ApplicationProperties.Thymeleaf_Cache.getDefaultValue()
                .equals(env.getProperty(ApplicationProperties.Thymeleaf_Cache.getKey())));
        templateResolver.setTemplateMode(TemplateMode.HTML);

        return templateResolver;
    }

    @Bean
    public TemplateEngine templateEngine(ServletContextTemplateResolver templateResolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine;
    }

    @Bean
    public ViewResolver viewResolver(TemplateEngine templateEngine) {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine);
        viewResolver.setCharacterEncoding("UTF8");
        viewResolver.setOrder(1);
        viewResolver.setViewNames(new String[]{"*.html", "*.xhtml"});
        return viewResolver;
    }
}
