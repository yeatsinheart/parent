package com.lottory.lotos.六合彩;

import java.util.List;

public class Form {
    private String itemKey;
    boolean valueCloseable;

    private boolean status;
    // 几块区域分别叫什么名字 正码 特码
    private List<String> lableKeys;
    // 对应位置区域 可选的内容
    private List<String> choiceKeys;

    //投注方法 單式 複式 胆拖
    // 1｜2 最低1 最多2
    private List<Long> choosedNum;

    public static void createFormValues(){

    }
}
