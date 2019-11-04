
package filters;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Role;
import models.User;
import services.UserService;

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
        
        try {
            // code that is executed before the servlet
            
            HttpServletRequest hsr = (HttpServletRequest)request;
            HttpSession session = hsr.getSession();
            
            UserService us = new UserService();
            
            User user = us.get((String) session.getAttribute("email"));
            Role role = user.getRole();
            
            if(!role.getRoleName().equals("admin"))
            {
                HttpServletResponse hsr2 = (HttpServletResponse) response;
                hsr2.sendRedirect("home");
                return;
                
            }
            // allow the user to access the servlet
            chain.doFilter(request, response);
            
            // code that is executed after the servlet
        } catch (Exception ex) {
            //Logger.getLogger(AdminFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }

    @Override
    public void destroy() {        
    }

    @Override
    public void init(FilterConfig filterConfig) {        
       
    }

}
