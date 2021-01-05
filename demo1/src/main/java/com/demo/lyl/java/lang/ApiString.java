package com.demo.lyl.java.lang;


import com.google.common.base.Strings;
import org.apache.commons.io.FilenameUtils;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ApiString {


    @Test
    public void api1(){
        String str = "abc";
        char data[] = {'a','b','c'};
        String str2 = new String(data);
        System.out.println("str=str2:"+str==str2);
        System.out.println("str.equals(str2):"+str.equals(str2));
    }

    @Test
    public void api2(){
        char data[] = {'a','b','c'};
        String str = new String(data,0,2);
        System.out.println("str:"+str);
    }

    @Test
    public void api3(){
        char data[] = {'a','b','c'};
        String str = new String(data);
        System.out.println(str.charAt(0));
    }
    @Test
    public void api4(){
        char data[] = {'a','b','c','d'};
        String str = new String(data);
        String str2 = "bcde";
        String str3 = "bcde";
        String str4 = "Bcde";
        System.out.println(str.compareTo(str2));
        System.out.println(str2.compareTo(str));
        System.out.println(str2.compareTo(str3));
        System.out.println(str2.compareTo(str4));
        System.out.println(str2.compareToIgnoreCase(str4));
    }
    @Test
    public void api5(){
        char data[] = {'a','b','c','d'};
        String str = new String(data);
        String str2 = "";
        System.out.println(str.hashCode());
        System.out.println(str2.hashCode());
    }

    @Test
    public void api6(){
        char data[] = {'a','b','c','d'};
        String str = new String(data);
        System.out.println(str.indexOf(1));
    }

    @Test
    public void api7(){
        char data[] = {'a','b','c','d'};
        String str = new String(data);
        CharSequence charSequence = str.subSequence(0, 3);
        System.out.println(charSequence);
    }

    @Test
    public void api8(){
        char data[] = {'a','b','c','d'};
        String str = new String(data);
        String str2 = "efg";
        System.out.println(str.concat(str2));
    }

    @Test
    public void api9(){
        char data[] = {'a','b','c','d'};
        String str = new String(data);
        String str2 = "efg";
        System.out.println(str.matches("abcd"));
    }

    @Test
    public void api10(){
        char data[] = {'a','b','c','d'};
        String str = new String(data);
        String str2 = "efg";
        System.out.println(str.contains("a"));
    }

    @Test
    public void api11(){
        char data[] = {'a','b','c','d','a'};
        String str = new String(data);
        String str2 = "efg";
        System.out.println(str.replace("a","gg"));
    }
    @Test
    public void api12(){
        for(int i=1;i<=99;i++) System.out.println(Strings.padStart(String.valueOf(i),2,'0'));
    }

    @Test
    public void api13(){
        File filename = new File("C:\\Users\\liyulong\\Desktop\\差异\\360截图20200709143426725.jpg");
        System.out.println(filename.getName());
        String extension = FilenameUtils.getExtension(filename.getName());
        System.out.println(extension); 
    }

    public static void main(String[] args) {
        System.out.println("TS20200716002".substring(10,13));
        System.out.println("JPG".toLowerCase());
    }

}
