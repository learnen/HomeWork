import begin.Login;
import begin.Register;
import option.Option;
import person.Person;
import tools.Match;


import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final int REGISTER_CODE =1;
    private static final int LOGIN_CODE =2;

    public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException, InterruptedException {
        Scanner input = new Scanner(System.in);
        while (true){
            System.out.println("请输入你的选择1、注册 2、登录");
            int chioce = input.nextInt();
            switch (chioce){
                case REGISTER_CODE:
                    Register register = new Register();
                    register.register();
                    break;
                case LOGIN_CODE:
                   Login.login();
                    break;
                default:
                    System.out.println("请输入指定的选择项：");
                    break;

            }
            if (chioce == 2){
                break;
            }
        }

        Person person = Match.returnPerson();
        System.out.println("欢迎用户名为："+person.getUserName()+"昵称为:" + person.getNickName()+"的玩家成功登录");

        int choice = 0;
        while (choice != 5){
            System.out.println("请输入你的选择"+"\n"+"1、查询天气"+"\n"
                    +"2、查询手机归属地"+"\n"+"3、手速游戏"+"\n"+
                    "4、查询手速前十的玩家" + "5、退出选择");
            choice= input.nextInt();
            input.nextLine();
            switch (choice){
                case 1://查询天气
                    System.out.println("请输入你要查询天气的城市：");
                    String city = input.nextLine();
                    Option.consultWeather(city);
                    break;
                case 2://查询手机归属地
                    System.out.println("请输入你要查询天气的城市：");
                    String phoneNumber = input.nextLine();
                    Option.consultAdress(phoneNumber);
                    break;
                case 3://手速游戏
                    Option.playGame();
                    break;
                case 4://查询手速前十的玩家、
                    Option.consultPlayer();
                    break;
                case 5:
                    break;//退出游戏
                default:
                    break;
            }

        }


    }

}
