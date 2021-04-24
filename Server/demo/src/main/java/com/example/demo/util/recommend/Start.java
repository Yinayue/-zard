package com.example.demo.util.recommend;

import java.util.ArrayList;
import java.util.List;

public class Start {

    public void start(BuyerSet buyerSet, String userEmail){
        long startTime =  System.currentTimeMillis();
        Recommend recommend = new Recommend();
        List<BuyerSet.Set> recommendations = recommend.recommend(userEmail, buyerSet);
//        System.out.println("-----------------------");
        long endTime =  System.currentTimeMillis();
        long usedTime = (endTime-startTime)/1000;
        for (BuyerSet.Set set : recommendations) {
            System.out.println(set.houseId +" "+set.score);
        }

        System.out.println(usedTime+"s");
    }

   public List<String> start2(BuyerSet buyerSet, String username){
       long startTime =  System.currentTimeMillis();
       Recommend recommend = new Recommend();
       List<BuyerSet.Set> recommendations = recommend.recommend(username, buyerSet);
//        System.out.println("-----------------------");
       long endTime =  System.currentTimeMillis();
       long usedTime = (endTime-startTime)/1000;
       List<String> result = new ArrayList<>();
       for (BuyerSet.Set set : recommendations) {
           result.add(set.houseId);
       }
       return result;
   }

}
