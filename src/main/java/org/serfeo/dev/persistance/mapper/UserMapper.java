package org.serfeo.dev.persistance.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.serfeo.dev.persistance.domain.User;

public interface UserMapper {
    @Select( "select * from users where login=#{login}" )
    public User findByLogin( @Param( "login" ) String login );
    public void insertUser( User user );
}
