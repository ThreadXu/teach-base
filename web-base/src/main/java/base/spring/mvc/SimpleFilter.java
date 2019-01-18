package base.spring.mvc;

import javax.servlet.*;
import java.io.IOException;
import java.util.logging.Logger;

public class SimpleFilter implements Filter {
    private Logger logger = Logger.getLogger(SimpleFilter.class.getName());

    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("simple filter starting");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("simple filter say hello world");
    }

    public void destroy() {
        logger.info("simple filter destroy");
    }
}
