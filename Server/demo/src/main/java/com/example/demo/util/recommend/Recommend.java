package com.example.demo.util.recommend;

import java.util.*;


public class Recommend {
	 
    
    private Map<Double, String> computeNearestNeighbor(String userEmail, BuyerSet set) {
        Map<Double, String> distances = new TreeMap<>();
 
        BuyerSet.Buyer u1 = set.getUser(userEmail);

        for (int i = 0; i < set.buyers.size(); i++) {
            BuyerSet.Buyer u2 = set.getUser(i);

            if (!u2.buyerEmail.equals(userEmail)) {
                double distance = pearson_dis(u2.list, u1.list);
                distances.put(distance, u2.buyerEmail);
            }

        }

//        System.out.println("distance => " + distances);
        return distances;
    }
 
 

    private double pearson_dis(List<BuyerSet.Set> rating1, List<BuyerSet.Set> rating2) {
        int sum_xy = 0;
        int sum_x = 0;
        int sum_y = 0;
        double sum_x2 = 0;
        double sum_y2 = 0;
        int n = 0;
        for (int i = 0; i < rating1.size(); i++) {
            BuyerSet.Set key1 = rating1.get(i);
            for (int j = 0; j < rating2.size(); j++) {
                BuyerSet.Set key2 = rating2.get(j);
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
        double denominator = Math.sqrt(sum_x2 - Math.pow(sum_x, 2) / n) * Math.sqrt(sum_y2 - Math.pow(sum_y, 2) / n);
        if (denominator == 0) {
            return 0;
        } else {
            return (sum_xy - (sum_x * sum_y) / n) / denominator;
        }
    }
 
 
    public List<BuyerSet.Set> recommend(String userEmail, BuyerSet set) {

        Map<Double, String> distances = computeNearestNeighbor(userEmail, set);
        String nearest = distances.values().iterator().next();
//        System.out.println("nearest -> " + nearest);
 
 
        List<BuyerSet.Set> recommendations = new ArrayList<>();

        BuyerSet.Buyer neighborRatings = set.getUser(nearest);
//        System.out.println("neighborRatings -> " + neighborRatings.list);
 
        BuyerSet.Buyer buyerRatings = set.getUser(userEmail);
//        System.out.println("userRatings -> " + userRatings.list);
 
        for (BuyerSet.Set artist : neighborRatings.list) {
            if (buyerRatings.find(artist.houseId) == null) {
                recommendations.add(artist);
            }
        }
        Collections.sort(recommendations);
//        System.out.println("recommendations -> " + recommendations.toString());
        return recommendations;
    }

}
