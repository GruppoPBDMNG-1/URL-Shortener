package URLShortener.Test;

import URLShortener.Utility.JsonValues;
import URLShortener.Utility.ShortURLData;
import URLShortener.Utility.URLShortener;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Vincenzo_Pc on 20/08/2015.
 */
public class ShortURLDataTest extends TestCase {


    String[] casoPrimo = {"broJG","SQhAJ","GHoHp","q8xTB","vlynY","IRosu","NuUr8","cxmDH"};
    String[] casoSecondo = {"www.google.it","www.amazon.com","www.basididatiavanzati.de","https://www.focus.it","www.ciaosic.sl","www.happycasa.org","www.ultimoesameesto.ciao","www.tempesta.civediamo"};



    /**
     * @throws Exception
     */
    @Test
    public void testSetJsonString() throws Exception {
        for(int i=0; i<casoPrimo.length;i++){
            ShortURLData test = new ShortURLData(casoPrimo[i],casoSecondo[i]);
            JsonValues result = test.setJsonString();
            Assert.assertTrue(result != null);
        }

    }

    /**
     * @throws Exception
     */
    @Test
    public void testAddNewClick() throws Exception {
        for (int i=0; i<casoPrimo.length;i++) {
            ShortURLData test = new ShortURLData(casoPrimo[i],casoSecondo[i]);
            JsonValues result1 = test.setJsonString();
            Assert.assertTrue(result1 != null);
        }

    }

    /**
     * @throws Exception
     */
    /*@Test
    public void testSaveShortLongURL() throws Exception {
        for(i=0;i<casoPrimo.length;i++){
            for(j=0;j<casoSecondo.length;j++){

                boolean result = test.saveShortLongURL(casoPrimo[i], casoSecondo[j]);
                assertTrue(result==false);
            }
        }

    }*/


}
