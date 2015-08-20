package URLShortener.DAO;


import URLShortener.Utility.JsonValues;
import URLShortener.Utility.ShortURLData;
import redis.clients.jedis.Jedis;


/**
 *
 */
public class RedisConnection {

    private Jedis redis = new Jedis("127.0.0.1",6379);

    private static final RedisConnection REDIS = new RedisConnection();

    /**
     * istanza di classe
     * @return
     */
    public static RedisConnection getIstance(){
        return REDIS;
    }

    public boolean setKeyData (ShortURLData url){
        if(!isExists(url.getShortUrl())){
            return false;
        }
        REDIS.setValue(url.getShortUrl(), url.setJsonString());
        return true;
    }

    /**
     * @param shortUrl
     * @param json
     */
    public void setValue(String shortUrl, JsonValues json){
        redis.set(shortUrl, json.getJsonString());
    }

    /**
     * @param shortUrl
     * @return
     */
    public boolean isExists(String shortUrl){
        return (REDIS.getValue(shortUrl)==null);
    }

    /**
     * @param shortUrl
     * @return
     */
    public JsonValues getValue(String shortUrl){
        redis.connect();
        String response=redis.get(shortUrl);
        if(response == null){
            return null;
        }
        JsonValues json = new JsonValues(response);
        redis.close();
        return json;
    }

    /**
     * @param url
     */
    public void update(ShortURLData url){

        REDIS.setValue(url.getShortUrl(), url.setJsonString());

    }

    /**
     * @param shortUrl
     * @return
     */
    public ShortURLData getKeyData (String shortUrl){
        JsonValues json = REDIS.getValue(shortUrl);
        if(json == null){
            return null;
        }
        return new ShortURLData(shortUrl, json);
    }
}
