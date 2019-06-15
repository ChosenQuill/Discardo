/*
 * Copyright (c) 2019 SureDroid. Published under the GNU General Public Use License. See LICENSE.MD for more information.
 */

package com.suredroid.discardo;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * An average bot should take around 50 turns, given the original bot tester parameters
 */
public class BotTester 
{
	public static final int TESTS_PER_BOT = 50_000; //Default 1_000_000 but turned down so my server doesn't get fried.
	public static final int MAX_TURNS     = 4_000; //max turns allowed per game, for bad bots



    public static void outputTest(Player Bot){
        System.out.println("Player reached goal in avg. of " + test(Bot) + " turns");
    }
	
    public static double test(Player bot)
    {
        int numCards = 5;
        
        MultiGoal multi = new MultiGoal();
        
        int goal1 = (int)(Math.random() * 9);
        int goal2 = (int)(Math.random() * 9);
        
        while (goal1 == goal2) //ensure unique values for each AllSameGoal
        	goal2 = (int)(Math.random() * 9);
        
        multi.addGoal(new AllSameGoal(goal1));
        multi.addGoal(new AllSameGoal(goal2));
        multi.addGoal(new RunGoal());
        
        int totalTurns = 0;
        
        for (int i = 0; i < TESTS_PER_BOT; i++) {
        	totalTurns += play(bot, numCards, multi);
        }

        //average
        return new BigDecimal((double)totalTurns / TESTS_PER_BOT).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public static int play(Player p, int numCards, Goal goal)
    {
        int[] hand = new int[numCards];
        
        for (int i = 0; i < hand.length; i++)
            hand[i] = randomCard();

        p.init(hand, goal);

        int numTurns = 0;

        while (!goal.hasWon(hand)) 
        {
        	if (numTurns == MAX_TURNS)
        		return MAX_TURNS;
        	
            p.maybeReplaceCard(randomCard());
            numTurns++;
        }

        return numTurns;
    }

    public static int randomCard()
    {
        return (int)(Math.random() * 9) + 1;
    }
}
