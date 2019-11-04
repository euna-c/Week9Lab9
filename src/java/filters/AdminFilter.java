
package filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 784564
 */
public class AdminFilter implements Filter {
    
   
  
    public AdminFilter() {
    }    
    
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
      // code that is executed before the servlet
        
        HttpServletRequest hsr = (HttpServletRequest)request;
        HttpSession session = hsr.getSession();
        
        if (session.getAttribute("email") == null) {
            HttpServletResponse hsre = (HttpServletResponse)response;
            hsre.sendRedirect("login");
            return;
        } 
        
         // allow the user to access the servlet
         chain.doFilter(request, response);
         
         // code that is executed after the servlet
      
    }

    @Override
    public void destroy() {        
    }

    @Override
    public void init(FilterConfig filterConfig) {        
       
    }

}
