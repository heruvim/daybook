package org.serfeo.dev.persistance.domain;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties( ignoreUnknown = true )
public class User {
    public User() {};

    private long id;
    private String login;
    private String nickname;
    private byte[] salt;
    private byte[] verifier;

    public long getId() { return id; }
    public void setId( long id ) { this.id = id; }

    public String getLogin() { return login; }
    public void setLogin( String login ) { this.login = login; }

    public String getNickname() { return nickname; }
    public void setNickname( String nickname ) { this.nickname = nickname; }

    public byte[] getSalt() { return salt; }
    public void setSalt( byte[] salt ) { this.salt = salt; }

    public byte[] getVerifier() { return verifier; }
    public void setVerifier( byte[] verifier ) { this.verifier = verifier; }
}
