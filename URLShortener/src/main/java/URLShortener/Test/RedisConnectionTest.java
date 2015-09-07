package URLShortener.Test;

import URLShortener.DAO.RedisConnection;
import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Vincenzo_Pc on 18/08/2015.
 */
public class RedisConnectionTest extends TestCase{

    String [] casi  ={"www.amazon.sl"};
    boolean [] aspetto  ={true};


    /**
     * @throws Exception
     */
   @Test
    public void testIsExists() throws Exception {

       for (int i = 0; i < casi.length; i++) {
           boolean c = RedisConnection.getIstance().isExists(casi[i]);

           assertTrue(c == aspetto[i]);
       }

    }
}
