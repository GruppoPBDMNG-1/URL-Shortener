package URLShortener.Test;

import URLShortener.Utility.JsonValues;
import URLShortener.Utility.ShortURLData;
import URLShortener.Utility.URLShortener;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Vincenzo_Pc on 20/08/2015.
 */
public class ShortURLDataTest {

    int i=0;
    int j=0;
    String[] cases = {"broJG","SQhAJ","GHoHp","q8xTB","vlynY","IRosu","NuUr8","cxmDH"};
    String[] cases1 = {"www.google.it","www.amazon.com","www.basididatiavanzati.de","https://www.focus.it","www.ciaosic.sl","www.happycasa.org","www.ultimoesameesto.ciao","www.tempesta.civediamo"};

<<<<<<< HEAD

=======
<<<<<<< HEAD
    String cases = "broJG";
    String cases1 = "www.google.it";
    ShortURLData test = new ShortURLData(cases,cases1);


=======
>>>>>>> origin/master
    ShortURLData test = new ShortURLData(cases[i],cases1[j]);
>>>>>>> origin/master
    @Test
    public void testSetJsonString() throws Exception {
        JsonValues result = test.setJsonString();
        assertTrue(result != null);

    }

    @Test
    public void testAddNewClick() throws Exception {

    }

    @Test
    public void testSaveShortLongURL() throws Exception {
        for(i=0;i<cases.length;i++){
            for(j=0;j<cases1.length;j++){

                boolean result = test.saveShortLongURL(cases[i], cases1[j]);
                assertTrue(result==false);
            }
        }

    }


}
