package URLShortener.Test;

import URLShortener.Utility.ShortURLData;
import URLShortener.Utility.URLShortener;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Vincenzo_Pc on 20/08/2015.
 */
public class ShortURLDataTest {


    String cases = "broJG";
    String cases1 = "www.google.it";
    ShortURLData test = new ShortURLData(cases,cases1);
    @Test
    public void testSetJsonString() throws Exception {


    }

    @Test
    public void testAddNewClick() throws Exception {

    }

    @Test
    public void testSaveShortLongURL() throws Exception {
        boolean result = test.saveShortLongURL(cases, cases1);
        assertTrue(result==false);
    }


}
