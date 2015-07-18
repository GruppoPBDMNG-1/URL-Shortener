package URLShortener;

import static spark.Spark.*;

import URLShortener.Utility.GenerateShortUrl;
import URLShortener.util.*;
import static spark.Spark.get;

import org.json.JSONObject;

public class App {


    private  static final String ERROR = "error";
    private  static final String OKAY = "okay";
    private static final String SHORTURL = "shortUrl";
    private  static final String LONGURL = "longUrl";
    private static final String DATA = "responseData";

    private  static final String EMPTY_LONGURL = "Empty long Url";



    public static JSONObject convertShortUrl(String longUrl) {
        JSONObject data = new JSONObject();


        if (longUrl.equalsIgnoreCase("undefined")
            || longUrl.equalsIgnoreCase("")) {
            data.put(ERROR, EMPTY_LONGURL);
        } else {
            String shortUrl = GenerateShortUrl
                .generateShortUrl(longUrl);
          //  boolean isCreated = UrlAssociation.createNewAssociation(shortUrl,
          //      longUrl);
          //  if (isCreated) {
                data.put(ERROR, OKAY);
                data.put(SHORTURL, shortUrl);
           // }

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

        get("/convertShortUrl", (request, response) -> {
            String longUrl = request.queryParams(LONGURL);
            // response.redirect("/");
            return convertShortUrl(longUrl).toString();
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
