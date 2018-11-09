/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

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
import managedBean.AuthenticationManagedBean;
import managedBean.TrippyManagedBean;

/**
 *
 * @author fengl
 */
public class AuthenticationFilter implements Filter {

    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public AuthenticationFilter() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request1 = (HttpServletRequest) request;
        AuthenticationManagedBean bean;
        TrippyManagedBean trippyManagedBean;
        HttpSession session = request1.getSession(false);
        if (session == null) {
            bean = null;
            trippyManagedBean = null;
        } else {
            bean = (AuthenticationManagedBean) session.getAttribute("authenticationManagedBean");
            trippyManagedBean = (TrippyManagedBean) session.getAttribute("trippyManagedBean");
        }

        if (bean == null || bean.getId() == -1L) {
                ((HttpServletResponse) response).sendRedirect(request1.getContextPath() + "/login.xhtml");
        } else {
                chain.doFilter(request1, response);
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void destroy() {

    }

}
