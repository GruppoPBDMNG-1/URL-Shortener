package URLShortener.SparkServer;

//test  03.08
import static spark.Spark.*;

import URLShortener.Utility.GenerateShortUrl;
import URLShortener.util.*;
import static spark.Spark.get;

import org.json.JSONObject;

public class SparkServer {


    private  static final String RESULT = "result";
    private  static final String OKAY = "okay";
    private static final String SHORTURL = "shortUrl";
    private  static final String LONGURL = "longUrl";
    private static final String DATA = "responseData";

    private  static final String EMPTY_LONGURL = "Empty long Url";



    public static JSONObject convertToShortUrl(String longUrl) {
        JSONObject data = new JSONObject();


        if (longUrl.equalsIgnoreCase("undefined")
            || longUrl.equalsIgnoreCase("")) {
            data.put(RESULT, EMPTY_LONGURL);
        } else {
            String shortUrl = GenerateShortUrl
                .generateShortUrl(longUrl);
          {
                data.put(RESULT, OKAY);
                data.put(SHORTURL, shortUrl);
            }

        }
            JSONObject dataReturn = new JSONObject();
            dataReturn.put(DATA, data);

            return dataReturn;

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
