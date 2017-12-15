package option;

import bean.FirstPlayer;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
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

public class NetTool {


    public static String getString(String a) throws IOException {
        URL url = new URL(a);

        URLConnection con = url.openConnection();

        InputStream is = con.getInputStream();

        byte[] buff = new byte[1024*1024];

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

    public static JSONObject getJsonListObject(String a) throws IOException {
        String result = getString(a);
        return JSONObject.fromObject(result);
    }

    public static void xmlGetString(String a) throws IOException, DocumentException {
        String result = getString(a);
        Document document = DocumentHelper.parseText(result);
        Element root = document.getRootElement();

        Element nickname = root.element("nickname");
        System.out.println(nickname.getText());

    }

    public static void xStreamXml(String a) throws IOException {
        String result = getString(a);
        XStream xStream = new XStream(new DomDriver());
        xStream.aliasType("User", FirstPlayer.class);
        FirstPlayer firstPlayer = (FirstPlayer)xStream.fromXML(result);
        System.out.println(firstPlayer.getNickname());
    }

}
