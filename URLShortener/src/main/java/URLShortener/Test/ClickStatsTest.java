package URLShortener.Test;

import URLShortener.Stats.ClickStats;
import junit.framework.TestCase;
import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Fabiola on 20/08/2015.
 */
public class ClickStatsTest extends TestCase {

    String cases = "2015/08/20";
    ClickStats prova = new ClickStats(cases);


    /**
     * @throws Exception
     */
    @Test
    public void testGetDate() throws Exception {
        String result = prova.getDate();
        assertTrue(result != null);
    }

    /**
     * @throws Exception
     */
    @Test
    public void testToJson() throws Exception {
        JSONObject result = prova.toJson();
        assertTrue(result != null);
    }
}
