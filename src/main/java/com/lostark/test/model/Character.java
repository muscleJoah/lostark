package com.lostark.test.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class Character {

    private String name;
    private String server;
    private String job;
    private Integer expeditionLv;
    private Float itemLv;
    List<Engrave> engraves;


    public Character (String name, String server, Integer expeditionLv, Float itemLv , String job, List<Engrave> engraves)
    {
        this.name = name;
        this.server = server;
        this.expeditionLv = expeditionLv;
        this.itemLv = itemLv;
        this.job = job;
        this.engraves = engraves;
    }
}
