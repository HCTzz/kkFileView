package com.ctt;

/**
 * @Description
 * @auther Administrator
 * @create 2020-03-12 下午 10:14
 */
public class SubString {

    //辗转相除法  欧几里得算法
    //gcd(a,b) = gcd(b,a mod b)。
    public String gcdOfStrings(String str1,String str2){
        if(!(str1 + str2).equals((str2 + str1))){
            return "";
        }
        return str1.substring(0, gcd((int)str1.length(), (int)str2.length()));
    }

    public int gcd(int l1,int l2){
        return l2 == 0 ? l1 : gcd(l2,l1 % l2);
    }
    public static void main(String[] args) {

    }
}
