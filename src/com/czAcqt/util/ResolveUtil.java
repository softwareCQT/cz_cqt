package com.czAcqt.util;

/**
 * @author chenqiting
 */
public class ResolveUtil {
    /***
     * 解析分数，返回分子、分母的大小
     * @param fraction 分式
     * @return 返回分子和分母的大小
     */
    public static int[] analysis(String fraction) {
        int[] result = new int[2];
        //按"/或'"切分后的分数
        String[] strings = fraction.split("[/']");


        if (strings.length == 3) {
            //获取分母
            result[1] = Integer.parseInt(strings[2]);
            //判断是否有负号
            boolean flag = strings[0].contains("-");
            //获取分子,公式为 原分子 + 分母*beginNum
            if (flag) {
                result[0] = Integer.parseInt(strings[0]) * result[1] - Integer.parseInt(strings[1]);
            } else {
                result[0] = Integer.parseInt(strings[1]) + Integer.parseInt(strings[0]) * result[1];
            }
        } else {
            result[0] = Integer.parseInt(strings[0]);
            result[1] = Integer.parseInt(strings[1]);
        }

        return result;
    }

    /***
     * 生成真分数的方法(对分数进行简化到最小）
     * @param molecule 分子
     * @param denominator 分母
     * @return "分子/分母" || "数字"
     */
    public static String createFraction(int molecule, int denominator) {
        //对于分子为0和分母为0的解决方法
        if (molecule == 0 || denominator == 0) {
            return "0";
        }

        int result = 0;
        //用辗转相除法来确定最大公因数
        for (int i = Math.min(molecule, denominator); i > 1; --i) {
            if (molecule % i == 0 && denominator % i == 0) {
                result = i;
                break;
            }
        }
        int beforeNum = 0;
        //molecule取前缀真分数
        if (molecule > denominator) {
            beforeNum = molecule / denominator;
        }

        //判断公因数的结果
        if (result == 0) {
            return beforeNum != 0 ?
                    (beforeNum + "'" + molecule % denominator + "/" + denominator) :
                    (molecule + "/" + denominator);
        } else if (denominator == result) {
            return molecule / result + "";
        } else {
            //再进行处理，因为只有以上两种分母分子形式
            return createFraction(molecule / result, denominator / result);
        }
    }
}
