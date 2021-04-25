package com.recommend;

public class Demo {
	 
    public static void main(String[] args) {
        GetHouses getHouses = new GetHouses();
        Start start = new Start();

        BuyerSet buyerSet = getHouses.getHouses();
        start.start(buyerSet,"A");
    }

}
