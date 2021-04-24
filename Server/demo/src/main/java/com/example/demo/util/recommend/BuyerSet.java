package com.example.demo.util.recommend;

import java.util.ArrayList;
import java.util.List;

public class BuyerSet {
	public List<Buyer> buyers = new ArrayList<>();

	public BuyerSet() {
	}

	public Buyer put(String username) {
		return new Buyer(username);
	}

	public Buyer getUser(int position) {
		return buyers.get(position);
	}

	public Buyer getUser(String username) {
		for (Buyer buyer : buyers) {
			if (buyer.buyerEmail.equals(username)) {
				return buyer;
			}
		}
		return null;
	}

	public final class Buyer {
		public String buyerEmail;
		public List<Set> list = new ArrayList<>();

		private Buyer(String buyerEmail) {
			this.buyerEmail = buyerEmail;
		}

		public Buyer set(String buyerEmail, int score) {
			this.list.add(new Set(buyerEmail, score));
			return this;
		}

		public void create() {
			buyers.add(this);
		}

		public Set find(String houseId) {
			for (Set set : list) {
				if (set.houseId.equals(houseId)) {
					return set;
				}
			}
			return null;
		}

		@Override
		public String toString() {
			return "User{" + "username='" + buyerEmail + '\'' + '}';
		}
	}

	public final class Set implements Comparable<Set> {
		public String houseId;
		public int score;

		public Set(String userEmail, int score) {
			this.houseId = userEmail;
			this.score = score;
		}

		@Override
		public String toString() {
			return "Set{" + "userEmail='" + houseId + '\'' + ", score=" + score + '}';
		}

		@Override
		public int compareTo(Set o) {
			return score > o.score ? -1 : 1;
		}
	}

}