package com.example.demo.util.recommend;

import java.util.ArrayList;
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

   public List<String> start2(BuyerSet buyerSet, String username){
       long startTime =  System.currentTimeMillis();
       Recommend recommend = new Recommend();
       List<BuyerSet.HouseSet> recommendations = recommend.recommend(username, buyerSet);
//        System.out.println("-----------------------");
       long endTime =  System.currentTimeMillis();
       long usedTime = (endTime-startTime)/1000;
       List<String> result = new ArrayList<>();
       for (BuyerSet.HouseSet set : recommendations) {
           result.add(set.houseId);
       }
       return result;
   }

}
