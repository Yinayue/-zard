package com.example.demo.util.recommend;

import java.util.*;


public class Recommend {

    // Reference: https://www.pianshen.com/article/8269129173/
    private TreeMap<Double, String> getNeighbourTree(String userEmail, BuyerSet set) {
        TreeMap<Double, String> disTree = new TreeMap<>();// TreeMap can order automatically

        BuyerSet.Buyer u1 = set.getBuyer(userEmail);

        for (int i = 0; i < set.buyers.size(); i++) {
            BuyerSet.Buyer u2 = set.getBuyer(i);

            if (!u2.buyerEmail.equals(userEmail)) {
                double dis = pearson_dis(u2.list, u1.list);
                disTree.put(dis, u2.buyerEmail);
            }

        }

        return disTree;
    }


    // Reference: https://www.pianshen.com/article/8269129173/
//    private double pearson_dis(List<BuyerSet.HouseSet> rating1, List<BuyerSet.HouseSet> rating2) {
//        int sum_xy = 0;
//        int sum_x = 0;
//        int sum_y = 0;
//        double sum_x2 = 0;
//        double sum_y2 = 0;
//        int n = 0;
//        for (int i = 0; i < rating1.size(); i++) {
//            BuyerSet.HouseSet key1 = rating1.get(i);
//            for (int j = 0; j < rating2.size(); j++) {
//                BuyerSet.HouseSet key2 = rating2.get(j);
//                if (key1.houseId.equals(key2.houseId)) {
//                    n += 1;
//                    int x = key1.score;
//                    int y = key2.score;
//                    sum_xy += x * y;
//                    sum_x += x;
//                    sum_y += y;
//                    sum_x2 += Math.pow(x, 2);
//                    sum_y2 += Math.pow(y, 2);
//                }
//
//            }
//        }
//        double denominator = Math.sqrt(sum_x2 - Math.pow(sum_x, 2) / n) * Math.sqrt(sum_y2 - Math.pow(sum_y, 2) / n);
//        if (denominator == 0) {
//            return 0;
//        } else {
//            return (sum_xy - (sum_x * sum_y) / n) / denominator;
//        }
//    }

    private double pearson_dis(List<BuyerSet.HouseSet> rating1, List<BuyerSet.HouseSet> rating2) {
        int sum_xy = 0;
        int sum_x = 0;
        int sum_y = 0;
        double sum_x2 = 0;
        double sum_y2 = 0;
        int n = 0;
        for (int i = 0; i < rating1.size(); i++) {
            BuyerSet.HouseSet key1 = rating1.get(i);
            for (int j = 0; j < rating2.size(); j++) {
                BuyerSet.HouseSet key2 = rating2.get(j);
                if (key1.houseId.equals(key2.houseId)) {
                    n += 1;
                    int x = key1.score;
                    int y = key2.score;
                    sum_xy += x * y;
                    sum_x += x;
                    sum_y += y;
                    sum_x2 += Math.pow(x, 2);
                    sum_y2 += Math.pow(y, 2);
                }

            }
        }
        if (n==0){
            return -1;
        }
        double denominator = Math.sqrt(sum_x2 - Math.pow(sum_x, 2) / n) * Math.sqrt(sum_y2 - Math.pow(sum_y, 2) / n);
        if (denominator == 0) {
            return 0;
        } else {
            return (sum_xy - (sum_x * sum_y) / n) / denominator;
        }
    }


    // Reference: https://www.pianshen.com/article/8269129173/
    public List<BuyerSet.HouseSet> recommend(String userEmail, BuyerSet set) {

        TreeMap<Double, String> disTree = getNeighbourTree(userEmail, set);

        String nearest = disTree.lastEntry().getValue();

        List<BuyerSet.HouseSet> recommendations = new ArrayList<>();

        BuyerSet.Buyer neighborRatings = set.getBuyer(nearest);

        BuyerSet.Buyer buyerRatings = set.getBuyer(userEmail);

        for (BuyerSet.HouseSet house : neighborRatings.list) {
//            if (buyerRatings.find(house.houseId) == null) {
                recommendations.add(house);
//            }
        }
        Collections.sort(recommendations);

        return recommendations;
    }

}
