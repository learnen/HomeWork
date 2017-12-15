package option;

import bean.Adress;
import bean.TenPlayer;
import bean.Weather;
import game.Game;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.dom4j.DocumentException;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static option.NetTool.*;

public class Option<T>{

    public static void consultWeather(String city) throws IOException {

        String a = "http://api.k780.com/?app=weather.future&weaid="+
                city + "&appkey=29836&sign=4fe58a60443a900a251f8b05763d4622&format=json";
        JSONObject jsonObject = getJsonListObject(a);

        Map classMap = new HashMap();

        classMap.put("result", Weather.ResultBean.class);


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
        String a = "http://"+ Constants.ADRESS+"/day16/ten";

        JSONArray arr = getJsonObjectArray(a, TenPlayer.class);

        List<TenPlayer> newsList1 =new ArrayList<TenPlayer>();

        for (int i = 0; i < arr.size(); i++) {
            JSONObject jsonObj =(JSONObject) arr.get(i);
            newsList1.add((TenPlayer) JSONObject.toBean(jsonObj , TenPlayer.class));
            System.out.println(newsList1.get(i).getNickname() +"的成绩是"+ newsList1.get(i).getScore());
        }



    }

    public static void getFirstOne() throws IOException, DocumentException {
        String url = "http://"+ Constants.ADRESS+"/day16/first";

        xmlGetString(url);
//        xStreamXml(url);

    }


}
