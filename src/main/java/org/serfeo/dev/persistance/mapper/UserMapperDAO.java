package org.serfeo.dev.persistance.mapper;

import com.google.inject.Inject;
import org.serfeo.dev.persistance.domain.User;

public class UserMapperDAO {
    private final UserMapper mapper;

    @Inject
    public UserMapperDAO(UserMapper mapper) { this.mapper = mapper; }

    public User findUserByLogin( String login ) { return mapper.findByLogin( login ); }

    public User checkAndInsert( User user ) {
        User existsUser = mapper.findByLogin( user.getLogin() );
        if ( existsUser == null ) {
            mapper.insertUser( user );
            return user;
        } else {
            return null;
        }
    }
}

