package URLShortener.Utility;

import URLShortener.Stats.ClickStats;

import URLShortener.DAO.RedisConnection;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Created by raffaele on 03/08/2015.
 */
public class ShortURLData {


    private static final String SHORTURL="shortUrl";
    private static final String LONGURL="longUrl";
    private static final String CLICKS ="clicks";
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

    public static boolean saveShortLongURL (String shortUrl,String longUrl){
        ShortURLData sud=new ShortURLData(shortUrl,longUrl);
        return RedisConnDAO.setKeyData(sud);
    }

    public String getShortUrl() {
        return shortUrl;
    }

}
