package URLShortener.Test;

import URLShortener.Utility.URLShortener;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by Fabiola on 18/08/2015.
 */
public class URLShortenerTest extends TestCase {

    String[] cases= {"http://www.google.com", "https://www.google.com", "www.google.com/"};
    String[] result= {"www.google.com", "www.google.com", "www.google.com"};
    String[] result1={};
    Boolean[] result2={true, true, true};

    String[] prova1= {"www.aa.it"};
    String[] aspetto={"http://fkt.in/ciao"};
    
    private String domain = "http://fkt.in";
    URLShortener prova;

    public URLShortenerTest() {
        prova = new URLShortener();
    }
    @Test
    public void testShortenURL() throws Exception {

    }

    @Test
    public void testValidateURL() throws Exception {
        for (int i = 0; i<cases.length; i++){
            Boolean d = prova.validateURL(cases[i]);
            assertTrue(d == result2[i]);
        }
    }

    @Test
    public void testSanitizeURL() throws Exception {
        for (int i = 0; i < cases.length; i++) {
            String c = prova.sanitizeURL(cases[i]);
            assertEquals(c, result[i]);
        }
    }
}

