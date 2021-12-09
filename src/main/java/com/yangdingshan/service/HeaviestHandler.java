package com.yangdingshan.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yangdingshan
 * @Date: 2021/12/8 22:53
 * @Description:
 */
@Data
public class HeaviestHandler {

    private List<Node> table = new ArrayList<>();

    private int capacity = 16;

    private Node head;

    private Node tail;

    HeaviestHandler(int capacity) {
        this();
        this.capacity = capacity;
    }

    HeaviestHandler() {
        head = new Node(-1);
        tail = new Node(-1);
        head.next = tail;
        tail.pre = head;
    }

    public void put(String word) {
        if (word == null || word.length() == 0) {
            return;
        }
        Node temp = head.next;
        Node node = new Node(word);
        //System.out.println("word=" + node.getWord() + "    weight=" + node.getWeight());
        while (temp != null) {
            if (temp.weight < node.weight) {
                // 添加到temp的前面
                node.next = temp;
                node.pre = temp.pre;
                temp.pre.next = node;
                temp.pre = node;
                table.add(node);
                if (table.size() > capacity) {
                    // 移除表尾部
                    Node removeNode = removeTail();
                    table.remove(removeNode);
                }
                break;
            }
            temp = temp.next;
        }
    }

    private Node removeTail() {
        Node node = tail.pre;
        removeNode(node);
        return node;

    }

    private void removeNode(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    /**
     * 处理结果中的标点符号
     */
    public void handlePunctuation() {
        table.forEach(node -> node.setWord(getPrimeWord(node.getWord())));
    }


    private String getPrimeWord(String word) {
        if (word.contains(",")) {
            word = word.replace(",", "");
        }
        if (word.contains(".")) {
            word = word.replace(".", "");
        }
        if (word.contains(";")) {
            word = word.replace(";", "");
        }
        if (word.contains("!")) {
            word = word.replace("!", "");
        }
        return word;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class Node {
        private String word;

        private Integer weight;

        private Node next;

        private Node pre;

        Node(Integer weight) {
            this.weight = weight;
        }

        Node(String word) {
            this.word =word;
            this.weight = getWordWeight(word);
        }


        /**
         * 获取单词权重
         *
         * @param word
         * @return
         */
        private Integer getWordWeight(String word) {
            // 大小写权重相同
            word = word.toLowerCase();
            int sum = 0;
            for (int i = 0; i < word.length(); i++) {
                int w = word.charAt(i) - 'a';
                // 其他符号不加入权重
                if (w >=0 && w <=25) {
                    sum += w;
                }
            }
            return sum;
        }

    }
}
