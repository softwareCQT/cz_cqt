package com.czAcqt.generate;

import com.czAcqt.util.ResolveUtil;
import com.sun.istack.internal.Nullable;

import java.util.Map;

/***
 * @author chenqiting
 */
public enum Symbol implements CalculateGenerate {
    /***
     * 相加操作
     */
    ADD("+"){
        @Override
        public String calculate(String a, String b) {
            boolean flagA = a.contains("/");
            boolean flagB = b.contains("/");
            //两个都是分数
            if (flagA && flagB){
                int[] anInt = ResolveUtil.analysis(a);
                int[] bnInt = ResolveUtil.analysis(b);
                //以AB为分母
                int denominator = anInt[1] * bnInt[1];
                //相加后的分子
                int molecule = anInt[0] * bnInt[1] + anInt[1] * bnInt[0];
                return ResolveUtil.createFraction(molecule, denominator);
            }else if (flagA){
                int[] anInt = ResolveUtil.analysis(a);
                //直接更新分子便可
                anInt[0] += Integer.parseInt(b) * anInt[1];
                return ResolveUtil.createFraction(anInt[0], anInt[1]);
            }else if (flagB){
                int[] bnInt = ResolveUtil.analysis(b);
                //直接更新分子便可
                bnInt[0] += Integer.parseInt(a) * bnInt[1];
                return ResolveUtil.createFraction(bnInt[0], bnInt[1]);
            }else {
                return String.valueOf(Integer.parseInt(a) + Integer.parseInt(b));
            }
        }
    },
    /***
     * 相乘操作
     */
    MULTIPLY("×"){
        @Override
        public String calculate(String a, String b) {
            boolean flagA = a.contains("/");
            boolean flagB = b.contains("/");

            if (flagA && flagB){
                int[] anInt = ResolveUtil.analysis(a);
                int[] bnInt = ResolveUtil.analysis(b);
                //以AB为分母
                int denominator = anInt[1] * bnInt[1];
                //分子相乘
                int molecule = anInt[0] * bnInt[0];
                return ResolveUtil.createFraction(molecule, denominator);
            }else if (flagA){
                int[] anInt = ResolveUtil.analysis(a);
                return ResolveUtil.createFraction(anInt[0] * Integer.parseInt(b), anInt[1]);
            }else if (flagB){
                int[] bnInt = ResolveUtil.analysis(b);
                return ResolveUtil.createFraction(bnInt[0] * Integer.parseInt(a), bnInt[1]);
            }else {
                return String.valueOf(Integer.parseInt(a) * Integer.parseInt(b));
            }
        }
    },
    /***
     * 相除操作
     */
    DIVIDE("÷"){
        @Override
        public String calculate(String a, String b) {
            //除法，从另外一种角度来说，是乘法的倒转，所以，只需要把b分子分母倒过来用乘法就行了
            boolean flag = b.contains("/");
            //新的数b的字符串
            String newB;
            //判断b是否为分数
            if (flag) {
                int[] bnInt = ResolveUtil.analysis(b);
                newB = ResolveUtil.createFraction(bnInt[1], bnInt[0]);
            }else{
                newB = 1 + "/" + b;
            }
            return Symbol.MULTIPLY.calculate(a, newB);
        }
    },
    /***
     * 相减操作
     */
    SUB("-"){
        @Override
        public String calculate(String a, String b) {
            //减是加的特例，把b弄成-就可以了
            return Symbol.ADD.calculate(a, "-" + b);
        }
    },

    BEGIN("("){
        @Override
        public String calculate(String a, String b) {
            return null;
        }
    },

    END(")"){
        @Override
        public String calculate(String a, String b) {
            return null;
        }
    };
    Symbol(String string){
        this.symbol = string;
    }

    private String symbol;

    public String getSymbol(){
        return symbol;
    }
    @Nullable
    public static Symbol value(String symbol) {
        switch (symbol){
            case "+": return ADD;
            case "-": return SUB;
            case "×":return MULTIPLY;
            case "÷":return DIVIDE;
            default: return BEGIN;
        }
    }
}