package com.zh.es.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheStore {

    private static DefaultCache defaultCache;

    static {
        defaultCache = new DefaultCache();
    }
    public static Object get(String key){
        return defaultCache.get(key);
    }

    public static void set(String key,Object v){
        defaultCache.set(key,v);
    }


    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            CacheStore.set(i+"",i);
        }
        CacheStore.set("93",1);
        CacheStore.set("95",1);
        System.out.println(CacheStore.get("1"));
        System.out.println(CacheStore.get("3"));
        System.out.println(defaultCache.cache);
    }

    public static class DefaultCache<T> {
        private Map<String, Node<T>> cache = new ConcurrentHashMap(16);
        int capacity = 10;
        int size=0;
        Node head;
        Node end;
        private Object lock = new Object();
        class Node<T> {
            Node previous;
            Node next;
            T data;
            String key;
            Node(Node previous, Node next,String key, T data) {
                this.key = key;
                this.data = data;
                this.previous = previous;
                this.next = next;
            }
        }


        public <T> T get(String key) {
            if (key == null || !cache.containsKey(key)) {
                return null;
            }
            Node<T> tNode = (Node<T>) cache.get(key);
            setHead(tNode);
            return tNode.data;
        }

        public void set(String key, T value) {
            if (key == null || value == null) {
                return;
            }

            synchronized (lock){
                this.size ++;
                if(this.size > capacity){
                    this.size--;
                    Node old = end;
                    cache.remove(old.key);
                    old = old.previous;
                    end = old;
                    old.next = null;
                }
            }

            if(cache != null && cache.containsKey(key)){
                Node<T> tNode = cache.get(key);
                tNode.data = value;
                setHead(tNode);
                synchronized (lock){
                    this.size--;
                }

                return;
            }

            Node<T> tNode1;
            if (this.head == null) {
                tNode1 = new Node<T>(null, null,key, value);
                this.head = tNode1;
                this.end = tNode1;
                this.head.next = this.end;
                this.end.previous = this.head;
            } else {
                tNode1 = new Node<T>(null, this.head,key, value);
                this.head.previous = tNode1;
                this.head = tNode1;
            }
            cache.put(key,tNode1);
        }

        void remove(Node node) {
            if (node.previous != null) {
                node.previous.next = node.next;
            } else {
                head = node.next;
            }

            if (node.next != null) {
                node.next.previous = node.previous;
            } else {
                end = node.previous;
            }
        }


        void setHead(Node node) {
            if(this.head.key == node.key){
                return;
            }
            if(this.end.key == node.key){


                this.end = node.previous;
                this.end.next =null;
                node.next = this.head;

                this.head.previous=node;
                this.head = node;
                node.previous = null;
                return;
            }

            node.previous.next = node.next.previous;
            node.previous = null;
            node.next = this.head;
            this.head = node;
        }


    }
}
