package base.spring.mvc;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletRegistration.Dynamic;

/**
 * Dispatcher servlet 配置, 等价于servlet 3之前的web.xml配置
 */
public class DispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    /***
     * spring mvc 基础配置
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    /**
     * dispatcher servlet mapping path
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     * 自定义配置
     *
     * @param registration
     */
    @Override
    protected void customizeRegistration(Dynamic registration) {
        //registration.setLoadOnStartup(1);
    }
}
