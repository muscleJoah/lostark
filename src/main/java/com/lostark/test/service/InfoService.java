package com.lostark.test.service;


import com.lostark.test.model.Character;
import com.lostark.test.model.Engrave;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
public class InfoService {

    public Character getInfoByName(String name) {

        try {
            String URL = String.format("https://lostark.game.onstove.com/Profile/Character/%s", URLEncoder.encode(name, "UTF-8"));
            System.out.println(URL);
            Connection conn = Jsoup.connect(URL);
            Document test = conn.get();

            Integer expeditionLv;
            Float itemLv;
            String server;
            String job;

            String temp;
            temp = test.select(".level-info__expedition").text();
            expeditionLv = intParser(temp.substring(9));

            temp = test.select(".profile-character-info__img").toString();
            job = getJob(temp);

            temp = test.select(".level-info2__expedition").text();
            itemLv = Float.parseFloat(temp.substring(12).replace(",", ""));

            temp = test.select(".profile-character-info__Server").text();
            server = temp.substring(1);


//            System.out.print(name);
//            System.out.println(String.format("   %s", name.getClass().getName()));
//            System.out.print(expeditionLv);
//            System.out.println(String.format("   %s", expeditionLv.getClass().getName()));
//            System.out.print(itemLv);
//            System.out.println(String.format("   %s", itemLv.getClass().getName()));
//            System.out.print(server);
//            System.out.println(String.format("   %s", server.getClass().getName()));
//            System.out.print(job);
//            System.out.println(String.format("   %s", job.getClass().getName()));


            //public Character (String name, String server, int expeditionLv, float itemLv , String job)
            return new Character(name, server, expeditionLv, itemLv, job,getEngrave(test));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private List<Engrave> getEngrave(Document test) {
        String html = test.select(".swiper-slide").toString();
        List<String> engraveList = substringBetween(html ,"<span>" , "</span>");
        System.out.println(engraveList);
        List<Engrave> engraves = new ArrayList<>();
        engraveList.forEach(engrave -> engraves.add(new Engrave(engrave.substring(0, engrave.indexOf("Lv")), intParser(engrave.substring(engrave.indexOf("Lv") + 4)))));
        return engraves;

    }


    String getJob(String html) {
        int index = html.indexOf("alt=");
        return html.substring(index + 5, html.length() - 2);
    }


    int intParser(String age) {
        try {
            return Integer.parseInt(age);
        } catch (ClassCastException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private List<String> substringBetween(String str, String open, String close) {
            List<String> engraves = new ArrayList<>();

            if (str == null || open == null || close == null) {
                return null;
            }
            int count = 0;
            while(true) {
                count ++;
                int start = str.indexOf(open);
                if (start != -1) {
                    int end = str.indexOf(close, start + open.length());
                    if (end != -1) {
                        String engrave = str.substring(start + open.length(),end);
                        engraves.add(engrave);
                    }
                }
                str = str.substring(str.indexOf(close)+5);
                if(!str.contains("<span>") || count == 10){
                    break;
                }
            }
            return engraves;
    }


}