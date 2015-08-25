package URLShortener.Test;

import URLShortener.SparkServer.SparkServer;
import junit.framework.TestCase;
import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Vincenzo_Pc on 20/08/2015.
 */
public class SparkServerTest extends TestCase {

    String cases= "http://www.google.com";
    String cases1 = "www.sht.com/DxSoc";
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
    @Test
    public void testGetGraphPage() throws Exception {
        JSONObject result = prova.getGraphPage(cases1);
        assertTrue(result != null);

    }
}
