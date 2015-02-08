package org.serfeo.dev.security.srp6;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class SRPUtils {
    private MessageDigest digest;
    private Random rnd;
    private Logger log = LoggerFactory.getLogger( SRPUtils.class );

    @Inject
    public SRPUtils() {
        try { this.digest = MessageDigest.getInstance( "SHA-512" ); }
        catch( NoSuchAlgorithmException e ) { log.error( "SHA-512 algorithm: " + e.getMessage() ); }
        rnd = new Random( System.currentTimeMillis() );
    }

    public byte[] generateSalt() {
        byte[] salt = new byte[ 64 ];
        rnd.nextBytes( salt );
        return salt;
    }

    public byte[] getDigestOfJoinArrays( byte[] a, byte[] b ) {
        byte[] joinArray = new byte[ a.length + b.length ];
        System.arraycopy( a, 0, joinArray, 0, a.length );
        System.arraycopy( b, 0, joinArray, a.length, b.length );
        return digest.digest( joinArray );
    }
}
