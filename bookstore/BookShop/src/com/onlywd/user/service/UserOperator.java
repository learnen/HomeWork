package com.onlywd.user.service;

import com.onlywd.user.dao.UserDao;
import com.onlywd.user.domain.User;
import com.onlywd.user.service.exception.*;
import org.apache.commons.beanutils.BeanUtils;
import util.SendEmail;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

public class UserOperator {

    private UserDao userDao;

    public void register(HttpServletRequest request) throws UserNameExistException, EmailNotMatchException, EmailExistException, MessagingException {
        String uuid = UUID.randomUUID().toString().
                replaceAll("-", "");

       User user = getUser(request);
       request.setAttribute("user",user);
        user.setUid(uuid);
        user.setCode(uuid+uuid);
        user.setState(0);
        userDao = new UserDao();
        User query = userDao.queryByUserName(user.getUsername());
        User query1 = userDao.queryByEmail(user.getEmail());
        if (query == null){
            if (query1 == null){
                //匹配邮箱是否合理
                matchEmail(user.getEmail());
                SendEmail sendEmail = new SendEmail();
                sendEmail.sendEmail(user);
            }else {
                throw new EmailExistException();
            }
        }else {
            throw new UserNameExistException();
        }





        userDao.insert(user);

    }

    public void login(HttpServletRequest request) throws PasswordNotMatchException, UsernameNotExistException, CodeNotActiveException {
        User formU = getUser(request);
        userDao = new UserDao();
        User user = userDao.queryByUserName(formU.getUsername());
        if (user != null){
            if (user.getPassword().equals(formU.getPassword())){
            }else {
                throw new PasswordNotMatchException();
            }
            if (user.getState() != 1){
                throw new CodeNotActiveException();
            }
        }else {
            throw new UsernameNotExistException();
        }
        request.getSession().setAttribute("user",user);
    }

    public String email(String code){
        userDao = new UserDao();
        User user = userDao.queryByCode(code);
        int i = userDao.updateState(user);
        if (i != 0){
            return "激活成功";
        }else {
            return "激活失败";
        }
    }

    public void matchEmail(String email) throws EmailNotMatchException {
        boolean isMatched1 = Pattern.matches("[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)" +
                "*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?", email);
        if (!isMatched1){
            throw new EmailNotMatchException();
        }
    }

    public User getUser(HttpServletRequest request) {
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user,map);
            return user;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

}
