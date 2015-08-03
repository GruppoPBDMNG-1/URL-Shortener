package URLShortener.Utility;

import URLShortener.Stats.ClickStats;

import URLShortener.DAO.RedisConnection;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ShortURLData {

    private static final String SHORTURL="shortUrl";
    private static final String LONGURL="longUrl";
    private static final String CLICKS ="clicks";
    private static final int FIRST_TEN = 10;
    private GregorianCalendar gc = new GregorianCalendar();
    private String shortUrl;
    private String longUrl;


    private static RedisConnection RedisConnDAO=RedisConnection.getIstance();

    private ArrayList<ClickStats> ClicksNumber;

    public JsonValues setJsonString(){
        JSONObject UrlAssociationJson=new JSONObject();
        JSONArray clickJsonArray=new JSONArray();
        UrlAssociationJson.put(LONGURL, longUrl);
        UrlAssociationJson.put(SHORTURL, shortUrl);

        for(ClickStats c: ClicksNumber){
            clickJsonArray.put(c.toJson());
        }

        UrlAssociationJson.put(CLICKS, clickJsonArray);
        return new JsonValues(UrlAssociationJson.toString());
    }


    public ShortURLData(String shortUrl,String longUrl){
        ClicksNumber = new ArrayList<ClickStats>();
        longUrl=longUrl.replace("http://", "");
        longUrl=longUrl.replace("https://", "");
        this.shortUrl=shortUrl;
        this.longUrl = "http://"+ longUrl;

    }

    public ShortURLData(String shortUrl, JsonValues json){
        ClicksNumber = new ArrayList<ClickStats>();
        JSONObject values = new JSONObject(json.getJsonString());
        this.shortUrl = shortUrl;
        this.longUrl = values.getString(LONGURL);
        this.shortUrl = values.getString(SHORTURL);
        JSONArray clickJArray = values.getJSONArray(CLICKS);

        for(int i = 0; i < clickJArray.length(); i++){
            ClicksNumber.add(new ClickStats(clickJArray.getJSONObject(i)));
        }

    }

    public void addNewClick(){


        String year =  (gc.get(Calendar.YEAR))+"";
        String  month = (gc.get(Calendar.MONTH) + 1) +"";
        int date = gc.get(Calendar.DATE);
        String day;
        if(date< FIRST_TEN){
            day="0"+ date;
        }else{
            day=""+ date;
        }
        String  today= day+" " + month+ " " + year;

        try {
            ClicksNumber.add(new ClickStats(today));
        }catch(Exception e){
            System.out.println(e.getMessage()+e.getCause());
        }
        RedisConnDAO.update(this);
    }

    public static boolean saveShortLongURL (String shortUrl,String longUrl){
        ShortURLData sud=new ShortURLData(shortUrl,longUrl);
        return RedisConnDAO.setKeyData(sud);
    }

    public static ShortURLData getURLData(String shortUrl) {
        return RedisConnDAO.getKeyData(shortUrl);
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public String getLongUrl(){
        return longUrl;
    }

}
