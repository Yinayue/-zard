package com.example.demo.util.search;

public class Start {

    public static void main(String[] args) {
        Operation operation = new Operation();
        operation.buildIndex();

        //在这里定义一下要search的query，数组形式
        String[] query = {"3栋"};
        operation.search(query);

    }

}
