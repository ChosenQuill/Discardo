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

	public int getGoalValue() { return this.value; } //used for the better bot, not part of original lab
}