package org.serfeo.dev.beans;

public class User
{
    public User() {};

    private long id;
    private String login;
    private String password;
    private String nickname;

    public long getId() { return id; }
    public void setId( long id ) { this.id = id; }

    public String getLogin() { return login; }
    public void setLogin( String login ) { this.login = login; }

    public String getPassword() { return password; }
    public void setPassword( String password ) { this.password = password; }

    public String getNickname() { return nickname; }
    public void setNickname( String nickname ) { this.nickname = nickname; }
}
