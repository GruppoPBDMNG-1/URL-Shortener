package URLShortener.Stats;

import org.json.JSONObject;

/**
 * Created by raffaele on 03/08/2015.
 */

public class ClickStats {

    public static final String DATE="Date";
    private String date;

    public String getDate(){
        return date;
    }

    public JSONObject toJson(){
        JSONObject jdate=new JSONObject();
        jdate.put(DATE, getDate());
        return jdate;
    }
}
