package com.suredroid.unused;

import com.suredroid.discardo.Goal;
import com.suredroid.discardo.Player;

import java.util.Random;

public class TerribleBot implements Player
{
	public static final String NAME = "Mr. Bunn"; //name of bot implementer
	
	private int[] hand;
	private Goal goal;
	
	Random rng = new Random();
	
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
		//play completely randomly, don't even check for a win
		if (rng.nextBoolean())
			this.hand[rng.nextInt(this.hand.length)] = card;
	}
}
