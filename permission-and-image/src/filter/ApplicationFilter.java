package filter;

import com.googlecode.objectify.ObjectifyService;
import entity.Product;

import javax.servlet.*;
import java.io.IOException;

public class ApplicationFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        ObjectifyService.register(Product.class);
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
