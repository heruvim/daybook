package org.serfeo.dev.rest.beans;

public class UserCredentials {
    private String login;
    private byte[] verifier;

    public UserCredentials() {}

    public String getLogin() { return login; }
    public void setLogin( String login ) { this.login = login; }

    public byte[] getVerifier() { return verifier; }
    public void setVerifier( byte[] verifier ) { this.verifier = verifier; }
}
