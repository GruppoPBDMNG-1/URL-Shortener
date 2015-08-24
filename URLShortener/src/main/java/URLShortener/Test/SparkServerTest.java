package URLShortener.Test;

import URLShortener.SparkServer.SparkServer;
import junit.framework.TestCase;
import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class SparkServerTest extends TestCase {

    String cases= "http://www.google.com";
    String cases1 = "http://fkt.in/ciao";
    SparkServer prova = new SparkServer();


    /**
     * @throws Exception
     */
    @Test
    public void testConvertToShortUrl() throws Exception {
        JSONObject result = prova.convertToShortUrl(cases);
        assertTrue(result != null);
    }

    /**
     * @throws Exception
     */
    @Test
    public void testSaveShort() throws Exception {
        JSONObject result = prova.saveShort(cases1, cases);
        assertTrue(result != null);
    }

    /**
     * @throws Exception
     */
    @Test
    public void testViewWindow() throws Exception {
        JSONObject result = prova.viewWindow(cases1);
        assertTrue(result != null);
    }

    /**
     * @throws Exception
     */
    /*@Test
    public void testGetGraph() throws Exception {
        JSONObject result = prova.getGraph(cases1);
        assertTrue(result != null);
    }*/

    @Test
    public void testGetGraphPage() throws Exception {

    }
}
