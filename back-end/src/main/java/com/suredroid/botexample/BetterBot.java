package com.suredroid.unused;

import com.suredroid.discardo.AllSameGoal;
import com.suredroid.discardo.Goal;
import com.suredroid.discardo.MultiGoal;
import com.suredroid.discardo.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BetterBot implements Player
{
	public static final String NAME = "Mr. Bunn"; //name of bot implementer
	
	private int[] hand;
	private Goal goal;
	
	//implicit constructor not shown

	@Override
	/** must call before using a Bot */
	public void init(int[] hand, Goal goal) {
		this.hand = hand;
		this.goal = goal;
	}

	@Override
	public void maybeReplaceCard(int card) 
	{
		boolean hasWin = false;
		
		for (int i = 0; i < this.hand.length; i++)
		{
			int save = this.hand[i];
			
			this.hand[i] = card;
			
			if (goal.hasWon(this.hand))
				hasWin = true;
			else
				this.hand[i] = save;
		}
			
		if (hasWin) //terminate turn if replacing card results in a win
			return;
		
		int sameSteps = 0; //number of steps it would take to reach an AllSame goal
		int runSteps  = 0; //number of steps it would taek to reach a RunGoal
		
		MultiGoal multi = (MultiGoal) this.goal; //it's assumed it's a MultiGoal
		
		List<Integer> sameGoalAmounts = new ArrayList<>();
		
		for (Goal g : multi.getGoals())
			if (g instanceof AllSameGoal)
				sameGoalAmounts.add(((AllSameGoal) g).getGoalValue());

		sameSteps = Math.min(this.nStepsFromAllSame(hand, card, sameGoalAmounts.get(0)),
				             this.nStepsFromAllSame(hand, card, sameGoalAmounts.get(1)));
		
		runSteps = this.nStepsFromRunGoal(hand, card);
		
		/*
		 * TODO finish
		 */
	}
	
	private int nStepsFromAllSame(int[] hand, int card, int goal)
	{
		int result = 0;
		
		for (int val : hand)
			if (val != goal)
				result++;
		
		return result;
	}
	
	private int nStepsFromRunGoal(int[] hand, int card)
	{
		int result = 0;
		
		int[] copy = Arrays.copyOfRange(hand, 0, hand.length-1);
		
		Arrays.sort(copy);
		
		for (int i = 0; i < copy.length-1; i++)
			if (copy[i + 1] - copy[i] > 1)
				result++;
			
		return result;
	}
}
