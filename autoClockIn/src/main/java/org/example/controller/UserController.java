package org.example.controller;


import org.example.dao.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@Controller
public class UserController {

    @RequestMapping("/addUser")
//    @ResponseBody
    public String addUser(String name, String id) {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao = (UserDao) app.getBean("userDao");
        try {
            userDao.insert(name, id);
        } catch (Exception e) {
            return "/failed.jsp";
        }
        return "/success.jsp";
    }
}
