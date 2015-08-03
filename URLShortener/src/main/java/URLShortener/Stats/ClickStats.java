package URLShortener.Stats;

import org.json.JSONObject;

public class ClickStats {

    public static final String DATE="Date";
    private String date;

    public ClickStats(String date){
        this.date=date;
    }

    public String getDate(){
        return date;
    }

    public ClickStats(JSONObject json){
        this.date=json.getString(DATE);
    }

    public JSONObject toJson(){
        JSONObject jdate=new JSONObject();
        jdate.put(DATE, getDate());
        return jdate;
    }
}
