package org.serfeo.dev.security;

import org.serfeo.dev.common.Const;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {
    @Override
    public void doFilter( ServletRequest servletRequest,
                          ServletResponse servletResponse,
                          FilterChain filterChain ) throws IOException, ServletException {
        if ( servletRequest instanceof HttpServletRequest &&
             servletResponse instanceof HttpServletResponse ) {
            HttpSession session = ( ( HttpServletRequest ) servletRequest ).getSession( false );
            if ( session == null || session.getAttribute( Const.sessionCredentials ) == null )
                ( ( HttpServletResponse ) servletResponse ).sendError( HttpServletResponse.SC_FORBIDDEN );
            else
                filterChain.doFilter( servletRequest, servletResponse );
        }
    }

    public void init( FilterConfig filterConfig ) throws ServletException { /*noop*/ }
    public void destroy() { /*noop*/ }
}
