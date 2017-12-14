package option;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;

import java.awt.print.Book;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Option<T>{

    public static void consultWeather(String city) throws IOException {
        String result = getString(city);

        Map classMap = new HashMap();
        classMap.put("result",Weather.ResultBean.class);

        JSONObject jsonObject = JSONObject.fromObject(result);

        Weather weather = (Weather)JSONObject.toBean(jsonObject,Weather.class,classMap);

        List<Weather.ResultBean> weatherResult = weather.getResult();

        for (int i = 0; i < weatherResult.size(); i++) {
            Weather.ResultBean resultBean = (Weather.ResultBean)weatherResult.get(i);
            System.out.println(resultBean.getWeek() + "--" +
                    resultBean.getWeather()+"--" +
                    resultBean.getWind()+"--"+
                    resultBean.getWinp());
        }

    }

    private static String getString(String city) throws IOException {
        String a = "http://api.k780.com/?app=weather.future&weaid="+
                city + "&appkey=29836&sign=4fe58a60443a900a251f8b05763d4622&format=json";
        URL url = new URL(a);

        URLConnection con = url.openConnection();

        InputStream is = con.getInputStream();

        byte[] buff = new byte[1024];

        int len;

        StringBuffer sb = new StringBuffer();

        while ((len = is.read(buff)) != -1){
            sb.append(new String(buff,0,len));
        }

        return new String(sb);
    }

    public static void consultAdress(String phoneNumber) throws IOException {
        String a = "http://api.k780.com/?app=phone.get&phone="
                + phoneNumber + "&appkey=29836&sign=4fe58a60443a900a251f8b05763d4622&format=json";
        URL url = new URL(a);

        URLConnection con = url.openConnection();

        InputStream is = con.getInputStream();

        byte[] buff = new byte[1024];

        int len;

        StringBuffer sb = new StringBuffer();

        while ((len = is.read(buff)) != -1){
            sb.append(new String(buff,0,len));
        }

        String result = new String(sb);

        JSONObject jsonObject = JSONObject.fromObject(result);

        Adress adress = (Adress)JSONObject.toBean(jsonObject,Adress.class);

        System.out.println("该手机号的归属地是："+adress.getResult().getStyle_citynm());

    }

    public static void consultPlayer() throws IllegalAccessException, IOException, InstantiationException {
        String a = "http://192.168.20.221:8080/day16/ten";

        JSONArray arr = create(a,Player.class);

        List<Player> newsList1 =new ArrayList<Player>();

        for (int i = 0; i < arr.size(); i++) {
            JSONObject jsonObj =(JSONObject) arr.get(i);
            newsList1.add((Player) JSONObject.toBean(jsonObj ,Player.class));
            System.out.println(newsList1.get(i).getNickName() +"的成绩是"+ newsList1.get(i).getScore());
        }



    }


    public static void playGame(){

    }


    public static <T> JSONArray create(String c, Class clazz) throws IOException, IllegalAccessException, InstantiationException {

        URL url = new URL(c);

        URLConnection con = url.openConnection();

        InputStream is = con.getInputStream();

        byte[] buff = new byte[1024];

        int len;

        StringBuffer sb = new StringBuffer();

        while ((len = is.read(buff)) != -1){
            sb.append(new String(buff,0,len));
        }

        String result = new String(sb);

        JSONArray array_news =new JSONArray();
        array_news = JSONArray.fromObject(result);

        return  array_news;

    }

}
