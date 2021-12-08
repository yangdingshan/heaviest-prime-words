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
public class HeaviestCache {

    private List<Node> table = new ArrayList<>();

    private int capacity = 16;

    private Node head;

    private Node tail;

    HeaviestCache(int capacity) {
        this();
        this.capacity = capacity;
    }

    HeaviestCache() {
        head = new Node(-1);
        tail = new Node(-1);
        head.next = tail;
        tail.pre = head;
    }

    public void put(String word) {
        if (word == null || word.intern().length() == 0) {
            return;
        }
        Node temp = head.next;
        Node node = new Node(word);
        while (temp != null) {
            if (temp.weight < node.weight) {
                node.next = temp;
                node.pre = temp.pre;
                temp.pre.next = node;
                temp.pre = node;
                table.add(node);
                if (table.size() > capacity) {
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
            this.word = word;
            this.weight = getWordWeight(word);
        }

        private Integer getWordWeight(String word) {
            word = word.toLowerCase();
            int sum = 0;
            for (int i = 0; i < word.length(); i++) {
                sum += word.charAt(i) - 'a';
            }
            return sum;
        }

    }
}
