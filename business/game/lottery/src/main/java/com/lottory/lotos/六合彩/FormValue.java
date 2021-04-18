package com.lottory.lotos.六合彩;

import java.util.List;

public class FormValue {
    // 标准单价 最高单价 最低单价 。复式单输入买入总金额后，获取单价是否满足最大最小限制
    private Integer lotteryId;
    private Integer FormId;
    private String value;
    private List<Long> standPrice;
    private List<Long> maxPrice;
    private List<Long> minPrice;
    // 标准奖金
    private Long prize;
}
