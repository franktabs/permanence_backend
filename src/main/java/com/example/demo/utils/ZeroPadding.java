package com.example.demo.utils;

public class ZeroPadding {

    public static String paddingNumber(Integer integer){
        return integer < 10 ? "0"+ integer:integer.toString();
    }
}
