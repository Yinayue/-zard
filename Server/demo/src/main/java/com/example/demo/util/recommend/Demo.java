package com.example.demo.util.recommend;

import java.util.List;

public class Demo {
	 
    public static void main(String[] args) {
        GetHouses getHouses = new GetHouses();
        Start start = new Start();

        BuyerSet buyerSet = getHouses.getHouses();
        buyerSet.getUser("小明").set("测试",5);
        List<BuyerSet.Set> temp =  buyerSet.getUser("小明").list;
        start.start(buyerSet,"小明");
    }

}
