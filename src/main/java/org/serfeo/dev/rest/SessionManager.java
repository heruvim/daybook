package org.serfeo.dev.rest;

import org.serfeo.dev.common.Const;
import org.serfeo.dev.persistance.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionManager {
    public static long getUserId( HttpServletRequest request ) {
        HttpSession session = request.getSession( false );
        if ( session != null ) {
            Object credentials = session.getAttribute( Const.sessionCredentials );
            if (credentials != null && credentials instanceof User)
                return ( ( User ) credentials ).getId();
        }

        return -1;
    }
}
