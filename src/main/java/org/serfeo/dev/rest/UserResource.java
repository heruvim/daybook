package org.serfeo.dev.rest;

import com.google.inject.Inject;
import org.serfeo.dev.common.Const;
import org.serfeo.dev.persistance.domain.User;
import org.serfeo.dev.persistance.mapper.UserMapperDAO;
import org.serfeo.dev.rest.beans.UserCredentials;
import org.serfeo.dev.rest.response.CommonResponse;
import org.serfeo.dev.security.srp6.SRPUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;

@Path( "/user" )
public class UserResource {
    private final UserMapperDAO userMapperDAO;
    private final SRPUtils srpUtils;

    @Inject
    public UserResource( UserMapperDAO userMapperDAO,
                         SRPUtils srpUtils ) {
        this.userMapperDAO = userMapperDAO;
        this.srpUtils = srpUtils;
    }

    @Path( "/auth" )
    @POST
    @Consumes( MediaType.APPLICATION_JSON )
    @Produces( MediaType.APPLICATION_JSON )
    public CommonResponse auth( UserCredentials credentials,
                                @Context HttpServletRequest request ) {
        User user = userMapperDAO.findUserByLogin( credentials.getLogin() );
        if ( user == null )
            return CommonResponse.error( new CommonResponse.Param[]{ new CommonResponse.Param( "message", "User does not exists" ) } );
        else {
            if ( Arrays.equals( user.getVerifier(), srpUtils.getDigestOfJoinArrays( user.getSalt(), credentials.getVerifier() ) ) ) {
                HttpSession session = request.getSession( false );
                if ( session != null )
                    session.invalidate();

                session = request.getSession();
                session.setAttribute( Const.sessionCredentials, user );
                return CommonResponse.ok( new CommonResponse.Param[]{ new CommonResponse.Param( "nickname", user.getNickname() ) } );
            } else {
                return CommonResponse.error( new CommonResponse.Param[]{ new CommonResponse.Param( "message", "Wrong login or password" ) } );
            }
        }
    }

    @Path( "/register" )
    @POST
    @Consumes( MediaType.APPLICATION_JSON )
    @Produces( MediaType.APPLICATION_JSON )
    public CommonResponse register( User user ) {
        user.setSalt( srpUtils.generateSalt() );
        user.setVerifier( srpUtils.getDigestOfJoinArrays( user.getSalt(), user.getVerifier() ) );

        User createdUser = userMapperDAO.checkAndInsert( user );
        if ( createdUser == null )
            return CommonResponse.error( new CommonResponse.Param[]{ new CommonResponse.Param( "message", "User with this login already exists!" ) } );
        else
            return CommonResponse.ok();
    }
}
