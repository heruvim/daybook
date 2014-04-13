import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class PrivateKeyCache
{
    private int MAX_CACHE_SIZE            = 1000;
    private Random randomGenerator        = new Random( System.currentTimeMillis() );

    private Map< Long, BigInteger > cache = new LinkedHashMap< Long, BigInteger >()
    {
        @Override
        protected boolean removeEldestEntry( Map.Entry< Long, BigInteger > eldest ){
            return size() > MAX_CACHE_SIZE;
        }
    };

    private PrivateKeyCache(){};

    public static class Holder {
        public static final PrivateKeyCache instance = new PrivateKeyCache();
    }

    public static PrivateKeyCache getInstance() {
        return Holder.instance;
    }

    synchronized public Long putPrivateKey( BigInteger privateKey )
    {
        Long cacheKey = getRandomCacheKey();
        cache.put( cacheKey, privateKey );

        return cacheKey;
    }

    synchronized public BigInteger getPrivateKey( Long cacheKey )
    {
        BigInteger privateKey = cache.get( cacheKey );
        cache.remove( cacheKey );

        return privateKey;
    }

    private Long getRandomCacheKey()
    {
        Long randomCacheKey = randomGenerator.nextLong();
        while ( cache.containsKey( randomCacheKey ) )
            randomCacheKey = randomGenerator.nextLong();

        return randomCacheKey;
    }
}
