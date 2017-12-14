package game;

import net.sf.json.JSONObject;
import option.Option;
import person.Person;
import tools.Match;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class Game {

    private static boolean game;
    private static String result;

    public static void play() throws InterruptedException, IllegalAccessException, IOException, InstantiationException {
        Scanner input  =  new Scanner(System.in);
        System.out.println("请输入你选择的选择1、困难  2、适中  3、简单");

        int choice = input.nextInt();

        switch (choice){
            case 1://one 表示奇异字符  two表示小写字母  three表示大写字母
                    // 0表示没有 ， 1表示有
                result = getString(1, 1, 1, 30);
                System.out.println(result);
                game = Game(input, result);
                break;
            case 2:
                result = getString(0, 1, 1, 20);
                System.out.println(result);
               game = Game(input, result);
                break;
            case 3:
               result = getString(0, 0, 1, 10);
                System.out.println(result);
                game = Game(input, result);
                break;
            default:
                break;
        }
    }

    private static boolean Game(Scanner input, String result1) throws IllegalAccessException, IOException, InstantiationException {
        System.out.println("请输入你所看到的字符：");
        long l = System.currentTimeMillis();
        input.nextLine();
        String getIn = input.nextLine();
        if (getIn.equals(result1)){
             l = System.currentTimeMillis() - l ;
             upLoad(l);
            System.out.println("挑战成功!   挑战成绩是"+l+"毫秒");
            return true;
        }else {
            System.out.println("输入字符不符合，请重新挑战");
            return false;
        }
    }

    public static String getString(int one, int two, int three, int difficulty) throws InterruptedException {


        for (int i = 3; i > 0; i--) {
            System.out.println("倒计时" + i + "秒后开始");
            Thread.sleep(1000);
        }

        System.out.println("开始!");

        Set set = new HashSet();

        char[] arr1 = {'~','！','@','#','￥','%','^','&','*','（','）'
                ,'{','}','[',']','\\','/','.','<','>','?','_','-','+','='};

        Random random = new Random();

        int a= 0;;
        int b = 0 ;
        int c =  0;

        while (b+c+a < difficulty-7){

           a= random.nextInt(difficulty)+1;
            if (one == 0){
                a = 0;
            }
            if (a > arr1.length-3){
                a= random.nextInt(difficulty)+1;
            }
            b = random.nextInt(difficulty-a)+1;
            if (two == 0){
                b = 0;
            }
            if (b > 20){
                b = random.nextInt(difficulty-a)+1;
            }
            c=  random.nextInt(difficulty-a-b)+1;
            if (three == 0){
                c = 0;
            }
            if (c > 20){
                c = random.nextInt(difficulty-a)+1;
            }
        }

        int d=  difficulty-a-b-c;

        for (int i = 0; i < a; i++) {
            int first = random.nextInt(arr1.length);
            if (set.contains(arr1[first])){
                i--;
            }
            set.add(arr1[first]);
        }

        for (int i = 0; i < c ; i++) {
            char first = (char) (random.nextInt(122-97+1)+97);
            if (set.contains(first)){
                i--;
            }
            set.add(first);
        }

        for (int i = 0; i < b ; i++) {
            char first = (char) (random.nextInt(90-65+1)+65);
            if (set.contains(first)){
                i--;
            }
            set.add(first);
        }

        for (int i = 0; i < d ; i++) {
            int first = (char) (random.nextInt(9+1));
            if (set.contains(first)){
                i--;
            }
            set.add(first);
        }

        String result = "";
        for (Object o : set) {
            result = result + o.toString();
        }
        return result;

    }

    public static void upLoad(long score) throws IllegalAccessException, IOException, InstantiationException {
        Person person = Match.returnPerson();
        String nickName = person.getNickName();
        String a = "http://192.168.20.221:8080/day16/insert?username="+nickName+"&score="+ score;

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

        System.out.println(result);

    }

}
