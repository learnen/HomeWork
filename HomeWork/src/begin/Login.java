package begin;

import tools.Match;

import java.util.Scanner;

public class Login {

    public static void login(){
        Scanner input = new Scanner(System.in);
        String userName;
        String passWord;
        System.out.println("请输入你的用户名：");
        userName = input.nextLine();
        System.out.println("请输入你的密码：");
        passWord = input.nextLine();
        while(!Match.loginMatch(userName,passWord)){
            System.out.println("请输入你的用户名：");
            userName = input.nextLine();
            System.out.println("请输入你的密码：");
            passWord = input.nextLine();
        }

        System.out.println("登录成功");
    }

}
