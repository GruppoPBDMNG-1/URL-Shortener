package URLShortener.Test;

import URLShortener.SparkServer.SparkServer;
import junit.framework.TestCase;
import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Fabiola on 20/08/2015.
 */
public class SparkServerTest extends TestCase {

    String cases= "http://www.google.com";
    String cases1 = "http://fkt.in/ciao";
    SparkServer prova = new SparkServer();


    @Test
    public void testConvertToShortUrl() throws Exception {
        JSONObject result = prova.convertToShortUrl(cases);
        assertTrue(result != null);
    }

    @Test
    public void testSaveShort() throws Exception {
        JSONObject result = prova.saveShort(cases1, cases);
        assertTrue(result != null);
    }

    @Test
    public void testViewWindow() throws Exception {
        JSONObject result = prova.viewWindow(cases1);
        assertTrue(result != null);
    }

    @Test
    public void testGetGraph() throws Exception {
        JSONObject result = prova.getGraph(cases1);
        assertTrue(result != null);
    }

    @Test
    public void testGetGraphPage() throws Exception {

    }
}
