package URLShortener.Test;

import URLShortener.DAO.RedisConnection;
import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Vincenzo_Pc on 18/08/2015.
 */
public class RedisConnectionTest extends TestCase{

    String [] a  ={"www.google.it","www.amazon.com","www.vincenzoMOlfettaInf.it"};
    boolean [] b  ={false,true,true};


   @Test
    public void testIsExists() throws Exception {

       for (int i = 0; i < a.length; i++) {
           boolean c = RedisConnection.getIstance().isExists(a[i]);

           assertTrue(c == b[i]);
       }

    }
}
