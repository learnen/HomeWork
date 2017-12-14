package option;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import game.Game;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Option<T>{

    public static void consultWeather(String city) throws IOException {

        String a = "http://api.k780.com/?app=weather.future&weaid="+
                city + "&appkey=29836&sign=4fe58a60443a900a251f8b05763d4622&format=json";
        JSONObject jsonObject = getJsonListObject(a);

        Map classMap = new HashMap();

        classMap.put("result",Weather.ResultBean.class);


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

    public static void consultAdress(String phoneNumber) throws IOException {
        String a = "http://api.k780.com/?app=phone.get&phone="
                + phoneNumber + "&appkey=29836&sign=4fe58a60443a900a251f8b05763d4622&format=json";

        String result = getString(a);

        JSONObject jsonObject = JSONObject.fromObject(result);

        Adress adress = (Adress)JSONObject.toBean(jsonObject,Adress.class);

        System.out.println("该手机号的归属地是："+adress.getResult().getStyle_citynm());

    }

    public static void playGame() throws InterruptedException, IllegalAccessException, IOException, InstantiationException {
        Game.play();
    }

    public static void consultPlayer() throws IllegalAccessException, IOException, InstantiationException {
        String a = "http://192.168.20.221:8080/day16/ten";

        JSONArray arr = getJsonObjectArray(a, TenPlayer.class);

        List<TenPlayer> newsList1 =new ArrayList<TenPlayer>();

        for (int i = 0; i < arr.size(); i++) {
            JSONObject jsonObj =(JSONObject) arr.get(i);
            newsList1.add((TenPlayer) JSONObject.toBean(jsonObj , TenPlayer.class));
            System.out.println(newsList1.get(i).getNickname() +"的成绩是"+ newsList1.get(i).getScore());
        }



    }

    public static void getFirstOne() throws IOException, DocumentException {
        String url = "http://192.168.20.221:8080/day16/first";

//        xmlGetString(url);
        xStreamXml(url);

    }



    private static String getString(String a) throws IOException {
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

    public static <T> JSONArray getJsonObjectArray(String c, Class clazz) throws IOException, IllegalAccessException, InstantiationException {

        String result = getString(c);

        JSONArray array_news =new JSONArray();
        array_news = JSONArray.fromObject(result);

        return  array_news;

    }

    private static JSONObject getJsonListObject(String a) throws IOException {
        String result = getString(a);
        return JSONObject.fromObject(result);
    }

    private static void xmlGetString(String a) throws IOException, DocumentException {
        String result = getString(a);
        Document document = DocumentHelper.parseText(result);

        Element root = document.getRootElement();

        Element nickname = root.element("nickname");
        System.out.println(nickname.getText());

    }

    private static void xStreamXml(String a) throws IOException {
        String result = getString(a);
        XStream xStream = new XStream(new DomDriver());
        xStream.aliasType("User", FirstPlayer.class);
        FirstPlayer firstPlayer = (FirstPlayer)xStream.fromXML(result);
        System.out.println(firstPlayer.getNickname());
    }



}
