package tools;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import person.Person;

import java.io.File;
import java.util.List;
import java.util.regex.Pattern;

public class Match {
    private static final int NICKNAME = 2;

    private static Person person;

    public static boolean matchUserName(String userName){

        boolean isMatched1;
        boolean isMatched2;
        boolean isMatched3;

        isMatched1 = Pattern.matches("[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)" +
                "*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?",userName);

        isMatched2 = Pattern.matches("^1[3|4|5|8][0-9]\\d{4,8}$",userName);

        isMatched3  = read(userName,1);



        if (isMatched1 || isMatched2){

        }else {
            System.out.println("用户名格式不正确，请重新输入：");
            return false;
        }
        if (isMatched3){
            return true;
        } else{
            System.out.println("用户名已存在，请重新输入：");
            return false;
        }

    }

    public static boolean checkPassWord(String passWord){
        if (!(passWord.length()>6 && passWord.length()<14)){
            System.out.println("密码格式不正确，请重新输入");
            return false;
        }
        int isEasy = 0;
        for (int i = 0; i < 10; i++) {
            String a = i + "";
            if (passWord.contains(a) && (isEasy == 0)){
                isEasy += 1;
            }
        }
        for (char j = 'a'; j < 'z'; j++) {
            String a = j+"";
            if (passWord.contains(a) && (isEasy == 1)){
                isEasy += 1;
            }
        }

        if (isEasy == 2){
            return true;
        }else {
            System.out.println("密码格式1不正确，请重新输入");
            return false;
        }


    }

    public static boolean read(String userName,int num){

        SAXReader saxReader = new SAXReader();
        Document document = null;
        Element root;
        try {
            document = saxReader.read(new File("src/user.xml"));
            root = document.getRootElement();
        } catch (DocumentException e) {
           return true;
        }

        List<Element> list = root.elements();

        for (int i = 0; i < list.size(); i++) {
            Element element = list.get(i);
            Element username = element.element("username");
            if (num == NICKNAME){
                username = element.element("nickname");
                System.out.println(userName);
                if (!Pattern.matches("[a-z]*[A-Z]*",userName)){
                    return false;
                }
            }
            String usernameText = username.getText();
            if (usernameText.equals(userName)){
                return false;
            }else {
                return true;
            }
        }

        return true;

    }

    public static boolean loginMatch(String userName,String passWord){

        SAXReader saxReader = new SAXReader();
        Element root;
        try {
            Document document = saxReader.read(new File("src/user.xml"));
           root = document.getRootElement();
        } catch (DocumentException e) {
            return false;
        }

        int judge = 0;
        List<Element> users = root.elements("user");
        for (int i = 0; i <users.size() ; i++) {
            Element user = users.get(i);
            Element username = user.element("username");
            String usernameText = username.getText();

            Element password = user.element("password");
            String passwordText = password.getText();

            Element nickname = user.element("nickname");
            String nicknameText = nickname.getText();

            if ( usernameText.equals(userName)  &&  passwordText.equals(passWord)){
                person = new Person(usernameText,passwordText,nicknameText);
                judge =1;
            }
        }


        if (judge == 1){
            return true;
        }else {
            System.out.println("用户名或密码不匹配，请重新输入：");
            return false;
        }

    }


    public static Person returnPerson(){
        return person;
    }

}
