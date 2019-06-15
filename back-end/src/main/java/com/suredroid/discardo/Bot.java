/*
 * Copyright (c) 2019 SureDroid. Published under the GNU General Public Use License. See LICENSE.MD for more information.
 */

package com.suredroid.discardo;

/**
 * attempts to use Player's methods without calling init will throw a NPE
 */
public class Bot implements Player
{
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
			
		/*
		 * if there were no wins possible, replace a random card in the array with the drawn card ~50% of the time
		 * a terrible AI strategy of course
		 */
		if (!hasWin) 
			if ((int) (Math.random() + 0.5) == 0)
				this.hand[(int) (Math.random() * this.hand.length)] = card;
	}
}