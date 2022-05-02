package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class util {
    public static void main(String[] args) {
        String path = "https://search.jd.com/Search?keyword=%E8%8D%AF&enc=utf-8&wq=yao&pvid=47efa101ce2e4ab8a4719216fa98eddd";
        String code = getCodeByPath(path);
        ArrayList<String> list = new ArrayList<>();
        Document document = Jsoup.parse(code);
        Elements elements = document.select("div[class=gl-i-wrap]");
        System.out.println(elements);
    }

    private static String getCodeByPath(String path) {
        String code = null;
        try {
            code = Jsoup.connect(path).execute().body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return code;
    }
}
