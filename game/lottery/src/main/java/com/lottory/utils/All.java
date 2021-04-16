package com.lottory.utils;

import java.math.BigInteger;
import java.util.Arrays;

public class All {
    // 1。提前列举所有开奖结果，落库获取ID
    // 2。提前具体玩法的所有投注可能，落库获取ID，并提供判断开奖结果是否符合预期的方法
    // 3。遍历开奖结果，调用是否符合预期若符合，则在开奖结果添加相对应玩法字段，值为是否中奖


    public static void main(String[] args) {
        // 假设成密码锁的个数
        int chooseNum = 7;
        Object[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};  // 初始数组
        // 每一圈上的所有数字
        a=new Object[49];
        //arrangementSelect(a, chooseNum);
        //combinationSelect(a, chooseNum);
        System.out.println(String.format("A(%d, %d) = %d", a.length, chooseNum, arrangement(new BigInteger(""+a.length), new BigInteger(""+chooseNum))));
        System.out.println(String.format("C(%d, %d)= %d", a.length, chooseNum, combination(new BigInteger(""+a.length), new BigInteger(""+chooseNum))));

    }

    /**
     * 计算n的阶乘：n! = n * (n-1) * (n-2) * ... *2 * 1
     */
    public static BigInteger one = new BigInteger("1");
    public static BigInteger zero = new BigInteger("0");
    public static BigInteger factorial(BigInteger n) {
        return (n .compareTo(one) >0) ? n .multiply(factorial(n .subtract(one)))  : one;
    }

    /**
     * 计算排列数：A(n, m) = n!/(n-m)!  -- 从n个数中取出m个数进行排列 ,需要考虑数的顺序 (如果n个数进行排列，有n!种情况)
     */
    public static BigInteger arrangement(BigInteger n, BigInteger m) {
        return (n .compareTo(m) >-1) ? factorial(n) .divide(factorial(n .subtract(m)) ) : zero;
    }

    /**
     * 计算组合数：C(n, m) = n!/((n-m)! * m!)  --  从n个数中取出m个数进行排列 ,不考虑数的顺序 （如 1234 和 4321 属于一种组合，都包含1，2，3，4这四个数）
     */
    public static BigInteger combination(BigInteger n, BigInteger m) {
        return (n .compareTo(m) >-1) ? factorial(n) .divide ((factorial(n.subtract(m) ) .multiply(factorial(m)))) : zero;
    }

    /**
     * 排列：从数组a中选择n个数进行排列
     */
    public static void arrangementSelect(Object[] a, BigInteger n) {
        arrangementSort(a, new Object[n.intValue()], 0);
        System.out.println(String.format("A(%d, %d) = %d", a.length, n, arrangement(new BigInteger(""+a.length), n)));
    }

    /**
     * 通过递归的方式罗列出所有的排列结果
     *
     * @param a：初始数组
     * @param result：排列数组初始状态
     * @param resultIndex：比较的起始索引
     */
    public static void arrangementSort(Object[] a, Object[] result, int resultIndex) {
        int result_length = result.length;
        if (resultIndex >= result_length) {
            System.out.println(Arrays.toString(result));  // 输出排列结果
            return;
        }
        //
        for (int i = 0; i < a.length; i++) {
            // 判断待选的数是否存在于排列的结果中
            boolean exist = false;

            /*for (int j = 0; j < resultIndex; j++) {
                if (a[i] == result[j]) {  // 若已存在，则不能重复选
                    exist = true;
                    break;
                }
            }*/
            if (!exist) {  // 若不存在，则可以选择
                result[resultIndex] = a[i];
                arrangementSort(a, result, resultIndex + 1);
            }
        }
    }

    /**
     * 组合：从数组a中选择n个数进行组合
     */
    public static void combinationSelect(Object[] a, BigInteger n) {
        Object[] b = new Object[n.intValue()];
        combinationSort(a, 0, b, 0);
        System.out.println(String.format("C(%d, %d)= %d", a.length, n, combination(new BigInteger(""+a.length), n)));

    }

    /**
     * 通过递归的方式罗列出所有的组合结果
     *
     * @param a：初始数组
     * @param a_index：初始数组起始下标
     * @param result：初始组合数组
     * @param r_index：初始组合数组的起始下标
     */
    public static void combinationSort(Object[] a, int a_index, Object[] result, int r_index) {
        int r_len = result.length;
        int r_count = r_index + 1;
        if (r_count > r_len) {
            System.out.println(Arrays.toString(result));  // 输出组合结果
            return;
        }
        for (int i = a_index; i < a.length + r_count - r_len; i++) {
            result[r_index] = a[i];
            combinationSort(a, i + 1, result, r_index + 1);
        }
    }


}
