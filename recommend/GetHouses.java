package com.recommend;

public class GetHouses {

    //这里把map转成set
    public BuyerSet getHouses(){

        //输入用户总量
        BuyerSet buyerSet = new BuyerSet();
        buyerSet.put("A")
                .set("a", 50)
                .set("b", 30)
                .set("c", 45)
                .set("d", 50)
                .set("e", 30)
                .set("f", 45)
                .set("g", 50)
                .create();

        buyerSet.put("B")
                .set("h", 40)
                .set("c", 30)
                .set("e", 50)
                .set("f", 50)
                .set("g", 30)
                .set("i", 30)
                .create();


        buyerSet.put("C")
                .set("h", 20)
                .set("a", 50)
                .set("e", 30)
                .set("d", 50)
                .set("f", 45)
                .set("j", 50)
                .create();

        buyerSet.put("D")
                .set("h", 50)
                .set("a", 30)
                .set("e", 40)
                .set("k", 40)
                .set("j", 35)
                .set("g", 35)
                .set("i", 45)
                .create();

        buyerSet.put("E")
                .set("h", 20)
                .set("a", 40)
                .set("c", 45)
                .set("d", 50)
                .set("e", 20)
                .create();

        buyerSet.put("F")
                .set("c", 50)
                .set("l", 50)
                .set("e", 30)
                .set("j", 50)
                .set("m", 45)
                .set("d", 40)
                .set("i", 35)
                .create();

        buyerSet.put("G")
                .set("c", 50)
                .set("a", 40)
                .set("e", 10)
                .set("n", 50)
                .set("o", 40)
                .set("p", 50)
                .create();

        buyerSet.put("H")
                .set("h", 40)
                .set("e", 45)
                .set("k", 45)
                .set("o", 25)
                .set("p", 30)
                .create();


        return buyerSet;


    }


}
