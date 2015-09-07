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

    int i=0;
    int j=0;
    String[] casoPrimo = {"www.sht.com/kWpcv","www.sht.com/4nlzR","www.sht.com/WIPmq","www.sht.com/5fDj0","www.sht.com/1RnpB"};
    String[] casoSecondo = {"www.caccapupu.com","www.gooogle.it!","www.amazon.sl","www.uniba.com","www.shazam.sc"};

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
    @Test
    // per funzionare questo test ovviamente i casi da testare devono essere inclusi all'interno del database redis
    //se non lo sono prima generate uno short url dal long
    // dopo di che testate che il metodo sia corretto

    public void testSaveShortLongURL() throws Exception {
        for(i=0;i<casoPrimo.length;i++){
            for(j=0;j<casoSecondo.length;j++){

                boolean result = ShortURLData.saveShortLongURL(casoPrimo[i], casoSecondo[j]);
                assertTrue(result==false);
            }
        }

    }


}
