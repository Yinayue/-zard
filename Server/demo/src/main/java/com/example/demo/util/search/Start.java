package com.example.demo.util.search;

public class Start {

    public static void main(String[] args) {
        Operation operation = new Operation();
        operation.buildIndex(); //这里是建立模型，如果数据库数据没变化的话只用在第一次调用就行

        //在这里定义一下要search的query，数组形式
//        String[] query = {"transparent","transparent"};
        String[] query = {"Xila"};
        operation.search(query);

    }

}
