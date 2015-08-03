package URLShortener.SparkServer;

import static spark.Spark.*;

import URLShortener.Utility.URLShortener;

import URLShortener.util.*;
import static spark.Spark.get;

import org.json.JSONObject;

public class SparkServer {

    private static final String JSON = "responseData";
    private  static final String RESULT = "result";
    private  static final String OKAY = "okay";
    private static final String SHORTURL = "shortUrl";
    private  static final String LONGURL = "longUrl";

    
    public static JSONObject convertToShortUrl(String longUrl) {
        JSONObject data = new JSONObject();
        JSONObject response = new JSONObject();

        URLShortener u = new URLShortener(4, "www.sht.com/");
        String shortUrl = u.shortenURL(longUrl);

        data.put(RESULT, OKAY);
        data.put(SHORTURL, shortUrl);
        response.put(JSON, data);

        return response;
    }

    public static void main(final String[] args) {
        setPort(8080);
        externalStaticFileLocation("public"); // Static files

        get("/", (request, response) -> {
            response.redirect("/index.html");
            return "";
        });

        get("/convertToShortUrl", (request, response) -> {
            String longUrl = request.queryParams(LONGURL);
            // response.redirect("/");
            return convertToShortUrl(longUrl).toString();
        });

        before((request, response) -> {
            HibernateUtil.getSession().beginTransaction();
        });


        after((request, response) -> {
            HibernateUtil.getSession().getTransaction().commit();
            HibernateUtil.closeSession();
        });

        exception(Exception.class, (e, request, response) -> {
            HibernateUtil.getSession().getTransaction().rollback();
            HibernateUtil.closeSession();
            response.status(500);
        });

    }

}
