package aier.Collection.HashMapTest;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author: ligang
 * date: 2018/3/19
 * time: 11:06
 */
public class HashMapTest {
    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("aa",1);
        map.put("bb",2);
        map.put("cc",3);
        map.put("cc",4);
        // 遍历键值
        /*for(String set:map.keySet()){
            System.out.println(set + ":" + map.get(set));
            *//**
             aa:1
             bb:2
             cc:4
             *//*
        }*/

        //遍历entry数组
        /*for(Map.Entry<String,Integer> entry:map.entrySet()){
            System.out.println(entry);
            *//**
             * aa=1
             bb=2
             cc=4
             *//*
        }*/

        /*Set<String> set = map.keySet();
        Iterator<String> iter = set.iterator();
        while(iter.hasNext()){
            String key = iter.next();
            Integer value = map.get(key);
            System.out.println(key + ":" + value);*/
            /*
            这里如果使用if的话,它会少判断.
            aa:1
            bb:2
            cc:4*
            while循环语句，可以执行多次，if是条件语句，只是单次执行。
            while(表达式) 语句组； 计算表达式，若为真，执行语句，并重复该过程，直到为假时，执行下一条语句。

        }*/

        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        Iterator<Map.Entry<String, Integer>> iter = entries.iterator();
        while(iter.hasNext()){
            System.out.println(iter.next());
            /**
             aa=1
             bb=2
             cc=4
             */
    }
    }
}
