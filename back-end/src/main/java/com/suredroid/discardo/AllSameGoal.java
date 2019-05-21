package com.suredroid.discardo;

public class AllSameGoal implements Goal
{
	private int value;
	
	public AllSameGoal(int value) {
		this.value = value;
	}
	
	@Override
	public boolean hasWon(int[] hand) //working
	{
		for (int val : hand)
			if (val != value)
				return false;
		
		return true;
	}
	
	public static void main(String[] args) { //given in lab
		Goal  goal2 = new AllSameGoal(2);
		
		int[] hand1 = {2, 2, 2, 8, 2};
		System.out.println(goal2.hasWon(hand1));  // false
		
		int[] hand2 = {2, 2, 2, 2, 2, 2};
		System.out.println(goal2.hasWon(hand2));  // true
		
		int[] hand3 = {5, 5, 5, 5};
		System.out.println(goal2.hasWon(hand3));  // false

		Goal only5 = new AllSameGoal(5);
		System.out.println(only5.hasWon(hand3));  // true
	}
	
	public int getGoalValue() { return this.value; } //used for the better bot, not part of original lab
}