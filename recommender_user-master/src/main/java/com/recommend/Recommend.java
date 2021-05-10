package com.recommend;

import java.util.*;


public class Recommend {
	 
<<<<<<< Updated upstream
    /**
     * 在给定username的情况下，计算其他用户和它的距离并排序
     * @param username
     * @param set
     * @return
     */
    private Map<Double, String> computeNearestNeighbor(String username, UserSet set) {
        Map<Double, String> distances = new TreeMap<>();
 
        UserSet.User u1 = set.getUser(username);

        for (int i = 0; i < set.users.size(); i++) {
            UserSet.User u2 = set.getUser(i);

            if (!u2.username.equals(username)) {
                double distance = pearson_dis(u2.list, u1.list);
                distances.put(distance, u2.username);
=======
    
    private Map<Double, String> getNeighbourTree(String userEmail, BuyerSet set) {
        Map<Double, String> disTree = new TreeMap<>();// TreeMap can order automatically
 
        BuyerSet.Buyer u1 = set.getBuyer(userEmail);

        for (int i = 0; i < set.buyers.size(); i++) {
            BuyerSet.Buyer u2 = set.getBuyer(i);

            if (!u2.buyerEmail.equals(userEmail)) {
                double dis = pearson_dis(u2.list, u1.list);
                disTree.put(dis, u2.buyerEmail);
>>>>>>> Stashed changes
            }

        }

<<<<<<< Updated upstream
//        System.out.println("distance => " + distances);
        return distances;
    }
 
 
    /**
     * 计算2个打分序列间的pearson距离
     *
     * @param rating1
     * @param rating2
     * @return
     */
    private double pearson_dis(List<UserSet.Set> rating1, List<UserSet.Set> rating2) {
=======
        return disTree;
    }
 
 
    // Reference: https://www.pianshen.com/article/8269129173/
    private double pearson_dis(List<BuyerSet.HouseSet> rating1, List<BuyerSet.HouseSet> rating2) {
>>>>>>> Stashed changes
        int sum_xy = 0;
        int sum_x = 0;
        int sum_y = 0;
        double sum_x2 = 0;
        double sum_y2 = 0;
        int n = 0;
        for (int i = 0; i < rating1.size(); i++) {
<<<<<<< Updated upstream
            UserSet.Set key1 = rating1.get(i);
            for (int j = 0; j < rating2.size(); j++) {
                UserSet.Set key2 = rating2.get(j);
                if (key1.houseName.equals(key2.houseName)) {
=======
            BuyerSet.HouseSet key1 = rating1.get(i);
            for (int j = 0; j < rating2.size(); j++) {
                BuyerSet.HouseSet key2 = rating2.get(j);
                if (key1.houseId.equals(key2.houseId)) {
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
 
 
    public List<UserSet.Set> recommend(String username, UserSet set) {
        //找到最近邻
        Map<Double, String> distances = computeNearestNeighbor(username, set);
        String nearest = distances.values().iterator().next();
//        System.out.println("nearest -> " + nearest);
 
 
        List<UserSet.Set> recommendations = new ArrayList<>();
 
        //找到最近邻看过，但是我们没看过的电影，计算推荐
        UserSet.User neighborRatings = set.getUser(nearest);
//        System.out.println("neighborRatings -> " + neighborRatings.list);
 
        UserSet.User userRatings = set.getUser(username);
//        System.out.println("userRatings -> " + userRatings.list);
 
        for (UserSet.Set artist : neighborRatings.list) {
            if (userRatings.find(artist.houseName) == null) {
                recommendations.add(artist);
            }
        }
        Collections.sort(recommendations);
//        System.out.println("recommendations -> " + recommendations.toString());
=======


    // Reference: https://www.pianshen.com/article/8269129173/
    public List<BuyerSet.HouseSet> recommend(String userEmail, BuyerSet set) {

        Map<Double, String> disTree = getNeighbourTree(userEmail, set);
        String nearest = disTree.values().iterator().next();

        List<BuyerSet.HouseSet> recommendations = new ArrayList<>();

        BuyerSet.Buyer neighborRatings = set.getBuyer(nearest);

        BuyerSet.Buyer buyerRatings = set.getBuyer(userEmail);
 
        for (BuyerSet.HouseSet house : neighborRatings.list) {
            if (buyerRatings.find(house.houseId) == null) {
                recommendations.add(house);
            }
        }
        Collections.sort(recommendations);

>>>>>>> Stashed changes
        return recommendations;
    }

}
