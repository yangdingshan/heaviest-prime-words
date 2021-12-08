package com.yangdingshan.service;

import java.util.List;

/**
 * @Author: yangdingshan
 * @Date: 2021/12/8 20:20
 * @Description:
 */
public class HeaviestPrimeWordsService {



    public static void main(String[] args) {
        HeaviestPrimeWordsService service = new HeaviestPrimeWordsService();
        String str = "If you can keep your head when all about you   \n" +
                "    Are losing theirs and blaming it on you,\n" +
                "If you can trust yourself when all men doubt you,\n" +
                "    But make allowance for their doubting too;";
        //List<String> list = service.heaviest(str);
        String[] split = str.split(" ");
        for (int i = 0; i < split.length; i++) {
            System.out.println(split[i]);
        }
        HeaviestCache cache = new HeaviestCache(3);
        cache.put("A");
        //cache.put("bc");
        cache.put("c");
        cache.put("d");
        List<HeaviestCache.Node> table = cache.getTable();
        table.forEach(node -> System.out.println(node.getWord()));


    }


}
