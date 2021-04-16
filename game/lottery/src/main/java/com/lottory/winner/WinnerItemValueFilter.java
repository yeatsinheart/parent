package com.lottory.winner;
/* 开奖后，根据lottery_id,获取所有定义好的投注项，遍历所有投注项，判断该投注项是否中奖*/
@Component(lottory_id_ItemValueId)
public class ItemValueFilter {
    boolean isWinner(String[] winnerCode){

        //比如下注 尾号"单" 则判断尾号单是否中奖
        //比如下注 尾号"双" 则判断尾号单是否中奖
        //比如下注 "任选二" 则判断"1，2"是否中奖
        //比如下注 "任选二" 则判断"3，4"是否中奖
        //比如下注 "五星直选" 则判断"3，4，5，6，7"是否中奖
        //比如下注 "冠军" 则判断冠军"1"是否中奖
        //比如下注 "冠军" 则判断冠军"2"是否中奖

        //中奖 则 添加改投注项的开奖历史记录
        //       update order where （select order_id from order_detail where item_id in(select item_id where item_value=该投注项ID)）
    }
    //获取下注总金额 计算成本
    caculate(){

    }
}
