package org.example;

import com.alibaba.fastjson.JSONObject;

import org.example.dao.UserDao;
import org.example.http.HttpClientExample;
import org.example.pojo.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MainClass {


    public static void main(String[] args) throws Exception {
//        HttpClientExample obj = new HttpClientExample();
//
//        try {
//            System.out.println("Testing 1 - Send Http GET request");
//            obj.sendGet();
//
//            System.out.println("Testing 2 - Send Http POST request");
//            obj.sendPost();
//        } finally {
//            obj.close();
//        }
//        String url = "http://yx.ty-ke.com/Home/Monitor/monitor_add";

//        String param = "mobile=142731200209044517&title=36.5&jk_type=健康&wc_type=否&jc_type=否&province=山西省&city=太原市&district=尖草坪区&address=山西省太原市尖草坪区X256&is_verify=0";
//        String param = "mobile=142723200105043018&title=36.5&jk_type=%E5%81%A5%E5%BA%B7&wc_type=%E5%90%A6&jc_type=%E5%90%A6&province=%E5%B1%B1%E8%A5%BF%E7%9C%81&city=%E5%A4%AA%E5%8E%9F%E5%B8%82&district=%E5%B0%96%E8%8D%89%E5%9D%AA%E5%8C%BA&address=%E5%B1%B1%E8%A5%BF%E7%9C%81%E5%A4%AA%E5%8E%9F%E5%B8%82%E5%B0%96%E8%8D%89%E5%9D%AA%E5%8C%BAX256&is_verify=0";
//        String param = "mobile=140821200301260010&title=36.5&jk_type=%E5%81%A5%E5%BA%B7&wc_type=%E5%90%A6&jc_type=%E5%90%A6&province=%E5%B1%B1%E8%A5%BF%E7%9C%81&city=%E5%A4%AA%E5%8E%9F%E5%B8%82&district=%E5%B0%96%E8%8D%89%E5%9D%AA%E5%8C%BA&address=%E5%B1%B1%E8%A5%BF%E7%9C%81%E5%A4%AA%E5%8E%9F%E5%B8%82%E5%B0%96%E8%8D%89%E5%9D%AA%E5%8C%BAX256&is_verify=0";
//        String result = HttpClientExample.sendPostUrl(url, param);
//        JSONObject jsonObject = JSONObject.parseObject(result);
//
////        打印日志
////      String code = jsonObject.getString("code");
////      System.out.println(code);
//        String msg = jsonObject.getString("msg");
//        System.out.println(msg);

//      发送请求的地址
        String url = "http://yx.ty-ke.com/Home/Monitor/monitor_add";
//      从数据库中获取用户信息
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao = (UserDao) app.getBean("userDao");
        List<User> userList = userDao.selectAll();
        for (User user : userList) {
            String id = user.getId();
            String result = HttpClientExample.sendPostUrl(url, generateParam(id));
            JSONObject jsonObject = JSONObject.parseObject(result);
            String msg = jsonObject.getString("msg");
            System.out.println(user.getName() + msg);
        }
    }

    public static String generateParam(String id) {
        return "mobile=" + id + "&title=36.5&jk_type=%E5%81%A5%E5%BA%B7&wc_type=%E5%90%A6&jc_type=%E5%90%A6&province=%E5%B1%B1%E8%A5%BF%E7%9C%81&city=%E5%A4%AA%E5%8E%9F%E5%B8%82&district=%E5%B0%96%E8%8D%89%E5%9D%AA%E5%8C%BA&address=%E5%B1%B1%E8%A5%BF%E7%9C%81%E5%A4%AA%E5%8E%9F%E5%B8%82%E5%B0%96%E8%8D%89%E5%9D%AA%E5%8C%BAX256&is_verify=0";
    }
}
