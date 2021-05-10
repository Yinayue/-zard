package com.recommend;

import java.util.ArrayList;
import java.util.List;

// Redefine a BuyerSet that makes it more easier to put in houses and their scores

// Reference: https://www.pianshen.com/article/8269129173/
public class BuyerSet {
	public List<Buyer> buyers = new ArrayList<>();

	public Buyer put(String username) {
		return new Buyer(username);
	}

	public Buyer getBuyer(int position) {
		return buyers.get(position);
	}

	public Buyer getBuyer(String username) {
		for (Buyer buyer : buyers) {
			if (buyer.buyerEmail.equals(username)) {
				return buyer;
			}
		}
		return null;
	}

	public final class Buyer {
		public String buyerEmail;
		public List<HouseSet> list = new ArrayList<>();

		private Buyer(String buyerEmail) {
			this.buyerEmail = buyerEmail;
		}

		public Buyer set(String buyerEmail, int score) {
			this.list.add(new HouseSet(buyerEmail, score));
			return this;
		}

		public void create() {
			buyers.add(this);
		}

		public HouseSet find(String houseId) {
			for (HouseSet houseSet : list) {
				if (houseSet.houseId.equals(houseId)) {
					return houseSet;
				}
			}
			return null;
		}

		@Override
		public String toString() {
			return "User{" + "username='" + buyerEmail + '\'' + '}';
		}
	}

	public final class HouseSet implements Comparable<HouseSet> {
		public String houseId;
		public int score;

		public HouseSet(String houseId, int score) {
			this.houseId = houseId;
			this.score = score;
		}

		@Override
		public String toString() {
			return "Set{" + "houseId='" + houseId + '\'' + ", score=" + score + '}';
		}

		@Override
		public int compareTo(HouseSet o) {
			return score > o.score ? -1 : 1;
		}
	}

}