package com.company.Map_put_git_remove;

import java.util.*;

public class MyMap<K, V> {

    private final class MyEntery<K,V> {
        private final K key;
        private V value;


        public MyEntery(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "MyMap{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

    private static final int DEFAULT_CAPACITY  = 16;
    private int size ;
    private MyEntery<K,V>[] entries = new MyEntery[DEFAULT_CAPACITY];

    public V get(K key ){
        for (int i=0; i<size ; i++){
            if( entries[i] != null){
                if (entries[i].getKey().equals(key)){
                    return entries[i].getValue();
                }
            }
        }
        return null;
    }
    public void put(K key,V value ){
        boolean success =true;
        for (int i=0 ;i< size ; i++){
            if(entries[i].getKey().equals(key)){
                entries[i].setValue(value);
                success=false;
            }
        }
        if(success){
            checkCapasity();
            entries[size++]=new MyEntery<>(key,value);
        }
    }
    private void checkCapasity(){
        if (size== entries.length){
            int newSize=entries.length*2;
            entries = Arrays.copyOf(entries,newSize);
        }
    }
    public void remove(K key){
        for (int i=0 ; i<size ;i++){
            if (entries[i].getKey().equals(key)){
                entries[i]=null;
                size--;
            }
        }

    }
    private void condenseArray(int start){
        int i;
        for (i=start;i<size;i++){
            entries[i]=entries[i+1];
        }
        entries[i]=null;

    }
    public Set<K> keySet(){
        Set<K> set = new HashSet<>();
        for (int i=0 ; i< size ; i++){
            set.add(entries[i].getKey());
        }
        return set;
    }
    public Collection<V> values(){
        List<V > list = new ArrayList<>();
        for (int i =0;i<size;i++){
            list.add(entries[i].getValue());

        }
        return list;
    }

}
