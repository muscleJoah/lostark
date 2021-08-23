package com.lostark.test.controller;


import com.lostark.test.model.Character;
import com.lostark.test.service.InfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/lostark")
@RestController
public class infoController {

    String infoURL = "https://lostark.game.onstove.com/Profile/Character/%EB%8F%84%ED%99%94%EB%8F%99%EB%B6%88%EA%B8%B0%EB%91%A5%EB%82%A8";
    @Autowired
    private InfoService infoService;

    @GetMapping("/get/{name}")
    @ApiOperation(value = "유저 조회" , notes = "도불남이 얼마나 약한지 알아보자.")
    public Character getInfo(@PathVariable("name") String name) {
        System.out.println(name);
        return infoService.getInfoByName(name);
    }
}

//        try {
//            String URL = String.format("https://lostark.game.onstove.com/Profile/Character/%s", URLEncoder.encode(name,"UTF-8"));
//
//            Connection conn = Jsoup.connect(URL);
//            Document html = conn.get();
//            Document test = conn.get();
//            Elements contents;
//
//            System.out.println(test.select(".level-info__expedition").text());
//            System.out.println(test.select(".level-info__item").text());
//            System.out.println(test.select(".level-info2__expedition").text());
//            System.out.println(test.select(".profile-character-info__server").text());
//
//
//            return html.toString();
//        }
//        catch(IOException e){
//            e.printStackTrace();
//            return null;
//        }
//    }

