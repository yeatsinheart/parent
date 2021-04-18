package com.lottory.lotos.六合彩;

import java.util.List;

public class Lottery {
    private String itemKey;
    //双色球 35、红、兔、火、野、女、天、春、琴
    private String valueKeys ;
    private String colors;
    private String icon;
    private boolean closed;

    // 开奖方式 1。自动 2。手工
    private Integer genCodeWay;
    // 1.时间戳，2。当天时间
    private Integer timeTime;
    // 剩余开奖时间 d-(x+期数*d)  (d+1)期数-x
    private long distance;
    private long standTime;

    private long sellStartTime;
    private long sellEndDaytime;

    private long stopStartTime;
    private long stopEndTime;

}
