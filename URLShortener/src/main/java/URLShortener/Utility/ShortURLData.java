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
    private static final String DATE = "data";
    private static final String NUMBER_OF_CLICK = "numeroclick";
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

    public JSONArray getGraph(){
        return createGraph();
    }

    public JSONArray createGraph(){
        JSONArray graphJsonArray = new JSONArray();
        int data = 0;
        int countClickInDay = 0;
        int dayMaximum= gc.getActualMaximum(Calendar.DAY_OF_MONTH);


        int click[]=new int[dayMaximum+1];

        for(int j=0; j<ClicksNumber.size();j++){
            countClickInDay=1;
            ClickStats c=ClicksNumber.get(j);

            data = Integer.parseInt(c.getDate().substring(0, 2));

            for(int i=j+1; i< ClicksNumber.size();i++){
                ClickStats c1=ClicksNumber.get(i);
                if(data==Integer.parseInt(c1.getDate().substring(0,2))){
                    countClickInDay++;
                }

            }

            click[data]= countClickInDay;
            j=j+countClickInDay-1;
        }

        JSONObject click1=new JSONObject();
        click1.put(DATE,1 );
        click1.put(NUMBER_OF_CLICK,click[1] );
        graphJsonArray.put(click1);

        JSONObject click2=new JSONObject();
        click2.put(DATE,2 );
        click2.put(NUMBER_OF_CLICK,click[2] );
        graphJsonArray.put(click2);


        JSONObject click3=new JSONObject();
        click3.put(DATE,3 );
        click3.put(NUMBER_OF_CLICK,click[3] );
        graphJsonArray.put(click3);

        JSONObject click4=new JSONObject();
        click4.put(DATE,4 );
        click4.put(NUMBER_OF_CLICK,click[4] );
        graphJsonArray.put(click4);

        JSONObject click5=new JSONObject();
        click5.put(DATE,5 );
        click5.put(NUMBER_OF_CLICK,click[5] );
        graphJsonArray.put(click5);

        JSONObject click6=new JSONObject();
        click6.put(DATE,6 );
        click6.put(NUMBER_OF_CLICK,click[6] );
        graphJsonArray.put(click6);

        JSONObject click7=new JSONObject();
        click7.put(DATE,7 );
        click7.put(NUMBER_OF_CLICK,click[7] );
        graphJsonArray.put(click7);

        JSONObject click8=new JSONObject();
        click8.put(DATE,8 );
        click8.put(NUMBER_OF_CLICK,click[8] );
        graphJsonArray.put(click8);

        JSONObject click9=new JSONObject();
        click9.put(DATE,9 );
        click9.put(NUMBER_OF_CLICK,click[9] );
        graphJsonArray.put(click9);

        JSONObject click10=new JSONObject();
        click10.put(DATE,10 );
        click10.put(NUMBER_OF_CLICK,click[10] );
        graphJsonArray.put(click10);

        JSONObject click11=new JSONObject();
        click11.put(DATE,11 );
        click11.put(NUMBER_OF_CLICK,click[11] );
        graphJsonArray.put(click11);

        JSONObject click12=new JSONObject();
        click12.put(DATE,12 );
        click12.put(NUMBER_OF_CLICK,click[12] );
        graphJsonArray.put(click12);

        JSONObject click13=new JSONObject();
        click13.put(DATE,13 );
        click13.put(NUMBER_OF_CLICK,click[13] );
        graphJsonArray.put(click13);

        JSONObject click14=new JSONObject();
        click14.put(DATE,14 );
        click14.put(NUMBER_OF_CLICK,click[14] );
        graphJsonArray.put(click14);

        JSONObject click15=new JSONObject();
        click15.put(DATE,15 );
        click15.put(NUMBER_OF_CLICK,click[15] );
        graphJsonArray.put(click15);

        JSONObject click16=new JSONObject();
        click16.put(DATE,16 );
        click16.put(NUMBER_OF_CLICK,click[16] );
        graphJsonArray.put(click16);

        JSONObject click17=new JSONObject();
        click17.put(DATE,17 );
        click17.put(NUMBER_OF_CLICK,click[17] );
        graphJsonArray.put(click17);

        JSONObject click18=new JSONObject();
        click18.put(DATE,18 );
        click18.put(NUMBER_OF_CLICK,click[18] );
        graphJsonArray.put(click18);

        JSONObject click19=new JSONObject();
        click19.put(DATE,19 );
        click19.put(NUMBER_OF_CLICK,click[19] );
        graphJsonArray.put(click19);

        JSONObject click20=new JSONObject();
        click20.put(DATE,20 );
        click20.put(NUMBER_OF_CLICK,click[20] );
        graphJsonArray.put(click20);


        JSONObject click21=new JSONObject();
        click21.put(DATE,21 );
        click21.put(NUMBER_OF_CLICK,click[21] );
        graphJsonArray.put(click21);

        JSONObject click22=new JSONObject();
        click22.put(DATE,22 );
        click22.put(NUMBER_OF_CLICK,click[22] );
        graphJsonArray.put(click22);

        JSONObject click23=new JSONObject();
        click23.put(DATE,23 );
        click23.put(NUMBER_OF_CLICK,click[23] );
        graphJsonArray.put(click23);

        JSONObject click24=new JSONObject();
        click24.put(DATE,24 );
        click24.put(NUMBER_OF_CLICK,click[24] );
        graphJsonArray.put(click24);

        JSONObject click25=new JSONObject();
        click25.put(DATE,25 );
        click25.put(NUMBER_OF_CLICK,click[25] );
        graphJsonArray.put(click25);

        JSONObject click26=new JSONObject();
        click26.put(DATE,26 );
        click26.put(NUMBER_OF_CLICK,click[26] );
        graphJsonArray.put(click26);

        JSONObject click27=new JSONObject();
        click27.put(DATE,27 );
        click27.put(NUMBER_OF_CLICK,click[27] );
        graphJsonArray.put(click27);

        JSONObject click28=new JSONObject();
        click28.put(DATE,28 );
        click28.put(NUMBER_OF_CLICK,click[28] );
        graphJsonArray.put(click28);

        if(dayMaximum>=29) {
            JSONObject click29 = new JSONObject();
            click29.put(DATE, 29);
            click29.put(NUMBER_OF_CLICK, click[29]);
            graphJsonArray.put(click29);
            if(dayMaximum>=30){
                JSONObject click30=new JSONObject();
                click30.put(DATE,30 );
                click30.put(NUMBER_OF_CLICK,click[30] );
                graphJsonArray.put(click30);
                if(dayMaximum==31){
                    JSONObject click31=new JSONObject();
                    click31.put(DATE,31 );
                    click31.put(NUMBER_OF_CLICK,click[31] );
                    graphJsonArray.put(click31);
                }
            }
        }

        return graphJsonArray;
    }
}
