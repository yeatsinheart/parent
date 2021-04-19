package com.common.utils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DrawUtils {
    // 1。提前列举所有开奖结果，落库获取ID
    // 2。提前具体玩法的所有投注可能，落库获取ID，并提供判断开奖结果是否符合预期的方法
    // 3。遍历开奖结果，调用是否符合预期若符合，则在开奖结果添加相对应玩法字段，值为是否中奖


    public static void main(String[] args) {
        // 假设成密码锁的个数
        int chooseNum = 7;
        // 每一圈上的所有数字
        Object[] a = new Object[49];
        //arrangementSelect(a, chooseNum);
        //combinationSelect(a, chooseNum);
        System.out.println(String.format("A(%d, %d) = %d", a.length, chooseNum, arrangement(a.length, chooseNum)));
        System.out.println(String.format("C(%d, %d)= %d", a.length, chooseNum, combination(a.length, chooseNum)));
        List t = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
        List ar = new ArrayList();
        for (Object asdf : t) {
            ar.add(asdf);
        }
        Object[] ob = getcode(ar, 5, true);
        System.out.println(Arrays.toString(ob));
        String[] x1 = {"1", "2"};
        String[] x2 = {"1", "2", "3"};
        String[] x3 = {"1", "2", "3"};
        List carr = new ArrayList();
        carr.add(x1);
        carr.add(x2);
        carr.add(x3);
        String[] target = new String[carr.size()];
        getcode(target, carr, 0, false);
    }


    // 多组不同数量的得到的排列
    public static void getcode(String[] target, List<String[]> arr, int arrreadindex, boolean repeatable) {
        if (arrreadindex == arr.size()) {
            System.out.println(Arrays.toString(target));
        } else {
            String[] now = arr.get(arrreadindex);
            for (int b = 0; b < now.length; b++) {
                String ob = now[b];
                boolean exist = false;
                for (int j = 0; j < arrreadindex; j++) {
                    if (ob.equals(target[j]) && !repeatable) {  // 若已存在，则不能重复选
                        exist = true;
                        break;
                    }
                }
                if (!exist) {
                    target[arrreadindex] = ob;
                    int tmpindex = arrreadindex + 1;
                    getcode(target, arr, tmpindex, repeatable);
                }

            }
        }
    }

    /**
     * 随机获取一个号码
     */
    public static Object[] getcode(List arr, int num, boolean repeatable) {
        Object[] target = new Object[num];
        for (int i = 0; i < num; i++) {
            Random random = new Random();
            int nowum = arr.size();
            int index = random.nextInt(nowum);
            target[i] = arr.get(index);
            if (!repeatable) {
                arr.remove(index);
            }
        }
        return target;
    }

    /**
     * 计算n的阶乘：n! = n * (n-1) * (n-2) * ... *2 * 1
     */
    public static BigInteger one = new BigInteger("1");
    public static BigInteger zero = new BigInteger("0");

    public static BigInteger factorial(BigInteger n) {
        return (n.compareTo(one) > 0) ? n.multiply(factorial(n.subtract(one))) : one;
    }

    /**
     * 计算排列数：A(n, m) = n!/(n-m)!  -- 从n个数中取出m个数进行排列 ,需要考虑数的顺序 (如果n个数进行排列，有n!种情况)
     */
    public static BigInteger arrangement(int n, int m) {
        BigInteger x = new BigInteger("" + n);
        BigInteger y = new BigInteger("" + m);
        return (n >= m) ? factorial(x).divide(factorial(x.subtract(y))) : zero;
    }

    /**
     * 计算组合数：C(n, m) = n!/((n-m)! * m!)  --  从n个数中取出m个数进行排列 ,不考虑数的顺序 （如 1234 和 4321 属于一种组合，都包含1，2，3，4这四个数）
     */
    public static BigInteger combination(int n, int m) {
        BigInteger x = new BigInteger("" + n);
        BigInteger y = new BigInteger("" + m);
        return (x.compareTo(y) > -1) ? factorial(x).divide((factorial(x.subtract(y)).multiply(factorial(x)))) : zero;
    }

    /**
     * 排列：从数组a中选择n个数进行排列
     */
    public static void arrangementSelect(Object[] a, int n) {
        arrangementSort(a, new Object[n], 0, true);
        System.out.println(String.format("A(%d, %d) = %d", a.length, n, arrangement(a.length, n)));
    }

    /**
     * 通过递归的方式罗列出所有的排列结果
     *
     * @param a：初始数组
     * @param result：排列数组初始状态
     * @param resultIndex：比较的起始索引
     */
    public static void arrangementSort(Object[] a, Object[] result, int resultIndex, boolean repeatable) {
        int result_length = result.length;
        if (resultIndex >= result_length) {
            System.out.println(Arrays.toString(result));  // 输出排列结果
            return;
        }
        //
        for (int i = 0; i < a.length; i++) {
            // 判断待选的数是否存在于排列的结果中
            boolean exist = false;
            for (int j = 0; j < resultIndex; j++) {
                if (a[i] == result[j] && !repeatable) {  // 若已存在，则不能重复选
                    exist = true;
                    break;
                }
            }
            if (!exist) {  // 若不存在，则可以选择
                result[resultIndex] = a[i];
                arrangementSort(a, result, resultIndex + 1, repeatable);
            }
        }
    }

    /**
     * 组合：从数组a中选择n个数进行组合
     */
    public static void combinationSelect(Object[] a, int n) {
        Object[] b = new Object[n];
        combinationSort(a, 0, b, 0);
        System.out.println(String.format("C(%d, %d)= %d", a.length, n, combination(a.length, n)));

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
