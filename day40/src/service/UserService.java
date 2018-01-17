package service;

import bean.User;
import dao.UserDao;

public class UserService {

    private static UserDao userDao = new UserDao();

    public static boolean register(User user){
        User user1 = userDao.queryByUserName(user.getUsername());
        if (user1 != null){
            return false;
        }else {
            return true;
        }
    }

    public static int login(User user){
        User user1 = userDao.queryByUserName(user.getUsername());
        if (user1 != null){
            if (user1.getPassword().equals(user.getPassword())){
                return 1;
            }else {
                return 2;
            }
        }else {
            return 3;
        }
    }

}
