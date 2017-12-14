package begin;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import tools.Match;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Register {

    private Element root;
    private Element root1;

    public  void register() throws IOException {
        Scanner input = new Scanner(System.in);
        String userName = "";
        System.out.println("请输入你的用户名：(用户名为手机号码或者邮箱)");
        userName = input.nextLine();
        while(!Match.matchUserName(userName)){
            userName = input.nextLine();
        }

        System.out.println("请输入你的密码：");
        String passWord = "";
        passWord = input.nextLine();
        while (!Match.checkPassWord(passWord)){
            passWord = input.nextLine();
        }

        System.out.println("请输入你的昵称：");
        String nickName = "";
        nickName = input.nextLine();
        while (!Match.read(nickName,2)){
            System.out.println("昵称格式错误，或者已经被使用");
            System.out.println("请重新输入你的昵称：");
            nickName = input.nextLine();
        }



        write(userName,passWord,nickName);
        System.out.println("注册成功");

//        write(userName, passWord);

    }

    private void write(String userName, String passWord,String nickName) throws IOException {
        SAXReader saxReader = new SAXReader();
        Document document = null;
        try {
            document = saxReader.read(new File("src/user.xml"));
            root = document.getRootElement();
        } catch (DocumentException e) {
            document = DocumentHelper.createDocument();
            root = document.addElement("users");
        }

        Element user = root.addElement("user");

        Element username = user.addElement("username");
        username.addText(userName);

        Element password = user.addElement("password");
        password.addText(passWord);

        Element nickname = user.addElement("nickname");
        nickname.addText(nickName);


        OutputFormat outputFormat = OutputFormat.createPrettyPrint();
        outputFormat.setEncoding("utf-8");

        XMLWriter xmlWriter = new XMLWriter(new FileWriter("src/user.xml"),outputFormat);
        xmlWriter.write(document);
        xmlWriter.close();
    }

}
