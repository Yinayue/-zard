package com.recommend;

import java.util.List;

public class Start {

    public void start(BuyerSet buyerSet, String userEmail){
        long startTime =  System.currentTimeMillis();
        Recommend recommend = new Recommend();
        List<BuyerSet.HouseSet> recommendations = recommend.recommend(userEmail, buyerSet);
//        System.out.println("-----------------------");
        long endTime =  System.currentTimeMillis();
        long usedTime = (endTime-startTime)/1000;
        for (BuyerSet.HouseSet houseSet : recommendations) {
            System.out.println(houseSet.houseId +" "+ houseSet.score);
        }

        System.out.println(usedTime+"s");
    }

}
